/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.Sign_In.model.DAO_SignIn;

import classes.date;
import classes.singletonMongo;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modules.Sign_In.view.Sign_In;
import modules.users.admin.model.classes.admin;
import modules.users.client.model.classes.client;
import modules.users.users.singleton;
import utils.validate;

/**
 *
 * @author lluis
 */
public class DAO_SignIn {

    // pide dni
    public static boolean askdni() {
        String inserted_dni = "";
        boolean checker = false;

        inserted_dni = Sign_In.dniField.getText();
        inserted_dni = inserted_dni.toUpperCase();
        Sign_In.dniField.setText(inserted_dni.toUpperCase());
        checker = validate.validate_dni(inserted_dni);
        if (inserted_dni.isEmpty()) {
            Sign_In.errorDni.setText("<html><font color=red>No ha introducido ningun dni</font></html>");
            checker = false;
        } else if (checker == false) {
            Sign_In.errorDni.setText("<html><font color=red>El dni introducido no es valido</font></html>");
        } else {
            Sign_In.errorDni.setText("<html><font color=green>DNI Valido</font></html>");
        }
        return checker;
    }

    public static void searchAdminDAO(Connection con) {

        ResultSet rs = null;
        PreparedStatement stmt = null;

        singleton.useradmin.clear();

        try {
            stmt = con.prepareStatement("SELECT * FROM bbdd_admin.admin WHERE dni=?");
            stmt.setString(1, Sign_In.dniField.getText());
            rs = stmt.executeQuery();

            while (rs.next()) {
                singleton.admin = new admin();
                singleton.admin.setDni(rs.getString("Dni"));
                singleton.admin.setName(rs.getString("Name"));
                singleton.admin.setSurname(rs.getString("Surname"));
                singleton.admin.setMobile(rs.getString("Mobile"));
                singleton.admin.setEmail(rs.getString("Email"));
                singleton.admin.setUser(rs.getString("User"));
                singleton.admin.setPass(rs.getString("Pass"));
                singleton.admin.setAvatar(rs.getString("Avatar"));
                singleton.admin.setState(rs.getString("State"));
                singleton.admin.setDate_birthday(new date(rs.getString("Date_Birthday"), 0));
                singleton.admin.setAge(rs.getInt("Age"));
                singleton.admin.setBenefits(rs.getFloat("Benefits"));
                singleton.admin.setAntiquity(rs.getInt("Antiquity"));
                singleton.admin.setSalary(rs.getFloat("Salary"));
                singleton.admin.setActivity(rs.getInt("Activity"));
                singleton.admin.setHiring_date(new date(rs.getString("Hiring_Date"), 0));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha habido un problema al obtener los administradores");
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Ha habido un error Logger");
                }
            }
        }
    }
    
    public static void searchClientDAO(){
        DBCursor cursor = null;
        singleton.userclient.clear();
        client c = new client();
        try {
            
            BasicDBObject searchClient= new BasicDBObject();
            c=new client(Sign_In.dniField.getText());
            searchClient.append("dni",c.getDni());
            cursor = singletonMongo.collection.find(searchClient);
            if(cursor.count()!=0){
                while(cursor.hasNext()){
                    BasicDBObject document = (BasicDBObject) cursor.next();
                    singleton.client = c.Mongo_to_client(document); 
                }
            }else{
                System.out.println("NOT DATA"); 
            }
        } finally {
            if (cursor != null){
		cursor.close();
            }
	}
    }

}
