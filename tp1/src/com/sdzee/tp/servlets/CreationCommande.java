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
import com.sdzee.tp.beans.Commande;
import com.sdzee.tp.forms.CreationCommandeForm;

public class CreationCommande extends HttpServlet {
    public static final String ATT_ORDER         = "commande";
    public static final String ATT_FORM          = "form";

    public static final String VIEW_ORDER        = "/WEB-INF/afficherCommande.jsp";
    public static final String VIEW_FORM         = "/WEB-INF/creerCommande.jsp";
    public static final String SESSION_CLIENTS   = "listClients";
    public static final String SESSION_COMMANDES = "listCommandes";

    public static final String PATH              = "path";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Map<String, Client> listClients = (HashMap<String, Client>) session.getAttribute( SESSION_CLIENTS );

        if ( listClients == null ) {
            listClients = new HashMap<String, Client>();
        }

        System.out.println( listClients );
        session.setAttribute( SESSION_CLIENTS, listClients );
        this.getServletContext().getRequestDispatcher( VIEW_FORM ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        String path = this.getServletConfig().getInitParameter( PATH );
        CreationCommandeForm form = new CreationCommandeForm();
        Commande commande = form.createCommande( request, path );

        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_ORDER, commande );

        if ( form.getIsOK() ) {
            HttpSession session = request.getSession();
            Map<String, Client> listClients = (HashMap<String, Client>) session.getAttribute( SESSION_CLIENTS );
            Map<String, Commande> listCommandes = (HashMap<String, Commande>) session.getAttribute( SESSION_COMMANDES );

            if ( listClients == null ) {
                listClients = new HashMap<String, Client>();
            }
            if ( listCommandes == null ) {
                listCommandes = new HashMap<String, Commande>();
            }

            listClients.put( commande.getClient().getNom(), commande.getClient() );
            listCommandes.put( commande.getDate(), commande );

            session.setAttribute( SESSION_CLIENTS, listClients );
            session.setAttribute( SESSION_COMMANDES, listCommandes );

            this.getServletContext().getRequestDispatcher( VIEW_ORDER ).forward( request, response );

        } else {
            this.getServletContext().getRequestDispatcher( VIEW_FORM ).forward( request, response );
        }

    }
}