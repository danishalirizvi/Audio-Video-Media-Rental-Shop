package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Models.Customer;

public class CustomerDao {
	Connection con = DbConnection.getInstance();

	public boolean registerCustomer(Customer customer) {
		try {
			String query = "insert into Customer (NME, EMAIL, PHNE, ACC_CARD, LYLTY_PNTS, ACCS_LVL, SBSC) values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setString(1, customer.getNME());
			pstmt.setString(2, customer.getEMAIL());
			pstmt.setLong(3, customer.getPHNE());
			pstmt.setLong(4, customer.getACC_CRD());
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

	public boolean updateCustomer(int id, Customer customer) {
		try {
			
			String query = "UPDATE Customer SET NME ='"+customer.getNME()+"',EMAIL ='"+customer.getEMAIL()+"',PHNE ="+customer.getPHNE()+" ,ACC_CARD ="+customer.getACC_CRD()+" ,ACCS_LVL ='"+customer.getACCS_LVL()+"' ,SBSC ='"+customer.getSBSC()+"' WHERE ID ="+id;
			System.out.println(query);
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.execute();

			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public ResultSet getCustomers(String name) {
		try {
			String query = "select * from Customer where NME like '%" + name + "%'";
			PreparedStatement pstmt = con.prepareStatement(query);

			ResultSet rs = pstmt.executeQuery();
			return rs;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	
	public ResultSet getLoyaltyPoints(int id) {
		try {
			String query = "select LYLTY_PNTS from Customer where ID = "+id;
			PreparedStatement pstmt = con.prepareStatement(query);

			ResultSet rs = pstmt.executeQuery();
//			while(rs.next()) {
//				System.out.println(rs.getString("LYLTY_PNTS"));
//			}
			return rs;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public boolean updateLoyaltyPoints(int id) {
		try {
			
			String query = "UPDATE Customer SET LYLTY_PNTS = LYLTY_PNTS + 10 WHERE ID ="+id;
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.execute();

			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public boolean redeemLoyaltyPoints(int id) {
		try {
			
			String query = "UPDATE Customer SET LYLTY_PNTS = LYLTY_PNTS - 100 WHERE ID ="+id;
			System.out.println(query);
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.execute();

			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
}
