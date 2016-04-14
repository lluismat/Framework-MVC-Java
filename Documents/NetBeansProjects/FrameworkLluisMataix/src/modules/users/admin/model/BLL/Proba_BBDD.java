/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.users.admin.model.BLL;

import classes.ConnectionBBDD;
import classes.date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modules.users.admin.model.classes.admin;
import modules.users.users.singleton;

/**
 *
 * @author lluis
 */
public class Proba_BBDD {

    public static void loadDates() throws SQLException {
        Connection connect;
        ConnectionBBDD _conexion_DB = new ConnectionBBDD();
        PreparedStatement stmt;

        String Dni = "12345678Z";
        String Name = "Pepe";
        String Surname = "Manuel";
        String Mobile = "657894561";
        String Email = "pepe@gmail.com";
        String User = "pepe";
        String Password = "1234";
        String Avatar = "sdfasdf";
        String State = "conectado";
        date Date_Birthday = new date("20/11/1990");
        int Activity = 54;
        date Hiring_Date = new date("11/05/2015");

        connect = _conexion_DB.OpenConnection();

        admin admin = new admin(Dni, Name, Surname, Mobile, Email, User, Password, Avatar, State, Date_Birthday, Activity, Hiring_Date);
        stmt = connect.prepareStatement("INSERT INTO bbdd_admin.admin(Dni,Name,Surname,"
                + "Mobile,Email,User,Pass,Avatar,State,Date_Birthday,Age,Benefits,Antiquity,Salary,Activity,Hiring_Date)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

        stmt.setString(1, admin.getDni());
        stmt.setString(2, admin.getName());
        stmt.setString(3, admin.getSurname());
        stmt.setString(4, admin.getMobile());
        stmt.setString(5, admin.getEmail());
        stmt.setString(6, admin.getUser());
        stmt.setString(7, admin.getPass());
        stmt.setString(8, admin.getAvatar());
        stmt.setString(9, admin.getState());
        stmt.setString(10, admin.getDate_birthday().toString());
        stmt.setInt(11, admin.getAge());
        stmt.setFloat(12, admin.getBenefits());
        stmt.setInt(13, admin.getAntiquity());
        stmt.setFloat(14, admin.getSalary());
        stmt.setInt(15, admin.getActivity());
        stmt.setString(16, admin.getHiring_date().toString());

        stmt.executeUpdate();
        _conexion_DB.CloseConnection(connect);
    }

    public static int NewAdmin() {
        int correct = 0;
        PreparedStatement stmt = null;
        Connection connect;
        ConnectionBBDD _conexion_DB = new ConnectionBBDD();

        try {
            connect = _conexion_DB.OpenConnection();
            for (int i = 0; i <= (singleton.useradmin.size() - 1); i++) {
                
                singleton.admin = singleton.useradmin.get(i);
                

                stmt = connect.prepareStatement("INSERT INTO bbdd_admin.admin(Dni,Name,Surname,"
                        + "Mobile,Email,User,Pass,Avatar,State,Date_Birthday,Age,Benefits,Antiquity,Salary,Activity,Hiring_Date)"
                        + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                stmt.setString(1, singleton.admin.getDni());
                stmt.setString(2, singleton.admin.getName());
                stmt.setString(3, singleton.admin.getSurname());
                stmt.setString(4, singleton.admin.getMobile());
                stmt.setString(5, singleton.admin.getEmail());
                stmt.setString(6, singleton.admin.getUser());
                stmt.setString(7, singleton.admin.getPass());
                stmt.setString(8, singleton.admin.getAvatar());
                stmt.setString(9, singleton.admin.getState());
                stmt.setString(10, singleton.admin.getDate_birthday().toString());
                stmt.setInt(11, singleton.admin.getAge());
                stmt.setFloat(12, singleton.admin.getBenefits());
                stmt.setInt(13, singleton.admin.getAntiquity());
                stmt.setFloat(14, singleton.admin.getSalary());
                stmt.setInt(15, singleton.admin.getActivity());
                stmt.setString(16, singleton.admin.getHiring_date().toString());
 
                correct = stmt.executeUpdate();
            }
            
            _conexion_DB.CloseConnection(connect);
            JOptionPane.showMessageDialog(null, "El usuario ha sido dado de alta correctamente");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha habido un problema al insertar un nuevo usuario");
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Ha habido un error Logger");
                }
            }
        }
        return correct;
    }

}
