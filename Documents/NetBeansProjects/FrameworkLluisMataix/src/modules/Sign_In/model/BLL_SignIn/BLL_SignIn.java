/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.Sign_In.model.BLL_SignIn;

import modules.Sign_In.model.DAO_SignIn.DAO_SignIn;
import modules.Sign_In.view.Sign_In;
import modules.users.admin.model.classes.admin;
import modules.users.client.model.classes.client;
import modules.users.userreg.model.classes.registered_user;
import modules.users.users.singleton;

/**
 *
 * @author lluis
 */
public class BLL_SignIn {

    public static int searchAdmin(admin admin) {

        for (int i = 0; i <= (singleton.useradmin.size() - 1); i++) {
            if ((singleton.useradmin.get(i)).equals(admin)) {
                return i;
            }
        }
        return -1;
    }

    //buscar admin por dni en la ventana de Sign In
    public static int searchAdminDNI() {
        int location;
        singleton.DNI = Sign_In.dniField.getText();
        singleton.admin = new admin(singleton.DNI);
        location = searchAdmin(singleton.admin);
        return location;
    }

    public static int searchClient(client client) {

        for (int i = 0; i <= (singleton.userclient.size() - 1); i++) {
            if ((singleton.userclient.get(i)).equals(client)) {
                return i;
            }
        }
        return -1;
    }

    //buscar cliente por dni en la ventana de Sign In
    public static int searchClientDNI() {
        int location;
        singleton.DNI = Sign_In.dniField.getText();
        singleton.client = new client(singleton.DNI);
        location = searchClient(singleton.client);
        return location;
    }

    public static int searchUserreg(registered_user userreg) {

        for (int i = 0; i <= (singleton.userreg.size() - 1); i++) {
            if ((singleton.userreg.get(i)).equals(userreg)) {
                return i;
            }
        }
        return -1;
    }

    //buscar usuario registrado por dni en la ventana de Sign In
    public static int searchUserregDNI() {
        int location;
        singleton.DNI = Sign_In.dniField.getText();
        singleton.registered_user = new registered_user(singleton.DNI);
        location = searchUserreg(singleton.registered_user);
        return location;
    }

    public static boolean searchUser() {
        boolean check;
        boolean checkAdmin = false;
        boolean checkClient = false;
        boolean checkUserreg = false;
        int locationAdmin = searchAdminDNI();
        int locationClient = searchClientDNI();
        int locationUserreg = searchUserregDNI();

        if (askDni() == true) {
            if (locationAdmin != -1) {
                singleton.admin = singleton.useradmin.get(locationAdmin);
                System.out.println("Admin");
                if (Sign_In.passField.getText().equals(singleton.admin.getPass())) {
                    Sign_In.errorSignIn.setText("<html><font color=green>DNI y Contraseña correcta, entrando...</font></html>");
                    checkAdmin = true;
                    singleton.user="Admin";
                } else {
                    Sign_In.errorSignIn.setText("<html><font color=red>El DNI o la Contraseña introducida no son validas</font></html>");
                    checkAdmin = false;
                }
            } if (locationClient != -1) {
                singleton.client = singleton.userclient.get(locationClient);
                System.out.println("Client");
                if (Sign_In.passField.getText().equals(singleton.client.getPass())) {
                    Sign_In.errorSignIn.setText("<html><font color=green>DNI y Contraseña correcta, entrando...</font></html>");
                    checkClient = true;
                    singleton.user="Client";
                } else {
                    Sign_In.errorSignIn.setText("<html><font color=red>El DNI o la Contraseña introducida no son validas</font></html>");
                    checkClient = false;
                }

            } if (locationUserreg != -1) {
                singleton.registered_user = singleton.userreg.get(locationUserreg);
                System.out.println("userreg");
                if (Sign_In.passField.getText().equals(singleton.registered_user.getPass())) {
                    Sign_In.errorSignIn.setText("<html><font color=green>DNI y Contraseña correcta, entrando...</font></html>");
                    checkUserreg = true;
                    singleton.user="Userreg";
                } else {
                    Sign_In.errorSignIn.setText("<html><font color=red>El DNI o la Contraseña introducida no son validas</font></html>");
                    checkUserreg = false;
                }

            }

        }else{
            Sign_In.errorSignIn.setText("<html><font color=red>El DNI o la Contraseña introducida no son validas</font></html>");
        }
        if(checkAdmin==true || checkClient==true || checkUserreg==true){
            check=true;
        }else{
            check=false;
        }

        return check;
    }

    public static boolean askDni() {
        boolean checker;
        checker = DAO_SignIn.askdni();
        return checker;
    }

}
