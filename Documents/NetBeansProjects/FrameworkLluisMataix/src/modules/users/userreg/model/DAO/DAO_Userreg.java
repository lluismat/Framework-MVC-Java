/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.users.userreg.model.DAO;

import classes.date;
import com.toedter.calendar.JTextFieldDateEditor;
import modules.users.userreg.model.classes.registered_user;
import modules.users.userreg.view.ChangeUserreg;
import modules.users.userreg.view.createUserreg;
import modules.users.users.singleton;
import utils.validate;

/**
 *
 * @author lluis
 */
public class DAO_Userreg {
    

    /**
     * crea un usuario registrado
     * @return 
     */
    public static registered_user pideUserreg() {

        String dni = "", name = "", surname = "", mobile = "", email = "", user = "", pass = "", avatar = "",
                state = "";
        date date_birthday = null;
        int activity = 0;

        dni = singleton.DNI;
        name = createUserreg.nameField.getText();
        surname = createUserreg.surnameField.getText();
        mobile = createUserreg.mobileField.getText();
        email = createUserreg.emailField.getText();
        user = createUserreg.userField.getText();
        pass = createUserreg.passField.getText();
        avatar = createUserreg.imgPath.getText();
        state = createUserreg.optionState.getText();
        date_birthday = new date(((JTextFieldDateEditor) createUserreg.birthdayField.getDateEditor()).getText());
        activity = Integer.parseInt(createUserreg.activityField.getText());

        registered_user userreg = new registered_user(dni, name, surname, mobile, email, user, pass, avatar, state, date_birthday, activity);

        return userreg;
    }
    
  /**
   * modifica un usario registrado
   * @return 
   */
    public static registered_user changeUserreg() {

        String dni = "", name = "", surname = "", mobile = "", email = "", user = "", pass = "", avatar = "",
                state = "";
        date date_birthday = null;
        int activity = 0;

        dni = singleton.DNI;
        name = ChangeUserreg.nameField.getText();
        surname = ChangeUserreg.surnameField.getText();
        mobile = ChangeUserreg.mobileField.getText();
        email = ChangeUserreg.emailField.getText();
        user = ChangeUserreg.userField.getText();
        pass = ChangeUserreg.passField.getText();
        avatar = ChangeUserreg.imgPath.getText();
        state = ChangeUserreg.optionState.getText();
        date_birthday = new date(((JTextFieldDateEditor) ChangeUserreg.birthdayField.getDateEditor()).getText());
        activity = Integer.parseInt(ChangeUserreg.activityField.getText());

        registered_user userreg=new registered_user(dni, name, surname, mobile, email, user, pass, avatar, state, date_birthday, activity);

        return userreg;
    }
    
    /**
     * valida un dni
     * @return 
     */
    public static boolean askdni() {
        String inserted_dni = "";
        boolean checker = false;

        inserted_dni = createUserreg.dniField.getText();
        inserted_dni = inserted_dni.toUpperCase();
        createUserreg.dniField.setText(inserted_dni.toUpperCase());
        checker = validate.validate_dni(inserted_dni);
        if (inserted_dni.isEmpty()) {
            createUserreg.errorDni.setText("<html><font color=red>No ha introducido ningun dni</font></html>");
            checker = false;
        } else if (checker == false) {
            createUserreg.errorDni.setText("<html><font color=red>El dni introducido no es valido</font></html>");
        } else {
            createUserreg.errorDni.setText("<html><font color=green>DNI Valido</font></html>");
        }
        return checker;
    }

    /**
     * valida el nombre
     * @return 
     */
    public static boolean askname() {
        String inserted_name = "";
        boolean checker = false;

        inserted_name = createUserreg.nameField.getText();
        checker = validate.validate_name(inserted_name);
        if (inserted_name.isEmpty()) {
            createUserreg.errorName.setText("<html><font color=red>No ha introducido ningun Nombre</font></html>");
            checker = false;
        } else if (checker == false) {
            createUserreg.errorName.setText("<html><font color=red>El nombre introducido no es valido</font></html>");
        } else {
            createUserreg.errorName.setText("<html><font color=green>El nombre es Valido</font></html>");
        }
        return checker;
    }

    /**
     * valida el apellido
     * @return 
     */
    public static boolean asksurname() {
        String inserted_surname = "";
        boolean checker = false;

        inserted_surname = createUserreg.surnameField.getText();
        checker = validate.validate_surname(inserted_surname);
        if (inserted_surname.isEmpty()) {
            createUserreg.errorSurname.setText("<html><font color=red>No ha introducido ningun Nombre</font></html>");
            checker = false;
        } else if (checker == false) {
            createUserreg.errorSurname.setText("<html><font color=red>El apellido introducido no es valido</font></html>");
        } else {
            createUserreg.errorSurname.setText("<html><font color=green>El apellido es Valido</font></html>");
        }
        return checker;
    }

