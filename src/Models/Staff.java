package Models;
public class Staff {
	
	int id;
	String name;
	long PHNE;
	String username;
	String password;
	
	public Staff(int id, String name, long pHNE, String username, String password) {
		this.id = id;
		this.name = name;
		PHNE = pHNE;
		this.username = username;
		this.password = password;
	}

	public Staff() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPHNE() {
		return PHNE;
	}

	public void setPHNE(long pHNE) {
		PHNE = pHNE;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
