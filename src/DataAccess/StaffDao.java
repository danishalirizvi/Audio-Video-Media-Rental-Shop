package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Models.*;

public class StaffDao {

	Connection con = DbConnection.getInstance();

	public Staff login(LoginCredential credential) {
		PreparedStatement pstmt;

		Staff staff = null;
		try {
			pstmt = con.prepareStatement("SELECT * FROM Staff where USR_NME=? and PSWD=?");
			pstmt.setString(1, credential.getUsername());
			pstmt.setString(2, credential.getPassword());

			ResultSet rst = pstmt.executeQuery();
			while (rst.next()) {

				int ID = rst.getInt(1);
				String NME = rst.getString(2);
				int PHNE = rst.getInt(3);
				String USR_NME = rst.getString(4);
				String PSWD = rst.getString(5);
				staff = new Staff(ID, NME, PHNE, USR_NME, PSWD);
			}
			return staff;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public boolean register(Staff staff) {
		PreparedStatement pstmt;

		try {
			String query = "insert into Staff (NME, PHNE, USR_NME, PSWD) values ('" + staff.getName() + "', '"
					+ staff.getPHNE() + "', '" + staff.getUsername() + "', '" + staff.getPassword() + "')";
			System.out.println(query);
			pstmt = con.prepareStatement(query);
			
			return pstmt.execute();
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
}
