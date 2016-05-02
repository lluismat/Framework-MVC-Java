/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.users.client.model.DAO;

import classes.date;
import com.toedter.calendar.JTextFieldDateEditor;
import modules.users.client.model.classes.client;
import modules.users.client.view.ChangeClient;
import modules.users.client.view.CreateClient;
import modules.users.users.singleton;
import utils.validate;

/**
 *
 * @author lluis
 */
public class DAO_Client {

    /**
     * funcion para crear un cliente
     * @return 
     */
    public static client pideClient() {

        String dni = "", name = "", surname = "", mobile = "", email = "", user = "", pass = "", avatar = "",
                client_type = "", state = "", premium = "";
        date date_birthday = null, discharge_date = null;
        float purchase = 0.0f;

        dni = singleton.DNI;
        name = CreateClient.nameField.getText();
        surname = CreateClient.surnameField.getText();
        mobile = CreateClient.mobileField.getText();
        email = CreateClient.emailField.getText();
        user = CreateClient.userField.getText();
        pass = CreateClient.passField.getText();
        avatar = CreateClient.imgPath.getText();
        state = CreateClient.optionState.getText();
        date_birthday = new date(((JTextFieldDateEditor) CreateClient.birthdayField.getDateEditor()).getText());
        purchase = Integer.parseInt(CreateClient.purchaseField.getText());
        premium = CreateClient.premium.getText();
        client_type = CreateClient.clientTypeField.getText();
        discharge_date = new date(((JTextFieldDateEditor) CreateClient.dischargeDateField.getDateEditor()).getText());

        client client = new client(dni, name, surname, mobile, email, user, pass, avatar, state, date_birthday,
                purchase, premium, client_type, discharge_date);

        return client;
    }

    /**
     * funcion para modificar un cliente
     * @return 
     */
    public static client changeClient() {

        String dni = "", name = "", surname = "", mobile = "", email = "", user = "", pass = "", avatar = "",
                client_type = "", state = "", premium = "";
        date date_birthday = null, discharge_date = null;
        float purchase = 0.0f;

        dni = singleton.DNI;
        name = ChangeClient.nameField.getText();
        surname = ChangeClient.surnameField.getText();
        mobile = ChangeClient.mobileField.getText();
        email = ChangeClient.emailField.getText();
        user = ChangeClient.userField.getText();
        pass = ChangeClient.passField.getText();
        avatar = ChangeClient.imgPath.getText();
        state = ChangeClient.optionState.getText();
        date_birthday = new date(((JTextFieldDateEditor) ChangeClient.birthdayField.getDateEditor()).getText());
        purchase = Float.parseFloat(ChangeClient.purchaseField.getText());
        premium = ChangeClient.premium.getText();
        client_type = ChangeClient.clientTypeField.getText();
        discharge_date = new date(((JTextFieldDateEditor) ChangeClient.dischargeDateField.getDateEditor()).getText());

        client client = new client(dni, name, surname, mobile, email, user, pass, avatar, state, date_birthday,
                purchase, premium, client_type, discharge_date);
        return client;
    }

    /**
     * funcion para validar un dni en la ventana de crear cliente
     * @return 
     */
    public static boolean askdni() {
        String inserted_dni = "";
        boolean checker = false;

        inserted_dni = CreateClient.dniField.getText();
        inserted_dni = inserted_dni.toUpperCase();
        CreateClient.dniField.setText(inserted_dni.toUpperCase());
        checker = validate.validate_dni(inserted_dni);
        if (inserted_dni.isEmpty()) {
            CreateClient.errorDni.setText("<html><font color=red>No ha introducido ningun dni</font></html>");
            checker = false;
        } else if (checker == false) {
            CreateClient.errorDni.setText("<html><font color=red>El dni introducido no es valido</font></html>");
        } else {
            CreateClient.errorDni.setText("<html><font color=green>DNI Valido</font></html>");
        }
        return checker;
    }

