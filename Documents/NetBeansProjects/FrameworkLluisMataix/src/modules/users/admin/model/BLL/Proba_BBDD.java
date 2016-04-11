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
import modules.users.admin.model.classes.admin;

/**
 *
 * @author lluis
 */
public class Proba_BBDD {
    
    public static void loadDates() throws SQLException {
        Connection connect;
        ConnectionBBDD _conexion_DB = new ConnectionBBDD();
        PreparedStatement stmt;
        
        String Dni="12345678Z";
        String Name="Pepe";
        String Surname="Manuel";
        String Mobile="657894561";
        String Email="pepe@gmail.com";
        String User="pepe";
        String Password="1234";
        String Avatar="sdfasdf";
        String State="conectado";
        date Date_Birthday=new date("20/11/1990");
        int Activity=54;
        date Hiring_Date=new date("11/05/2015");
        
        
        connect = _conexion_DB.OpenConnection();
     
        admin admin=new admin(Dni,Name,Surname,Mobile,Email,User,Password,Avatar,State,Date_Birthday,Activity,Hiring_Date);
            stmt=connect.prepareStatement("INSERT INTO bbdd_admin.admin(Dni,Name,Surname,"
                    + "Mobile,Email,User,Pass,Avatar,State,Date_Birthday,Age,Benefits,Antiquity,Salary,Activity,Hiring_Date)"
                    +"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
          
            stmt.setString(1,admin.getDni());
            stmt.setString(2,admin.getName());
            stmt.setString(3, admin.getSurname());
            stmt.setString(4, admin.getMobile());
            stmt.setString(5, admin.getEmail());
            stmt.setString(6, admin.getUser());
            stmt.setString(7, admin.getPass());
            stmt.setString(8,admin.getAvatar() );
            stmt.setString(9, admin.getState());
            stmt.setString(10,admin.getDate_birthday().toString());
            stmt.setInt(11, admin.getAge());
            stmt.setFloat(12, admin.getBenefits());
            stmt.setInt(13,admin.getAntiquity());
            stmt.setFloat(14, admin.getSalary());
            stmt.setInt(15,admin.getActivity());
            stmt.setString(16, admin.getHiring_date().toString());
            
            stmt.executeUpdate();
        _conexion_DB.CloseConnection(connect);
    }
}
