package bases_de_donnees;

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement prepare = SdzConnection.getInstance().prepareStatement("SELECT * FROM classe WHERE cls_nom =?");
			
			Statement state = SdzConnection.getInstance().createStatement();
			
			SdzConnection.getInstance().setAutoCommit(false);
			
			DatabaseMetaData meta = SdzConnection.getInstance().getMetaData();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
