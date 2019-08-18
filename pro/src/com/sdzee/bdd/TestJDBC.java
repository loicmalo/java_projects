package com.sdzee.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class TestJDBC {

    private List<String> messages = new ArrayList<String>();

    public List<String> executerTests( HttpServletRequest request ) {
        /* Chargement du driver JDBC pour MySQL */
        try {
            messages.add( "Chargement du driver..." );
            Class.forName( "org.postgresql.Driver" );
            messages.add( "Driver chargé !" );
        } catch ( ClassNotFoundException e ) {
            messages.add( "Erreur lors du chargement : le driver n'a pas été trouvé dans le classpath ! <br/>"
                    + e.getMessage() );
        }

        /* Connexion à la base de données */
        String url = "jdbc:postgresql://localhost:5432/bdd_sdzee";
        String utilisateur = "postgres";
        String motDePasse = "malo1978";
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;
        try {
            messages.add( "Connexion à la base de données..." );
            connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
            messages.add( "Connexion réussie !" );

            /* Création de l'objet gérant les requêtes préparées */
            preparedStatement = connexion.prepareStatement(
                    "INSERT INTO \"Utilisateur\" (email, mot_de_passe, nom, date_inscription) VALUES(?, MD5(?), ?, NOW());" );
            messages.add( "Requête préparée créée !" );

            /* Récupération des paramètres d'URL saisis par l'utilisateur */
            String paramEmail = request.getParameter( "email" );
            String paramMotDePasse = request.getParameter( "motdepasse" );
            String paramNom = request.getParameter( "nom" );

            /*
             * Remplissage des paramètres de la requête grâce aux méthodes
             * setXXX() mises à disposition par l'objet PreparedStatement.
             */
            preparedStatement.setString( 1, paramEmail );
            preparedStatement.setString( 2, paramMotDePasse );
            preparedStatement.setString( 3, paramNom );

            /* Exécution de la requête */
            int statut = preparedStatement.executeUpdate();

            /* Formatage pour affichage dans la JSP finale. */
            messages.add( "Résultat de la requête d'insertion préparée : " + statut + "." );

            /* Création de l'objet gérant les requêtes préparées */
            preparedStatement = connexion
                    .prepareStatement( "SELECT id, email, mot_de_passe, nom FROM \"Utilisateur\";" );
            messages.add( "Requête préparée créée !" );

            /* Exécution d'une requête de lecture */
            resultat = preparedStatement.executeQuery();
            messages.add( "Requête \"SELECT id, email, mot_de_passe, nom FROM Utilisateur;\" effectuée !" );

            /* Récupération des données du résultat de la requête de lecture */
            while ( resultat.next() ) {
                int idUtilisateur = resultat.getInt( "id" );
                String emailUtilisateur = resultat.getString( "email" );
                String motDePasseUtilisateur = resultat.getString( "mot_de_passe" );
                String nomUtilisateur = resultat.getString( "nom" );
                /* Formatage des données pour affichage dans la JSP finale. */
                messages.add(
                        "Données retournées par la requête : id = " + idUtilisateur + ", email = " + emailUtilisateur
                                + ", motdepasse = "
                                + motDePasseUtilisateur + ", nom = " + nomUtilisateur + "." );
            }
        } catch ( SQLException e ) {
            messages.add( "Erreur lors de la connexion : <br/>"
                    + e.getMessage() );
        } finally {
            messages.add( "Fermeture de l'objet ResultSet." );
            if ( resultat != null ) {
                try {
                    resultat.close();
                } catch ( SQLException ignore ) {
                }
            }
            messages.add( "Fermeture de l'objet PreparedStatement." );
            if ( preparedStatement != null ) {
                try {
                    preparedStatement.close();
                } catch ( SQLException ignore ) {
                }
            }
            messages.add( "Fermeture de l'objet Connection." );
            if ( connexion != null ) {
                try {
                    connexion.close();
                } catch ( SQLException ignore ) {
                }
            }
        }

        return messages;
    }
}
