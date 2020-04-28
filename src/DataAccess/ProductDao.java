package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Models.Product;

public class ProductDao {

	Connection con = DbConnection.getInstance();

	public boolean addProduct(int type, Product product) {
		try {
			System.out.println(product.toString());
			if (type == 2) {
				String query = "insert into Products (TITL, DSCP, TITL_TYPE, FRMT_TYPE, RELS_YEAR, Genre, Band, Organiser) values (?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement pstmt = con.prepareStatement(query);

				pstmt.setString(1, product.getTITL());
				pstmt.setString(2, product.getDSCP());
				pstmt.setString(3, product.getTITL_TYPE());
				pstmt.setString(4, product.getFRMT_TYPE());
				pstmt.setInt(5, product.getRELS_YEAR());
				pstmt.setString(6, product.getGenre());
				pstmt.setString(7, product.getBand());
				pstmt.setString(8, product.getOrganiser());

				pstmt.execute();
			}
			else if (type == 3) {
				String query = "insert into Products (TITL, DSCP, TITL_TYPE, FRMT_TYPE, RELS_YEAR, Genre, Director) values (?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement pstmt = con.prepareStatement(query);

				pstmt.setString(1, product.getTITL());
				pstmt.setString(2, product.getDSCP());
				pstmt.setString(3, product.getTITL_TYPE());
				pstmt.setString(4, product.getFRMT_TYPE());
				pstmt.setInt(5, product.getRELS_YEAR());
				pstmt.setString(6, product.getGenre());
				pstmt.setString(7, product.getDirector());

				pstmt.execute();
			}
			else if (type == 1) {
				String query = "insert into Products (TITL, DSCP, TITL_TYPE, FRMT_TYPE, RELS_YEAR, Manufacturer, Model) values (?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement pstmt = con.prepareStatement(query);

				pstmt.setString(1, product.getTITL());
				pstmt.setString(2, product.getDSCP());
				pstmt.setString(3, product.getTITL_TYPE());
				pstmt.setString(4, product.getFRMT_TYPE());
				pstmt.setInt(5, product.getRELS_YEAR());
				pstmt.setString(6, product.getManufacturer());
				pstmt.setString(7, product.getModel());

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
}