    /**
     * valida el mobil
     * @return 
     */
    public static boolean askmobile() {
        String inserted_mobile = "";
        boolean checker = false;

        inserted_mobile = createUserreg.mobileField.getText();
        checker = validate.validate_mobile(inserted_mobile);
        if (inserted_mobile.isEmpty()) {
           createUserreg.errorMobile.setText("<html><font color=red>No ha introducido ningun numero de telefono</font></html>");
            checker = false;
        } else if (checker == false) {
            createUserreg.errorMobile.setText("<html><font color=red>El telefono introducido no es valido</font></html>");
        } else {
            createUserreg.errorMobile.setText("<html><font color=green>El telefono es Valido</font></html>");
        }
        return checker;
    }

    /**
     * valida el email
     * @return 
     */
    public static boolean askemail() {
        String inserted_email = "";
        boolean checker = false;

        inserted_email = createUserreg.emailField.getText();
        checker = validate.validate_email(inserted_email);
        if (inserted_email.isEmpty()) {
            createUserreg.errorEmail.setText("<html><font color=red>No ha introducido ningun email</font></html>");
            checker = false;
        } else if (checker == false) {
            createUserreg.errorEmail.setText("<html><font color=red>El Email introducido no es valido</font></html>");
        } else {
            createUserreg.errorEmail.setText("<html><font color=green>El Email es Valido</font></html>");
        }
        return checker;
    }

    /**
     * valida el usuario
     * @return 
     */
    public static boolean askuser() {
        String inserted_user = "";
        boolean checker = false;

        inserted_user = createUserreg.userField.getText();
        checker = validate.validate_user(inserted_user);
        if (inserted_user.isEmpty()) {
            createUserreg.errorUser.setText("<html><font color=red>No ha introducido ningun usuario</font></html>");
            checker = false;
        } else if (checker == false) {
           createUserreg.errorUser.setText("<html><font color=red>El usuario introducido no es valido</font></html>");
        } else {
            createUserreg.errorUser.setText("<html><font color=green>El usuario es Valido</font></html>");
        }
        return checker;
    }

    /**
     * valida la contraseña
     * @return 
     */
    public static boolean askpassword() {
        String inserted_password = "";
        boolean checker = false;

        inserted_password = createUserreg.passField.getText();
        checker = validate.validate_password(inserted_password);
        if (inserted_password.isEmpty()) {
            createUserreg.errorPass.setText("<html><font color=red>No has introducido ninguna contraseña</font></html>");
            checker = false;
        } else if (checker == false) {
            createUserreg.errorPass.setText("<html><font color=red>La contraseña introducida no es valida</font></html>");
        } else {
            createUserreg.errorPass.setText("<html><font color=green>La contraseña es Valida</font></html>");
        }
        return checker;
    }

    /**
     * valida la fecha de nacimiento
     * @return 
     */
    public static boolean askdate() {
        String inserted_date_birthday = "";
        int age = 0;
        boolean checker = true;

        inserted_date_birthday = ((JTextFieldDateEditor) createUserreg.birthdayField.getDateEditor()).getText();
        checker = validate.validate_dateformat(inserted_date_birthday);

        date date = new date(inserted_date_birthday);
        checker = date.checkdate();

        age = date.substractdatesystem();
        if (age >= 18) {
            createUserreg.errorBirthday.setText("<html><font color=green>La fecha de nacimiento introducida es correcta</font></html>");

        } else {
            createUserreg.errorBirthday.setText("<html><font color=red>La contraseña introducida no es valida</font></html>");
        }
        return checker;
    }

    /**
     * valida la actividad
     * @return 
     */
    public static boolean askactivity() {
        String inserted_activity = "";
        boolean checker = false;
        inserted_activity = createUserreg.activityField.getText();

        checker = validate.validate_activity(inserted_activity);
        if (inserted_activity.isEmpty()) {
            createUserreg.errorActivity.setText("<html><font color=red>No ha introducido ninguna actividad</font></html>");
            checker = false;
        } else if (checker == false) {
            createUserreg.errorActivity.setText("<html><font color=red>La Actividad introducida no es valida</font></html>");
        } else {
            createUserreg.errorActivity.setText("<html><font color=green>La Actividad introducida es valida</font></html>");
            checker = true;
        }
        return checker;
    }

