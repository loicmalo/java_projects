package com.sdzee.tp.forms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.sdzee.tp.beans.Client;

public class CreationClientForm {
    public static final String  FIELD_NOM       = "nom";
    public static final String  FIELD_PRENOM    = "prenom";
    public static final String  FIELD_ADRESSE   = "adresse";
    public static final String  FIELD_TELEPHONE = "telephone";
    public static final String  FIELD_EMAIL     = "email";
    public static final String  FIELD_IMAGE     = "image";

    public static final String  SESSION_CLIENTS = "listClients";
    private static final int    TAILLE_TAMPON   = 10240;

    public static final String  MESSAGE_ISKO    = "Echec de la création du client.";
    public static final String  MESSAGE_ISOK    = "Succès de la création du client.";

    private String              message;
    private boolean             isOK;
    private Map<String, String> errors          = new HashMap<String, String>();

    public String getMessage() {
        return message;
    }

    public boolean getIsOK() {
        return isOK;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public Client createClient( HttpServletRequest request, String path ) {
        String nom = getFieldValue( request, FIELD_NOM );
        String prenom = getFieldValue( request, FIELD_PRENOM );
        String adresse = getFieldValue( request, FIELD_ADRESSE );
        String telephone = getFieldValue( request, FIELD_TELEPHONE );
        String email = getFieldValue( request, FIELD_EMAIL );
        String image = null;

        Client client = new Client();
        HttpSession session = request.getSession();

        try {
            checkNom( nom, session );
        } catch ( Exception e ) {
            setError( FIELD_NOM, e.getMessage() );
        }
        client.setNom( nom );

        try {
            checkPrenom( prenom );
        } catch ( Exception e ) {
            setError( FIELD_PRENOM, e.getMessage() );
        }
        client.setPrenom( prenom );

        try {
            checkAdresse( adresse );
        } catch ( Exception e ) {
            setError( FIELD_ADRESSE, e.getMessage() );
        }
        client.setAdresse( adresse );

        try {
            checkTelephone( telephone );
        } catch ( Exception e ) {
            setError( FIELD_TELEPHONE, e.getMessage() );
        }
        client.setTelephone( telephone );

        try {
            checkEmail( email );
        } catch ( Exception e ) {
            setError( FIELD_EMAIL, e.getMessage() );
        }
        client.setEmail( email );

        InputStream imageContent = null;
        try {
            Part part = request.getPart( FIELD_IMAGE );
            image = getImage( part );

            if ( image != null && !image.isEmpty() ) {
                image = image.substring( image.lastIndexOf( '/' ) + 1 )
                        .substring( image.lastIndexOf( '\\' ) + 1 );
                imageContent = part.getInputStream();
            }
        } catch ( IllegalStateException e ) {

            e.printStackTrace();
            setError( FIELD_IMAGE, "Le fichier envoyé est trop volumineux." );
        } catch ( IOException e ) {

            e.printStackTrace();
            setError( FIELD_IMAGE, "Erreur de configuration du serveur." );
        } catch ( ServletException e ) {

            e.printStackTrace();
            setError( FIELD_IMAGE,
                    "Ce type de requête n'est pas supporté, merci d'utiliser le formulaire prévu pour envoyer votre fichier." );
        }

        /*
         * Extraction du type MIME du fichier depuis l'InputStream nommé
         * "contenu"
         */
        if ( image != null && !image.isEmpty() ) {

            String mimeType = request.getServletContext().getMimeType( image );

            /*
             * Si le fichier est bien une image, alors son en-tête MIME commence
             * par la chaîne "image"
             */
            if ( mimeType.toString().startsWith( "image" ) ) {
                /*
                 * Appeler ici la méthode d'écriture du fichier sur le disque...
                 */
                try {
                    System.out.println( image );
                    System.out.println( path );
                    writeImage( imageContent, image, path );
                } catch ( Exception e ) {
                    setError( FIELD_IMAGE, "Erreur lors de l'écriture du fichier sur le disque." );
                }
            } else {
                /*
                 * Envoyer ici une exception précisant que le fichier doit être
                 * une image...
                 */

                setError( FIELD_IMAGE, "Le fichier envoyé n'est pas une image" );
            }
        }

        isOK = errors.isEmpty();
        if ( isOK ) {
            message = MESSAGE_ISOK;

        } else {
            message = MESSAGE_ISKO;
        }

        return client;
    }

    private void writeImage( InputStream imageContent, String image, String path ) {
        /* Prépare les flux. */
        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;
        try {
            /* Ouvre les flux. */
            entree = new BufferedInputStream( imageContent, TAILLE_TAMPON );
            sortie = new BufferedOutputStream( new FileOutputStream( new File( path + image ) ), TAILLE_TAMPON );

            /*
             * Lit le fichier reçu et écrit son contenu dans un fichier sur le
             * disque.
             */
            byte[] tampon = new byte[TAILLE_TAMPON];
            int longueur = 0;
            while ( ( longueur = entree.read( tampon ) ) > 0 ) {
                sortie.write( tampon, 0, longueur );
            }
        } catch ( IOException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                sortie.close();
            } catch ( IOException ignore ) {
            }
            try {
                entree.close();
            } catch ( IOException ignore ) {
            }
        }
    }

    private static String getImage( Part part ) {

        for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
            /* Recherche de l'éventuelle présence du paramètre "filename". */
            if ( contentDisposition.trim().startsWith( "filename" ) ) {
                /*
                 * Si "filename" est présent, alors renvoi de sa valeur,
                 * c'est-à-dire du nom de fichier sans guillemets.
                 */
                return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
            }
        }
        return null;
    }

    private void checkNom( String nom, HttpSession session ) throws Exception {
        if ( nom != null ) {
            Map<String, Client> listClients = (HashMap<String, Client>) session.getAttribute( SESSION_CLIENTS );
            if ( listClients != null && listClients.get( nom ) != null )
                throw new Exception( "Le client existe déjà." );
            else if ( nom.length() < 2 ) {
                throw new Exception( "Le nom doit contenir au moins 2 caractères." );
            }
        } else {
            throw new Exception( "Merci de saisir un nom" );
        }
    }

    private void checkPrenom( String prenom ) throws Exception {
        if ( prenom != null && prenom.length() < 2 ) {
            throw new Exception( "Le prénom doit contenir au moins 2 caractères." );
        }
    }

    private void checkAdresse( String adresse ) throws Exception {
        if ( adresse != null ) {
            if ( adresse.length() < 10 ) {

                throw new Exception( "L'adresse doit contenir au moins 10 caractères." );
            }
        } else {
            throw new Exception( "Merci de saisir une adresse." );
        }
    }

    private void checkTelephone( String telephone ) throws Exception {
        if ( telephone != null ) {
            if ( !telephone.matches( "^\\d+$" ) ) {
                throw new Exception( "Le numéro de téléphone doit uniquement contenir des chiffres." );
            } else if ( telephone.length() < 4 ) {
                throw new Exception( "Le numéro de téléphone doit contenir au moins 4 caractères." );
            }
        } else {
            throw new Exception( "Merci de saisir un numéro de téléphone." );
        }
    }

    private void checkEmail( String email ) throws Exception {
        if ( email != null && !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
            throw new Exception( "Merci de saisir une adresse mail valide" );
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