    /**
     * valida el nombre en la ventana de crear cliente
     * @return 
     */
    public static boolean askname() {
        String inserted_name = "";
        boolean checker = false;

        inserted_name = CreateClient.nameField.getText();
        checker = validate.validate_name(inserted_name);
        if (inserted_name.isEmpty()) {
            CreateClient.errorName.setText("<html><font color=red>No ha introducido ningun Nombre</font></html>");
            checker = false;
        } else if (checker == false) {
            CreateClient.errorName.setText("<html><font color=red>El nombre introducido no es valido</font></html>");
        } else {
            CreateClient.errorName.setText("<html><font color=green>El nombre es Valido</font></html>");
        }
        return checker;
    }

    /**
     * valida un apellido en la ventana de crea cliente
     * @return 
     */
    public static boolean asksurname() {
        String inserted_surname = "";
        boolean checker = false;

        inserted_surname = CreateClient.surnameField.getText();
        checker = validate.validate_surname(inserted_surname);
        if (inserted_surname.isEmpty()) {
            CreateClient.errorSurname.setText("<html><font color=red>No ha introducido ningun Nombre</font></html>");
            checker = false;
        } else if (checker == false) {
            CreateClient.errorSurname.setText("<html><font color=red>El apellido introducido no es valido</font></html>");
        } else {
            CreateClient.errorSurname.setText("<html><font color=green>El apellido es Valido</font></html>");
        }
        return checker;
    }

    /**
     * valida el numero de mobil en la ventana de crear cliente
     * @return 
     */
    public static boolean askmobile() {
        String inserted_mobile = "";
        boolean checker = false;

        inserted_mobile = CreateClient.mobileField.getText();
        checker = validate.validate_mobile(inserted_mobile);
        if (inserted_mobile.isEmpty()) {
            CreateClient.errorMobile.setText("<html><font color=red>No ha introducido ningun numero de telefono</font></html>");
            checker = false;
        } else if (checker == false) {
            CreateClient.errorMobile.setText("<html><font color=red>El telefono introducido no es valido</font></html>");
        } else {
            CreateClient.errorMobile.setText("<html><font color=green>El telefono es Valido</font></html>");
        }
        return checker;
    }

    /**
     * valida un email en la ventana de crear un cliente
     * @return 
     */
    public static boolean askemail() {
        String inserted_email = "";
        boolean checker = false;

        inserted_email = CreateClient.emailField.getText();
        checker = validate.validate_email(inserted_email);
        if (inserted_email.isEmpty()) {
            CreateClient.errorEmail.setText("<html><font color=red>No ha introducido ningun email</font></html>");
            checker = false;
        } else if (checker == false) {
            CreateClient.errorEmail.setText("<html><font color=red>El Email introducido no es valido</font></html>");
        } else {
            CreateClient.errorEmail.setText("<html><font color=green>El Email es Valido</font></html>");
        }
        return checker;
    }

    /**
     * valida el nombre de usuario en la ventana de crear cliente
     * @return 
     */
    public static boolean askuser() {
        String inserted_user = "";
        boolean checker = false;

        inserted_user = CreateClient.userField.getText();
        checker = validate.validate_user(inserted_user);
        if (inserted_user.isEmpty()) {
            CreateClient.errorUser.setText("<html><font color=red>No ha introducido ningun usuario</font></html>");
            checker = false;
        } else if (checker == false) {
            CreateClient.errorUser.setText("<html><font color=red>El usuario introducido no es valido</font></html>");
        } else {
            CreateClient.errorUser.setText("<html><font color=green>El usuario es Valido</font></html>");
        }
        return checker;
    }

    /**
     * valida la contraseña en la ventana de crear cliente
     * @return 
     */
    public static boolean askpassword() {
        String inserted_password = "";
        boolean checker = false;

        inserted_password = CreateClient.passField.getText();
        checker = validate.validate_password(inserted_password);
        if (inserted_password.isEmpty()) {
            CreateClient.errorPass.setText("<html><font color=red>No has introducido ninguna contraseña</font></html>");
            checker = false;
        } else if (checker == false) {
            CreateClient.errorPass.setText("<html><font color=red>La contraseña introducida no es valida</font></html>");
        } else {
            CreateClient.errorPass.setText("<html><font color=green>La contraseña es Valida</font></html>");
        }
        return checker;
    }

