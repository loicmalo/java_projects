package com.lm.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.lm.beans.Receipt;

public class ReceiptForm {
    public static final String  FIELD_ID         = "id";
    public static final String  FIELD_DATE       = "date";
    public static final String  FIELD_NAME       = "name";
    public static final String  FIELD_AMOUNT     = "amount";

    private static final String SESSION_RECEIPTS = "listReceipts";

    public static final String  MESSAGE_ISKO     = "Echec de la création du ticket";
    private Receipt             receipt;

    private String              message;
    private boolean             isOK;
    private Map<String, String> errors           = new HashMap<String, String>();

    public String getMessage() {
        return message;
    }

    public boolean getIsOK() {
        return isOK;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public Receipt createReceipt( HttpServletRequest request ) {
        String id = getFieldValue( request, FIELD_ID );
        String date = getFieldValue( request, FIELD_DATE );
        String name = getFieldValue( request, FIELD_NAME );
        String amount = getFieldValue( request, FIELD_AMOUNT );

        Receipt receipt = new Receipt();

        HttpSession session = request.getSession();

        Integer idValue = -1;
        try {
            idValue = checkId( id, session );
        } catch ( Exception e ) {
            setError( FIELD_ID, e.getMessage() );
        }
        receipt.setId( idValue );

        try {
            checkDate( date, idValue, session );
        } catch ( Exception e ) {
            setError( FIELD_DATE, e.getMessage() );
        }
        receipt.setDate( date );

        try {
            checkName( name );
        } catch ( Exception e ) {
            setError( FIELD_NAME, e.getMessage() );
        }
        receipt.setName( name );

        double amountValue = -1;
        try {
            amountValue = checkAmount( amount );
        } catch ( Exception e ) {
            setError( FIELD_AMOUNT, e.getMessage() );
        }
        receipt.setAmount( amountValue );

        isOK = errors.isEmpty();
        if ( !isOK )
            message = MESSAGE_ISKO;

        return receipt;
    }

    private Integer checkId( String id, HttpSession session ) throws Exception {
        Integer idValue;
        if ( id != null ) {
            try {
                idValue = Integer.parseInt( id );

                /*
                 * Map<Integer, Receipt> listReceipts = (HashMap<Integer,
                 * Receipt>) session .getAttribute( SESSION_RECEIPTS ); if (
                 * listReceipts.get( idValue ) != null ) return idValue;
                 * 
                 * else throw new Exception( "L'identifiant n'existe pas." );
                 */
            } catch ( NumberFormatException e ) {
                idValue = -1;
                throw new Exception( "Erreur sur l'identifiant." );
            }
        } else {
            idValue = 0;
        }
        return idValue;

    }

    private void checkDate( String date, Integer idValue, HttpSession session ) throws Exception {
        if ( date != null ) {
            Map<Integer, Receipt> listReceipts = (HashMap<Integer, Receipt>) session.getAttribute( SESSION_RECEIPTS );
            if ( listReceipts != null ) {

                for ( Map.Entry<Integer, Receipt> entry : listReceipts.entrySet() ) {
                    Integer id = entry.getKey();
                    Receipt receipt = entry.getValue();
                    if ( receipt.getDate().equals( date ) && id != idValue ) {
                        throw new Exception( "La date existe déjà" );
                    }
                }
            }
        } else {

            throw new Exception( "Merci de saisir une date" );
        }
    }

    private void checkName( String name ) throws Exception {
        if ( name != null ) {
            if ( name.length() < 2 ) {
                throw new Exception( "L'intitulé du produit doit contenir au moins 2 caractères." );
            }
        } else {
            throw new Exception( "Merci de saisir un intitulé." );
        }
    }

    private double checkAmount( String amount ) throws Exception {
        double temp;
        if ( amount != null ) {
            try {
                temp = Double.parseDouble( amount );
                if ( temp < 0 )
                    throw new Exception( "Le montant doit être un nombre positif." );
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
