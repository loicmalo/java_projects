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
import com.sdzee.tp.forms.CreationClientForm;

public class CreationClient extends HttpServlet {

    public static final String ATT_CLIENT      = "client";
    public static final String ATT_FORM        = "form";

    public static final String VIEW_CLIENT     = "/WEB-INF/afficherClient.jsp";
    public static final String VIEW_FORM       = "/WEB-INF/creerClient.jsp";
    public static final String SESSION_CLIENTS = "listClients";

    public static final String PATH            = "path";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher( VIEW_FORM ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        String path = this.getServletConfig().getInitParameter( PATH );

        CreationClientForm form = new CreationClientForm();
        Client client = form.createClient( request, path );

        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_CLIENT, client );

        if ( form.getIsOK() ) {
            HttpSession session = request.getSession();
            Map<String, Client> listClients = (HashMap<String, Client>) session.getAttribute( SESSION_CLIENTS );

            if ( listClients == null ) {
                listClients = new HashMap<String, Client>();
            }
            listClients.put( client.getNom(), client );
            session.setAttribute( SESSION_CLIENTS, listClients );

            this.getServletContext().getRequestDispatcher( VIEW_CLIENT ).forward( request, response );

        } else {
            this.getServletContext().getRequestDispatcher( VIEW_FORM ).forward( request, response );
        }

    }
}