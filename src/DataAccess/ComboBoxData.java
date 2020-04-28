package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class ComboBoxData {
	Connection con = DbConnection.getInstance();

	public Vector<String> getFormatType() {
		PreparedStatement pstmt;
		Vector<String> v = new Vector<String>(3,1);
		v.add("--Select Option--");
		try {
			pstmt = con.prepareStatement("SELECT * FROM Media_Format");
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				v.addElement(rs.getString("NME"));
			}
			
			return v;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	
	public Vector<String> getTitleType() {
		PreparedStatement pstmt;
		Vector<String> v = new Vector<String>(3,1);
		v.add("--Select Option--");
		try {
			pstmt = con.prepareStatement("SELECT * FROM Title_Type");
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				v.addElement(rs.getString("NME"));
			}
			
			return v;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
