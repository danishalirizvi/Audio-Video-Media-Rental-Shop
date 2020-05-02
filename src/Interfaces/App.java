package Interfaces;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.DefaultEditorKit.CutAction;

import DataAccess.ComboBoxData;
import DataAccess.CustomerDao;
import DataAccess.ProductDao;
import DataAccess.TransactionDao;
import Helpers.Adapter;
import Helpers.InputValidation;
import Models.Customer;
import Models.Product;
import Models.Staff;
import Models.Transaction;
import net.proteanit.sql.DbUtils;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelListener;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class App extends JFrame {

	private JPanel contentPane;

	private JLayeredPane layeredPane;

	// Home
	private JPanel Home, UserDetailsPanelHome, LogoPanelHome;
	private JLabel lblHome, lblActiveUserHome, lblActiveStaffNameHome, lblLogoNameHome, lblLogoCityNameHome;
	private JButton btnSearchCustomerHome, btnRegisterCustomerHome, btnSearchTitleHome, btnAddTitleHome, btnLogoutHome;
	private JTextField searchFieldHome;

	// RegisterCustomer
	private JPanel RegisterCustomer, CustDetailsPanelRegCust, LogoPanelRegCust;
	private JLabel lblRegisterCustomer, lblNameRegCust, lblEmailRegCust, lblPhoneRegCust, lblDebitcreditCardRegCust,
			lblAccessLevelRegCust, lblSubscriptionTypeRegCust, lblCustomerDetailsRegCust, lblLogoNameRegCust,
			lblLogoCityNameRegCust;
	private JButton btnGoToHomeRegCust, btnRegisterRegCust;
	private JTextField nameFieldRegCust, emailFieldRegCust, phoneFieldRegCust, cardNumberFieldRegCust;
	private JComboBox accesslvlComboRegCust, subscriptionComboRegCust;

	// Search Customer Panel
	private JPanel SearchCustomer, CustTablePanelSrchCust, LogoPanelSrchCust;
	private JLabel lblSearchResultsSrchCust, lblLogoNameSrchCust, lblLogoCityNameSrchCust, lblSelectedCustomerSrchCust,
			lblSelectedCustNameSrchCust;
	private JButton btnGoToHomeSrchCust, btnEditProfileSrchCust, btnViewProfileSrchCust;
	private JTable CustTableSrchCust;
	private JScrollPane CustScrollPaneSrchCust;

	// Search Title
	private JPanel SearchTitle, TitleTablePanelSrchTitle, LogoPanelSrchTitle;
	private JLabel lblSearchTitleSrchTitle, lblLogoNameSrchTitle, lblLogoCityNameSrchTitle;
	private JButton btnGoToHomeSrchTitle;
	private JScrollPane TitleScrollPaneSrchTitle;
	private JTable TitleTableSrchTitle;

	// Add Title
	private JPanel AddTitle, GenrePanelAddTitle, DirectorPanelAddTitle, TitlePanelAddTitle, TitleTypePanelAddTitle,
			ReleaseYearPanelAddTitle, QuantityPanelAddTitle, DescriptionPanelAddTitle, TitleDetailsPanelAddTitle,
			ManufacturerPanelAddTitle, ModelPanelAddTitle, BandPanelAddTitle, OrganiserPanelAddTitle,
			FormatTypePanelAddTitle, LogoPanelAddTitle;;
	private JLabel lblAddTitle, lblGenre, lblTitle, lblLogoNameAddTitle, lblLogoCityNameAddTitle, lblQuantity, lblModel,
			lblManufacturer, lblBand, lblOrganiser, lblFormatType, lblDirector, lblTitleDetails, lblTitleType;;
	private JButton btnAddAddTitle, btnGoToHomeAddTitle, btnResetFieldsAddTitle;
	private JTextField titleField, yearField, genreField, directorField, quantityField, modelField, manufacturerField,
			bandField, organiserField;
	private JComboBox<String> FormatTypeCombo, TitleTypeCombo;

	// Edit Customer
	private JPanel EditCustomer, LogoPanelEditCust, CustomerDetailsPanelEditCust;
	private JLabel lblEditCustomer, lblNameEditCust, lblEmailEditCust, lblPhoneEditCust, lblDebitcreditCardEditCust,
			lblAccessLevelEditCust, lblSubscriptionEditCust, lblLogoNameEditCust, lblLogoCityNameEditCust,
			lblCustomerDetailsEditCust;
	private JTextField nameFieldEditCust, emailFieldEditCust, phoneFieldEditCust, cardNumberFieldEditCust;
	private JComboBox accesslvlComboEditCust, subscriptionComboEditCust;
	private JButton btnUpdateEditCust, btnGoToHomeEditCust;

	// Profile
	private JPanel Profile, LogoPanelProfile, CustDetailsPanelProfile;
	private JLabel lblProfile, lblNameProfile, lblEmailProfile, lblPhoneProfile, lblDebitcreditCardProfile,
			lblLoyaltyPointsProfile, lblAccessLevelProfile, lblSubscriptionTypeProfile, lblNameCustProfile,
			lblEmailCustProfile, lblDebitcreditCardCustProfile, lblPhoneCustProfile, lblSubscriptionTypeCustProfile,
			lblAccessLevelCustProfile, lblLoyaltyPointsCustProfile, lblIdProfile, lblidCustProfile, lblLogoNameProfile,
			lblLogoCityNameProfile, lblCustomerDetailsProfile;
	private JButton btnIssueNewRentalProfile, btnViewIssuedRentalsProfile, btnGoToHomeProfile, btnRedeemLoyaltyPoints;

	// Issue Rental
	private JPanel IssueRental, LogoPanelIssueRental, TitleTablePanelIssueRental;
	private JLabel lblIssueRental, lblNameIssueRental, lblLogoNameIssueRental, lblLogoCityNameIssueRental,
			lblSelectedTitleIssueRental, lblSelectedTitleNameIssueRental, lblCustomerNameIssueRental;
	private JScrollPane TitleTableScrollPaneIssueRental;
	private JTable TitleTableIssueRental;
	private JTextField searchTitleFieldIssueRental;
	private JButton btnSearchTitleIssueRental, btnIssueIssueRental, btnGoBackProfileIssueRental;

	// Issued Rentals
	private JPanel IssuedRentals, RentalsTablePanelIssuedRentals, LogoPanelIssuedRentals;
	private JLabel lblIssuedRentals, lblCustomerIssuedRentals, lblCustomerNameIssuedRentals,
			lblSelectedRentalIssuedRentals, lblSelectedRentalNameIssuedRentals;
	private JButton btnGoBackIssuedRentals, btnReturnRentalIssuedRentals;
	private JScrollPane RentalsScrollPaneIssuedRentals;
	private JTable RentalsTableIssuedRentals;
	private JLabel lblLogoNameIssuedRentals, lblLogoCityNameIssuedRentals;

	// Other
	private Staff ActiveStaff;
	private Customer customer;

	private InputValidation inputValidation;
	private Adapter adapter = new Adapter();

	private CustomerDao customerDao = new CustomerDao();
	private ProductDao productDao = new ProductDao();
	private TransactionDao transactionDao = new TransactionDao();

	private TableModel model;

	// Initializations
	String[] accesslvl = { "--Select Access Level Type--", "Music Lovers", "Premium", "TV Lover", "Video Lovers" };
	String[] subscription = { "--Select Subscription Type--", "Basic", "Delux", "Premium", "Standard" };

	int index = -1;

	boolean redeemFlag = false;

	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private JLabel lblCantIssue;
	private JLabel lblLimitReached;
	private JPanel warningPanelIssueRental;

	private void abc(JTable table) {
		table.setModel(new DefaultTableModel() {

		    @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		});
		
	}
	
	public void switchPanels(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}

	public void InstanciateApp(Staff staff) {
		ActiveStaff = staff;
		customer = new Customer();
		inputValidation = new InputValidation();
		setTitle("Ultra-Vision");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 11, 674, 349);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
	}

	public void HomeGUI() {
		Home = new JPanel();
		Home.setBorder(null);
		layeredPane.add(Home, "name_2178593783278100");
		Home.setLayout(null);

		lblHome = new JLabel("Home");
		lblHome.setHorizontalAlignment(SwingConstants.CENTER);
		lblHome.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblHome.setToolTipText("Home Page");
		lblHome.setBounds(10, 11, 75, 31);
		Home.add(lblHome);

		searchFieldHome = new JTextField();
		searchFieldHome.setBounds(177, 160, 300, 30);
		Home.add(searchFieldHome);
		searchFieldHome.setColumns(10);

		btnSearchCustomerHome = new JButton("Search Customer");
		btnSearchCustomerHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (!searchFieldHome.getText().isEmpty()) {
						if (!customerDao.getCustomers(searchFieldHome.getText()).next()) {
							JOptionPane.showMessageDialog(rootPane, "No User Found");
						} else {
							
							
							
//							abc(CustTableSrchCust);
							TableModel tM = DbUtils.resultSetToTableModel(customerDao.getCustomers(searchFieldHome.getText()));
							
							tM.isCellEditable(1, 1);
							CustTableSrchCust.setModel(tM);
							
							
							
							
							
							searchFieldHome.setText(null);
							lblSelectedCustomerSrchCust.setVisible(false);
							lblSelectedCustNameSrchCust.setVisible(false);
							lblSelectedCustNameSrchCust.setText(null);
							index = -1;
							btnEditProfileSrchCust.setVisible(false);
							btnViewProfileSrchCust.setVisible(false);
							switchPanels(SearchCustomer);
						}
					} else {
						JOptionPane.showMessageDialog(rootPane, "Search Bar Empty!!!");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnSearchCustomerHome.setBounds(137, 234, 150, 30);
		Home.add(btnSearchCustomerHome);

		btnRegisterCustomerHome = new JButton("Register Customer");
		btnRegisterCustomerHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanels(RegisterCustomer);
			}
		});
		btnRegisterCustomerHome.setBounds(137, 275, 150, 30);
		Home.add(btnRegisterCustomerHome);

		UserDetailsPanelHome = new JPanel();
		UserDetailsPanelHome.setLayout(null);
		UserDetailsPanelHome.setBounds(504, 11, 160, 80);
		Home.add(UserDetailsPanelHome);

		btnLogoutHome = new JButton("Logout!");
		btnLogoutHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActiveStaff = null;
				dispose();
				new Login();
			}
		});
		btnLogoutHome.setBounds(59, 51, 100, 23);
		UserDetailsPanelHome.add(btnLogoutHome);

		lblActiveUserHome = new JLabel("Active User");
		lblActiveUserHome.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblActiveUserHome.setBounds(89, 11, 70, 14);
		UserDetailsPanelHome.add(lblActiveUserHome);

		lblActiveStaffNameHome = new JLabel("User Not Found!");
		lblActiveStaffNameHome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblActiveStaffNameHome.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblActiveStaffNameHome.setBounds(10, 25, 149, 22);
		UserDetailsPanelHome.add(lblActiveStaffNameHome);

		lblActiveStaffNameHome.setText(ActiveStaff.getName());

		btnSearchTitleHome = new JButton("Search Title");
		btnSearchTitleHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!searchFieldHome.getText().isEmpty()) {
						if (!productDao.getProducts(searchFieldHome.getText()).next()) {
							JOptionPane.showMessageDialog(rootPane, "No Title Found");
						} else {
							TitleTableSrchTitle.setModel(
									DbUtils.resultSetToTableModel(productDao.getProducts(searchFieldHome.getText())));
							switchPanels(SearchTitle);
							searchFieldHome.setText(null);
							index = -1;
						}
					} else {
						JOptionPane.showMessageDialog(rootPane, "Search Bar Empty!!!");
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnSearchTitleHome.setBounds(363, 234, 150, 30);
		Home.add(btnSearchTitleHome);

		btnAddTitleHome = new JButton("Add Title");
		btnAddTitleHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(AddTitle);
			}
		});
		btnAddTitleHome.setBounds(363, 275, 150, 30);
		Home.add(btnAddTitleHome);

		LogoPanelHome = new JPanel();
		LogoPanelHome.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		LogoPanelHome.setBounds(211, 34, 232, 95);
		Home.add(LogoPanelHome);
		LogoPanelHome.setLayout(null);

		lblLogoNameHome = new JLabel("Ultra-Vision");
		lblLogoNameHome.setBounds(6, 16, 220, 49);
		LogoPanelHome.add(lblLogoNameHome);
		lblLogoNameHome.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogoNameHome.setFont(new Font("Segoe Script", Font.BOLD, 30));

		lblLogoCityNameHome = new JLabel("Dublin.");
		lblLogoCityNameHome.setBounds(177, 62, 49, 26);
		LogoPanelHome.add(lblLogoCityNameHome);
		lblLogoCityNameHome.setFont(new Font("Segoe Print", Font.PLAIN, 14));

	}

	public void RegisterCustomerGUI() {
		RegisterCustomer = new JPanel();
		layeredPane.add(RegisterCustomer, "name_2221718511575500");
		RegisterCustomer.setLayout(null);

		lblRegisterCustomer = new JLabel("Register Customer");
		lblRegisterCustomer.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblRegisterCustomer.setBounds(10, 11, 220, 31);
		RegisterCustomer.add(lblRegisterCustomer);

		CustDetailsPanelRegCust = new JPanel();
		CustDetailsPanelRegCust.setBounds(30, 76, 400, 250);
		RegisterCustomer.add(CustDetailsPanelRegCust);
		CustDetailsPanelRegCust.setLayout(null);

		lblCustomerDetailsRegCust = new JLabel("Customer Details");
		lblCustomerDetailsRegCust.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblCustomerDetailsRegCust.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustomerDetailsRegCust.setBounds(130, 11, 140, 20);
		CustDetailsPanelRegCust.add(lblCustomerDetailsRegCust);

		lblNameRegCust = new JLabel("Name");
		lblNameRegCust.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNameRegCust.setBounds(35, 67, 120, 20);
		CustDetailsPanelRegCust.add(lblNameRegCust);

		lblEmailRegCust = new JLabel("Email");
		lblEmailRegCust.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblEmailRegCust.setBounds(35, 98, 120, 20);
		CustDetailsPanelRegCust.add(lblEmailRegCust);

		lblPhoneRegCust = new JLabel("Phone");
		lblPhoneRegCust.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblPhoneRegCust.setBounds(35, 129, 120, 20);
		CustDetailsPanelRegCust.add(lblPhoneRegCust);

		lblDebitcreditCardRegCust = new JLabel("Debit/Credit Card #");
		lblDebitcreditCardRegCust.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblDebitcreditCardRegCust.setBounds(35, 160, 120, 20);
		CustDetailsPanelRegCust.add(lblDebitcreditCardRegCust);

		lblAccessLevelRegCust = new JLabel("Access Level");
		lblAccessLevelRegCust.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblAccessLevelRegCust.setBounds(35, 191, 120, 20);
		CustDetailsPanelRegCust.add(lblAccessLevelRegCust);

		lblSubscriptionTypeRegCust = new JLabel("Subscription Type");
		lblSubscriptionTypeRegCust.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblSubscriptionTypeRegCust.setBounds(35, 219, 120, 20);
		CustDetailsPanelRegCust.add(lblSubscriptionTypeRegCust);

		nameFieldRegCust = new JTextField();
		nameFieldRegCust.setBounds(165, 68, 200, 20);
		CustDetailsPanelRegCust.add(nameFieldRegCust);
		nameFieldRegCust.setColumns(10);

		emailFieldRegCust = new JTextField();
		emailFieldRegCust.setBounds(165, 99, 200, 20);
		CustDetailsPanelRegCust.add(emailFieldRegCust);
		emailFieldRegCust.setColumns(10);

		phoneFieldRegCust = new JTextField();
		phoneFieldRegCust.setBounds(165, 129, 200, 20);
		CustDetailsPanelRegCust.add(phoneFieldRegCust);
		phoneFieldRegCust.setText("");
		phoneFieldRegCust.setColumns(10);

		cardNumberFieldRegCust = new JTextField();
		cardNumberFieldRegCust.setBounds(165, 161, 200, 20);
		CustDetailsPanelRegCust.add(cardNumberFieldRegCust);
		cardNumberFieldRegCust.setText("");
		cardNumberFieldRegCust.setColumns(10);

		accesslvlComboRegCust = new JComboBox(accesslvl);
		accesslvlComboRegCust.setBounds(165, 192, 200, 20);
		CustDetailsPanelRegCust.add(accesslvlComboRegCust);

		subscriptionComboRegCust = new JComboBox(subscription);
		subscriptionComboRegCust.setBounds(165, 220, 200, 20);
		CustDetailsPanelRegCust.add(subscriptionComboRegCust);
//		subscriptionComboRegCust.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				customer.setSBSC(subscriptionComboRegCust.getSelectedItem().toString());
//			}
//		});
//		accesslvlComboRegCust.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				customer.setACCS_LVL(accesslvlComboRegCust.getSelectedItem().toString());
//			}
//		});

		btnRegisterRegCust = new JButton("Register!");
		btnRegisterRegCust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (nameFieldRegCust.getText().isEmpty() || emailFieldRegCust.getText().isEmpty()
						|| phoneFieldRegCust.getText().isEmpty() || cardNumberFieldRegCust.getText().isEmpty()
						|| accesslvlComboRegCust.getSelectedIndex() == 0
						|| subscriptionComboRegCust.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(rootPane, "Fill all the Fields!!!");
				} else if (!inputValidation.validateAlphabets(nameFieldRegCust.getText())
						|| !inputValidation.validateEmail(emailFieldRegCust.getText())
						|| !inputValidation.validateNumbers(phoneFieldRegCust.getText())
						|| !inputValidation.validateNumbers(cardNumberFieldRegCust.getText())) {
					JOptionPane.showMessageDialog(rootPane, "Details are not Valid!!!");
				} else {

					customer.setNME(nameFieldRegCust.getText());
					customer.setEMAIL(emailFieldRegCust.getText());
					customer.setPHNE(Long.parseLong(phoneFieldRegCust.getText()));
					customer.setACC_CRD(Long.parseLong(cardNumberFieldRegCust.getText()));
					customer.setLYLTY_PNTS(0);
					customer.setACCS_LVL(accesslvlComboRegCust.getSelectedItem().toString());
					customer.setSBSC(subscriptionComboRegCust.getSelectedItem().toString());

					customerDao.registerCustomer(customer);

					JOptionPane.showMessageDialog(rootPane, "Success");
					customer = new Customer();
					nameFieldRegCust.setText(null);
					emailFieldRegCust.setText(null);
					phoneFieldRegCust.setText(null);
					cardNumberFieldRegCust.setText(null);
					accesslvlComboRegCust.setSelectedIndex(0);
					subscriptionComboRegCust.setSelectedIndex(0);
				}
			}
		});
		btnRegisterRegCust.setBounds(486, 303, 144, 23);
		RegisterCustomer.add(btnRegisterRegCust);

		btnGoToHomeRegCust = new JButton("Go to Home");
		btnGoToHomeRegCust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nameFieldRegCust.setText(null);
				emailFieldRegCust.setText(null);
				phoneFieldRegCust.setText(null);
				cardNumberFieldRegCust.setText(null);
				accesslvlComboRegCust.setSelectedIndex(0);
				subscriptionComboRegCust.setSelectedIndex(0);
				searchFieldHome.setText(null);
				switchPanels(Home);
			}
		});
		btnGoToHomeRegCust.setBounds(486, 269, 144, 23);
		RegisterCustomer.add(btnGoToHomeRegCust);

		LogoPanelRegCust = new JPanel();
		LogoPanelRegCust.setLayout(null);
		LogoPanelRegCust.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		LogoPanelRegCust.setBounds(432, 11, 232, 95);
		RegisterCustomer.add(LogoPanelRegCust);

		lblLogoNameRegCust = new JLabel("Ultra-Vision");
		lblLogoNameRegCust.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogoNameRegCust.setFont(new Font("Segoe Script", Font.BOLD, 30));
		lblLogoNameRegCust.setBounds(6, 16, 220, 49);
		LogoPanelRegCust.add(lblLogoNameRegCust);

		lblLogoCityNameRegCust = new JLabel("Dublin.");
		lblLogoCityNameRegCust.setFont(new Font("Segoe Print", Font.PLAIN, 14));
		lblLogoCityNameRegCust.setBounds(177, 62, 49, 26);
		LogoPanelRegCust.add(lblLogoCityNameRegCust);

	}

	public void SearchCustomerGUI() {
		SearchCustomer = new JPanel();
		layeredPane.add(SearchCustomer, "name_2324474656071100");
		SearchCustomer.setLayout(null);

		lblSearchResultsSrchCust = new JLabel("Search Results");
		lblSearchResultsSrchCust.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblSearchResultsSrchCust.setBounds(10, 11, 220, 40);
		SearchCustomer.add(lblSearchResultsSrchCust);

		CustTablePanelSrchCust = new JPanel();
		CustTablePanelSrchCust.setBounds(10, 130, 534, 208);
		SearchCustomer.add(CustTablePanelSrchCust);
		CustTablePanelSrchCust.setLayout(null);

		CustScrollPaneSrchCust = new JScrollPane();
		CustScrollPaneSrchCust.setBounds(0, 0, 534, 208);
		CustTablePanelSrchCust.add(CustScrollPaneSrchCust);

		CustTableSrchCust = new JTable();
		CustScrollPaneSrchCust.setViewportView(CustTableSrchCust);
		CustTableSrchCust.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				index = CustTableSrchCust.getSelectedRow();
				model = CustTableSrchCust.getModel();
				btnEditProfileSrchCust.setVisible(true);
				btnViewProfileSrchCust.setVisible(true);
				lblSelectedCustomerSrchCust.setVisible(true);
				lblSelectedCustNameSrchCust.setVisible(true);
				lblSelectedCustNameSrchCust.setText(model.getValueAt(index, 1).toString());

				System.out.println(model.getValueAt(index, 0).toString());
				
				customer.setID(Integer.parseInt(model.getValueAt(index, 0).toString()));
				customer.setNME(model.getValueAt(index, 1).toString());
				customer.setEMAIL(model.getValueAt(index, 2).toString());
				customer.setPHNE(Long.parseLong(model.getValueAt(index, 3).toString()));
				customer.setACC_CRD(Long.parseLong(model.getValueAt(index, 4).toString()));
				customer.setLYLTY_PNTS(Integer.parseInt(model.getValueAt(index, 5).toString()));
				customer.setACCS_LVL(model.getValueAt(index, 6).toString());
				customer.setSBSC(model.getValueAt(index, 7).toString());

			}
		});

		btnGoToHomeSrchCust = new JButton("Go to Home");
		btnGoToHomeSrchCust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(Home);
			}
		});
		btnGoToHomeSrchCust.setBounds(554, 191, 110, 23);
		SearchCustomer.add(btnGoToHomeSrchCust);

		btnEditProfileSrchCust = new JButton("Edit Profile");
		btnEditProfileSrchCust.setVisible(false);
		btnEditProfileSrchCust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (index != -1) {

					nameFieldEditCust.setText(customer.getNME());
					emailFieldEditCust.setText(customer.getEMAIL());
					phoneFieldEditCust.setText(String.valueOf(customer.getPHNE()));
					cardNumberFieldEditCust.setText(String.valueOf(customer.getACC_CRD()));
					accesslvlComboEditCust.setSelectedItem(customer.getACCS_LVL());
					subscriptionComboEditCust.setSelectedItem(customer.getSBSC());

					switchPanels(EditCustomer);

					btnEditProfileSrchCust.setText("Edit Customer");
					btnEditProfileSrchCust.setVisible(false);
					btnViewProfileSrchCust.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(rootPane, "Click on a row to Select");
				}
			}
		});
		btnEditProfileSrchCust.setBounds(554, 225, 110, 23);
		SearchCustomer.add(btnEditProfileSrchCust);

		btnViewProfileSrchCust = new JButton("View Profile");
		btnViewProfileSrchCust.setVisible(false);
		btnViewProfileSrchCust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (customer.getLYLTY_PNTS() >= 100) {
					btnRedeemLoyaltyPoints.setVisible(true);
				} else {
					btnRedeemLoyaltyPoints.setVisible(false);
				}
				lblidCustProfile.setText(String.valueOf(customer.getID()));
				lblNameCustProfile.setText(customer.getNME());
				lblEmailCustProfile.setText(customer.getEMAIL());
				lblPhoneCustProfile.setText(String.valueOf(customer.getPHNE()));
				lblDebitcreditCardCustProfile.setText(String.valueOf(customer.getACC_CRD()));
				lblLoyaltyPointsCustProfile.setText(String.valueOf(customer.getLYLTY_PNTS()));
				lblAccessLevelCustProfile.setText(customer.getACCS_LVL());
				lblSubscriptionTypeCustProfile.setText(customer.getSBSC());
				switchPanels(Profile);
			}
		});
		btnViewProfileSrchCust.setBounds(554, 259, 110, 23);
		SearchCustomer.add(btnViewProfileSrchCust);

		LogoPanelSrchCust = new JPanel();
		LogoPanelSrchCust.setLayout(null);
		LogoPanelSrchCust.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		LogoPanelSrchCust.setBounds(432, 11, 232, 95);
		SearchCustomer.add(LogoPanelSrchCust);

		lblLogoNameSrchCust = new JLabel("Ultra-Vision");
		lblLogoNameSrchCust.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogoNameSrchCust.setFont(new Font("Segoe Script", Font.BOLD, 30));
		lblLogoNameSrchCust.setBounds(6, 16, 220, 49);
		LogoPanelSrchCust.add(lblLogoNameSrchCust);

		lblLogoCityNameSrchCust = new JLabel("Dublin.");
		lblLogoCityNameSrchCust.setFont(new Font("Segoe Print", Font.PLAIN, 14));
		lblLogoCityNameSrchCust.setBounds(177, 62, 49, 26);
		LogoPanelSrchCust.add(lblLogoCityNameSrchCust);

		lblSelectedCustomerSrchCust = new JLabel("Selected Customer");
		lblSelectedCustomerSrchCust.setVisible(false);
		lblSelectedCustomerSrchCust.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSelectedCustomerSrchCust.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblSelectedCustomerSrchCust.setBounds(36, 92, 115, 14);
		SearchCustomer.add(lblSelectedCustomerSrchCust);

		lblSelectedCustNameSrchCust = new JLabel("");
		lblSelectedCustNameSrchCust.setVisible(false);
		lblSelectedCustNameSrchCust.setVerticalAlignment(SwingConstants.BOTTOM);
		lblSelectedCustNameSrchCust.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblSelectedCustNameSrchCust.setBounds(161, 79, 130, 31);
		SearchCustomer.add(lblSelectedCustNameSrchCust);
	}

	public void SearchTitleGUI() {

		SearchTitle = new JPanel();
		layeredPane.add(SearchTitle, "name_2406822016214400");
		SearchTitle.setLayout(null);

		lblSearchTitleSrchTitle = new JLabel("Search Title");
		lblSearchTitleSrchTitle.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblSearchTitleSrchTitle.setBounds(10, 11, 165, 40);
		SearchTitle.add(lblSearchTitleSrchTitle);

		TitleTablePanelSrchTitle = new JPanel();
		TitleTablePanelSrchTitle.setBounds(10, 130, 534, 208);
		SearchTitle.add(TitleTablePanelSrchTitle);
		TitleTablePanelSrchTitle.setLayout(null);

		TitleScrollPaneSrchTitle = new JScrollPane();
		TitleScrollPaneSrchTitle.setBounds(0, 0, 534, 208);
		TitleTablePanelSrchTitle.add(TitleScrollPaneSrchTitle);

		TitleTableSrchTitle = new JTable();
		TitleScrollPaneSrchTitle.setViewportView(TitleTableSrchTitle);

		btnGoToHomeSrchTitle = new JButton("Go to Home");
		btnGoToHomeSrchTitle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanels(Home);
			}
		});
		btnGoToHomeSrchTitle.setBounds(554, 219, 110, 25);
		SearchTitle.add(btnGoToHomeSrchTitle);

		LogoPanelSrchTitle = new JPanel();
		LogoPanelSrchTitle.setLayout(null);
		LogoPanelSrchTitle.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "",

				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		LogoPanelSrchTitle.setBounds(432, 11, 232, 95);
		SearchTitle.add(LogoPanelSrchTitle);

		lblLogoNameSrchTitle = new JLabel("Ultra-Vision");
		lblLogoNameSrchTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogoNameSrchTitle.setFont(new Font("Segoe Script", Font.BOLD, 30));
		lblLogoNameSrchTitle.setBounds(6, 16, 220, 49);
		LogoPanelSrchTitle.add(lblLogoNameSrchTitle);

		lblLogoCityNameSrchTitle = new JLabel("Dublin.");
		lblLogoCityNameSrchTitle.setFont(new Font("Segoe Print", Font.PLAIN, 14));
		lblLogoCityNameSrchTitle.setBounds(177, 62, 49, 26);
		LogoPanelSrchTitle.add(lblLogoCityNameSrchTitle);

	}

	public void AddTitleGUI() {
		AddTitle = new JPanel();
		layeredPane.add(AddTitle, "name_2409423209601400");
		AddTitle.setLayout(null);

		lblAddTitle = new JLabel("Add Title");
		lblAddTitle.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblAddTitle.setBounds(10, 11, 120, 32);
		AddTitle.add(lblAddTitle);

		LogoPanelAddTitle = new JPanel();
		LogoPanelAddTitle.setLayout(null);
		LogoPanelAddTitle.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "",

				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		LogoPanelAddTitle.setBounds(424, 11, 232, 95);
		AddTitle.add(LogoPanelAddTitle);

		lblLogoNameAddTitle = new JLabel("Ultra-Vision");
		lblLogoNameAddTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogoNameAddTitle.setFont(new Font("Segoe Script", Font.BOLD, 30));
		lblLogoNameAddTitle.setBounds(6, 16, 220, 49);
		LogoPanelAddTitle.add(lblLogoNameAddTitle);

		lblLogoCityNameAddTitle = new JLabel("Dublin.");
		lblLogoCityNameAddTitle.setFont(new Font("Segoe Print", Font.PLAIN, 14));
		lblLogoCityNameAddTitle.setBounds(177, 62, 49, 26);
		LogoPanelAddTitle.add(lblLogoCityNameAddTitle);

		TitleDetailsPanelAddTitle = new JPanel();
		TitleDetailsPanelAddTitle.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		TitleDetailsPanelAddTitle.setBounds(10, 144, 534, 194);
		AddTitle.add(TitleDetailsPanelAddTitle);
		TitleDetailsPanelAddTitle.setLayout(null);

		TitlePanelAddTitle = new JPanel();
		TitlePanelAddTitle.setBounds(5, 5, 248, 21);
		TitleDetailsPanelAddTitle.add(TitlePanelAddTitle);
		TitlePanelAddTitle.setLayout(null);

		lblTitle = new JLabel("Title");
		lblTitle.setBounds(0, 0, 120, 20);
		TitlePanelAddTitle.add(lblTitle);
		lblTitle.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		titleField = new JTextField();
		titleField.setBounds(118, 1, 130, 20);
		TitlePanelAddTitle.add(titleField);
		titleField.setColumns(10);

		DescriptionPanelAddTitle = new JPanel();
		DescriptionPanelAddTitle.setBounds(279, 5, 250, 60);
		TitleDetailsPanelAddTitle.add(DescriptionPanelAddTitle);
		DescriptionPanelAddTitle.setLayout(null);

		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(0, 0, 120, 60);
		DescriptionPanelAddTitle.add(lblDescription);
		lblDescription.setVerticalAlignment(SwingConstants.TOP);
		lblDescription.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		JTextArea dscpText = new JTextArea();
		dscpText.setBounds(120, 0, 130, 60);
		DescriptionPanelAddTitle.add(dscpText);

		ReleaseYearPanelAddTitle = new JPanel();
		ReleaseYearPanelAddTitle.setBounds(5, 44, 250, 21);
		TitleDetailsPanelAddTitle.add(ReleaseYearPanelAddTitle);
		ReleaseYearPanelAddTitle.setLayout(null);

		JLabel lblReleaseYear = new JLabel("Release Year");
		lblReleaseYear.setBounds(0, 0, 120, 20);
		ReleaseYearPanelAddTitle.add(lblReleaseYear);
		lblReleaseYear.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		yearField = new JTextField();
		yearField.setBounds(120, 0, 130, 20);
		ReleaseYearPanelAddTitle.add(yearField);
		yearField.setColumns(10);

		QuantityPanelAddTitle = new JPanel();
		QuantityPanelAddTitle.setBounds(5, 76, 250, 21);
		TitleDetailsPanelAddTitle.add(QuantityPanelAddTitle);
		QuantityPanelAddTitle.setLayout(null);

		lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(0, 0, 120, 20);
		QuantityPanelAddTitle.add(lblQuantity);
		lblQuantity.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		quantityField = new JTextField();
		quantityField.setBounds(120, 0, 130, 20);
		QuantityPanelAddTitle.add(quantityField);
		quantityField.setColumns(10);

		TitleTypePanelAddTitle = new JPanel();
		TitleTypePanelAddTitle.setBounds(279, 76, 250, 21);
		TitleDetailsPanelAddTitle.add(TitleTypePanelAddTitle);
		TitleTypePanelAddTitle.setLayout(null);

		lblTitleType = new JLabel("Title Type");
		lblTitleType.setBounds(0, 0, 120, 20);
		TitleTypePanelAddTitle.add(lblTitleType);
		lblTitleType.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		TitleTypeCombo = new JComboBox<String>(new ComboBoxData().getTitleType());
		TitleTypeCombo.setBounds(120, 0, 130, 20);
		TitleTypePanelAddTitle.add(TitleTypeCombo);
		TitleTypeCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String title = TitleTypeCombo.getSelectedItem().toString();
				if (TitleTypeCombo.getSelectedIndex() == 4) {
					GenrePanelAddTitle.setVisible(true);
					GenrePanelAddTitle.setLocation(5, 108);

					FormatTypePanelAddTitle.setVisible(true);
					FormatTypePanelAddTitle.setLocation(279, 108);

					DirectorPanelAddTitle.setVisible(true);
					DirectorPanelAddTitle.setLocation(145, 137);

					modelField.setText(null);
					manufacturerField.setText(null);
					bandField.setText(null);
					organiserField.setText(null);
					genreField.setText(null);
					directorField.setText(null);
					FormatTypeCombo.setSelectedIndex(0);
					// *********************************
					ManufacturerPanelAddTitle.setVisible(false);
					ModelPanelAddTitle.setVisible(false);
					BandPanelAddTitle.setVisible(false);
					OrganiserPanelAddTitle.setVisible(false);
				} else if (TitleTypeCombo.getSelectedIndex() == 2) {
					GenrePanelAddTitle.setVisible(true);
					GenrePanelAddTitle.setLocation(5, 108);

					FormatTypePanelAddTitle.setVisible(true);
					FormatTypePanelAddTitle.setLocation(279, 108);

					BandPanelAddTitle.setVisible(true);
					BandPanelAddTitle.setLocation(5, 137);

					OrganiserPanelAddTitle.setVisible(true);
					OrganiserPanelAddTitle.setLocation(279, 137);

					modelField.setText(null);
					manufacturerField.setText(null);
					bandField.setText(null);
					organiserField.setText(null);
					genreField.setText(null);
					directorField.setText(null);
					FormatTypeCombo.setSelectedIndex(0);
					// ****************************
					DirectorPanelAddTitle.setVisible(false);
					ManufacturerPanelAddTitle.setVisible(false);
					ModelPanelAddTitle.setVisible(false);
				} else if (TitleTypeCombo.getSelectedIndex() == 3) {
					GenrePanelAddTitle.setVisible(true);
					GenrePanelAddTitle.setLocation(5, 108);

					FormatTypePanelAddTitle.setVisible(true);
					FormatTypePanelAddTitle.setLocation(279, 108);

					DirectorPanelAddTitle.setVisible(true);
					DirectorPanelAddTitle.setLocation(145, 137);

					modelField.setText(null);
					manufacturerField.setText(null);
					bandField.setText(null);
					organiserField.setText(null);
					genreField.setText(null);
					directorField.setText(null);
					FormatTypeCombo.setSelectedIndex(0);
					// **********************************
					BandPanelAddTitle.setVisible(false);
					OrganiserPanelAddTitle.setVisible(false);
					ModelPanelAddTitle.setVisible(false);
					ManufacturerPanelAddTitle.setVisible(false);
				} else if (TitleTypeCombo.getSelectedIndex() == 1) {
					ModelPanelAddTitle.setVisible(true);
					ModelPanelAddTitle.setLocation(5, 108);

					ManufacturerPanelAddTitle.setVisible(true);
					ManufacturerPanelAddTitle.setLocation(279, 108);

					modelField.setText(null);
					manufacturerField.setText(null);
					bandField.setText(null);
					organiserField.setText(null);
					genreField.setText(null);
					directorField.setText(null);
					FormatTypeCombo.setSelectedIndex(0);
					// **********************************
					GenrePanelAddTitle.setVisible(false);
					DirectorPanelAddTitle.setVisible(false);
					OrganiserPanelAddTitle.setVisible(false);
					BandPanelAddTitle.setVisible(false);
					FormatTypePanelAddTitle.setVisible(false);
				} else if (TitleTypeCombo.getSelectedIndex() == 0) {
					GenrePanelAddTitle.setVisible(false);
					DirectorPanelAddTitle.setVisible(false);
					BandPanelAddTitle.setVisible(false);
					OrganiserPanelAddTitle.setVisible(false);
					ModelPanelAddTitle.setVisible(false);
					ManufacturerPanelAddTitle.setVisible(false);
					FormatTypePanelAddTitle.setVisible(false);
					modelField.setText(null);
					manufacturerField.setText(null);
					bandField.setText(null);
					organiserField.setText(null);
					genreField.setText(null);
					directorField.setText(null);
					FormatTypeCombo.setSelectedIndex(0);
				}

			}
		});

		ModelPanelAddTitle = new JPanel();
		ModelPanelAddTitle.setVisible(false);
		ModelPanelAddTitle.setBounds(5, 108, 250, 20);
		TitleDetailsPanelAddTitle.add(ModelPanelAddTitle);
		ModelPanelAddTitle.setLayout(null);

		lblModel = new JLabel("Model");
		lblModel.setBounds(0, 0, 120, 20);
		ModelPanelAddTitle.add(lblModel);
		lblModel.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		modelField = new JTextField();
		modelField.setBounds(120, 0, 130, 20);
		ModelPanelAddTitle.add(modelField);
		modelField.setColumns(10);

		ManufacturerPanelAddTitle = new JPanel();
		ManufacturerPanelAddTitle.setVisible(false);
		ManufacturerPanelAddTitle.setBounds(279, 108, 250, 20);
		TitleDetailsPanelAddTitle.add(ManufacturerPanelAddTitle);
		ManufacturerPanelAddTitle.setLayout(null);

		lblManufacturer = new JLabel("Manufacturer");
		lblManufacturer.setBounds(0, 0, 120, 20);
		ManufacturerPanelAddTitle.add(lblManufacturer);
		lblManufacturer.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		manufacturerField = new JTextField();
		manufacturerField.setBounds(120, 0, 130, 20);
		ManufacturerPanelAddTitle.add(manufacturerField);
		manufacturerField.setColumns(10);

		BandPanelAddTitle = new JPanel();
		BandPanelAddTitle.setVisible(false);
		BandPanelAddTitle.setBounds(5, 137, 250, 20);
		TitleDetailsPanelAddTitle.add(BandPanelAddTitle);
		BandPanelAddTitle.setLayout(null);

		lblBand = new JLabel("Band");
		lblBand.setBounds(0, 0, 120, 20);
		BandPanelAddTitle.add(lblBand);
		lblBand.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		bandField = new JTextField();
		bandField.setBounds(120, 0, 130, 20);
		BandPanelAddTitle.add(bandField);
		bandField.setColumns(10);

		OrganiserPanelAddTitle = new JPanel();
		OrganiserPanelAddTitle.setVisible(false);
		OrganiserPanelAddTitle.setBounds(279, 137, 250, 20);
		TitleDetailsPanelAddTitle.add(OrganiserPanelAddTitle);
		OrganiserPanelAddTitle.setLayout(null);

		lblOrganiser = new JLabel("Organiser");
		lblOrganiser.setBounds(0, 0, 120, 20);
		OrganiserPanelAddTitle.add(lblOrganiser);
		lblOrganiser.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		organiserField = new JTextField();
		organiserField.setBounds(120, 0, 130, 20);
		OrganiserPanelAddTitle.add(organiserField);
		organiserField.setColumns(10);

		GenrePanelAddTitle = new JPanel();
		GenrePanelAddTitle.setVisible(false);
		GenrePanelAddTitle.setBounds(5, 167, 250, 21);
		TitleDetailsPanelAddTitle.add(GenrePanelAddTitle);
		GenrePanelAddTitle.setLayout(null);

		lblGenre = new JLabel("Genre");
		lblGenre.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblGenre.setBounds(0, 0, 120, 20);
		GenrePanelAddTitle.add(lblGenre);

		genreField = new JTextField();
		genreField.setBounds(120, 0, 130, 20);
		GenrePanelAddTitle.add(genreField);
		genreField.setColumns(10);

		DirectorPanelAddTitle = new JPanel();
		DirectorPanelAddTitle.setVisible(false);
		DirectorPanelAddTitle.setBounds(279, 167, 250, 21);
		TitleDetailsPanelAddTitle.add(DirectorPanelAddTitle);
		DirectorPanelAddTitle.setLayout(null);

		lblDirector = new JLabel("Director");
		lblDirector.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblDirector.setBounds(0, 0, 120, 20);
		DirectorPanelAddTitle.add(lblDirector);

		directorField = new JTextField();
		directorField.setBounds(120, 0, 130, 20);
		DirectorPanelAddTitle.add(directorField);
		directorField.setColumns(10);

		FormatTypePanelAddTitle = new JPanel();
		FormatTypePanelAddTitle.setBounds(145, 137, 250, 21);
		TitleDetailsPanelAddTitle.add(FormatTypePanelAddTitle);
		FormatTypePanelAddTitle.setVisible(false);
		FormatTypePanelAddTitle.setLayout(null);

		lblFormatType = new JLabel("Format Type");
		lblFormatType.setBounds(0, 0, 120, 20);
		FormatTypePanelAddTitle.add(lblFormatType);

		FormatTypeCombo = new JComboBox<String>(new ComboBoxData().getFormatType());
		FormatTypeCombo.setBounds(120, 0, 130, 20);
		FormatTypePanelAddTitle.add(FormatTypeCombo);

		btnGoToHomeAddTitle = new JButton("Go To Home");
		btnGoToHomeAddTitle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(Home);
				titleField.setText(null);
				dscpText.setText(null);
				yearField.setText(null);
				TitleTypeCombo.setSelectedIndex(0);
				FormatTypeCombo.setSelectedIndex(0);
				modelField.setText(null);
				manufacturerField.setText(null);
				bandField.setText(null);
				organiserField.setText(null);
				genreField.setText(null);
				directorField.setText(null);
				quantityField.setText(null);
				searchFieldHome.setText(null);
			}
		});
		btnGoToHomeAddTitle.setBounds(554, 229, 110, 23);
		AddTitle.add(btnGoToHomeAddTitle);

		btnAddAddTitle = new JButton("Add!");
		btnAddAddTitle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (titleField.getText().isEmpty() || dscpText.getText().isEmpty()
						|| TitleTypeCombo.getSelectedIndex() == 0 || quantityField.getText().isEmpty()
						|| yearField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(rootPane, "Fill all the Fields!!!");
				} else {
					if (TitleTypeCombo.getSelectedIndex() == 1
							&& (modelField.getText().isEmpty() || manufacturerField.getText().isEmpty())) {
						JOptionPane.showMessageDialog(rootPane, "Fill all the Fields!!!");
					} else if (TitleTypeCombo.getSelectedIndex() == 2
							&& (bandField.getText().isEmpty() || organiserField.getText().isEmpty()
									|| FormatTypeCombo.getSelectedIndex() == 0 || genreField.getText().isEmpty())) {
						JOptionPane.showMessageDialog(rootPane, "Fill all the Fields!!!");
					} else if ((TitleTypeCombo.getSelectedIndex() == 3 || TitleTypeCombo.getSelectedIndex() == 4)
							&& (genreField.getText().isEmpty() || FormatTypeCombo.getSelectedIndex() == 0
									|| directorField.getText().isEmpty())) {
						JOptionPane.showMessageDialog(rootPane, "Fill all the Fields!!!");
					} else {
						if (!inputValidation.validateAlphabets(titleField.getText())
								|| !inputValidation.validateNumbers(yearField.getText())
								|| !inputValidation.validateNumbers(quantityField.getText())) {
							JOptionPane.showMessageDialog(rootPane, "Details are not Valid A");
						} else {
							if (TitleTypeCombo.getSelectedIndex() == 1
									&& (!inputValidation.validateAlphabets(modelField.getText())
											|| !inputValidation.validateAlphabets(manufacturerField.getText()))) {
								JOptionPane.showMessageDialog(rootPane, "Details are not Valid B");
							} else if (TitleTypeCombo.getSelectedIndex() == 2
									&& (!inputValidation.validateAlphabets(bandField.getText())
											|| !inputValidation.validateAlphabets(organiserField.getText())
											|| !inputValidation.validateAlphabets(genreField.getText()))) {
								JOptionPane.showMessageDialog(rootPane, "Details are not Valid C");
							} else if ((TitleTypeCombo.getSelectedIndex() == 3
									|| TitleTypeCombo.getSelectedIndex() == 4)
									&& (!inputValidation.validateAlphabets(genreField.getText())
											|| !inputValidation.validateAlphabets(directorField.getText()))) {
								JOptionPane.showMessageDialog(rootPane, "Details are not Valid D");
							} else {
								Product product = new Product(titleField.getText(), dscpText.getText(),
										TitleTypeCombo.getSelectedItem().toString(),
										Integer.parseInt(yearField.getText()),
										Integer.parseInt(quantityField.getText()));
								if (TitleTypeCombo.getSelectedIndex() == 1) {
									product.setModel(modelField.getText());
									product.setManufacturer(manufacturerField.getText());
									productDao.addProduct(1, product);
									JOptionPane.showMessageDialog(rootPane, "Success1");
								} else if (TitleTypeCombo.getSelectedIndex() == 2) {
									product.setBand(bandField.getText());
									product.setOrganiser(organiserField.getText());
									product.setGenre(genreField.getText());
									productDao.addProduct(2, product);
									JOptionPane.showMessageDialog(rootPane, "Success2");
								} else if ((TitleTypeCombo.getSelectedIndex() == 3
										|| TitleTypeCombo.getSelectedIndex() == 4)) {
									product.setGenre(genreField.getText());
									product.setDirector(directorField.getText());
									productDao.addProduct(3, product);
									JOptionPane.showMessageDialog(rootPane, "Success3");
								}
								titleField.setText(null);
								dscpText.setText(null);
								yearField.setText(null);
								TitleTypeCombo.setSelectedIndex(0);
								FormatTypeCombo.setSelectedIndex(0);
								modelField.setText(null);
								manufacturerField.setText(null);
								bandField.setText(null);
								organiserField.setText(null);
								genreField.setText(null);
								directorField.setText(null);
								quantityField.setText(null);
							}

						}
					}
				}
			}
		});
		btnAddAddTitle.setBounds(554, 263, 110, 23);
		AddTitle.add(btnAddAddTitle);

		btnResetFieldsAddTitle = new JButton("Reset Fields");
		btnResetFieldsAddTitle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				titleField.setText(null);
				dscpText.setText(null);
				yearField.setText(null);
				TitleTypeCombo.setSelectedIndex(0);
				FormatTypeCombo.setSelectedIndex(0);
				modelField.setText(null);
				manufacturerField.setText(null);
				bandField.setText(null);
				organiserField.setText(null);
				genreField.setText(null);
				directorField.setText(null);
				quantityField.setText(null);
			}
		});
		btnResetFieldsAddTitle.setBounds(554, 195, 110, 23);
		AddTitle.add(btnResetFieldsAddTitle);

		lblTitleDetails = new JLabel("Title Details");
		lblTitleDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleDetails.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblTitleDetails.setBounds(227, 111, 100, 22);
		AddTitle.add(lblTitleDetails);
	}

	public void EditCustomerGUI() {
		EditCustomer = new JPanel();
		layeredPane.add(EditCustomer, "name_2671265022984500");
		EditCustomer.setLayout(null);

		lblEditCustomer = new JLabel("Edit Customer");
		lblEditCustomer.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblEditCustomer.setBounds(10, 11, 195, 32);
		EditCustomer.add(lblEditCustomer);

		lblCustomerDetailsEditCust = new JLabel("Customer Details");
		lblCustomerDetailsEditCust.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblCustomerDetailsEditCust.setBounds(204, 119, 135, 22);
		EditCustomer.add(lblCustomerDetailsEditCust);

		CustomerDetailsPanelEditCust = new JPanel();
		CustomerDetailsPanelEditCust.setBounds(10, 144, 534, 194);
		EditCustomer.add(CustomerDetailsPanelEditCust);
		CustomerDetailsPanelEditCust.setLayout(null);

		lblNameEditCust = new JLabel("Name");
		lblNameEditCust.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNameEditCust.setBounds(101, 11, 120, 20);
		CustomerDetailsPanelEditCust.add(lblNameEditCust);

		lblEmailEditCust = new JLabel("Email");
		lblEmailEditCust.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblEmailEditCust.setBounds(101, 42, 120, 20);
		CustomerDetailsPanelEditCust.add(lblEmailEditCust);

		lblPhoneEditCust = new JLabel("Phone");
		lblPhoneEditCust.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblPhoneEditCust.setBounds(101, 73, 120, 20);
		CustomerDetailsPanelEditCust.add(lblPhoneEditCust);

		lblDebitcreditCardEditCust = new JLabel("Debit/Credit Card #");
		lblDebitcreditCardEditCust.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblDebitcreditCardEditCust.setBounds(101, 104, 120, 20);
		CustomerDetailsPanelEditCust.add(lblDebitcreditCardEditCust);

		lblAccessLevelEditCust = new JLabel("Access Level");
		lblAccessLevelEditCust.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblAccessLevelEditCust.setBounds(101, 135, 120, 20);
		CustomerDetailsPanelEditCust.add(lblAccessLevelEditCust);

		lblSubscriptionEditCust = new JLabel("Subscription Type");
		lblSubscriptionEditCust.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblSubscriptionEditCust.setBounds(101, 166, 120, 20);
		CustomerDetailsPanelEditCust.add(lblSubscriptionEditCust);

		nameFieldEditCust = new JTextField();
		nameFieldEditCust.setBounds(231, 12, 200, 20);
		CustomerDetailsPanelEditCust.add(nameFieldEditCust);
		nameFieldEditCust.setColumns(10);

		emailFieldEditCust = new JTextField();
		emailFieldEditCust.setBounds(231, 43, 200, 20);
		CustomerDetailsPanelEditCust.add(emailFieldEditCust);
		emailFieldEditCust.setColumns(10);

		phoneFieldEditCust = new JTextField();
		phoneFieldEditCust.setBounds(231, 74, 200, 20);
		CustomerDetailsPanelEditCust.add(phoneFieldEditCust);
		phoneFieldEditCust.setColumns(10);

		cardNumberFieldEditCust = new JTextField();
		cardNumberFieldEditCust.setBounds(231, 105, 200, 20);
		CustomerDetailsPanelEditCust.add(cardNumberFieldEditCust);
		cardNumberFieldEditCust.setColumns(10);

		accesslvlComboEditCust = new JComboBox(accesslvl);
		accesslvlComboEditCust.setBounds(231, 136, 200, 20);
		CustomerDetailsPanelEditCust.add(accesslvlComboEditCust);

		subscriptionComboEditCust = new JComboBox(subscription);
		subscriptionComboEditCust.setBounds(231, 167, 200, 20);
		CustomerDetailsPanelEditCust.add(subscriptionComboEditCust);

		btnUpdateEditCust = new JButton("Update");
		btnUpdateEditCust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(index);
				System.out.println(model.getValueAt(index, 5).toString());
				customer = new Customer(Integer.parseInt(model.getValueAt(index, 5).toString()));
				if (nameFieldEditCust.getText().isEmpty() || emailFieldEditCust.getText().isEmpty()
						|| phoneFieldEditCust.getText().isEmpty() || cardNumberFieldEditCust.getText().isEmpty()
						|| accesslvlComboEditCust.getSelectedIndex() == 0
						|| subscriptionComboEditCust.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(rootPane, "Fill all the Fields!!!");
				} else if (!inputValidation.validateAlphabets(nameFieldEditCust.getText())
						|| !inputValidation.validateEmail(emailFieldEditCust.getText())
						|| !inputValidation.validateNumbers(phoneFieldEditCust.getText())
						|| !inputValidation.validateNumbers(cardNumberFieldEditCust.getText())) {
					JOptionPane.showMessageDialog(rootPane, "Details are not Valid!!!");
				} else {

					customer.setNME(nameFieldEditCust.getText());
					customer.setEMAIL(emailFieldEditCust.getText());
					customer.setPHNE(Integer.parseInt(phoneFieldEditCust.getText()));
					customer.setACC_CRD(Integer.parseInt(cardNumberFieldEditCust.getText()));
					customer.setACCS_LVL(accesslvlComboEditCust.getSelectedItem().toString());
					customer.setSBSC(subscriptionComboEditCust.getSelectedItem().toString());

					customerDao.updateCustomer(Integer.parseInt(model.getValueAt(index, 0).toString()), customer);
					customer = null;
					JOptionPane.showMessageDialog(rootPane, "Success");
					switchPanels(Home);
				}
			}
		});
		btnUpdateEditCust.setBounds(554, 239, 110, 23);
		EditCustomer.add(btnUpdateEditCust);

		btnGoToHomeEditCust = new JButton("Go to Home");
		btnGoToHomeEditCust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index = -1;
				switchPanels(Home);
				customer = new Customer();
			}
		});
		btnGoToHomeEditCust.setBounds(554, 205, 110, 23);
		EditCustomer.add(btnGoToHomeEditCust);

		LogoPanelEditCust = new JPanel();
		LogoPanelEditCust.setLayout(null);
		LogoPanelEditCust.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		LogoPanelEditCust.setBounds(432, 11, 232, 95);
		EditCustomer.add(LogoPanelEditCust);

		lblLogoNameEditCust = new JLabel("Ultra-Vision");
		lblLogoNameEditCust.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogoNameEditCust.setFont(new Font("Segoe Script", Font.BOLD, 30));
		lblLogoNameEditCust.setBounds(6, 16, 220, 49);
		LogoPanelEditCust.add(lblLogoNameEditCust);

		lblLogoCityNameEditCust = new JLabel("Dublin.");
		lblLogoCityNameEditCust.setFont(new Font("Segoe Print", Font.PLAIN, 14));
		lblLogoCityNameEditCust.setBounds(177, 62, 49, 26);
		LogoPanelEditCust.add(lblLogoCityNameEditCust);
	}

	private void ProfileGUI() {
		Profile = new JPanel();
		layeredPane.add(Profile, "name_2701493917085700");
		Profile.setLayout(null);

		lblProfile = new JLabel("Profile");
		lblProfile.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblProfile.setBounds(10, 11, 100, 40);
		Profile.add(lblProfile);

		LogoPanelProfile = new JPanel();
		LogoPanelProfile.setLayout(null);
		LogoPanelProfile.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "",

				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		LogoPanelProfile.setBounds(432, 11, 232, 95);
		Profile.add(LogoPanelProfile);

		lblLogoNameProfile = new JLabel("Ultra-Vision");
		lblLogoNameProfile.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogoNameProfile.setFont(new Font("Segoe Script", Font.BOLD, 30));
		lblLogoNameProfile.setBounds(6, 16, 220, 49);
		LogoPanelProfile.add(lblLogoNameProfile);

		lblLogoCityNameProfile = new JLabel("Dublin.");
		lblLogoCityNameProfile.setFont(new Font("Segoe Print", Font.PLAIN, 14));
		lblLogoCityNameProfile.setBounds(177, 62, 49, 26);
		LogoPanelProfile.add(lblLogoCityNameProfile);

		lblCustomerDetailsProfile = new JLabel("Customer Details");
		lblCustomerDetailsProfile.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblCustomerDetailsProfile.setBounds(20, 86, 154, 27);
		Profile.add(lblCustomerDetailsProfile);

		btnRedeemLoyaltyPoints = new JButton("Redeem Loyalty Points");

		btnRedeemLoyaltyPoints.setVisible(false);

		btnRedeemLoyaltyPoints.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				redeemFlag = true;
				lblCustomerNameIssueRental.setText(customer.getNME());
				switchPanels(IssueRental);
			}
		});
		btnRedeemLoyaltyPoints.setBounds(257, 90, 165, 23);
		Profile.add(btnRedeemLoyaltyPoints);

		CustDetailsPanelProfile = new JPanel();
		CustDetailsPanelProfile.setBounds(10, 124, 504, 214);
		Profile.add(CustDetailsPanelProfile);
		CustDetailsPanelProfile.setLayout(null);

		lblNameProfile = new JLabel("Name");
		lblNameProfile.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNameProfile.setBounds(34, 11, 120, 25);
		CustDetailsPanelProfile.add(lblNameProfile);

		lblEmailProfile = new JLabel("Email");
		lblEmailProfile.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblEmailProfile.setBounds(34, 46, 120, 25);
		CustDetailsPanelProfile.add(lblEmailProfile);

		lblPhoneProfile = new JLabel("Phone");
		lblPhoneProfile.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblPhoneProfile.setBounds(34, 81, 120, 25);
		CustDetailsPanelProfile.add(lblPhoneProfile);

		lblDebitcreditCardProfile = new JLabel("Debit/Credit Card #");
		lblDebitcreditCardProfile.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblDebitcreditCardProfile.setBounds(34, 116, 120, 25);
		CustDetailsPanelProfile.add(lblDebitcreditCardProfile);

		lblAccessLevelProfile = new JLabel("Access Level");
		lblAccessLevelProfile.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblAccessLevelProfile.setBounds(34, 151, 120, 25);
		CustDetailsPanelProfile.add(lblAccessLevelProfile);

		lblSubscriptionTypeProfile = new JLabel("Subscription Type");
		lblSubscriptionTypeProfile.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblSubscriptionTypeProfile.setBounds(34, 186, 120, 25);
		CustDetailsPanelProfile.add(lblSubscriptionTypeProfile);

		lblNameCustProfile = new JLabel("");
		lblNameCustProfile.setBounds(154, 11, 199, 25);
		CustDetailsPanelProfile.add(lblNameCustProfile);
		lblNameCustProfile.setFont(new Font("Times New Roman", Font.BOLD, 20));

		lblEmailCustProfile = new JLabel("");
		lblEmailCustProfile.setBounds(154, 46, 199, 25);
		CustDetailsPanelProfile.add(lblEmailCustProfile);
		lblEmailCustProfile.setFont(new Font("Times New Roman", Font.BOLD, 20));

		lblPhoneCustProfile = new JLabel("");
		lblPhoneCustProfile.setBounds(154, 81, 199, 25);
		CustDetailsPanelProfile.add(lblPhoneCustProfile);
		lblPhoneCustProfile.setFont(new Font("Times New Roman", Font.BOLD, 20));

		lblDebitcreditCardCustProfile = new JLabel("");
		lblDebitcreditCardCustProfile.setBounds(154, 116, 199, 25);
		CustDetailsPanelProfile.add(lblDebitcreditCardCustProfile);
		lblDebitcreditCardCustProfile.setFont(new Font("Times New Roman", Font.BOLD, 20));

		lblSubscriptionTypeCustProfile = new JLabel("");
		lblSubscriptionTypeCustProfile.setBounds(154, 186, 199, 25);
		CustDetailsPanelProfile.add(lblSubscriptionTypeCustProfile);
		lblSubscriptionTypeCustProfile.setFont(new Font("Times New Roman", Font.BOLD, 20));

		lblLoyaltyPointsProfile = new JLabel("Loyalty Points:");
		lblLoyaltyPointsProfile.setBounds(398, 116, 85, 14);
		CustDetailsPanelProfile.add(lblLoyaltyPointsProfile);
		lblLoyaltyPointsProfile.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		lblLoyaltyPointsCustProfile = new JLabel("000");
		lblLoyaltyPointsCustProfile.setBounds(398, 131, 85, 24);
		CustDetailsPanelProfile.add(lblLoyaltyPointsCustProfile);
		lblLoyaltyPointsCustProfile.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoyaltyPointsCustProfile.setFont(new Font("Times New Roman", Font.BOLD, 20));

		lblIdProfile = new JLabel("ID");
		lblIdProfile.setBounds(436, 63, 15, 15);
		CustDetailsPanelProfile.add(lblIdProfile);
		lblIdProfile.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		lblidCustProfile = new JLabel("0");
		lblidCustProfile.setBounds(410, 78, 65, 24);
		CustDetailsPanelProfile.add(lblidCustProfile);
		lblidCustProfile.setHorizontalAlignment(SwingConstants.CENTER);
		lblidCustProfile.setFont(new Font("Times New Roman", Font.BOLD, 20));

		lblAccessLevelCustProfile = new JLabel("");
		lblAccessLevelCustProfile.setBounds(154, 151, 199, 25);
		CustDetailsPanelProfile.add(lblAccessLevelCustProfile);
		lblAccessLevelCustProfile.setFont(new Font("Times New Roman", Font.BOLD, 20));

		btnIssueNewRentalProfile = new JButton("Issue New Rental");
		btnIssueNewRentalProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				switch (customer.getSBSC()) {
				case ("Basic"):
					if (transactionDao.getIssuedCount(customer.getID()) >= 2) {
						btnIssueIssueRental.setEnabled(false);
						warningPanelIssueRental.setVisible(true);
					}else {
						btnIssueIssueRental.setEnabled(true);
						warningPanelIssueRental.setVisible(false);
					}
					break;
				case ("Standard"):
					if (transactionDao.getIssuedCount(customer.getID()) >= 4) {
						btnIssueIssueRental.setEnabled(false);
						warningPanelIssueRental.setVisible(true);
					}else {
						btnIssueIssueRental.setEnabled(true);
						warningPanelIssueRental.setVisible(false);
					}
					break;
				case ("Delux"):
					if (transactionDao.getIssuedCount(customer.getID()) >= 8) {
						btnIssueIssueRental.setEnabled(false);
						warningPanelIssueRental.setVisible(true);
					}else {
						btnIssueIssueRental.setEnabled(true);
						warningPanelIssueRental.setVisible(false);
					}
					break;
				case ("Premium"):
					System.out.println("Premium");
					break;
				}

				lblCustomerNameIssueRental.setText(lblNameCustProfile.getText());
				index = -1;
				switchPanels(IssueRental);
			}
		});

		btnGoToHomeProfile = new JButton("Go to Home");
		btnGoToHomeProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(Home);
			}
		});
		btnGoToHomeProfile.setBounds(524, 188, 140, 25);
		Profile.add(btnGoToHomeProfile);
		btnIssueNewRentalProfile.setBounds(524, 223, 140, 25);
		Profile.add(btnIssueNewRentalProfile);

		btnViewIssuedRentalsProfile = new JButton("View Issued \r\nRentals");
		btnViewIssuedRentalsProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblCustomerNameIssuedRentals.setText(lblNameCustProfile.getText());
				RentalsTableIssuedRentals.setModel(DbUtils.resultSetToTableModel(
						transactionDao.getTransactions(Integer.parseInt(lblidCustProfile.getText()))));
				switchPanels(IssuedRentals);
			}
		});
		btnViewIssuedRentalsProfile.setBounds(524, 258, 140, 25);
		Profile.add(btnViewIssuedRentalsProfile);
	}

	private void IssueRentalGUI() {
		IssueRental = new JPanel();
		layeredPane.add(IssueRental, "name_2750947078941900");
		IssueRental.setLayout(null);

		lblIssueRental = new JLabel("Issue Rental");
		lblIssueRental.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblIssueRental.setBounds(10, 11, 170, 32);
		IssueRental.add(lblIssueRental);

		searchTitleFieldIssueRental = new JTextField();
		searchTitleFieldIssueRental.setBounds(20, 54, 210, 20);
		IssueRental.add(searchTitleFieldIssueRental);
		searchTitleFieldIssueRental.setColumns(10);

		btnSearchTitleIssueRental = new JButton("Search Title");
		btnSearchTitleIssueRental.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblSelectedTitleIssueRental.setVisible(false);
				lblSelectedTitleNameIssueRental.setVisible(false);
				if (searchTitleFieldIssueRental.getText().isEmpty()) {
					JOptionPane.showMessageDialog(rootPane, "Fill all the Fields!!!");
				} else if (!inputValidation.validateAlphabets(searchTitleFieldIssueRental.getText())) {
					JOptionPane.showMessageDialog(rootPane, "Details invalid");
				} else {
					try {
						switch (customer.getACCS_LVL()) {
						case "Premium":
							if (!productDao.getProducts(searchTitleFieldIssueRental.getText()).next()) {
								TitleTablePanelIssueRental.setVisible(false);
								index = -1;
								JOptionPane.showMessageDialog(rootPane, "No Record Found");
							} else {
								TitleTableIssueRental.setModel(DbUtils.resultSetToTableModel(
										productDao.getProducts(searchTitleFieldIssueRental.getText())));
								TitleTablePanelIssueRental.setVisible(true);
							}
							break;

						default:
							if (!productDao.getProductsCust(searchTitleFieldIssueRental.getText(),
									adapter.translateAccessLvl(customer.getACCS_LVL())).next()) {
								TitleTablePanelIssueRental.setVisible(false);
								index = -1;
								JOptionPane.showMessageDialog(rootPane, "No Record Found");
							} else {
								TitleTableIssueRental.setModel(DbUtils.resultSetToTableModel(
										productDao.getProductsCust(searchTitleFieldIssueRental.getText(),
												adapter.translateAccessLvl(customer.getACCS_LVL()))));
								TitleTablePanelIssueRental.setVisible(true);
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}
			}
		});
		btnSearchTitleIssueRental.setBounds(65, 85, 120, 23);
		IssueRental.add(btnSearchTitleIssueRental);

		lblNameIssueRental = new JLabel("Customer Name");
		lblNameIssueRental.setHorizontalAlignment(SwingConstants.CENTER);
		lblNameIssueRental.setBounds(260, 36, 130, 14);
		IssueRental.add(lblNameIssueRental);

		lblCustomerNameIssueRental = new JLabel("");
		lblCustomerNameIssueRental.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustomerNameIssueRental.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblCustomerNameIssueRental.setBounds(260, 50, 130, 30);
		IssueRental.add(lblCustomerNameIssueRental);

		lblSelectedTitleIssueRental = new JLabel("Selected Title");
		lblSelectedTitleIssueRental.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectedTitleIssueRental.setVisible(false);
		lblSelectedTitleIssueRental.setBounds(260, 91, 130, 14);
		IssueRental.add(lblSelectedTitleIssueRental);

		lblSelectedTitleNameIssueRental = new JLabel("");
		lblSelectedTitleNameIssueRental.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectedTitleNameIssueRental.setVisible(false);
		lblSelectedTitleNameIssueRental.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblSelectedTitleNameIssueRental.setBounds(260, 107, 130, 30);
		IssueRental.add(lblSelectedTitleNameIssueRental);

		TitleTablePanelIssueRental = new JPanel();
		TitleTablePanelIssueRental.setVisible(false);
		TitleTablePanelIssueRental.setBounds(10, 162, 534, 176);
		IssueRental.add(TitleTablePanelIssueRental);
		TitleTablePanelIssueRental.setLayout(null);

		TitleTableScrollPaneIssueRental = new JScrollPane();
		TitleTableScrollPaneIssueRental.setBounds(0, 0, 534, 176);
		TitleTablePanelIssueRental.add(TitleTableScrollPaneIssueRental);

		TitleTableIssueRental = new JTable();
		TitleTableScrollPaneIssueRental.setViewportView(TitleTableIssueRental);
		TitleTableIssueRental.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				index = TitleTableIssueRental.getSelectedRow();
				model = TitleTableIssueRental.getModel();
				System.out.println(model.getValueAt(index, 0));
				lblSelectedTitleIssueRental.setVisible(true);
				lblSelectedTitleNameIssueRental.setText(model.getValueAt(index, 1).toString());
				lblSelectedTitleNameIssueRental.setVisible(true);
			}
		});

		btnIssueIssueRental = new JButton("Issue");
		btnIssueIssueRental.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (index != -1) {
					int input = JOptionPane.showConfirmDialog(rootPane,
							"Issue " + model.getValueAt(index, 1).toString() + " to " + lblNameCustProfile.getText(),
							"Select an Option...", JOptionPane.YES_NO_OPTION);
					// 0=yes, 1=no, 2=cancel
					if (input == 0) {
						Transaction t = new Transaction();
						t.setCustomerID(Integer.parseInt(lblidCustProfile.getText()));
						t.setProductID(Integer.parseInt(model.getValueAt(index, 0).toString()));
						t.setStaffID(ActiveStaff.getId());

						transactionDao.addTransaction(t);

						productDao.decrement(t.getProductID());

						customerDao.updateLoyaltyPoints(Integer.parseInt(lblidCustProfile.getText()));

						ResultSet rs = customerDao.getLoyaltyPoints(customer.getID());
						try {
							while (rs.next()) {
								customer.setLYLTY_PNTS(Integer.parseInt(rs.getString("LYLTY_PNTS")));
								lblLoyaltyPointsCustProfile.setText(rs.getString("LYLTY_PNTS"));
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						if (redeemFlag) {
							customerDao.redeemLoyaltyPoints(customer.getID());
						}

						searchTitleFieldIssueRental.setText(null);
						TitleTablePanelIssueRental.setVisible(false);
						JOptionPane.showMessageDialog(rootPane, "Rental Issued Successfully");
						System.out.println("Count = " + transactionDao.getIssuedCount(customer.getID()));

						switch (customer.getSBSC()) {
						case ("Basic"):
							System.out.println("Basic");
							if (transactionDao.getIssuedCount(customer.getID()) >= 2) {
								btnIssueIssueRental.setEnabled(false);
								warningPanelIssueRental.setVisible(true);
							}else {
								btnIssueIssueRental.setEnabled(true);
								warningPanelIssueRental.setVisible(false);
							}
							break;
						case ("Standard"):
							System.out.println("Standard");
							if (transactionDao.getIssuedCount(customer.getID()) >= 4) {
								btnIssueIssueRental.setEnabled(false);
								warningPanelIssueRental.setVisible(true);
							}else {
								btnIssueIssueRental.setEnabled(true);
								warningPanelIssueRental.setVisible(false);
							}
							break;
						case ("Delux"):
							System.out.println("Delux");
							if (transactionDao.getIssuedCount(customer.getID()) >= 8) {
								btnIssueIssueRental.setEnabled(false);
								warningPanelIssueRental.setVisible(true);
							}else {
								btnIssueIssueRental.setEnabled(true);
								warningPanelIssueRental.setVisible(false);
							}
							break;
						case ("Premium"):
							System.out.println("Premium");
							break;
						}
					}
					lblSelectedTitleIssueRental.setVisible(false);
					lblSelectedTitleNameIssueRental.setVisible(false);
					TitleTableIssueRental.clearSelection();
					index = -1;
				} else {
					JOptionPane.showMessageDialog(rootPane, "No Item Selected");
				}
			}
		});

		btnGoBackProfileIssueRental = new JButton("Go Back");
		btnGoBackProfileIssueRental.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ResultSet rs = customerDao.getLoyaltyPoints(customer.getID());
				try {
					while (rs.next()) {
						System.out.println(rs.getString("LYLTY_PNTS"));
						customer.setLYLTY_PNTS(Integer.parseInt(rs.getString("LYLTY_PNTS")));
						lblLoyaltyPointsCustProfile.setText(rs.getString("LYLTY_PNTS"));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (customer.getLYLTY_PNTS() >= 100) {
					btnRedeemLoyaltyPoints.setVisible(true);
				} else {
					btnRedeemLoyaltyPoints.setVisible(false);
				}
				searchTitleFieldIssueRental.setText(null);
				lblSelectedTitleIssueRental.setVisible(false);
				lblSelectedTitleNameIssueRental.setVisible(false);
				model = null;
				TitleTableIssueRental.setModel(new DefaultTableModel());
				index = -1;
				TitleTablePanelIssueRental.setVisible(false);
				switchPanels(Profile);
			}
		});
		btnGoBackProfileIssueRental.setBounds(554, 209, 110, 23);
		IssueRental.add(btnGoBackProfileIssueRental);
		btnIssueIssueRental.setBounds(554, 243, 110, 23);
		IssueRental.add(btnIssueIssueRental);

		LogoPanelIssueRental = new JPanel();
		LogoPanelIssueRental.setLayout(null);
		LogoPanelIssueRental.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "",

				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		LogoPanelIssueRental.setBounds(435, 11, 232, 95);
		IssueRental.add(LogoPanelIssueRental);

		lblLogoNameIssueRental = new JLabel("Ultra-Vision");
		lblLogoNameIssueRental.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogoNameIssueRental.setFont(new Font("Segoe Script", Font.BOLD, 30));
		lblLogoNameIssueRental.setBounds(6, 16, 220, 49);
		LogoPanelIssueRental.add(lblLogoNameIssueRental);

		lblLogoCityNameIssueRental = new JLabel("Dublin.");
		lblLogoCityNameIssueRental.setFont(new Font("Segoe Print", Font.PLAIN, 14));
		lblLogoCityNameIssueRental.setBounds(177, 62, 49, 26);
		LogoPanelIssueRental.add(lblLogoCityNameIssueRental);

		warningPanelIssueRental = new JPanel();
		warningPanelIssueRental
				.setToolTipText("You have reached your issuing limit.\nRetun previous or upade plan to issue more.");
		warningPanelIssueRental.setVisible(false);
		warningPanelIssueRental.setBounds(554, 277, 110, 41);
		IssueRental.add(warningPanelIssueRental);
		warningPanelIssueRental.setLayout(null);

		lblCantIssue = new JLabel("Can't Issue.");
		lblCantIssue.setBounds(0, 0, 110, 20);
		warningPanelIssueRental.add(lblCantIssue);
		lblCantIssue.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCantIssue.setHorizontalAlignment(SwingConstants.CENTER);

		lblLimitReached = new JLabel("Limit Reached.");
		lblLimitReached.setBounds(0, 21, 110, 20);
		warningPanelIssueRental.add(lblLimitReached);
		lblLimitReached.setHorizontalAlignment(SwingConstants.CENTER);
		lblLimitReached.setFont(new Font("Tahoma", Font.BOLD, 14));
	}

	private void IssuedRentalsGUI() {
		IssuedRentals = new JPanel();
		layeredPane.add(IssuedRentals, "name_2796370915378100");
		IssuedRentals.setLayout(null);

		lblIssuedRentals = new JLabel("Issued Rentals");
		lblIssuedRentals.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblIssuedRentals.setBounds(10, 11, 205, 32);
		IssuedRentals.add(lblIssuedRentals);

		btnGoBackIssuedRentals = new JButton("Go Back");
		btnGoBackIssuedRentals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (customer.getLYLTY_PNTS() >= 100) {
					btnRedeemLoyaltyPoints.setVisible(true);
				} else {
					btnRedeemLoyaltyPoints.setVisible(false);
				}
				index = -1;
				lblSelectedRentalIssuedRentals.setVisible(false);
				lblSelectedRentalNameIssuedRentals.setVisible(false);
				switchPanels(Profile);
			}
		});

		lblCustomerIssuedRentals = new JLabel("Customer Name");
		lblCustomerIssuedRentals.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustomerIssuedRentals.setBounds(38, 97, 130, 14);
		IssuedRentals.add(lblCustomerIssuedRentals);

		lblCustomerNameIssuedRentals = new JLabel("");
		lblCustomerNameIssuedRentals.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustomerNameIssuedRentals.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblCustomerNameIssuedRentals.setBounds(38, 112, 130, 30);
		IssuedRentals.add(lblCustomerNameIssuedRentals);

		lblSelectedRentalIssuedRentals = new JLabel("Selected");
		lblSelectedRentalIssuedRentals.setVisible(false);
		lblSelectedRentalIssuedRentals.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectedRentalIssuedRentals.setBounds(280, 97, 130, 15);
		IssuedRentals.add(lblSelectedRentalIssuedRentals);

		lblSelectedRentalNameIssuedRentals = new JLabel("");
		lblSelectedRentalNameIssuedRentals.setVisible(false);
		lblSelectedRentalNameIssuedRentals.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectedRentalNameIssuedRentals.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblSelectedRentalNameIssuedRentals.setBounds(280, 112, 130, 30);
		IssuedRentals.add(lblSelectedRentalNameIssuedRentals);

		RentalsTablePanelIssuedRentals = new JPanel();
		RentalsTablePanelIssuedRentals.setBounds(10, 153, 534, 185);
		IssuedRentals.add(RentalsTablePanelIssuedRentals);
		RentalsTablePanelIssuedRentals.setLayout(null);

		RentalsScrollPaneIssuedRentals = new JScrollPane();
		RentalsScrollPaneIssuedRentals.setBounds(0, 0, 534, 185);
		RentalsTablePanelIssuedRentals.add(RentalsScrollPaneIssuedRentals);

		RentalsTableIssuedRentals = new JTable();
		RentalsScrollPaneIssuedRentals.setViewportView(RentalsTableIssuedRentals);
		RentalsTableIssuedRentals.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				index = RentalsTableIssuedRentals.getSelectedRow();
				model = RentalsTableIssuedRentals.getModel();
				lblSelectedRentalIssuedRentals.setVisible(true);
				lblSelectedRentalNameIssuedRentals.setVisible(true);
				lblSelectedRentalNameIssuedRentals.setText(model.getValueAt(index, 0).toString());
			}
		});
		btnGoBackIssuedRentals.setBounds(554, 210, 110, 23);
		IssuedRentals.add(btnGoBackIssuedRentals);

		btnReturnRentalIssuedRentals = new JButton("Return ");
		btnReturnRentalIssuedRentals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (index != -1) {

					String status = model.getValueAt(index, 4).toString();
					switch (status) {
					case ("Issued"):
						System.out.println("ID= " + model.getValueAt(index, 0).toString());
						System.out.println("ProdID= " + model.getValueAt(index, 2).toString());
						transactionDao.markReturn(Integer.parseInt(model.getValueAt(index, 0).toString()),
								formatter.format(new Date()));
						productDao.increment(Integer.parseInt(model.getValueAt(index, 2).toString()));
						JOptionPane.showMessageDialog(rootPane, "Success!!!");
						RentalsTableIssuedRentals.setModel(DbUtils.resultSetToTableModel(
								transactionDao.getTransactions(Integer.parseInt(lblidCustProfile.getText()))));
						break;
					case ("Returned"):
						JOptionPane.showMessageDialog(rootPane, "Item already Returned");
						break;
					}
				} else {
					JOptionPane.showMessageDialog(rootPane, "No Selection Made!!!");
				}
			}
		});
		btnReturnRentalIssuedRentals.setBounds(554, 244, 110, 23);
		IssuedRentals.add(btnReturnRentalIssuedRentals);

		LogoPanelIssuedRentals = new JPanel();
		LogoPanelIssuedRentals.setLayout(null);
		LogoPanelIssuedRentals.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "",

				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		LogoPanelIssuedRentals.setBounds(432, 11, 232, 95);
		IssuedRentals.add(LogoPanelIssuedRentals);

		lblLogoNameIssuedRentals = new JLabel("Ultra-Vision");
		lblLogoNameIssuedRentals.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogoNameIssuedRentals.setFont(new Font("Segoe Script", Font.BOLD, 30));
		lblLogoNameIssuedRentals.setBounds(6, 16, 220, 49);
		LogoPanelIssuedRentals.add(lblLogoNameIssuedRentals);

		lblLogoCityNameIssuedRentals = new JLabel("Dublin.");
		lblLogoCityNameIssuedRentals.setFont(new Font("Segoe Print", Font.PLAIN, 14));
		lblLogoCityNameIssuedRentals.setBounds(177, 62, 49, 26);
		LogoPanelIssuedRentals.add(lblLogoCityNameIssuedRentals);
	}

	public App(Staff staff) {

		setResizable(false);

		InstanciateApp(staff);

		HomeGUI();

		RegisterCustomerGUI();

		SearchCustomerGUI();

		SearchTitleGUI();

		AddTitleGUI();

		EditCustomerGUI();

		ProfileGUI();

		IssueRentalGUI();

		IssuedRentalsGUI();

		setVisible(true);
	}
}
