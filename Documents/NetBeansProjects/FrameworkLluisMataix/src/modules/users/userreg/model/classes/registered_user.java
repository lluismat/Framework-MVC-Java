package modules.users.userreg.model.classes;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import modules.menu.model.Language;
import classes.date;
import modules.users.users.users;

@XStreamAlias("Registered_user")
public class registered_user extends users implements Serializable {

    @XStreamAlias("Activity")
    private int activity;
    @XStreamAlias("karma")
    private String karma;

    /**
     * constructor
     *
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
     * @param activity
     */
    public registered_user(String dni, String name, String surname, String mobile, String email, String user,
            String pass, String avatar, String state, date date_birthday, int activity) {

        super(dni, name, surname, mobile, email, user, pass, avatar, state, date_birthday);

        this.activity = activity;
        super.setBenefits(calculate_benefits());
        this.setKarma(calculate_karma());

    }

    /**
     * constructor por defecto
     */
    public registered_user() {
        super();
    }
    
    
/**
 * constructor de llave primaria
 * @param dni 
 */
    public registered_user(String dni) {
        super(dni);

    }

    /**
     * Constructor a peticion del usuario
     * @param election
     * @param i 
     */
    public registered_user(Object election, int i) {
        super(election, i);
        switch (i) {
            case 11:
                activity = (int) election;
                break;
        }
    }

    /**
     * getters and setters
     * @return 
     */
    public int getActivity() {
        return activity;
    }

    public void setActivity(int activity) {
        this.activity = activity;
        super.setBenefits(calculate_benefits());
        this.setKarma(calculate_karma());
    }

    public String getKarma() {
        return karma;
    }

    public void setKarma(String karma) {
        this.karma = karma;
    }

    /**
     * calcular beneficios points
     * @return 
     */
    public float calculate_benefits() {
        float calculate = 0.0f;
        calculate = this.getActivity() + 100;
        return calculate;
    }

   /**
    * calcular karma
    * @return 
    */
    public String calculate_karma() {
        String karma = "";
        if (this.getActivity() < 100) {
            karma = "Low";
        } else if ((this.getActivity() >= 100) && (this.getActivity() < 200)) {
            karma = "Medium";
        } else {
            karma = "Hight";
        }
        return karma;

    }

    /**
     * toString
     * @return 
     */
    public String toString() {
        String cad = "";

        cad = cad + super.toString() + "\n";

        cad = cad + Language.getInstance().getProperty("activity") + ": " + this.getActivity() + "\n";
        cad = cad + Language.getInstance().getProperty("karma") + ": " + this.getKarma() + "\n";
        cad = cad + Language.getInstance().getProperty("points") + ": " + this.getBenefits() + "\n";

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
	 * + "Activity: " + this.getActivity() + "\n"; break; } }
	 * 
	 * return cad; }
     */
}
