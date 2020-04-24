package Interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DataAccess.StaffDao;
import Models.LoginCredential;
import Models.Staff;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField, passwordField;
	private Staff staff;
	private StaffDao staffDao = new StaffDao();
	
	public Login() {
		setTitle("Login Active");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(10, 11, 414, 239);
		contentPane.add(panel);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(10, 11, 46, 14);
		panel.add(lblLogin);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(80, 61, 60, 14);
		panel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(80, 93, 60, 14);
		panel.add(lblPassword);
		
		usernameField = new JTextField();
		usernameField.setColumns(10);
		usernameField.setBounds(150, 58, 150, 20);
		panel.add(usernameField);
		
		passwordField = new JTextField();
		passwordField.setColumns(10);
		passwordField.setBounds(150, 90, 150, 20);
		panel.add(passwordField);
		
		JButton btnLogin = new JButton("Login!");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(rootPane, "Fill all the Fields!!!");
				}
				else {
					LoginCredential credential = new LoginCredential(usernameField.getText(), passwordField.getText());
			
					staff = staffDao.login(credential);
									
					if(staff != null) {
						dispose();
						new App(staff);
					}
					else {
						JOptionPane.showMessageDialog(rootPane, "Invalid Credentials");
					}
				}
			}
		});
		btnLogin.setBounds(211, 121, 89, 23);
		panel.add(btnLogin);
		
		setVisible(true);
	}

}
