import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;

public class MainClass {

	public static void main(String[] args) {
		
		try {
			/*
			 * Connection con = DbConnection.getInstance();
			 * 
			 * String sql = "SELECT ID,NME,DSCP,MAX_ALWD FROM Subscription_Type";
			 * 
			 * PreparedStatement stmt=con.prepareStatement(sql); ResultSet
			 * rs=stmt.executeQuery(); while(rs.next()){
			 * System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(4)); }
			 */
			GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
			Rectangle bounds = env.getMaximumWindowBounds();
			System.out.println("Screen Bounds: " + bounds );
			System.out.println("END");
			Login l = new Login();
			l.setVisible(true);
			l.setExtendedState(l.getExtendedState()|JFrame.MAXIMIZED_BOTH);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
