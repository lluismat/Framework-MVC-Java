/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

/**
 *
 * @author lluis
 */
public class connectionPool {
    
     /* Pool de conexiones */
    public static BasicDataSource dataSource;
    
    /* Logger */
    private static final Logger log = Logger.getLogger("admin");

    
    public static void start_BasicDataSourceFactory() {
        Properties propiedades = new Properties();
        /*
        setMaxActive(): Nº máx de conexiones que se pueden abrir simultáneamente.
        setMinIdle(): Nº mín de conexiones inactivas que queremos que haya. Si el nº de conexiones baja de este nº, se abriran más.
        setMaxIdle(): Nº máx de conexiones inactivas que queremos que haya. Si hay más, se irán cerrando.
        */
        
        propiedades.setProperty("driverClassName", "com.mysql.jdbc.Driver");
        propiedades.setProperty("url", "jdbc:mysql://127.0.0.1:3306/bbdd_admin");
        propiedades.setProperty("maxActive", "10");
        propiedades.setProperty("maxIdle", "8");
        propiedades.setProperty("minIdle", "0");
        propiedades.setProperty("maxWait", "500");
        propiedades.setProperty("initialSize", "5");
        propiedades.setProperty("defaultAutoCommit", "true");
        propiedades.setProperty("username", "root");
        propiedades.setProperty("password", "");
        propiedades.setProperty("validationQuery", "select 1");
        propiedades.setProperty("validationQueryTimeout", "100");
        propiedades.setProperty("initConnectionSqls", "SELECT 1;SELECT 2");
        propiedades.setProperty("poolPreparedStatements", "true");
        propiedades.setProperty("maxOpenPreparedStatements", "10");
        
        try {
            dataSource = (BasicDataSource)BasicDataSourceFactory.createDataSource(propiedades);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    public static Connection getConexion() {
        Connection con=null;
        try {
            con = dataSource.getConnection();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return con;
    }
    
    
    public static void connectionRelease(Connection conexion) {
        try {
            if (null != conexion) 
                // En realidad no cierra, solo libera la conexion.
                conexion.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
  
    
}
