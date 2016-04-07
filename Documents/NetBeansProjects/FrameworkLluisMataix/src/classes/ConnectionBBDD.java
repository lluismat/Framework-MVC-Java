/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author lluis
 */
public class ConnectionBBDD {
    
    
    /**
     * Abrir conexion con la BBDD
     * 
     * @return
     * */
    
    public Connection OpenConnection54() {

        Connection connect = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String urlOdbc = "jdbc:mysql://127.0.0.1:3306/Framework_BBDD";
            connect = (java.sql.DriverManager.getConnection(urlOdbc, "root", ""));
            
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Ha sido imposible establecer la conexion!");
        }
        return connect;
    }
    
    
    /**
     * cerramos la conexion en la BBDD
     *
     * @param con
     */
    public void CloseConnection(Connection connect) {
        try {
            if (connect != null) {
                connect.close();
            }
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Ha sido imposible cerrar la conexion!");
        }
    }
    
}
