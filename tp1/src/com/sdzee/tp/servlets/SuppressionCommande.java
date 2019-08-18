package com.sdzee.tp.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdzee.tp.beans.Commande;

public class SuppressionCommande extends HttpServlet {

    public static final String FIELD_DATE        = "date";
    public static final String SESSION_COMMANDES = "listCommandes";
    public static final String VIEW_COMMANDES    = "listeCommandes";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        String date = getFieldValue( request, FIELD_DATE );

        HttpSession session = request.getSession();
        Map<String, Commande> listCommandes = (HashMap<String, Commande>) session.getAttribute( SESSION_COMMANDES );

        if ( date != null && listCommandes != null ) {
            listCommandes.remove( date );
            session.setAttribute( SESSION_COMMANDES, listCommandes );
        }

        response.sendRedirect( VIEW_COMMANDES );
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
