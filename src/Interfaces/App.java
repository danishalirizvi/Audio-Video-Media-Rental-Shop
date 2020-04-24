package Interfaces;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DataAccess.CustomerDao;
import Helpers.InputValidation;
import Models.Customer;
import Models.Staff;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class App extends JFrame {

	private JPanel contentPane, Home, UserDetails, RegisterCustomer;
	private JLayeredPane layeredPane;
	private JLabel lblHome, lblActiveUser, lblActiveStaffName, lblRegisterCustomer, lblName, lblEmail, lblPhone, lblDebitcreditCard, lblAccessLevel, lblSubscriptionType;;
	private JButton btnSearchCustomer, btnRegisterCustomer;
	private Staff ActiveStaff;
	private JTextField searchField, nameField, emailField, phoneField, cardNumberField;
	private InputValidation inputValidation;
	JComboBox accesslvlCombo,subscriptionCombo;
	Customer customer = new Customer(0);
	CustomerDao customerDao = new CustomerDao();
	private JButton btnGoToHome;
	
	
	public void switchPanels(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
	
	public void InstanciateApp(Staff staff) {
		ActiveStaff = staff;
		inputValidation = new InputValidation();
		setTitle("Ultra-Vision");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 11, 414, 239);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
	}
	
	public void HomeGUI() {
		Home = new JPanel();
		layeredPane.add(Home, "name_2178593783278100");
		Home.setLayout(null);
		
		lblHome = new JLabel("Home");
		lblHome.setBounds(10, 11, 46, 14);
		Home.add(lblHome);
		
		searchField = new JTextField();
		searchField.setBounds(10, 103, 394, 20);
		Home.add(searchField);
		searchField.setColumns(10);
		
		btnSearchCustomer = new JButton("Search Customer");
		btnSearchCustomer.setBounds(10, 134, 115, 23);
		Home.add(btnSearchCustomer);
		
		btnRegisterCustomer = new JButton("Register Customer");
		btnRegisterCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanels(RegisterCustomer);
			}
		});
		btnRegisterCustomer.setBounds(10, 168, 121, 23);
		Home.add(btnRegisterCustomer);
		
		UserDetails = new JPanel();
		UserDetails.setLayout(null);
		UserDetails.setBounds(140, 11, 264, 72);
		Home.add(UserDetails);
		
		JButton btnLogout = new JButton("Logout!");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActiveStaff = null;
				dispose();
				new Login();
			}
		});
		btnLogout.setBounds(75, 44, 100, 23);
		UserDetails.add(btnLogout);
		
		lblActiveUser = new JLabel("Active User");
		lblActiveUser.setBounds(10, 11, 70, 14);
		UserDetails.add(lblActiveUser);
		
		lblActiveStaffName = new JLabel("User Not Found!");
		lblActiveStaffName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblActiveStaffName.setBounds(90, 11, 164, 22);
		UserDetails.add(lblActiveStaffName);
		
		lblActiveStaffName.setText(ActiveStaff.getName());
		
	}

	public void RegisterCustomerGUI() {
		RegisterCustomer = new JPanel();
		layeredPane.add(RegisterCustomer, "name_2221718511575500");
		RegisterCustomer.setLayout(null);
		
		lblRegisterCustomer = new JLabel("Register Customer");
		lblRegisterCustomer.setBounds(10, 11, 89, 14);
		RegisterCustomer.add(lblRegisterCustomer);
		
		lblName = new JLabel("Name");
		lblName.setBounds(20, 41, 46, 14);
		RegisterCustomer.add(lblName);
		
		lblEmail = new JLabel("Email");
		lblEmail.setBounds(20, 67, 46, 14);
		RegisterCustomer.add(lblEmail);
		
		lblPhone = new JLabel("Phone");
		lblPhone.setBounds(20, 92, 46, 14);
		RegisterCustomer.add(lblPhone);
		
		lblDebitcreditCard = new JLabel("Debit/Credit Card #");
		lblDebitcreditCard.setBounds(20, 117, 95, 14);
		RegisterCustomer.add(lblDebitcreditCard);
		
		lblAccessLevel = new JLabel("Access Level");
		lblAccessLevel.setBounds(20, 142, 61, 14);
		RegisterCustomer.add(lblAccessLevel);
		
		lblSubscriptionType = new JLabel("Subscription Type");
		lblSubscriptionType.setBounds(20, 167, 85, 14);
		RegisterCustomer.add(lblSubscriptionType);
		
		nameField = new JTextField();
		nameField.setBounds(125, 41, 200, 20);
		RegisterCustomer.add(nameField);
		nameField.setColumns(10);
		
		emailField = new JTextField();
		emailField.setBounds(125, 67, 200, 20);
		RegisterCustomer.add(emailField);
		emailField.setColumns(10);
		
		phoneField = new JTextField();
		phoneField.setText("");
		phoneField.setBounds(125, 92, 200, 20);
		RegisterCustomer.add(phoneField);
		phoneField.setColumns(10);
		
		cardNumberField = new JTextField();
		cardNumberField.setText("");
		cardNumberField.setBounds(125, 117, 200, 20);
		RegisterCustomer.add(cardNumberField);
		cardNumberField.setColumns(10);
		
		JButton btnRegister = new JButton("Register!");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(		
						nameField.getText().isEmpty()|| 
						emailField.getText().isEmpty() || 
						phoneField.getText().isEmpty() || 
						cardNumberField.getText().isEmpty() || 
						accesslvlCombo.getSelectedIndex() == 0 || 
						subscriptionCombo.getSelectedIndex() == 0
						) 
				{
					JOptionPane.showMessageDialog(rootPane, "Fill all the Fields!!!");
				}
				else if(!inputValidation.validateAlphabets(nameField.getText()) || !inputValidation.validateEmail(emailField.getText()) || !inputValidation.validateNumbers(phoneField.getText()) || !inputValidation.validateNumbers(cardNumberField.getText()))
				{
					JOptionPane.showMessageDialog(rootPane, "Details are not Valid!!!");
				}
				else {
					
					customer.setNME(nameField.getText());
					customer.setEMAIL(emailField.getText());
					customer.setPHNE(Integer.parseInt(phoneField.getText()));
					customer.setACC_CRD(Integer.parseInt(cardNumberField.getText()));

					customerDao.registerCustomer(customer);
					
					JOptionPane.showMessageDialog(rootPane, "Success");
					System.out.println(customer.toString());
				}
			}
		});
		btnRegister.setBounds(315, 205, 89, 23);
		RegisterCustomer.add(btnRegister);
		
		String[] accesslvl = {"--Select Access Level Type--","Music Lovers","Premium","TV Lover","Video Lovers"}; 
		accesslvlCombo = new JComboBox(accesslvl);
		accesslvlCombo.setBounds(125, 139, 200, 20);
		RegisterCustomer.add(accesslvlCombo);
		accesslvlCombo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				customer.setACCS_LVL(accesslvlCombo.getSelectedItem().toString());
			}
		});
		
		
		String[] subscription = {"--Select Subscription Type--","Basic","Delux","Premium","Standard"}; 
		subscriptionCombo = new JComboBox(subscription);
		subscriptionCombo.setBounds(125, 164, 200, 20);
		RegisterCustomer.add(subscriptionCombo);
		
		btnGoToHome = new JButton("Go to Home");
		btnGoToHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanels(Home);
			}
		});
		btnGoToHome.setBounds(315, 7, 89, 23);
		RegisterCustomer.add(btnGoToHome);
		subscriptionCombo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				customer.setSBSC(subscriptionCombo.getSelectedItem().toString());
			}
		});
		
	}
	
	public App(Staff staff) {
		
		InstanciateApp(staff);

		HomeGUI();
		
		RegisterCustomerGUI();
		
		setVisible(true);
	}
}
