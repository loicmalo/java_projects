package com.lm.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lm.beans.Receipt;

public class RemovalReceipt extends HttpServlet {

    public static final String FIELD_ID         = "id";
    public static final String SESSION_RECEIPTS = "listReceipts";

    public static final String VIEW_LIST        = "liste";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        String id = getFieldValue( request, FIELD_ID );

        HttpSession session = request.getSession();
        Map<Integer, Receipt> listReceipts = (HashMap<Integer, Receipt>) session.getAttribute( SESSION_RECEIPTS );

        try {
            Integer idValue = Integer.parseInt( id );
            System.out.println( idValue );
            System.out.println( listReceipts );
            if ( idValue != null && listReceipts != null ) {
                listReceipts.remove( idValue );
                session.setAttribute( SESSION_RECEIPTS, listReceipts );
            }

        } catch ( NumberFormatException e ) {
            e.getMessage();
        }

        response.sendRedirect( VIEW_LIST );

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
