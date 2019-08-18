package bases_de_donnees;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class Practice {
	public static void main(String[] args) {
		try {
			Class.forName("org.postgresql.Driver");

			String url = "jdbc:postgresql://localhost:5432/Ecole";
			String user = "postgres";
			String passwd = "malo1978";

			Connection connection = DriverManager.getConnection(url, user, passwd);

			Statement statement = connection.createStatement();

			String query = "select classe.cls_id as cls_id, classe.cls_nom as cls_nom, professeur.prof_id, professeur.prof_nom as prof_nom, professeur.prof_prenom, matiere.mat_nom as mat_nom";
			query += " from professeur join j_mat_prof";
			query += " on professeur.prof_id = j_mat_prof.jmp_prof_k";
			query += " join matiere";
			query += " on j_mat_prof.jmp_mat_k = matiere.mat_id";
			query += " join j_cls_jmp";
			query += " on j_cls_jmp.jcm_jmp_k = j_mat_prof.jmp_id";
			query += " join classe";
			query += " on classe.cls_id = j_cls_jmp.jcm_cls_k";
			query += " and classe.cls_id in (1, 7)";
			query += " order by classe.cls_nom desc, professeur.prof_nom";

			ResultSet result = statement.executeQuery(query);

			int previousClsId = 0;
			Boolean isNewClsId;
			int previousProfId = 0;
			Boolean isNewProfId;
			String lastName;
			String firstName;
			String subject;
			String clsName;
			int profId;
			int clsId;

			while (result.next()) {
				clsId = result.getInt("cls_id");
				profId = result.getInt("prof_id");
				isNewClsId = clsId != previousClsId;
				isNewProfId = profId != previousProfId;
				lastName = result.getString("prof_nom").toUpperCase();
				firstName = result.getString("prof_prenom");
				subject = result.getString("mat_nom");
				clsName = result.getString("cls_nom");
				if (isNewClsId) {
					System.out.println("Classe de " + clsName);
					System.out.println("\t* " + lastName + " " + firstName + " enseigne :");
					System.out.println("\t\t\t\t- " + subject);
					previousClsId = clsId;
					previousProfId = profId;

				} else if (isNewProfId) {

					System.out.println("\t* " + lastName + " " + firstName + " enseigne :");
					System.out.println("\t\t\t\t- " + subject);
					previousProfId = profId;
				} else {
					System.out.println("\t\t\t\t- " + subject);

				}

			}

			result.close();
			statement.close();
		} catch (

		Exception e) {
			e.printStackTrace();// : handle exception
		}
	}

}