    /**
     * valida la fecha de nacimiento en la ventana de crear cliente
     * @return 
     */
    public static boolean askdate() {
        String inserted_date_birthday = "";
        int age = 0;
        boolean checker = true;

        inserted_date_birthday = ((JTextFieldDateEditor) CreateClient.birthdayField.getDateEditor()).getText();
        checker = validate.validate_dateformat(inserted_date_birthday);

        date date = new date(inserted_date_birthday);
        checker = date.checkdate();

        age = date.substractdatesystem();
        if (age >= 18) {
            CreateClient.errorBirthday.setText("<html><font color=green>La fecha de nacimiento introducida es correcta</font></html>");

        } else {
            CreateClient.errorBirthday.setText("<html><font color=red>La contraseña introducida no es valida</font></html>");
        }
        return checker;
    }

    /**
     * valida las compras en la ventana de crear cliente
     * @return 
     */
    public static boolean askpurchase() {
        String inserted_purchase = "";
        boolean checker = false;
       
            inserted_purchase = CreateClient.purchaseField.getText();
            checker = validate.validate_purchase(inserted_purchase);
            if (inserted_purchase.isEmpty()) {
                CreateClient.errorPurchases.setText("<html><font color=red>No has introducido ninguna compra</font></html>");
                checker = false;
            } else if (checker == false) {
                CreateClient.errorPurchases.setText("<html><font color=red>La compra introducida no es valida</font></html>");
            } else {
                CreateClient.errorPurchases.setText("<html><font color=green>La compra es Valida</font></html>");
            }
        return checker;
    }

    /**
     * valida el tipo de cliente
     * @return 
     */
    public static boolean askclient_type() {
        String inserted_clienttype = "";
        boolean checker = false;
       
            inserted_clienttype = CreateClient.clientTypeField.getText();
            checker = validate.validate_client_type(inserted_clienttype);
            if (inserted_clienttype.isEmpty()) {
                CreateClient.errorclientType.setText("<html><font color=red>No has introducido ninguna tipo de cliente</font></html>");
                checker = false;
            } else if (checker == false) {
                CreateClient.errorclientType.setText("<html><font color=red>El tipo de cliente introducido no es valido</font></html>");
            } else {
                CreateClient.errorclientType.setText("<html><font color=green>El tipo de client es Valido</font></html>");
            }
        return checker;
    }

    /**
     * valida la fecha de alta en la ventana de crear cliente
     * @return 
     */
    public static boolean askdischargedate() {
        boolean checker = false;
        date dischargeDate = null;
        int difage = 0, difage2 = 0;
        String inserted_dischargedate = "";
        String dateBirthday = ((JTextFieldDateEditor) CreateClient.birthdayField.getDateEditor()).getText();
        date birthday = new date(dateBirthday);

        inserted_dischargedate = ((JTextFieldDateEditor) CreateClient.dischargeDateField.getDateEditor()).getText();
        checker = validate.validate_dateformat(inserted_dischargedate);

        dischargeDate = new date(inserted_dischargedate);

        difage = dischargeDate.comparedate(birthday);
        difage2 = dischargeDate.comparedatesystem();
        if ((difage == 0) || (difage == 1)) {
            CreateClient.errorDischargeDate.setText("<html><font color=red>La fecha de alta introducida es incorrecta,<br>"
                    + "la fecha de contratacion debe ser posterior a la fecha de nacimiento</br></font></html>");
            checker = false;
        } else if (difage2 == 1) {
            CreateClient.errorDischargeDate.setText("<html><font color=red>La fecha de alta introducida es incorrecta,<br> "
                    + "la fecha de alta debe ser anterior a la fecha actual</br></font></html>");
            checker = false;
        } else if (birthday.substractdate(dischargeDate) >= 18) {
            CreateClient.errorDischargeDate.setText("<html><font color=green>La fecha de alta introducida es "
                    + "correcta</font></html>");
            checker = true;
        } else {
            CreateClient.errorDischargeDate.setText("<html><font color=red>La fecha de alta introducida "
                    + "es incorrecta,<br> "
                    + "debes tener al menos 18 años de edad</br></font></html>");
            checker = false;
        } // fi 3 else // fi 2 else // fi 1 else  
        return checker;
    }