    /**
     * valida el nombre en la ventana de modificar
     * @return 
     */
    public static boolean changeName() {
        String inserted_name = "";
        boolean checker = false;

        inserted_name = ChangeUserreg.nameField.getText();
        checker = validate.validate_name(inserted_name);
        if (checker == false) {
            ChangeUserreg.errorName.setText("<html><font color=red>El nombre introducido no es valido</font></html>");
        } else {
            ChangeUserreg.errorName.setText("<html><font color=green>El nombre es Valido</font></html>");
            checker = true;
        }
        return checker;
    }

    /**
     * valida el apellido en la ventana de modificar
     * @return 
     */
    public static boolean changeSurname() {
        String inserted_surname = "";
        boolean checker = false;

        inserted_surname = ChangeUserreg.surnameField.getText();
        checker = validate.validate_surname(inserted_surname);
        if (checker == false) {
            ChangeUserreg.errorSurname.setText("<html><font color=red>El apellido introducido no es valido</font></html>");
        } else {
            ChangeUserreg.errorSurname.setText("<html><font color=green>El apellido es Valido</font></html>");
            checker = true;
        }
        return checker;
    }

    /**
     * valida el mobil en la ventana de modificar
     * @return 
     */
    public static boolean changeMobile() {
        String inserted_mobile = "";
        boolean checker = false;

        inserted_mobile = ChangeUserreg.mobileField.getText();
        checker = validate.validate_mobile(inserted_mobile);
        if (checker == false) {
            ChangeUserreg.errorMobile.setText("<html><font color=red>El telefono introducido no es valido</font></html>");
        } else {
            ChangeUserreg.errorMobile.setText("<html><font color=green>El telefono es Valido</font></html>");
            checker = true;
        }
        return checker;
    }

    /**
     * valida el email en la ventana de modificar
     * @return 
     */
    public static boolean changeEmail() {
        String inserted_email = "";
        boolean checker = false;

        inserted_email = ChangeUserreg.emailField.getText();
        checker = validate.validate_email(inserted_email);
        if (checker == false) {
            ChangeUserreg.errorEmail.setText("<html><font color=red>El Email introducido no es valido</font></html>");
        } else {
            ChangeUserreg.errorEmail.setText("<html><font color=green>El Email es Valido</font></html>");
            checker = true;
        }
        return checker;
    }

    /**
     * valida el usuario en la ventana de modificar
     * @return 
     */
    public static boolean changeUser() {
        String inserted_user = "";
        boolean checker = false;

        inserted_user = ChangeUserreg.userField.getText();
        checker = validate.validate_user(inserted_user);
        if (checker == false) {
            ChangeUserreg.errorUser.setText("<html><font color=red>El usuario introducido no es valido</font></html>");
        } else {
            ChangeUserreg.errorUser.setText("<html><font color=green>El usuario es Valido</font></html>");
            checker = true;
        }
        return checker;
    }

    /**
     * valida la contraseña en la ventana de modificar
     * @return 
     */
    public static boolean changePassword() {
        String inserted_password = "";
        boolean checker = false;

        inserted_password = ChangeUserreg.passField.getText();
        checker = validate.validate_password(inserted_password);
        if (checker == false) {
            ChangeUserreg.errorPass.setText("<html><font color=red>La contraseña introducida no es valida</font></html>");
        } else {
            ChangeUserreg.errorPass.setText("<html><font color=green>La contraseña es Valida</font></html>");
            checker = true;
        }
        return checker;
    }

    /**
     * valida la fecha de nacimiento
     * @return 
     */
    public static boolean changeDate() {
        String inserted_date_birthday = "";
        int age = 0;
        boolean checker = false;

        inserted_date_birthday = ((JTextFieldDateEditor) ChangeUserreg.birthdayField.getDateEditor()).getText();
        checker = validate.validate_dateformat(inserted_date_birthday);
        date date = new date(inserted_date_birthday);
        checker = date.checkdate();

        age = date.substractdatesystem();
        if (age >= 18) {
            ChangeUserreg.errorBirthday.setText("<html><font color=green>La fecha de nacimiento introducida es correcta</font></html>");

        } else {
            ChangeUserreg.errorBirthday.setText("<html><font color=red>La contraseña introducida no es valida</font></html>");

        }
        return checker;
    }

    /**
     * valida la actividad
     * @return 
     */
    public static boolean changeActivity() {
        String inserted_activity = "";
        boolean checker = false;
        inserted_activity = ChangeUserreg.activityField.getText();

        checker = validate.validate_activity(inserted_activity);
        if (checker == false) {
            ChangeUserreg.errorActivity.setText("<html><font color=red>La Actividad introducida no es valida</font></html>");
        } else {
            ChangeUserreg.errorActivity.setText("<html><font color=green>La Actividad introducida es valida</font></html>");
            checker = true;
        }
        return checker;
    }
    
    
}
