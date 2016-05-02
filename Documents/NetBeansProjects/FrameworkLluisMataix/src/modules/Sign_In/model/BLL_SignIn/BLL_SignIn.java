/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.Sign_In.model.BLL_SignIn;

import classes.Mongo_DB;
import classes.connectionPool;
import classes.singletonMongo;
import java.sql.Connection;
import javax.swing.JOptionPane;
import modules.Sign_In.model.DAO_SignIn.DAO_SignIn;
import modules.Sign_In.view.Sign_In;
import modules.users.admin.model.DAO.DAO_BBDD;
import modules.users.userreg.model.classes.registered_user;
import modules.users.users.singleton;

/**
 *
 * @author lluis
 */
public class BLL_SignIn {

    /**
     * funcion buscar admin por dni en bases de datos
     */
    public static void searchAdminDB() {

        Connection con = null;

        con = connectionPool.getConexion();
        DAO_BBDD DAOBD = new DAO_BBDD();

        try {
            DAO_SignIn.searchAdminDAO(con);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ha habido un error Logger");
        }
        connectionPool.connectionRelease(con);

    }

    /**
     * buscar cliente por dni en la ventana de Sign In
     *
     */
    public static void searchClientMongo() {
        singletonMongo.client = Mongo_DB.connect();
        DAO_SignIn.searchClientDAO();
        Mongo_DB.disconnect();
    }

    /**
     * 
     * @param userreg
     * @return 
     */
    public static int searchUserreg(registered_user userreg) {

        for (int i = 0; i <= (singleton.userreg.size() - 1); i++) {
            if ((singleton.userreg.get(i)).equals(userreg)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * buscar usuario registrado por dni en la ventana de Sign In
     *
     * @return 
     */
    public static int searchUserregDNI() {
        int location;
        singleton.DNI = Sign_In.dniField.getText();
        singleton.registered_user = new registered_user(singleton.DNI);
        location = searchUserreg(singleton.registered_user);
        return location;
    }

    /**
     *funcion para buscar el usuario y saber si es admin, cliente o usuario registrado
     * @return
     */
    public static boolean searchUser() {
        boolean check = false;
        boolean checkAdmin = false;
        boolean checkClient = false;
        boolean checkUserreg = false;
        searchAdminDB();
        searchClientMongo();
        int locationUserreg = searchUserregDNI();

        if (askDni() == true) {
            if (singleton.admin != null) {
                if (Sign_In.passField.getText().equals(singleton.admin.getPass())) {
                    checkAdmin = true;
                    singleton.user = "Admin";
                }
            } else {
                checkAdmin = false;
            }
            if (singleton.client != null) {
                if (Sign_In.passField.getText().equals(singleton.client.getPass())) {
                    checkClient = true;
                    singleton.user = "Client";
                }
            } else {
                checkClient = false;
            }
            if (locationUserreg != -1) {
                singleton.registered_user = singleton.userreg.get(locationUserreg);
                if (Sign_In.passField.getText().equals(singleton.registered_user.getPass())) {
                    checkUserreg = true;
                    singleton.user = "Userreg";
                } else {
                    checkUserreg = false;
                }
            }

        } else {
            Sign_In.errorSignIn.setText("<html><font color=red>El DNI o la Contraseña introducida no son validas</font></html>");
        }
        if (checkAdmin == true || checkClient == true || checkUserreg == true) {
            Sign_In.errorSignIn.setText("<html><font color=green>DNI y Contraseña correcta, entrando...</font></html>");
            check = true;
        } else {
            Sign_In.errorSignIn.setText("<html><font color=red>El DNI o la Contraseña introducida no son validas</font></html>");
            check = false;
        }

        return check;
    }

    /**
     * funcion para validar el DNI
     * @return 
     */
    public static boolean askDni() {
        boolean checker;
        checker = DAO_SignIn.askdni();
        return checker;
    }

}