    /**
     * MODIFICAR CLIENTE
     */
    /**
    * valida el nombre en la ventana de modificar cliente
    * @return 
    */
    public static boolean changeName() {
        String inserted_name = "";
        boolean checker = false;

        inserted_name = ChangeClient.nameField.getText();
        checker = validate.validate_name(inserted_name);
        if (checker == false) {
            ChangeClient.errorName.setText("<html><font color=red>El nombre introducido no es valido</font></html>");
        } else {
            ChangeClient.errorName.setText("<html><font color=green>El nombre es Valido</font></html>");
            checker = true;
        }
        return checker;
    }

    /**
     * valida el apellido en la ventana de modificar cliente
     * @return 
     */
    public static boolean changeSurname() {
        String inserted_surname = "";
        boolean checker = false;

        inserted_surname = ChangeClient.surnameField.getText();
        checker = validate.validate_surname(inserted_surname);
        if (checker == false) {
            ChangeClient.errorSurname.setText("<html><font color=red>El apellido introducido no es valido</font></html>");
        } else {
            ChangeClient.errorSurname.setText("<html><font color=green>El apellido es Valido</font></html>");
            checker = true;
        }
        return checker;
    }

    /**
     * valida el numero de mobil en la ventana de modificar cliente
     * @return 
     */
    public static boolean changeMobile() {
        String inserted_mobile = "";
        boolean checker = false;

        inserted_mobile = ChangeClient.mobileField.getText();
        checker = validate.validate_mobile(inserted_mobile);
        if (checker == false) {
            ChangeClient.errorMobile.setText("<html><font color=red>El telefono introducido no es valido</font></html>");
        } else {
            ChangeClient.errorMobile.setText("<html><font color=green>El telefono es Valido</font></html>");
            checker = true;
        }
        return checker;
    }

    /**
     * valida el email en la ventana de modificar cliente
     * @return 
     */
    public static boolean changeEmail() {
        String inserted_email = "";
        boolean checker = false;

        inserted_email = ChangeClient.emailField.getText();
        checker = validate.validate_email(inserted_email);
        if (checker == false) {
            ChangeClient.errorEmail.setText("<html><font color=red>El Email introducido no es valido</font></html>");
        } else {
            ChangeClient.errorEmail.setText("<html><font color=green>El Email es Valido</font></html>");
            checker = true;
        }
        return checker;
    }

    /**
     * valida el nombre de usuario en la ventana de modificar cliente
     * @return 
     */
    public static boolean changeUser() {
        String inserted_user = "";
        boolean checker = false;

        inserted_user = ChangeClient.userField.getText();
        checker = validate.validate_user(inserted_user);
        if (checker == false) {
            ChangeClient.errorUser.setText("<html><font color=red>El usuario introducido no es valido</font></html>");
        } else {
            ChangeClient.errorUser.setText("<html><font color=green>El usuario es Valido</font></html>");
            checker = true;
        }
        return checker;
    }

    /**
     * valida la contraseña en la ventana de modificar cliente
     * @return 
     */
    public static boolean changePassword() {
        String inserted_password = "";
        boolean checker = false;

        inserted_password = ChangeClient.passField.getText();
        checker = validate.validate_password(inserted_password);
        if (checker == false) {
            ChangeClient.errorPass.setText("<html><font color=red>La contraseña introducida no es valida</font></html>");
        } else {
            ChangeClient.errorPass.setText("<html><font color=green>La contraseña es Valida</font></html>");
            checker = true;
        }
        return checker;
    }

