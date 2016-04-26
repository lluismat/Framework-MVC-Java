/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.Sign_In.model.DAO_SignIn;

import modules.Sign_In.view.Sign_In;
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
    
    
}
