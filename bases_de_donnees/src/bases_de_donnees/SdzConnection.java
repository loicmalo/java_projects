package bases_de_donnees;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//CTRL + SHIFT + O pour générer les imports

//CTRL + SHIFT + O pour générer les imports
public class SdzConnection{

private static String url = "jdbc:postgresql://localhost:5432/Ecole";
private static String user = "postgres";
private static String passwd = "malo1978";
private static Connection connect;
 
public static Connection getInstance(){
  if(connect == null){
    try {
      connect = DriverManager.getConnection(url, user, passwd);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }      
  return connect;
}   
}