    /**
     * valida la fecha de nacimiento en la ventana de modificar cliente
     * @return 
     */
    public static boolean changeDate() {
        String inserted_date_birthday = "";
        int age = 0;
        boolean checker = false;

        inserted_date_birthday = ((JTextFieldDateEditor) ChangeClient.birthdayField.getDateEditor()).getText();
        checker = validate.validate_dateformat(inserted_date_birthday);
        date date = new date(inserted_date_birthday);
        checker = date.checkdate();

        age = date.substractdatesystem();
        if (age >= 18) {
            ChangeClient.errorBirthday.setText("<html><font color=green>La fecha de nacimiento introducida es correcta</font></html>");

        } else {
            ChangeClient.errorBirthday.setText("<html><font color=red>La contraseña introducida no es valida</font></html>");

        }
        return checker;
    }

    /**
     * valida las compras en la ventana de modificar cliente
     * @return 
     */
    public static boolean changePurchase() {
        String inserted_purchase = "";
        boolean checker = false;
        
            inserted_purchase = ChangeClient.purchaseField.getText();
            checker = validate.validate_purchase(inserted_purchase);
            if (inserted_purchase.isEmpty()) {
                ChangeClient.errorPurchases.setText("<html><font color=red>No has introducido ninguna compra</font></html>");
                checker = false;
            } else if (checker == false) {
                ChangeClient.errorPurchases.setText("<html><font color=red>La compra introducida no es valida</font></html>");
            } else {
                ChangeClient.errorPurchases.setText("<html><font color=green>La compra es Valida</font></html>");
            }
        
        return checker;
    }

    /**
     * valida el tipo de cliente en la ventana de modificar cliente
     * @return 
     */
    public static boolean changeClient_type() {
        String inserted_clienttype = "";
        boolean checker = false;
       
            inserted_clienttype = ChangeClient.clientTypeField.getText();
            checker = validate.validate_client_type(inserted_clienttype);
            if (inserted_clienttype.isEmpty()) {
                ChangeClient.errorclientType.setText("<html><font color=red>No has introducido ninguna tipo de cliente</font></html>");
                checker = false;
            } else if (checker == false) {
                ChangeClient.errorclientType.setText("<html><font color=red>El tipo de cliente introducido no es valido</font></html>");
            } else {
                ChangeClient.errorclientType.setText("<html><font color=green>El tipo de client es Valido</font></html>");
            }
            
        return checker;
    }
    
    /**
     * valida la fecha de alta en la ventana de modificar cliente
     * @return 
     */
    public static boolean changedischargedate() {
        boolean checker = false;
        date dischargeDate = null;
        int difage = 0, difage2 = 0;
        String inserted_dischargedate = "";
        String dateBirthday = ((JTextFieldDateEditor) ChangeClient.birthdayField.getDateEditor()).getText();
        date birthday = new date(dateBirthday);

        inserted_dischargedate = ((JTextFieldDateEditor) ChangeClient.dischargeDateField.getDateEditor()).getText();

        dischargeDate = new date(inserted_dischargedate);

        difage = dischargeDate.comparedate(birthday);
        difage2 = dischargeDate.comparedatesystem();
        if ((difage == 0) || (difage == 1)) {
            ChangeClient.errorDischargeDate.setText("<html><font color=red>La fecha de alta introducida es incorrecta,<br>"
                    + "la fecha de contratacion debe ser posterior a la fecha de nacimiento</br></font></html>");
            checker = false;
        } else if (difage2 == 1) {
            ChangeClient.errorDischargeDate.setText("<html><font color=red>La fecha de alta introducida es incorrecta,<br> "
                    + "la fecha de alta debe ser anterior a la fecha actual</br></font></html>");
            checker = false;
        } else if (birthday.substractdate(dischargeDate) >= 18) {
            ChangeClient.errorDischargeDate.setText("<html><font color=green>La fecha de alta introducida es "
                    + "correcta</font></html>");
            checker = true;
        } else {
            ChangeClient.errorDischargeDate.setText("<html><font color=red>La fecha de alta introducida "
                    + "es incorrecta,<br> "
                    + "debes tener al menos 18 años de edad</br></font></html>");
            checker = false;
        } // fi 3 else // fi 2 else // fi 1 else  
        return checker;
    }
    
    
}
