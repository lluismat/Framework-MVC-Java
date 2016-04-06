package modules.users.users;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import modules.Config.model.Config;
import modules.Config.model.Language;
import classes.date;

@XStreamAlias("Users")
public abstract class users implements Comparable<users>, Serializable {
	@XStreamAlias("DNI")
	private String dni;
	@XStreamAlias("Name")
	private String name;
	@XStreamAlias("Surname")
	private String surname;
	@XStreamAlias("Mobile")
	private String mobile;
	@XStreamAlias("Email")
	private String email;
	@XStreamAlias("User")
	private String user;
	@XStreamAlias("Pass")
	private String pass;
	@XStreamAlias("Avatar")
	private String avatar;
	@XStreamAlias("State")
	private String state;
	@XStreamAlias("Date_Birthday")
	private date date_birthday = null;
	@XStreamAlias("Age")
	private int age = 0;
	@XStreamAlias("Benefits")
	private float benefits = 0.0f;

	// Constructor
	public users(String dni, String name, String surname, String mobile, String email, String user, String pass,
			String avatar, String state, date date_birthday) {
		this.dni = dni;
		this.name = name;
		this.surname = surname;
		this.mobile = mobile;
		this.email = email;
		this.user = user;
		this.pass = pass;
		this.avatar = avatar;
		this.state = state;
		this.date_birthday = date_birthday;
		this.setAge(calculateage());
	}

	// constructor per defecte

	public users() {
	}

	// constructor clau primaria

	public users(String dni) {
		this.dni = dni;
	}

	// Constructor a peticio de usuari

	public users(Object election, int i) {

		switch (i) {

		case 0:
			this.dni = (String) election;
			break;
		case 1:
			this.name = (String) election;
			break;
		case 2:
			this.surname = (String) election;
			break;
		case 3:
			this.mobile = (String) election;
			break;
		case 4:
			this.email = (String) election;
			break;
		case 5:
			this.user = (String) election;
			break;
		case 6:
			this.pass = (String) election;
			break;
		case 7:
			this.avatar = (String) election;
			break;
		case 8:
			this.state = (String) election;
			break;
		case 9:
			this.date_birthday = (date) election;
			break;
		}
	}

	// getters and setters

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getAvatar() {
		return this.avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public date getDate_birthday() {
		return this.date_birthday;
	}

	public void setDate_birthday(date date_birthday) {
		this.date_birthday = date_birthday;
		this.setAge(calculateage());
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public float getBenefits() {
		return benefits;
	}

	public void setBenefits(float benefits) {
		this.benefits = benefits;
	}

	public int calculateage() {

		int calculateage = this.getDate_birthday().substractdatesystem();

		return calculateage;
	}

	public int compareTo(users users) {
		if (this.getDni().compareTo(users.getDni()) > 0)
			return 1;
		if (this.getDni().compareTo(users.getDni()) < 0)
			return -1;
		return 0;
	}

	public boolean equals(Object param) {
		return getDni().equals(((users) param).getDni());
	}
	// toString
	public String toString() {
		String cad = "";
		cad = cad + Language.getInstance().getProperty("dni") + ": " + this.getDni() + "\n";
		cad = cad + Language.getInstance().getProperty("name") + ": " + this.getName() + "\n";
		cad = cad + Language.getInstance().getProperty("surname") + ": " + this.getSurname() + "\n";
		cad = cad + Language.getInstance().getProperty("mobile") + ": " + this.getMobile() + "\n";
		cad = cad + Language.getInstance().getProperty("email") + ": " + this.getEmail() + "\n";
		cad = cad + Language.getInstance().getProperty("user") + ": " + this.getUser() + "\n";
		cad = cad + Language.getInstance().getProperty("pass") + ": " + this.getPass() + "\n";
		cad = cad + Language.getInstance().getProperty("avatar") + ": " + this.getAvatar() + "\n";
		cad = cad + Language.getInstance().getProperty("state") + ": " + this.getState() + "\n";
		cad = cad + Language.getInstance().getProperty("birthday") + ": "
				+ this.getDate_birthday().toString(Config.getInstance().getFormatDate()) + "\n";
		cad = cad + Language.getInstance().getProperty("age") + ": " + this.getAge() + "\n";

		return cad;
	}

	public abstract float calculate_benefits();
}
