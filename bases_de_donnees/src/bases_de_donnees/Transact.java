package bases_de_donnees;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//CTRL + SHIFT + O pour générer les imports
public class Transact {
public static void main(String[] args) {
  try {
    Class.forName("org.postgresql.Driver");
       
    String url = "jdbc:postgresql://localhost:5432/Ecole";
    String user = "postgres";
    String passwd = "malo1978";
       
    Connection conn = DriverManager.getConnection(url, user, passwd);
    conn.setAutoCommit(false);
    Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    String query = "UPDATE professeur SET prof_prenom = 'Cyrille' "+"WHERE prof_nom = 'MAMOU'";
       
    ResultSet result = state.executeQuery("SELECT * FROM professeur"+" WHERE prof_nom = 'MAMOU'");
    result.first();
    System.out.println("NOM : " + result.getString("prof_nom") + " - PRENOM : " + result.getString("prof_prenom"));
       
    state.executeUpdate(query);
       
    result = state.executeQuery("SELECT * FROM professeur WHERE prof_nom = 'MAMOU'");
    result.first();
    System.out.println("NOM : " + result.getString("prof_nom") + " - PRENOM : " + result.getString("prof_prenom"));
       
    result.close();
    state.close();         
  } catch (Exception e) {
    e.printStackTrace();
  }
}
}
