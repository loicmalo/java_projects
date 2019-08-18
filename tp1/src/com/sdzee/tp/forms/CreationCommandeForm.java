package com.sdzee.tp.forms;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sdzee.tp.beans.Client;
import com.sdzee.tp.beans.Commande;

public class CreationCommandeForm {

    public static final String  FIELD_NOUVEAU_CLIENT   = "nouveauClient";
    public static final String  FIELD_CLIENT           = "client";
    public static final String  FIELD_MONTANT          = "montant";
    public static final String  FIELD_MODE_PAIEMENT    = "modePaiement";
    public static final String  FIELD_STATUT_PAIEMENT  = "statutPaiement";
    public static final String  FIELD_MODE_LIVRAISON   = "modeLivraison";
    public static final String  FIELD_STATUT_LIVRAISON = "statutLivraison";

    public static final String  SESSION_CLIENTS        = "listClients";

    public static final String  MESSAGE_ISKO           = "Echec de la création de la commande";
    public static final String  MESSAGE_ISOK           = "succès de la création de la commande";

    public static final String  FORMATTER              = "dd/MM/yyyy HH:mm:ss";

    Client                      client;
    CreationClientForm          form                   = new CreationClientForm();

    private String              message;
    private boolean             isOK;
    private Map<String, String> errors                 = new HashMap<String, String>();

    public String getMessage() {
        return message;
    }

    public boolean getIsOK() {
        return isOK;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public Commande createCommande( HttpServletRequest request, String path ) {
        Client client;
        String nouveauClient = getFieldValue( request, FIELD_NOUVEAU_CLIENT );

        if ( "non".equals( nouveauClient ) ) {
            String ancienClient = getFieldValue( request, FIELD_CLIENT );
            HttpSession session = request.getSession();
            Map<String, Client> listClients = (HashMap<String, Client>) session.getAttribute( SESSION_CLIENTS );

            client = listClients.get( ancienClient );

        } else {

            form = new CreationClientForm();
            client = form.createClient( request, path );
        }

        errors = form.getErrors();

        String montant = getFieldValue( request, FIELD_MONTANT );
        String modePaiement = getFieldValue( request, FIELD_MODE_PAIEMENT );
        String statutPaiement = getFieldValue( request, FIELD_STATUT_PAIEMENT );
        String modeLivraison = getFieldValue( request, FIELD_MODE_LIVRAISON );
        String statutLivraison = getFieldValue( request, FIELD_STATUT_LIVRAISON );

        Commande commande = new Commande();
        commande.setClient( client );

        LocalDateTime dt = LocalDateTime.now();
        /* Conversion de la date en String selon le format défini */
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern( FORMATTER );
        String date = formatter.format( dt );
        commande.setDate( date );

        double montantValue = -1;
        try {
            montantValue = checkMontant( montant );
        } catch ( Exception e ) {
            setError( FIELD_MONTANT, e.getMessage() );
        }
        commande.setMontant( montantValue );

        try {
            checkModePaiement( modePaiement );
        } catch ( Exception e ) {
            setError( FIELD_MODE_PAIEMENT, e.getMessage() );
        }
        commande.setModePaiement( modePaiement );

        try {
            checkStatutPaiement( statutPaiement );
        } catch ( Exception e ) {
            setError( FIELD_STATUT_PAIEMENT, e.getMessage() );
        }
        commande.setStatutPaiement( statutPaiement );

        try {
            checkModeLivraison( modeLivraison );
        } catch ( Exception e ) {
            setError( FIELD_MODE_LIVRAISON, e.getMessage() );
        }
        commande.setModeLivraison( modeLivraison );

        try {
            checkStatutLivraison( statutLivraison );
        } catch ( Exception e ) {
            setError( FIELD_STATUT_LIVRAISON, e.getMessage() );
        }
        commande.setModeLivraison( modeLivraison );

        isOK = errors.isEmpty();
        if ( isOK )
            message = MESSAGE_ISOK;
        else
            message = MESSAGE_ISKO;

        return commande;

    }

    private double checkMontant( String montant ) throws Exception {
        double temp;
        if ( montant != null ) {
            try {
                temp = Double.parseDouble( montant );
                if ( temp < 0 ) {
                    throw new Exception( "Le montant doit être un nombre positif." );
                }
            } catch ( NumberFormatException e ) {
                temp = -1;
                throw new Exception( "Le montant doit être un nombre." );
            }
        } else {
            temp = -1;
            throw new Exception( "Merci d'entrer un montant." );
        }
        return temp;
    }

    private void checkModePaiement( String modePaiement ) throws Exception {
        if ( modePaiement != null ) {
            if ( modePaiement.length() < 2 ) {
                throw new Exception( "Le mode de paiement doit contenir au moins 2 caractères." );
            }
        } else {
            throw new Exception( "Merci de saisir un mode de paiement" );
        }
    }

    private void checkStatutPaiement( String statutPaiement ) throws Exception {

        if ( statutPaiement != null && statutPaiement.length() < 2 ) {
            throw new Exception( "Le statut de paiement doit contenir au moins 2 caractères." );
        }
    }

    private void checkModeLivraison( String modeLivraison ) throws Exception {
        if ( modeLivraison != null ) {
            if ( modeLivraison.length() < 2 ) {
                throw new Exception( "Le mode de livraison doit contenir au moins 2 caractères." );
            }
        } else {
            throw new Exception( "Merci de saisir un mode de livraison" );
        }
    }

    private void checkStatutLivraison( String statutLivraison ) throws Exception {

        if ( statutLivraison != null && statutLivraison.length() < 2 ) {
            throw new Exception( "Le statut de livraison doit contenir au moins 2 caractères." );
        }
    }

    private void setError( String field, String message ) {
        errors.put( field, message );
    }

    private static String getFieldValue( HttpServletRequest request, String fieldName ) {
        String value = request.getParameter( fieldName );
        if ( value == null || value.trim().length() == 0 ) {
            return null;
        } else {
            return value.trim();
        }
    }

}