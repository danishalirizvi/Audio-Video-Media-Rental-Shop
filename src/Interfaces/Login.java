package Interfaces;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import DataAccess.StaffDao;
import Helpers.InputValidation;
import Models.LoginCredential;
import Models.Staff;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private Staff staff;
	private StaffDao staffDao = new StaffDao();
	private JTextField usernameFieldReg;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldReg;
	private JTextField nameFieldReg;
	private JTextField phoneFieldReg;
	private InputValidation inputValidation = new InputValidation();

	public Login() {
		setTitle("Login Active");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(10, 11, 664, 339);
		contentPane.add(panel);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblLogin.setBounds(10, 11, 85, 32);
		panel.add(lblLogin);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblUsername.setBounds(62, 54, 90, 20);
		panel.add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblPassword.setBounds(62, 85, 90, 20);
		panel.add(lblPassword);

		usernameField = new JTextField();
		usernameField.setColumns(10);
		usernameField.setBounds(162, 54, 200, 20);
		panel.add(usernameField);

		JButton btnLogin = new JButton("Login!");
		btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(rootPane, "Fill all the Fields!!!");
				} else {
					LoginCredential credential = new LoginCredential(usernameField.getText(), passwordField.getText());

					staff = staffDao.login(credential);

					if (staff != null) {
						dispose();
						new App(staff);
					} else {
						JOptionPane.showMessageDialog(rootPane, "Invalid Credentials");
					}
				}
			}
		});
		btnLogin.setBounds(272, 116, 90, 23);
		panel.add(btnLogin);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "",

				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(422, 11, 232, 95);
		panel.add(panel_1);

		JLabel label = new JLabel("Ultra-Vision");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Segoe Script", Font.BOLD, 30));
		label.setBounds(6, 16, 220, 49);
		panel_1.add(label);

		JLabel label_1 = new JLabel("Dublin.");
		label_1.setFont(new Font("Segoe Print", Font.PLAIN, 14));
		label_1.setBounds(177, 62, 49, 26);
		panel_1.add(label_1);

		JLabel lblAddNewStaff = new JLabel("Add New Staff Member");
		lblAddNewStaff.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblAddNewStaff.setBounds(257, 147, 146, 17);
		panel.add(lblAddNewStaff);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(35, 175, 581, 152);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblUsernameReg = new JLabel("Username");
		lblUsernameReg.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblUsernameReg.setBounds(54, 11, 83, 14);
		panel_2.add(lblUsernameReg);

		usernameFieldReg = new JTextField();
		usernameFieldReg.setBounds(64, 29, 126, 20);
		panel_2.add(usernameFieldReg);
		usernameFieldReg.setColumns(10);

		JLabel lblPasswordReg = new JLabel("Password");
		lblPasswordReg.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblPasswordReg.setBounds(54, 56, 83, 14);
		panel_2.add(lblPasswordReg);

		JButton btnRegister = new JButton("Register!");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (usernameFieldReg.getText().isEmpty() || passwordFieldReg.getText().isEmpty()
						|| nameFieldReg.getText().isEmpty() || phoneFieldReg.getText().isEmpty()) {
					JOptionPane.showMessageDialog(rootPane, "Fill all Fields");
				} else if (!inputValidation.validateAlphabets(nameFieldReg.getText())
						|| !inputValidation.validateNumbers(phoneFieldReg.getText())) {
					JOptionPane.showMessageDialog(rootPane, "Details are not Valid");
				} else {
					Staff staff = new Staff();
					
					staff.setName(nameFieldReg.getText());
					staff.setPHNE(Long.parseLong(phoneFieldReg.getText()));
					staff.setUsername(usernameFieldReg.getText());
					staff.setPassword(passwordFieldReg.getText());
					
					staffDao.register(staff);
					JOptionPane.showMessageDialog(rootPane, "Registered Successfully");
					nameFieldReg.setText(null);
					phoneFieldReg.setText(null);
					usernameFieldReg.setText(null);
					passwordFieldReg.setText(null);
				}
			}
		});
		btnRegister.setBounds(244, 118, 89, 23);
		panel_2.add(btnRegister);

		passwordFieldReg = new JPasswordField();
		passwordFieldReg.setBounds(64, 73, 126, 20);
		panel_2.add(passwordFieldReg);

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblName.setBounds(370, 11, 83, 14);
		panel_2.add(lblName);

		nameFieldReg = new JTextField();
		nameFieldReg.setColumns(10);
		nameFieldReg.setBounds(380, 29, 126, 20);
		panel_2.add(nameFieldReg);

		JLabel lblPhoneReg = new JLabel("Phone");
		lblPhoneReg.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblPhoneReg.setBounds(370, 56, 83, 14);
		panel_2.add(lblPhoneReg);

		phoneFieldReg = new JTextField();
		phoneFieldReg.setBounds(380, 73, 126, 20);
		panel_2.add(phoneFieldReg);
		phoneFieldReg.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(162, 87, 200, 20);
		panel.add(passwordField);

		setVisible(true);
	}
}
