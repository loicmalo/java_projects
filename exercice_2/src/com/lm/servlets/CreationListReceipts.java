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

public class CreationListReceipts extends HttpServlet {
    public static final String ATT_TOTAL        = "total";
    public static final String SESSION_RECEIPTS = "listReceipts";
    public static final String VIEW_LIST        = "/WEB-INF/listReceipts.jsp";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Map<Integer, Receipt> listReceipts = (HashMap<Integer, Receipt>) session.getAttribute( SESSION_RECEIPTS );
        Double total = 0.0;

        if ( listReceipts != null ) {
            for ( Map.Entry<Integer, Receipt> entry : listReceipts.entrySet() ) {
                Receipt receipt = entry.getValue();
                total += receipt.getAmount();
            }
        }

        request.setAttribute( ATT_TOTAL, total );

        this.getServletContext().getRequestDispatcher( VIEW_LIST ).forward( request, response );

    }

}
