package modules.users.client.model.classes;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import modules.menu.model.Config;
import modules.menu.model.Language;
import classes.date;
import modules.users.users.users;
import utils.format;

@XStreamAlias("Clients")
public class client extends users implements Serializable {
	@XStreamAlias("Purchases")
	private float purchase;
	@XStreamAlias("Client_Type")
	private String client_type;
	@XStreamAlias("Antiquity")
	private int antiquity;
	@XStreamAlias("Discharge_date")
	private date discharge_date;
	@XStreamAlias("Premium")
	private String premium;

	// constructor

	public client(String dni, String name, String surname, String mobile, String email, String user, String pass,
			String avatar, String state, date date_birthday, float purchase, String premium, String client_type,
			date discharge_date) {

		super(dni, name, surname, mobile, email, user, pass, avatar, state, date_birthday);

		this.purchase = purchase;
		this.client_type = client_type;
		this.discharge_date = discharge_date;
		this.premium = premium;
		super.setBenefits(calculate_benefits());
		this.setAntiquity(calculateantiquity());
	}

	// Constructor per defecte

	public client() {
		super();
	}

	// Constructor de clau primaria

	public client(String dni) {
		super(dni);
	}

	// Constructor a peticio del usuari

	public client(Object election, int i) {
		super(election, i);
		switch (i) {
		case 11:
			this.purchase = (float) election;
			break;
		case 12:
			this.client_type = (String) election;
			break;
		case 13:
			this.antiquity = (int) election;
			break;
		case 14:
			this.discharge_date = (date) election;
			break;
		case 15:
			this.premium = (String) election;
			break;
		}
	}

	// calculate antiquity
	public int calculateantiquity() {
		int calculateantiquity = this.getDischarge_date().substractdatesystem();
		return calculateantiquity;
	}

	// getters and setters

	public float getPurchase() {
		return purchase;
	}

	public void setPurchase(float purchase) {
		this.purchase = purchase;
		super.setBenefits(calculate_benefits());
	}

	public String getPremium() {
		return premium;
	}

	public void setPremium(String premium) {
		this.premium = premium;
	}

	public String getClient_type() {
		return client_type;
	}

	public void setClient_type(String client_type) {
		this.client_type = client_type;
	}

	public int getAntiquity() {
		return antiquity;
	}

	public void setAntiquity(int antiquity) {
		this.antiquity = antiquity;
		super.setBenefits(calculate_benefits());
	}

	public date getDischarge_date() {
		return discharge_date;
	}

	public void setDischarge_date(date discharge_date) {
		this.discharge_date = discharge_date;
		this.setAntiquity(calculateantiquity());
	}

	// calculate_benefits discount
	public float calculate_benefits() {
		float calculate = 0.0f;
		calculate = this.getPurchase() + (float) this.getAntiquity();
		return calculate;
	}

	// toString
	public String toString() {
		String cad = "";
		cad = cad + super.toString();

		cad = cad + Language.getInstance().getProperty("purchase") + ": "
				+ format.formatcurrency(this.getPurchase(), Config.getInstance()) + "\n";
		cad = cad + Language.getInstance().getProperty("clienttype") + ": " + this.getClient_type() + "\n";
		cad = cad + Language.getInstance().getProperty("antiquity") + ": " + this.getAntiquity() + "\n";
		cad = cad + Language.getInstance().getProperty("dischargedate") + ": "
				+ this.getDischarge_date().toString(Config.getInstance().getFormatDate()) + "\n";
		cad = cad + Language.getInstance().getProperty("premium") + ": " + this.getPremium() + "\n";
		cad = cad + Language.getInstance().getProperty("discount") + ": "
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
	 * + "Purchase: " + this.getPurchase() + "\n"; break; case 12: cad = cad +
	 * "Client Type: " + this.getClient_type() + "\n"; break; case 13: cad = cad
	 * + "Antiquity: " + this.getAntiquity() + "\n"; break; case 14: cad = cad +
	 * "Discharge date: " + this.getDischarge_date() + "\n"; break; case 15: cad
	 * = cad + "Premium: " + this.isPremium() + "\n"; break;
	 * 
	 * } } return cad; }
	 */

}
