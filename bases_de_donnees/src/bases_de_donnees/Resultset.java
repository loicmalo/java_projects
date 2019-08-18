package bases_de_donnees;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//CTRL + SHIFT + O pour générer les imports
public class Resultset {
public static void main(String[] args) {
  try {
    Class.forName("org.postgresql.Driver");
    String url = "jdbc:postgresql://localhost:5432/Ecole";
    String user = "postgres";
    String passwd = "malo1978";
       
    Connection conn = DriverManager.getConnection(url, user, passwd);
    Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
       
    String query = "SELECT prof_nom, prof_prenom FROM professeur";         
    ResultSet res = state.executeQuery(query);
    int i = 1;         
       
    System.out.println("\n\t---------------------------------------");
    System.out.println("\tLECTURE STANDARD.");
    System.out.println("\t---------------------------------------");
       
    while(res.next()){
      System.out.println("\tNom : "+res.getString("prof_nom") +" \t prénom : "+res.getString("prof_prenom"));
      //On regarde si on se trouve sur la dernière ligne du résultat
      if(res.isLast())
        System.out.println("\t\t* DERNIER RESULTAT !\n");
      i++;
    }
       
    //Une fois la lecture terminée, on contrôle si on se trouve bien à l'extérieur des lignes de résultat
    if(res.isAfterLast())
      System.out.println("\tNous venons de terminer !\n");
       
    System.out.println("\t---------------------------------------");
    System.out.println("\tLecture en sens contraire.");
    System.out.println("\t---------------------------------------");
       
    //On se trouve alors à la fin
    //On peut parcourir le résultat en sens contraire
    while(res.previous()){
      System.out.println("\tNom : "+res.getString("prof_nom") +" \t prénom : "+res.getString("prof_prenom"));

      //On regarde si on se trouve sur la première ligne du résultat
      if(res.isFirst())
        System.out.println("\t\t* RETOUR AU DEBUT !\n");
    }
       
    //On regarde si on se trouve avant la première ligne du résultat
    if(res.isBeforeFirst())
      System.out.println("\tNous venons de revenir au début !\n");
       
    System.out.println("\t---------------------------------------");
    System.out.println("\tAprès positionnement absolu du curseur à la place N° "+ i/2 + ".");
    System.out.println("\t---------------------------------------");
    //On positionne le curseur sur la ligne i/2
    //Peu importe où on se trouve
    res.absolute(i/2);
    while(res.next())
      System.out.println("\tNom : "+res.getString("prof_nom") +" \t prénom : "+ res.getString("prof_prenom"));
       
    System.out.println("\t---------------------------------------");
    System.out.println("\tAprès positionnement relatif du curseur à la place N° "+(i-(i-2)) + ".");
    System.out.println("\t---------------------------------------");
    //On place le curseur à la ligne actuelle moins i-2
    //Si on n'avait pas mis de signe moins, on aurait avancé de i-2 lignes 
    res.relative(-(i-2));
    while(res.next())
      System.out.println("\tNom : "+res.getString("prof_nom") +" \t prénom : "+res.getString("prof_prenom"));
       
    res.close();
    state.close();

  } catch (Exception e) {
    e.printStackTrace();
  }
}
}
