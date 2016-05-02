package modules.users.client.model.classes;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import modules.menu.model.Config;
import modules.menu.model.Language;
import classes.date;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
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

	
        /**
         * Constructor
         * @param dni
         * @param name
         * @param surname
         * @param mobile
         * @param email
         * @param user
         * @param pass
         * @param avatar
         * @param state
         * @param date_birthday
         * @param purchase
         * @param premium
         * @param client_type
         * @param discharge_date 
         */

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

	/**
         * constructor por defecto
         */

	public client() {
		super();
	}

	/**
         * constructor de llave primaria
         * @param dni 
         */

	public client(String dni) {
		super(dni);
	}

	/**
         * constructor a peticio del usuari
         * @param election
         * @param i 
         */

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
        
        /** 
         * pasar de mongo a client
         * @param dBObjectClient
         * @return 
         */
        public client Mongo_to_client(DBObject dBObjectClient){
         
            this.setDni((String)dBObjectClient.get("dni"));
            this.setName((String)dBObjectClient.get("name"));
            this.setSurname((String)dBObjectClient.get("surname"));
            this.setMobile((String)dBObjectClient.get("mobile"));
            this.setEmail((String)dBObjectClient.get("email"));
            this.setUser((String)dBObjectClient.get("user"));
            this.setPass((String)dBObjectClient.get("pass"));
            this.setAvatar((String)dBObjectClient.get("avatar"));
            this.setState((String)dBObjectClient.get("state"));
            this.setDate_birthday(new date((String)dBObjectClient.get("date_birthday"),0));
            Float purchases=Float.valueOf(dBObjectClient.get("purchase").toString());
            this.setPurchase(purchases);
            this.setPremium((String)dBObjectClient.get("premium"));
            this.setClient_type((String)dBObjectClient.get("client_type"));
            this.setAntiquity((int)dBObjectClient.get("antiquity"));
            this.setDischarge_date(new date((String)dBObjectClient.get("discharge_date"),0));
            
            return new client(this.getDni(),this.getName(),this.getSurname(),this.getMobile(),this.getEmail(),this.getUser(),this.getPass(),
            this.getAvatar(),this.getState(),this.getDate_birthday(),this.getPurchase(),this.getPremium(),this.client_type
                    ,this.getDischarge_date());
        }
        
        /**
         * pasa de client a mongo
         * @return 
         */
        public BasicDBObject client_to_Mongo(){
            BasicDBObject dBObjectClient = new BasicDBObject();
            
            dBObjectClient.append("dni", this.getDni());
            dBObjectClient.append("name", this.getName());
            dBObjectClient.append("surname", this.getSurname());
            dBObjectClient.append("mobile", this.getMobile());
            dBObjectClient.append("email", this.getEmail());
            dBObjectClient.append("user", this.getUser());
            dBObjectClient.append("pass", this.getPass());
            dBObjectClient.append("avatar", this.getAvatar());
            dBObjectClient.append("state", this.getState());
            dBObjectClient.append("date_birthday", this.getDate_birthday().toString("dd/MM/yyyy"));
            dBObjectClient.append("purchase", this.getPurchase());
            dBObjectClient.append("premium", this.getPremium());
            dBObjectClient.append("client_type", this.getClient_type());
            dBObjectClient.append("antiquity", this.getAntiquity());
            dBObjectClient.append("discharge_date", this.getDischarge_date().toString("dd/MM/yyyy"));
            

            return dBObjectClient;
        }

	/**
         * calcula la antiguedad
         * @return 
         */
	public int calculateantiquity() {
		int calculateantiquity = this.getDischarge_date().substractdatesystem();
		return calculateantiquity;
	}

	/**
         * gettes and setters
         * @return 
         */

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

	/**
         * calcula los beneficios
         * @return 
         */
	public float calculate_benefits() {
		float calculate = 0.0f;
		calculate = this.getPurchase() + (float) this.getAntiquity();
		return calculate;
	}

	/**
         * toString
         * @return 
         */
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
