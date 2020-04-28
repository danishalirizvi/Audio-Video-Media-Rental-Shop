package Interfaces;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import DataAccess.ComboBoxData;
import DataAccess.CustomerDao;
import DataAccess.ProductDao;
import Helpers.InputValidation;
import Models.Customer;
import Models.Product;
import Models.Staff;
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
import java.awt.geom.GeneralPath;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class App extends JFrame {

	private JPanel contentPane, Home, UserDetails, RegisterCustomer, SearchCustomer, CustTablePanel, LCVpanel,
			Genrepanel, BoxSetpanel, Directorpanel, AddTitle, SearchTitle, TitleTablePanel;
	private JLayeredPane layeredPane;
	private JLabel lblHome, lblActiveUser, lblActiveStaffName, lblRegisterCustomer, lblName, lblEmail, lblPhone,
			lblDebitcreditCard, lblAccessLevel, lblSubscriptionType, CustNotFoundlbl, lblSearchResults, lblGenre,
			TitleNotFoundlbl, lblAddTitle, lblTitle, lblSearchTitle;
	private JButton btnSearchCustomer, btnRegisterCustomer, btnGoToHomeCust, btnGoBackCust, btnRegister, btnLogout,
			btnAdd, btnGoBackTitle;
	private Staff ActiveStaff;
	private JTextField searchField, nameField, emailField, phoneField, cardNumberField, titleField, yearField,
			genreField, directorField, bandField, organiserField, manufacturerField, modelField;;
	private InputValidation inputValidation;
	private JComboBox accesslvlCombo, subscriptionCombo;
	private Customer customer = new Customer(0);
	private CustomerDao customerDao = new CustomerDao();
	private ProductDao productDao = new ProductDao();
	private JTable CustTable, TitleTable;
	private JScrollPane CustScrollPane, TitleScrollPane;
	private JButton btnResetFields;

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
		setBounds(100, 100, 450, 295);
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
		btnSearchCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(!searchField.getText().isEmpty()) {
						if (!customerDao.getCustomers(searchField.getText()).next()) {
							CustTablePanel.setVisible(false);
							CustNotFoundlbl.setVisible(true);
						} else {
							CustTable.setModel(
									DbUtils.resultSetToTableModel(customerDao.getCustomers(searchField.getText())));
						}
						switchPanels(SearchCustomer);
					}
					else {
						JOptionPane.showMessageDialog(rootPane, "Search Bar Empty!!!");
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnSearchCustomer.setBounds(10, 134, 150, 23);
		Home.add(btnSearchCustomer);

		btnRegisterCustomer = new JButton("Register Customer");
		btnRegisterCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanels(RegisterCustomer);
			}
		});
		btnRegisterCustomer.setBounds(10, 168, 150, 23);
		Home.add(btnRegisterCustomer);

		UserDetails = new JPanel();
		UserDetails.setLayout(null);
		UserDetails.setBounds(140, 11, 264, 72);
		Home.add(UserDetails);

		btnLogout = new JButton("Logout!");
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

		JButton btnSearchTitle = new JButton("Search Title");
		btnSearchTitle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!searchField.getText().isEmpty()) {
						if (!productDao.getProducts(searchField.getText()).next()) {
							TitleTablePanel.setVisible(false);
							TitleNotFoundlbl.setVisible(true);
						} else {
							TitleTable.setModel(
									DbUtils.resultSetToTableModel(productDao.getProducts(searchField.getText())));
						}
						switchPanels(SearchTitle);
					} else {
						JOptionPane.showMessageDialog(rootPane, "Search Bar Empty!!!");
					}

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnSearchTitle.setBounds(254, 134, 150, 23);
		Home.add(btnSearchTitle);

		JButton btnAddTitle = new JButton("Add Title");
		btnAddTitle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(AddTitle);
			}
		});
		btnAddTitle.setBounds(254, 168, 150, 23);
		Home.add(btnAddTitle);

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

		btnRegister = new JButton("Register!");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (nameField.getText().isEmpty() || emailField.getText().isEmpty() || phoneField.getText().isEmpty()
						|| cardNumberField.getText().isEmpty() || accesslvlCombo.getSelectedIndex() == 0
						|| subscriptionCombo.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(rootPane, "Fill all the Fields!!!");
				} else if (!inputValidation.validateAlphabets(nameField.getText())
						|| !inputValidation.validateEmail(emailField.getText())
						|| !inputValidation.validateNumbers(phoneField.getText())
						|| !inputValidation.validateNumbers(cardNumberField.getText())) {
					JOptionPane.showMessageDialog(rootPane, "Details are not Valid!!!");
				} else {

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
		btnRegister.setBounds(294, 205, 110, 23);
		RegisterCustomer.add(btnRegister);

		String[] accesslvl = { "--Select Access Level Type--", "Music Lovers", "Premium", "TV Lover", "Video Lovers" };
		accesslvlCombo = new JComboBox(accesslvl);
		accesslvlCombo.setBounds(125, 139, 200, 20);
		RegisterCustomer.add(accesslvlCombo);
		accesslvlCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				customer.setACCS_LVL(accesslvlCombo.getSelectedItem().toString());
			}
		});

		String[] subscription = { "--Select Subscription Type--", "Basic", "Delux", "Premium", "Standard" };
		subscriptionCombo = new JComboBox(subscription);
		subscriptionCombo.setBounds(125, 164, 200, 20);
		RegisterCustomer.add(subscriptionCombo);
		subscriptionCombo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				customer.setSBSC(subscriptionCombo.getSelectedItem().toString());
			}
		});

		btnGoToHomeCust = new JButton("Go to Home");
		btnGoToHomeCust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nameField.setText(null);
				emailField.setText(null);
				phoneField.setText(null);
				cardNumberField.setText(null);
				accesslvlCombo.setSelectedIndex(0);
				subscriptionCombo.setSelectedIndex(0);
				searchField.setText(null);
				switchPanels(Home);
			}
		});
		btnGoToHomeCust.setBounds(294, 7, 110, 23);
		RegisterCustomer.add(btnGoToHomeCust);

	}

	public void SearchCustomerGUI() {
		SearchCustomer = new JPanel();
		layeredPane.add(SearchCustomer, "name_2324474656071100");
		SearchCustomer.setLayout(null);

		lblSearchResults = new JLabel("Search Results");
		lblSearchResults.setBounds(10, 11, 80, 14);
		SearchCustomer.add(lblSearchResults);

		CustNotFoundlbl = new JLabel("No matching Reault Found");
		CustNotFoundlbl.setBounds(108, 65, 180, 14);
		SearchCustomer.add(CustNotFoundlbl);
		CustNotFoundlbl.setVisible(false);

		CustTablePanel = new JPanel();
		CustTablePanel.setBounds(10, 90, 394, 139);
		SearchCustomer.add(CustTablePanel);
		CustTablePanel.setLayout(null);

		CustScrollPane = new JScrollPane();
		CustScrollPane.setBounds(0, 0, 394, 139);
		CustTablePanel.add(CustScrollPane);

		CustTable = new JTable();
		CustScrollPane.setViewportView(CustTable);

		btnGoBackCust = new JButton("Go Back");
		btnGoBackCust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustTablePanel.setVisible(true);
				CustNotFoundlbl.setVisible(false);
				searchField.setText(null);
				switchPanels(Home);
			}
		});
		btnGoBackCust.setBounds(315, 11, 89, 23);
		SearchCustomer.add(btnGoBackCust);
	}

	public void SearchTitleGUI() {

		SearchTitle = new JPanel();
		layeredPane.add(SearchTitle, "name_2406822016214400");
		SearchTitle.setLayout(null);

		lblSearchTitle = new JLabel("Search Title");
		lblSearchTitle.setBounds(10, 11, 66, 14);
		SearchTitle.add(lblSearchTitle);

		TitleNotFoundlbl = new JLabel("No matching Result Found");
		TitleNotFoundlbl.setBounds(108, 65, 180, 14);
		SearchTitle.add(TitleNotFoundlbl);

		TitleTablePanel = new JPanel();
		TitleTablePanel.setBounds(10, 90, 394, 139);
		SearchTitle.add(TitleTablePanel);
		TitleTablePanel.setLayout(null);

		TitleScrollPane = new JScrollPane();
		TitleScrollPane.setBounds(0, 0, 394, 139);
		TitleTablePanel.add(TitleScrollPane);

		TitleTable = new JTable();
		TitleScrollPane.setViewportView(TitleTable);

		btnGoBackTitle = new JButton("Go Back");
		btnGoBackTitle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TitleTablePanel.setVisible(true);
				TitleNotFoundlbl.setVisible(false);
				searchField.setText(null);
				switchPanels(Home);
			}
		});
		btnGoBackTitle.setBounds(315, 11, 89, 23);
		SearchTitle.add(btnGoBackTitle);

	}

	public void AddTitleGUI() {
		AddTitle = new JPanel();
		layeredPane.add(AddTitle, "name_2409423209601400");
		AddTitle.setLayout(null);

		JComboBox<String> FormatTypeCombo = new JComboBox<String>(new ComboBoxData().getFormatType());
		FormatTypeCombo.setBounds(90, 64, 100, 20);
		AddTitle.add(FormatTypeCombo);

		lblAddTitle = new JLabel("Add Title");
		lblAddTitle.setBounds(10, 11, 60, 14);
		AddTitle.add(lblAddTitle);

		lblTitle = new JLabel("Title");
		lblTitle.setBounds(20, 39, 70, 14);
		AddTitle.add(lblTitle);

		titleField = new JTextField();
		titleField.setBounds(90, 36, 100, 20);
		AddTitle.add(titleField);
		titleField.setColumns(10);

		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(200, 39, 70, 14);
		AddTitle.add(lblDescription);

		JTextArea dscpText = new JTextArea();
		dscpText.setBounds(280, 36, 120, 48);
		AddTitle.add(dscpText);

		JLabel lblTitleType = new JLabel("Title Type");
		lblTitleType.setBounds(200, 98, 70, 14);
		AddTitle.add(lblTitleType);

		JComboBox<String> TitleTypeCombo = new JComboBox<String>(new ComboBoxData().getTitleType());
		TitleTypeCombo.setBounds(280, 92, 120, 20);
		AddTitle.add(TitleTypeCombo);
		TitleTypeCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String title = TitleTypeCombo.getSelectedItem().toString();
				if (TitleTypeCombo.getSelectedIndex() == 4) {
					Genrepanel.setVisible(true);
					Genrepanel.setLocation(20, 123);
					Directorpanel.setVisible(true);
					Directorpanel.setLocation(210, 123);

					modelField.setText(null);
					manufacturerField.setText(null);
					bandField.setText(null);
					organiserField.setText(null);
					genreField.setText(null);
					directorField.setText(null);
					// *********************************
					LCVpanel.setVisible(false);
					BoxSetpanel.setVisible(false);
				} else if (TitleTypeCombo.getSelectedIndex() == 2) {
					LCVpanel.setVisible(true);
					LCVpanel.setLocation(20, 123);
					Genrepanel.setVisible(true);
					Genrepanel.setLocation(20, 148);
					modelField.setText(null);
					manufacturerField.setText(null);
					bandField.setText(null);
					organiserField.setText(null);
					genreField.setText(null);
					directorField.setText(null);
					// ****************************
					Directorpanel.setVisible(false);
					BoxSetpanel.setVisible(false);
				} else if (TitleTypeCombo.getSelectedIndex() == 3) {
					Genrepanel.setVisible(true);
					Genrepanel.setLocation(20, 123);
					Directorpanel.setVisible(true);
					Directorpanel.setLocation(210, 123);
					modelField.setText(null);
					manufacturerField.setText(null);
					bandField.setText(null);
					organiserField.setText(null);
					genreField.setText(null);
					directorField.setText(null);
					// **********************************
					LCVpanel.setVisible(false);
					BoxSetpanel.setVisible(false);
				} else if (TitleTypeCombo.getSelectedIndex() == 1) {
					BoxSetpanel.setVisible(true);
					BoxSetpanel.setLocation(20, 123);
					modelField.setText(null);
					manufacturerField.setText(null);
					bandField.setText(null);
					organiserField.setText(null);
					genreField.setText(null);
					directorField.setText(null);
					// **********************************
					Genrepanel.setVisible(false);
					Directorpanel.setVisible(false);
					LCVpanel.setVisible(false);
				} else if (TitleTypeCombo.getSelectedIndex() == 0) {
					Genrepanel.setVisible(false);
					Directorpanel.setVisible(false);
					LCVpanel.setVisible(false);
					BoxSetpanel.setVisible(false);
					modelField.setText(null);
					manufacturerField.setText(null);
					bandField.setText(null);
					organiserField.setText(null);
					genreField.setText(null);
					directorField.setText(null);
				}

			}
		});

		JLabel lblFormatType = new JLabel("Format Type");
		lblFormatType.setBounds(20, 67, 70, 14);
		AddTitle.add(lblFormatType);

		JLabel lblReleaseYear = new JLabel("Release Year");
		lblReleaseYear.setBounds(20, 95, 70, 14);
		AddTitle.add(lblReleaseYear);

		yearField = new JTextField();
		yearField.setBounds(90, 92, 100, 20);
		AddTitle.add(yearField);
		yearField.setColumns(10);

		Genrepanel = new JPanel();
		Genrepanel.setVisible(false);
		Genrepanel.setBounds(20, 184, 170, 20);
		AddTitle.add(Genrepanel);
		Genrepanel.setLayout(null);

		lblGenre = new JLabel("Genre");
		lblGenre.setBounds(0, 3, 70, 14);
		Genrepanel.add(lblGenre);

		genreField = new JTextField();
		genreField.setBounds(70, 0, 100, 20);
		Genrepanel.add(genreField);
		genreField.setColumns(10);

		Directorpanel = new JPanel();
		Directorpanel.setVisible(false);
		Directorpanel.setBounds(230, 184, 170, 20);
		AddTitle.add(Directorpanel);
		Directorpanel.setLayout(null);

		JLabel lblDirector = new JLabel("Director");
		lblDirector.setBounds(0, 3, 70, 14);
		Directorpanel.add(lblDirector);

		directorField = new JTextField();
		directorField.setBounds(70, 0, 100, 20);
		Directorpanel.add(directorField);
		directorField.setColumns(10);

		BoxSetpanel = new JPanel();
		BoxSetpanel.setVisible(false);
		BoxSetpanel.setBounds(20, 150, 380, 23);
		AddTitle.add(BoxSetpanel);
		BoxSetpanel.setLayout(null);

		JLabel lblManufacturer = new JLabel("Manufacturer");
		lblManufacturer.setBounds(180, 6, 85, 14);
		BoxSetpanel.add(lblManufacturer);

		manufacturerField = new JTextField();
		manufacturerField.setBounds(260, 3, 120, 20);
		BoxSetpanel.add(manufacturerField);
		manufacturerField.setColumns(10);

		JLabel lblModel = new JLabel("Model");
		lblModel.setBounds(0, 3, 35, 14);
		BoxSetpanel.add(lblModel);

		modelField = new JTextField();
		modelField.setBounds(70, 0, 100, 20);
		BoxSetpanel.add(modelField);
		modelField.setColumns(10);

		JButton btnGoToHomeTitle = new JButton("Go To Home");
		btnGoToHomeTitle.addActionListener(new ActionListener() {
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
				searchField.setText(null);
			}
		});
		btnGoToHomeTitle.setBounds(284, 2, 120, 23);
		AddTitle.add(btnGoToHomeTitle);

		btnAdd = new JButton("Add!");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (titleField.getText().isEmpty() || dscpText.getText().isEmpty()
						|| FormatTypeCombo.getSelectedIndex() == 0 || TitleTypeCombo.getSelectedIndex() == 0
						|| yearField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(rootPane, "Fill all the Fields!!!");
				} else {
					if (TitleTypeCombo.getSelectedIndex() == 1
							&& (modelField.getText().isEmpty() || manufacturerField.getText().isEmpty())) {
						JOptionPane.showMessageDialog(rootPane, "Fill all the Fields!!!");
					} else if (TitleTypeCombo.getSelectedIndex() == 2 && (bandField.getText().isEmpty()
							|| organiserField.getText().isEmpty() || genreField.getText().isEmpty())) {
						JOptionPane.showMessageDialog(rootPane, "Fill all the Fields!!!");
					} else if ((TitleTypeCombo.getSelectedIndex() == 3 || TitleTypeCombo.getSelectedIndex() == 4)
							&& (genreField.getText().isEmpty() || directorField.getText().isEmpty())) {
						JOptionPane.showMessageDialog(rootPane, "Fill all the Fields!!!");
					} else {
						if (!inputValidation.validateAlphabets(titleField.getText())
								|| !inputValidation.validateNumbers(yearField.getText())) {
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
										FormatTypeCombo.getSelectedItem().toString(),
										Integer.parseInt(yearField.getText()));
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
							}

						}
					}
				}
			}
		});
		btnAdd.setBounds(344, 216, 60, 23);
		AddTitle.add(btnAdd);

		LCVpanel = new JPanel();
		LCVpanel.setVisible(false);
		LCVpanel.setBounds(20, 123, 384, 20);
		AddTitle.add(LCVpanel);
		LCVpanel.setLayout(null);

		JLabel lblBand = new JLabel("Band");
		lblBand.setBounds(0, 3, 70, 14);
		LCVpanel.add(lblBand);

		bandField = new JTextField();
		bandField.setBounds(70, 0, 100, 20);
		LCVpanel.add(bandField);
		bandField.setColumns(10);

		JLabel lblOrganiser = new JLabel("Organiser");
		lblOrganiser.setBounds(225, 3, 60, 14);
		LCVpanel.add(lblOrganiser);

		organiserField = new JTextField();
		organiserField.setBounds(284, 0, 100, 20);
		LCVpanel.add(organiserField);
		organiserField.setColumns(10);

		btnResetFields = new JButton("Reset Fields");
		btnResetFields.addActionListener(new ActionListener() {
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
			}
		});
		btnResetFields.setBounds(219, 216, 110, 23);
		AddTitle.add(btnResetFields);
	}

	public App(Staff staff) {

		InstanciateApp(staff);

		HomeGUI();

		RegisterCustomerGUI();

		SearchCustomerGUI();

		SearchTitleGUI();

		AddTitleGUI();

		setVisible(true);
	}
}