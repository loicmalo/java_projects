package com.sdzee.tp.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdzee.tp.beans.Client;

public class SuppressionClient extends HttpServlet {

    public static final String FIELD_NOM       = "nom";
    public static final String SESSION_CLIENTS = "listClients";
    public static final String VIEW_CLIENTS    = "listeClients";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        String nom = getFieldValue( request, FIELD_NOM );

        HttpSession session = request.getSession();
        Map<String, Client> listClients = (HashMap<String, Client>) session.getAttribute( SESSION_CLIENTS );

        if ( nom != null && listClients != null ) {
            listClients.remove( nom );
            session.setAttribute( SESSION_CLIENTS, listClients );

        }

        response.sendRedirect( VIEW_CLIENTS );

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
