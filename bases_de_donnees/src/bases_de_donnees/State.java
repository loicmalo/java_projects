package bases_de_donnees;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//CTRL + SHIFT + O pour générer les imports
public class State {
public static void main(String[] args) {
  try {         
    Class.forName("org.postgresql.Driver");
    String url = "jdbc:postgresql://localhost:5432/Ecole";
    String user = "postgres";
    String passwd = "malo1978";

    Connection conn = DriverManager.getConnection(url, user, passwd);
    //On autorise la mise à jour des données 
    //Et la mise à jour de l'affichage
    Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
    PreparedStatement prepare = conn.prepareStatement("UPDATE professeur set prof_prenom = ? "+"WHERE prof_nom = 'MAMOU'");

    //On va chercher une ligne dans la base de données
    String query = "SELECT prof_nom, prof_prenom FROM professeur "+"WHERE prof_nom ='MAMOU'";         

    //On exécute la requête
    ResultSet res = state.executeQuery(query);
    res.first();
    //On affiche
    System.out.println("\n\tDONNEES D'ORIGINE : ");
    System.out.println("\t-------------------");
    System.out.println("\tNOM : " + res.getString("prof_nom") + " - PRENOM : " +  res.getString("prof_prenom"));

    //On paramètre notre requête préparée
    prepare.setString(1, "Gérard");
    //On exécute
    prepare.executeUpdate();

    res = state.executeQuery(query);
    res.first();
    //On affiche à nouveau
    System.out.println("\n\t\t APRES MAJ : ");
    System.out.println("\t\t * NOM : " + res.getString("prof_nom") + " - PRENOM :" + res.getString("prof_prenom"));
                
    //On effectue une mise à jour
    prepare.setString(1, "Daniel");
    prepare.executeUpdate();
       
    res = state.executeQuery(query);
    res.first();
    //On affiche une nouvelle fois
    System.out.println("\n\t\t REMISE A ZERO : ");
    System.out.println("\t\t * NOM : " + res.getString("prof_nom") + " - PRENOM :" + res.getString("prof_prenom"));

    prepare.close();
    res.close();
    state.close();         
  } catch (ClassNotFoundException e) {
    e.printStackTrace();
  } catch (SQLException e) {
    e.printStackTrace();
  }      
}
}