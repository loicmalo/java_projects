package com.lm.servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lm.forms.MonthlyTotalForm;

public class CreationMonthlyTotal extends HttpServlet {
    public static final String ATT_MONTHLY_TOTAL = "monthlyTotal";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        MonthlyTotalForm form = new MonthlyTotalForm();
        HashMap<String, Double> monthlyTotal = form.getMonthlyTotal( request );

        request.setAttribute( ATT_MONTHLY_TOTAL, monthlyTotal );

        this.getServletContext().getRequestDispatcher( "/WEB-INF/monthlyTotal.jsp" ).forward( request, response );
    }

}
