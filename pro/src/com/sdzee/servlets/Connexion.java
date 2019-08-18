package com.sdzee.servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdzee.beans.Utilisateur;
import com.sdzee.forms.ConnexionForm;

public class Connexion extends HttpServlet {
    public static final String ATT_USER                  = "utilisateur";
    public static final String ATT_FORM                  = "form";
    public static final String ATT_INTERVALLE_CONNEXIONS = "intervalleConnexions";
    public static final String ATT_SESSION_USER          = "sessionUtilisateur";
    public static final String COOKIE_DERNIERE_CONNEXION = "derniereConnexion";
    public static final String FORMAT_DATE               = "dd/MM/yyyy HH:mm:ss";
    public static final String VUE                       = "/WEB-INF/connexion.jsp";
    public static final String CHAMP_MEMOIRE             = "memoire";
    public static final int    COOKIE_MAX_AGE            = 60 * 60 * 24 * 365;

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        String derniereConnexion = getCookieValue( request,
                COOKIE_DERNIERE_CONNEXION );
        if ( derniereConnexion != null ) {
            LocalDateTime dtCourante = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern( FORMAT_DATE );

            LocalDateTime dtDerniereConnexion = LocalDateTime.parse(
                    derniereConnexion, formatter );
            LocalDateTime tempDateTime = LocalDateTime.from(
                    dtDerniereConnexion );

            long years = tempDateTime.until( dtCourante, ChronoUnit.YEARS );
            tempDateTime = tempDateTime.plusYears( years );

            long months = tempDateTime.until( dtCourante, ChronoUnit.MONTHS );
            tempDateTime = tempDateTime.plusMonths( months );

            long days = tempDateTime.until( dtCourante, ChronoUnit.DAYS );
            tempDateTime = tempDateTime.plusDays( days );

            long hours = tempDateTime.until( dtCourante, ChronoUnit.HOURS );
            tempDateTime = tempDateTime.plusHours( hours );

            long minutes = tempDateTime.until( dtCourante, ChronoUnit.MINUTES );
            tempDateTime = tempDateTime.plusMinutes( minutes );

            long seconds = tempDateTime.until( dtCourante, ChronoUnit.SECONDS );

            String intervalleConnexions = years + " an(s) ";
            intervalleConnexions += months + " mois ";
            intervalleConnexions += days + " jour(s)";
            intervalleConnexions += hours + " heures(s) ";
            intervalleConnexions += minutes + " minute(s) ";
            intervalleConnexions += "et ";
            intervalleConnexions += seconds + " seconde(s)";
            request.setAttribute( ATT_INTERVALLE_CONNEXIONS, intervalleConnexions );
        }

        /* Affichage de la page de connexion */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        /* Préparation de l'objet formulaire */
        ConnexionForm form = new ConnexionForm();

        /* Traitement de la requête et récupération du bean en résultant */
        Utilisateur utilisateur = form.connecterUtilisateur( request );

        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();

        /**
         * Si aucune erreur de validation n'a eu lieu, alors ajout du bean
         * Utilisateur à la session, sinon suppression du bean de la session.
         */
        if ( form.getErreurs().isEmpty() ) {
            session.setAttribute( ATT_SESSION_USER, utilisateur );
        } else {
            session.setAttribute( ATT_SESSION_USER, null );
        }

        if ( request.getParameter( CHAMP_MEMOIRE ) != null ) {
            LocalDateTime dt = LocalDateTime.now();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern( FORMAT_DATE );
            String dateDerniereConnexion = formatter.format( dt );

            setCookie( response, COOKIE_DERNIERE_CONNEXION, dateDerniereConnexion, COOKIE_MAX_AGE );

        } else {
            setCookie( response, COOKIE_DERNIERE_CONNEXION, "", 0 );
        }

        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_USER, utilisateur );

        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    private static String getCookieValue( HttpServletRequest request, String nom ) {
        Cookie[] cookies = request.getCookies();
        if ( cookies != null ) {
            for ( Cookie cookie : cookies ) {
                if ( cookie != null && nom.contentEquals( cookie.getName() ) ) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    private static void setCookie( HttpServletResponse response, String nom, String valeur, int maxAge ) {
        Cookie cookie = new Cookie( nom, valeur );
        cookie.setMaxAge( maxAge );
        response.addCookie( cookie );
    }
}