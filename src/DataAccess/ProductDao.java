package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Models.Customer;
import Models.Product;

public class ProductDao {

	Connection con = DbConnection.getInstance();

	public boolean addProduct(int type, Product product) {
		try {
			System.out.println(product.toString());
			if (type == 2) {
				String query = "insert into Products (TITL, DSCP, TITL_TYPE, FRMT_TYPE, RELS_YEAR, Genre, Band, Organiser, Quantity) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement pstmt = con.prepareStatement(query);

				pstmt.setString(1, product.getTITL());
				pstmt.setString(2, product.getDSCP());
				pstmt.setString(3, product.getTITL_TYPE());
				pstmt.setString(4, product.getFRMT_TYPE());
				pstmt.setInt(5, product.getRELS_YEAR());
				pstmt.setString(6, product.getGenre());
				pstmt.setString(7, product.getBand());
				pstmt.setString(8, product.getOrganiser());
				pstmt.setInt(9, product.getQuantity());

				pstmt.execute();
			}
			else if (type == 3) {
				String query = "insert into Products (TITL, DSCP, TITL_TYPE, FRMT_TYPE, RELS_YEAR, Genre, Director, Quantity) values (?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement pstmt = con.prepareStatement(query);

				pstmt.setString(1, product.getTITL());
				pstmt.setString(2, product.getDSCP());
				pstmt.setString(3, product.getTITL_TYPE());
				pstmt.setString(4, product.getFRMT_TYPE());
				pstmt.setInt(5, product.getRELS_YEAR());
				pstmt.setString(6, product.getGenre());
				pstmt.setString(7, product.getDirector());
				pstmt.setInt(8, product.getQuantity());

				pstmt.execute();
			}
			else if (type == 1) {
				String query = "insert into Products (TITL, DSCP, TITL_TYPE, RELS_YEAR, Manufacturer, Model, Quantity) values (?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement pstmt = con.prepareStatement(query);

				pstmt.setString(1, product.getTITL());
				pstmt.setString(2, product.getDSCP());
				pstmt.setString(3, product.getTITL_TYPE());
				pstmt.setInt(4, product.getRELS_YEAR());
				pstmt.setString(5, product.getManufacturer());
				pstmt.setString(6, product.getModel());
				pstmt.setInt(7, product.getQuantity());
				
				pstmt.execute();
			}
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public ResultSet getProducts(String name) {
		try {
			String query = "select * from Products where TITL like '%"+name+"%'";
			PreparedStatement pstmt = con.prepareStatement(query);

			ResultSet rs =  pstmt.executeQuery();
			return rs;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public ResultSet getProductsCust(String name, String titleType) {
		try {
			String query = null;
			if(titleType == "Music+Live Concert Videos") {
				query = "select * from Products where TITL like '%"+name+"%' and TITL_TYPE = 'Music' or TITL_TYPE = 'Live Concert Videos'";
			} else {
				query = "select * from Products where TITL like '%"+name+"%' and TITL_TYPE = '"+titleType+"'";
			}
			
			PreparedStatement pstmt = con.prepareStatement(query);

			ResultSet rs =  pstmt.executeQuery();
			return rs;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}	
	
	public boolean decrement(int prodID) {
		try {
			
			String query = "UPDATE Products SET Quantity = Quantity-1 WHERE ID ="+prodID;

			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.execute();

			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public boolean increment(int prodID) {
		try {
			
			String query = "UPDATE Products SET Quantity = Quantity+1 WHERE ID ="+prodID;

			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.execute();

			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
}
