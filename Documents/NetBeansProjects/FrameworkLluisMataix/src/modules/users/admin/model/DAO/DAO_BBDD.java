/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.users.admin.model.DAO;

import classes.ConnectionBBDD;
import classes.date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modules.users.admin.model.classes.admin;
import modules.users.users.singleton;

/**
 *
 * @author lluis
 */
public class DAO_BBDD {

    public static int NewAdmin(Connection connect) {
        int correct = 0;
        PreparedStatement stmt = null;

        try {

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

    //Listamos todos los empleados y los metemos en su array
    public void ViewAdminDAO(Connection con) {

        ResultSet rs = null;
        PreparedStatement stmt = null;

        singleton.useradmin.clear();

        try {
            stmt = con.prepareStatement("SELECT * FROM bbdd_admin.admin");
            rs = stmt.executeQuery();

            admin admin = null;

            while (rs.next()) {
                admin = new admin();
                admin.setDni(rs.getString("Dni"));
                admin.setName(rs.getString("Name"));
                admin.setSurname(rs.getString("Surname"));
                admin.setMobile(rs.getString("Mobile"));
                admin.setEmail(rs.getString("Email"));
                admin.setUser(rs.getString("User"));
                admin.setPass(rs.getString("Pass"));
                admin.setAvatar(rs.getString("Avatar"));
                admin.setState(rs.getString("State"));
                admin.setDate_birthday(new date(rs.getString("Date_Birthday")));
                admin.setAge(rs.getInt("Age"));
                admin.setBenefits(rs.getFloat("Benefits"));
                admin.setAntiquity(rs.getInt("Antiquity"));
                admin.setSalary(rs.getFloat("Salary"));
                admin.setActivity(rs.getInt("Activity"));
                admin.setHiring_date(new date(rs.getString("Hiring_Date")));
                singleton.useradmin.add(admin);

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

    //Modificamos un empleado
    public void modifyAdminDAO(Connection con) {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE bbdd_admin.admin SET Dni=?,Name=?,Surname=?,"
                    + "Mobile=?,Email=?,User=?,Pass=?,Avatar=?,State=?,"
                    + "Date_Birthday=?,Age=?,Benefits=?,Antiquity=?,Salary=?,Activity=?,Hiring_Date=?");

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
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "El administrador ha sido modificado correctamente!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha habido un problema al actualizar el administrador");
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Ha habido un error Logger!");
                }
            }
        }

    }

    //Borramos un empleado
    public boolean DeleteAdminDAO(Connection con) {

        PreparedStatement stmt = null;
        boolean correct = false;

        try {
            stmt = con.prepareStatement("DELETE FROM bbdd_admin.admin WHERE Dni=?");
            stmt.setString(1, singleton.admin.getDni());
            stmt.executeUpdate();
            correct = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha habido un error al eliminar el administrador");
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error en el Logger!");
                }
            }
        }
        return correct;
    }

}
