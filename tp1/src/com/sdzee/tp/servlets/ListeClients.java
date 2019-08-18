package com.sdzee.tp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListeClients extends HttpServlet {

    public static final String SESSION_CLIENTS = "listClients";
    public static final String VIEW_CLIENTS    = "/WEB-INF/listerClients.jsp";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        // HttpSession session = request.getSession();
        // Map<String, Client> listClients = (HashMap<String, Client>)
        // session.getAttribute( SESSION_CLIENTS );

        this.getServletContext().getRequestDispatcher( VIEW_CLIENTS ).forward( request, response );
    }

}
