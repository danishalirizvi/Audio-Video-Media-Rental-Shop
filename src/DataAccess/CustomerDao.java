package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;

import Models.Customer;

public class CustomerDao {
	Connection con = DbConnection.getInstance();

	public boolean registerCustomer(Customer customer) {
		try {
			String query = "insert into Customer (NME, EMAIL, PHNE, ACC_CARD, LYLTY_PNTS, ACCS_LVL, SBSC) values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setString(1, customer.getNME());
			pstmt.setString(2, customer.getEMAIL());
			pstmt.setInt(3, customer.getPHNE());
			pstmt.setInt(4, customer.getACC_CRD());
			pstmt.setInt(5, customer.getLYLTY_PNTS());
			pstmt.setString(6, customer.getACCS_LVL());
			pstmt.setString(7, customer.getSBSC());

			pstmt.execute();
			
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
}
