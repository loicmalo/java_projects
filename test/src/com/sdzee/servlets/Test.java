package com.sdzee.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.beans.Coyote;

public class Test extends HttpServlet {
	public static final String ATT_MESSAGE = "test";
	public static final String ATT_BEAN	 = "coyote";
	public static final String ATT_LISTE	 = "liste";
	public static final String ATT_JOUR	 = "jour";
	public static final String VUE	 = "/WEB-INF/test.jsp";
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		/** Création et initialisation du message. */
		String message = "Message transmis de la servlet à la JSP.";
		
		/** Création du bean et initialisation de ses propriétés */
		Coyote premierBean = new Coyote();
		premierBean.setNom( "Coyote" );
		premierBean.setPrenom( "Wile E." );
		
		/** Création de la liste et insertion de quatre éléments */
		List<Integer> premiereListe = new ArrayList<Integer>();
		premiereListe.add( 27 );
		premiereListe.add( 12 );
		premiereListe.add( 138 );
		premiereListe.add( 6 );
		
		/** On utilise ici la libraire Joda pour manipuler les dates, pour deux raisons :
		 *    - c'est tellement plus simple et limpide que de travailler avec les objets Date ou Calendar !
		 *    - c'est (probablement) un futur standard de l'API Java.
		 */
		LocalDate dt = LocalDate.now();
		Integer jourDuMois = dt.getDayOfMonth();
		
		/** Stockage du message, du bean et de la liste dans l'objet request */
		request.setAttribute( ATT_MESSAGE, message );
		request.setAttribute( ATT_BEAN, premierBean );
		request.setAttribute( ATT_LISTE, premiereListe );
		request.setAttribute( ATT_JOUR, jourDuMois );
		
		/** Transmission de la paire d'objets request/response à notre JSP */
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}
}