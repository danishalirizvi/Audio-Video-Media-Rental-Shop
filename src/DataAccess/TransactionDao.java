package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Models.Customer;
import Models.Transaction;

public class TransactionDao {
	Connection con = DbConnection.getInstance();

	public boolean addTransaction(Transaction transaction) {
		try {
			String query = "INSERT INTO Transactions (ProductID, CustomerID, StaffID, Status, IssueDate) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, transaction.getProductID());
			pstmt.setInt(2, transaction.getCustomerID());
			pstmt.setInt(3, transaction.getStaffID());
			pstmt.setString(4, transaction.getStatus());
			pstmt.setString(5, transaction.getIssueDate());
			pstmt.execute();

			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public ResultSet getTransactions(int id) {
		try {
			String query = "select * from Transactions where CustomerID = " + id;
			PreparedStatement pstmt = con.prepareStatement(query);

			ResultSet rs = pstmt.executeQuery();
			return rs;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	
	public int getIssuedCount(int id) {
		try {
			String query = "select * from Transactions where CustomerID = " + id;
			PreparedStatement pstmt = con.prepareStatement(query);
			int count = 0;
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				switch(rs.getString("Status")) {
				case("Issued"):
					count++;
				break;
				}
				
//				if(rs.getString("Status") == "Issued") {
//					count++;
//					System.out.println(count);
//				}
			}
			return count;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0; 
		}
	}

	public boolean markReturn(int id,String returnTime) {
		try {

			String query = "UPDATE Transactions SET Status = 'Returned', ReturnDate = '"+returnTime+"' WHERE ID =" + id;

			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.execute();

			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}
}
