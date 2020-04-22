import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField usernameField;
	private JTextField passwordField;
	Staff staff;
	public Login() {
		
		
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(189, 132, 75, 23);
		contentPane.add(loginButton);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblLogin.setBounds(10, 11, 83, 37);
		contentPane.add(lblLogin);
		
		usernameField = new JTextField();
		usernameField.setBounds(103, 56, 161, 20);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JTextField();
		passwordField.setBounds(103, 87, 161, 20);
		contentPane.add(passwordField);
		passwordField.setColumns(10);
		
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(45, 59, 48, 14);
		contentPane.add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(45, 90, 46, 14);
		contentPane.add(passwordLabel);
		
		loginButton.addActionListener(this);
		loginButton.setActionCommand("login");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "login") {
			PreparedStatement pstmt;
			Connection con = DbConnection.getInstance();
			try {
				pstmt = con.prepareStatement("SELECT * FROM Staff where USR_NME=? and PSWD=?");
				pstmt.setString(1, usernameField.getText());
				pstmt.setString(2, passwordField.getText());

				ResultSet rst = pstmt.executeQuery();
				while (rst.next()) {
					
					int ID = rst.getInt(1);
					String NME = rst.getString(2);
					int PHNE = rst.getInt(3);
					String USR_NME = rst.getString(4);
					String PSWD = rst.getString(5);
					
					staff = new Staff(ID, NME, PHNE, USR_NME, PSWD);
				}
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
			this.dispose();
			Home h = new Home(staff);
			h.setExtendedState(h.getExtendedState()|JFrame.MAXIMIZED_BOTH);
		};
	}
}
