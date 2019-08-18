package com.lm.forms;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.lm.beans.Receipt;

public class MonthlyTotalForm {
    private static final String     SESSION_RECEIPTS = "listReceipts";

    private HashMap<String, Double> monthlyTotal     = new LinkedHashMap<String, Double>();

    public HashMap<String, Double> getMonthlyTotal( HttpServletRequest request ) {

        for ( int i = 1; i < 13; i++ ) {
            monthlyTotal.put( monthNumberToMonth( "0" + Integer.toString( i ) ), 0.0 );
        }
        HttpSession session = request.getSession();
        Map<Integer, Receipt> listReceipts = (HashMap<Integer, Receipt>) session.getAttribute( SESSION_RECEIPTS );

        if ( listReceipts != null ) {
            for ( Map.Entry<Integer, Receipt> entry : listReceipts.entrySet() ) {
                Receipt receipt = entry.getValue();
                try {
                    String month = monthNumberToMonth( receipt.getDate().substring( 5, 7 ) );
                    Double amount = monthlyTotal.get( month );
                    monthlyTotal.put( month, amount + receipt.getAmount() );
                } catch ( NumberFormatException e ) {
                    e.getMessage();
                }

            }
        }

        return monthlyTotal;

    }

    private String monthNumberToMonth( String monthNumber ) {
        String[] months = { "Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre",
                "Octobre", "Novembre", "Décembre" };

        int i = Integer.parseInt( monthNumber );

        return months[i - 1];
    }

}
