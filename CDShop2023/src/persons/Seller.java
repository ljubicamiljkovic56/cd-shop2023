package persons;

public class Seller {
	
	private String name;
	private String surname;
	private String username;
	private String password;
	private Gender gender;
	
	public Seller() {
		this.name = "";
		this.surname = "";
		this.username = "";
		this.password = "";
		this.gender = Gender.female;
	}
	
	public Seller(String name, String surname, String username, String password, Gender gender) {
		super();
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.gender = gender;
	}
	
	public Seller(Seller original) {
		this.name = original.name;
		this.surname = original.surname;
		this.username = original.username;
		this.password = original.password;
		this.gender = original.gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Seller name=" + name + 
				", surname=" + surname + 
				", username=" + username + 
				", password=" + password
				+ ", gender=" + gender + " ";
	}
}