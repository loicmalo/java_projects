package bases_de_donnees;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//CTRL + SHIFT + O pour générer les imports
public class Modif {
public static void main(String[] args) {
  try {
    Class.forName("org.postgresql.Driver");
    String url = "jdbc:postgresql://localhost:5432/Ecole";
    String user = "postgres";
    String passwd = "malo1978";

    Connection conn = DriverManager.getConnection(url, user, passwd);
    //On autorise la mise à jour des données 
    //Et la mise à jour de l'affichage
    Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

    //On va chercher une ligne dans la base de données
    String query = "SELECT prof_id, prof_nom, prof_prenom FROM professeur " + "WHERE prof_nom = 'MAMOU'";         
    ResultSet res = state.executeQuery(query);
    res.first();

    //On affiche ce que l'on trouve
    System.out.println("NOM : " + res.getString("prof_nom") + " - PRENOM : " + res.getString("prof_prenom"));

    //On met à jour les champs
    res.updateString("prof_nom", "COURTEL");
    res.updateString("prof_prenom", "Angelo");
    //On valide
    res.updateRow();

    //On affiche les modifications
    System.out.println("*********************************");
    System.out.println("APRES MODIFICATION : ");
    System.out.println("\tNOM : " + res.getString("prof_nom") + " - PRENOM : " + res.getString("prof_prenom") + "\n");

    //On remet les informations de départ
    res.updateString("prof_nom", "MAMOU");
    res.updateString("prof_prenom", "Daniel");
    //On valide à nouveau
    res.updateRow();

    //Et voilà !
    System.out.println("*********************************");
    System.out.println("APRES REMODIFICATION : ");
    System.out.println("\tNOM : " + res.getString("prof_nom") + " - PRENOM : " + res.getString("prof_prenom") + "\n");

    res.close();
    state.close();

  } catch (Exception e) {
    e.printStackTrace();
  }
}
}
