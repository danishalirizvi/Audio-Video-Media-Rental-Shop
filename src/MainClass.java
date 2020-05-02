import Interfaces.App;
import Interfaces.Login;

public class MainClass {

	public static void main(String[] args) {
		
		try {
			Login l = new Login();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
