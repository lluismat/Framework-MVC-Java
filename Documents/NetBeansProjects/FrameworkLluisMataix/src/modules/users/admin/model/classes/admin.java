package modules.users.admin.model.classes;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import modules.menu.model.Config;
import modules.menu.model.Language;
import classes.date;
import modules.users.users.users;
import utils.format;

@XStreamAlias("Admin")
public class admin extends users implements Serializable {
	@XStreamAlias("Antiquity")
	private int antiquity;
	@XStreamAlias("Salary")
	private float salary;
	@XStreamAlias("Activity")
	private int activity;
	@XStreamAlias("Hiring_date")
	private date hiring_date;

	// constructor
	public admin(String dni, String name, String surname, String mobile, String email, String user, String pass,
			String avatar, String state, date date_birthday, int activity, date hiring_date) {

		super(dni, name, surname, mobile, email, user, pass, avatar, state, date_birthday);

		this.setSalary(calculate_salary());
		this.activity = activity;
		this.hiring_date = hiring_date;
		super.setBenefits(calculate_benefits());
		this.setAntiquity(calculateantiquity());
	}

	// Constructor per defecte
	public admin(String dni, String name, String surname, String mobile, String email, String user, String pass,
			String avatar, boolean state, date date_birthday, String antiquity, String activity, date hiring_date) {
		super();
	}

	// Constructor clau primaria
	public admin(String dni) {
		super(dni);
	}
        
        public admin(){
            
        }

	// Constructor a peticio del usuari
	public admin(Object election, int i) {

		super(election, i);

		switch (i) {
		case 11:
			this.antiquity = (int) election;
			break;
		case 12:
			this.salary = (float) election;
			break;
		case 13:
			this.activity = (int) election;
			break;
		case 14:
			this.hiring_date = (date) election;
			break;
		}
	}

	// getters and setters

	public int getAntiquity() {
		return antiquity;
	}

	public void setAntiquity(int antiquity) {
		this.antiquity = antiquity;
		super.setBenefits(calculate_benefits());
		this.setSalary(calculate_salary());
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public int getActivity() {
		return activity;
	}

	public void setActivity(int activity) {
		this.activity = activity;
		super.setBenefits(calculate_benefits());
		this.setSalary(calculate_salary());
	}

	public date getHiring_date() {
		return hiring_date;
	}

	public void setHiring_date(date hiring_date) {
		this.hiring_date = hiring_date;
		this.setAntiquity(calculateantiquity());
	}

	// calculate_salary
	public float calculate_salary() {
		float calculate = 1000 + this.calculate_benefits();
		return calculate;
	}

	// calculate_benefits incentive
	public float calculate_benefits() {
		float calculate = 0.0f;
		calculate = this.getActivity() * this.getAntiquity() + 100;
		return calculate;
	}

	// calculate antiquity
	public int calculateantiquity() {
		int calculateantiquity = this.getHiring_date().substractdatesystem();
		return calculateantiquity;
	}

	// toString
	public String toString() {
		String cad = "";

		cad = cad + super.toString();

		cad = cad + Language.getInstance().getProperty("antiquity") + ": " + this.getAntiquity() + "\n";
		cad = cad + Language.getInstance().getProperty("salary") + ": "
				+ format.formatcurrency(this.getSalary(), Config.getInstance()) + "\n";
		cad = cad + Language.getInstance().getProperty("activity") + ": " + this.getActivity() + "\n";
		cad = cad + Language.getInstance().getProperty("hiringdate") + ": "
				+ this.getHiring_date().toString(Config.getInstance().getFormatDate()) + "\n";
		cad = cad + Language.getInstance().getProperty("incentive") + ": "
				+ format.formatcurrency(this.getBenefits(), Config.getInstance()) + "\n";
		return cad;
	}

	/*
	 * // toString clau primaria
	 * 
	 * public String toString(String dni) { return super.toString(dni); }
	 * 
	 * // toString a peticio del usuari
	 * 
	 * public String toString(int i) { String cad = "";
	 * 
	 * if (i < 11) { super.toString(i); } else { switch (i) { case 11: cad = cad
	 * + "Antiquity: " + this.getAntiquity() + "\n"; break; case 12: cad = cad +
	 * "Salary: " + this.getSalary() + "\n"; break; case 13: cad = cad +
	 * "Activity: " + this.getActivity() + "\n"; break; case 14: cad = cad +
	 * "Hiring date: " + this.getHiring_date() + "\n"; break;
	 * 
	 * } } return cad; }
	 */

}
