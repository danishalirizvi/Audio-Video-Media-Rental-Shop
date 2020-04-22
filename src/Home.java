import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

public class Home extends JFrame {

	private JPanel contentPane;

	public Home(Staff staff) {
		
		setVisible(true);
		setTitle("Home");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 507, 343);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHome = new JLabel("Home");
		lblHome.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblHome.setBounds(10, 11, 89, 37);
		contentPane.add(lblHome);
		
		JLabel lblActiveUser = new JLabel("Active User");
		lblActiveUser.setBounds(324, 11, 55, 14);
		contentPane.add(lblActiveUser);
		
		JLabel usernameLabel = new JLabel("User Not Found!");
		usernameLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		usernameLabel.setBounds(279, 26, 145, 22);
		contentPane.add(usernameLabel);
		
		usernameLabel.setText(staff.name);
		
	}

}
