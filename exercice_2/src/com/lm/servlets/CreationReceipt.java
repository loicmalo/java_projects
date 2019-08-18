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
import com.lm.forms.ReceiptForm;

public class CreationReceipt extends HttpServlet {
    public static final String  ATT_RECEIPT      = "receipt";
    public static final String  ATT_FORM         = "form";

    public static final String  VIEW_LIST        = "liste";
    public static final String  VIEW_FORM        = "/WEB-INF/receipt.jsp";
    private static final String SESSION_RECEIPTS = "listReceipts";
    private static final String SESSION_COUNTER  = "counter";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        String paramId = request.getParameter( "id" );
        if ( paramId != null ) {
            try {

                Integer id = Integer.parseInt( paramId );

                HttpSession session = request.getSession();
                Map<Integer, Receipt> listReceipts = (HashMap<Integer, Receipt>) session
                        .getAttribute( SESSION_RECEIPTS );
                Receipt receipt = listReceipts.get( id );
                request.setAttribute( ATT_RECEIPT, receipt );

            } catch ( Exception e ) {
                e.getMessage();
            }
        }
        this.getServletContext().getRequestDispatcher( VIEW_FORM ).forward( request, response );

    }

    public void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        ReceiptForm form = new ReceiptForm();
        Receipt receipt = form.createReceipt( request );

        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_RECEIPT, receipt );

        if ( form.getIsOK() ) {

            HttpSession session = request.getSession();
            Map<Integer, Receipt> listReceipts = (HashMap<Integer, Receipt>) session.getAttribute( SESSION_RECEIPTS );
            Integer counter = (Integer) session.getAttribute( SESSION_COUNTER );

            // System.out.println( listReceipts );
            if ( listReceipts == null ) {
                listReceipts = new HashMap<Integer, Receipt>();
                counter = 0;
            }

            // System.out.println( receipt.getId() );
            if ( listReceipts.get( receipt.getId() ) == null ) {
                receipt.setId( ++counter );
                listReceipts.put( receipt.getId(), receipt );

            } else
                listReceipts.put( receipt.getId(), receipt );

            session.setAttribute( SESSION_RECEIPTS, listReceipts );
            session.setAttribute( SESSION_COUNTER, counter );
            response.sendRedirect( VIEW_LIST );

        } else {
            this.getServletContext().getRequestDispatcher( VIEW_FORM ).forward( request, response );
        }
    }
}
