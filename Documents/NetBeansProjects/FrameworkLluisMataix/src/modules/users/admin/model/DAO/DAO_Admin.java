/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.users.admin.model.DAO;

import classes.date;
import com.toedter.calendar.JTextFieldDateEditor;
import modules.menu.model.Language;
import modules.users.admin.model.classes.admin;
import modules.users.users.singleton;
import modules.users.admin.view.ChangeAdmin;
import modules.users.admin.view.Create_Admin;
import utils.validate;

/**
 *
 * @author lluis
 */
public class DAO_Admin {

    /**
     * funcion para crear un administrador
     */
    public static void pideAdmin() {

        String dni = "", name = "", surname = "", mobile = "", email = "", user = "", pass = "", avatar = "",
                state = "";
        date date_birthday = null, hiring_date = null;
        int activity = 0;

        dni = singleton.DNI;
        name = Create_Admin.nameField.getText();
        surname = Create_Admin.surnameField.getText();
        mobile = Create_Admin.mobileField.getText();
        email = Create_Admin.emailField.getText();
        user = Create_Admin.userField.getText();
        pass = Create_Admin.passField.getText();
        avatar = Create_Admin.imgPath.getText();
        state = Create_Admin.optionState.getText();
        date_birthday = new date(((JTextFieldDateEditor) Create_Admin.birthdayField.getDateEditor()).getText());
        activity = Integer.parseInt(Create_Admin.activityField.getText());
        hiring_date = new date(((JTextFieldDateEditor) Create_Admin.HiringDateField.getDateEditor()).getText());

        singleton.admin = new admin(dni, name, surname, mobile, email, user, pass, avatar, state, date_birthday, activity,
                hiring_date);

    }

    /**
     * funcion para crear un administrador
     */
    public static void changeAdmin() {

        String dni = "", name = "", surname = "", mobile = "", email = "", user = "", pass = "", avatar = "",
                state = "";
        date date_birthday = null, hiring_date = null;
        int activity = 0;

        dni = singleton.DNI;
        name = ChangeAdmin.nameField.getText();
        surname = ChangeAdmin.surnameField.getText();
        mobile = ChangeAdmin.mobileField.getText();
        email = ChangeAdmin.emailField.getText();
        user = ChangeAdmin.userField.getText();
        pass = ChangeAdmin.passField.getText();
        avatar = ChangeAdmin.imgPath.getText();
        state = ChangeAdmin.optionState.getText();
        date_birthday = new date(((JTextFieldDateEditor) ChangeAdmin.birthdayField.getDateEditor()).getText());
        activity = Integer.parseInt(ChangeAdmin.activityField.getText());
        hiring_date = new date(((JTextFieldDateEditor) ChangeAdmin.HiringDateField.getDateEditor()).getText());

        singleton.admin = new admin(dni, name, surname, mobile, email, user, pass, avatar, state, date_birthday, activity,
                hiring_date);

    }

    /**
     * funcion que valida un dni
     * @return 
     */
    public static boolean askdni() {
        String inserted_dni = "";
        boolean checker = false;

        inserted_dni = Create_Admin.dniField.getText();
        inserted_dni = inserted_dni.toUpperCase();
        Create_Admin.dniField.setText(inserted_dni.toUpperCase());
        checker = validate.validate_dni(inserted_dni);
        if (inserted_dni.isEmpty()) {
            Create_Admin.errorDni.setForeground(java.awt.Color.red);
            Create_Admin.errorDni.setText(Language.getInstance().getProperty("errordni1"));
            checker = false;
        } else if (checker == false) {
            Create_Admin.errorDni.setForeground(java.awt.Color.red);
            Create_Admin.errorDni.setText(Language.getInstance().getProperty("errordni2"));
        } else {
            Create_Admin.errorDni.setForeground(java.awt.Color.green);
            Create_Admin.errorDni.setText(Language.getInstance().getProperty("dniok"));
        }
        return checker;
    }

    /**
     * funcion para validar el nombre
     * @return 
     */
    public static boolean askname() {
        String inserted_name = "";
        boolean checker = false;

        inserted_name = Create_Admin.nameField.getText();
        checker = validate.validate_name(inserted_name);
        if (inserted_name.isEmpty()) {
            Create_Admin.errorName.setForeground(java.awt.Color.red);
            Create_Admin.errorName.setText(Language.getInstance().getProperty("errorname1"));
            checker = false;
        } else if (checker == false) {
            Create_Admin.errorName.setForeground(java.awt.Color.red);
            Create_Admin.errorName.setText(Language.getInstance().getProperty("errorname2"));
        } else {
            Create_Admin.errorName.setForeground(java.awt.Color.green);
            Create_Admin.errorName.setText(Language.getInstance().getProperty("nameok"));
        }
        return checker;
    }

    /**
     * funcion par avalidar el apellido
     * @return 
     */
    public static boolean asksurname() {
        String inserted_surname = "";
        boolean checker = false;

        inserted_surname = Create_Admin.surnameField.getText();
        checker = validate.validate_surname(inserted_surname);
        if (inserted_surname.isEmpty()) {
            Create_Admin.errorSurname.setForeground(java.awt.Color.red);
            Create_Admin.errorSurname.setText(Language.getInstance().getProperty("errorsurname1"));
            checker = false;
        } else if (checker == false) {
            Create_Admin.errorSurname.setForeground(java.awt.Color.red);
            Create_Admin.errorSurname.setText(Language.getInstance().getProperty("errorsurname2"));
        } else {
            Create_Admin.errorSurname.setForeground(java.awt.Color.green);
            Create_Admin.errorSurname.setText(Language.getInstance().getProperty("surnameok"));
        }
        return checker;
    }

  /**
   * funcion para validar el movil
   * @return 
   */
    public static boolean askmobile() {
        String inserted_mobile = "";
        boolean checker = false;

        inserted_mobile = Create_Admin.mobileField.getText();
        checker = validate.validate_mobile(inserted_mobile);
        if (inserted_mobile.isEmpty()) {
            Create_Admin.errorMobile.setForeground(java.awt.Color.red);
            Create_Admin.errorMobile.setText(Language.getInstance().getProperty("errormobile1"));
            checker = false;
        } else if (checker == false) {
            Create_Admin.errorMobile.setForeground(java.awt.Color.red);
            Create_Admin.errorMobile.setText(Language.getInstance().getProperty("errormobile2"));
        } else {
            Create_Admin.errorMobile.setForeground(java.awt.Color.green);
            Create_Admin.errorMobile.setText(Language.getInstance().getProperty("mobileok"));
        }
        return checker;
    }

    /**
     * funcion para validar el email
     * @return 
     */
    public static boolean askemail() {
        String inserted_email = "";
        boolean checker = false;

        inserted_email = Create_Admin.emailField.getText();
        checker = validate.validate_email(inserted_email);
        if (inserted_email.isEmpty()) {
            Create_Admin.errorEmail.setForeground(java.awt.Color.red);
            Create_Admin.errorEmail.setText(Language.getInstance().getProperty("erroremail1"));
            checker = false;
        } else if (checker == false) {
            Create_Admin.errorEmail.setForeground(java.awt.Color.red);
            Create_Admin.errorEmail.setText(Language.getInstance().getProperty("erroremail2"));
        } else {
            Create_Admin.errorEmail.setForeground(java.awt.Color.green);
            Create_Admin.errorEmail.setText(Language.getInstance().getProperty("emailok"));
        }
        return checker;
    }

    /**
     * funcion para validar el nombre de usuario
     * @return 
     */
    public static boolean askuser() {
        String inserted_user = "";
        boolean checker = false;

        inserted_user = Create_Admin.userField.getText();
        checker = validate.validate_user(inserted_user);
        if (inserted_user.isEmpty()) {
            Create_Admin.errorUser.setForeground(java.awt.Color.red);
            Create_Admin.errorUser.setText(Language.getInstance().getProperty("erroruser1")); 
            checker = false;
        } else if (checker == false) {
            Create_Admin.errorUser.setForeground(java.awt.Color.red);
            Create_Admin.errorUser.setText(Language.getInstance().getProperty("erroruser2")); 
        } else {
            Create_Admin.errorUser.setForeground(java.awt.Color.green);
            Create_Admin.errorUser.setText(Language.getInstance().getProperty("userok")); 
        }
        return checker;
    }

    /**
     * funcion para validar la contraseña
     * @return 
     */
    public static boolean askpassword() {
        String inserted_password = "";
        boolean checker = false;

        inserted_password = Create_Admin.passField.getText();
        checker = validate.validate_password(inserted_password);
        if (inserted_password.isEmpty()) {
            Create_Admin.errorPass.setForeground(java.awt.Color.red);
            Create_Admin.errorPass.setText(Language.getInstance().getProperty("errorpass1")); 
            checker = false;
        } else if (checker == false) {
            Create_Admin.errorPass.setForeground(java.awt.Color.red);
            Create_Admin.errorPass.setText(Language.getInstance().getProperty("errorpass2")); 
        } else {
            Create_Admin.errorPass.setForeground(java.awt.Color.green);
            Create_Admin.errorPass.setText(Language.getInstance().getProperty("passok")); 
        }
        return checker;
    }

    /**
     * funcion para validar la fecha de nacimiento
     * @return 
     */
    public static boolean askdate() {
        String inserted_date_birthday = "";
        int age = 0;
        boolean checker = true;

        inserted_date_birthday = ((JTextFieldDateEditor) Create_Admin.birthdayField.getDateEditor()).getText();
        checker = validate.validate_dateformat(inserted_date_birthday);

        date date = new date(inserted_date_birthday);
        checker = date.checkdate();

        age = date.substractdatesystem();
        if (age >= 18) {
            Create_Admin.errorBirthday.setForeground(java.awt.Color.green);
            Create_Admin.errorBirthday.setText(Language.getInstance().getProperty("dateok"));   

        } else {
            Create_Admin.errorBirthday.setForeground(java.awt.Color.red);
            Create_Admin.errorBirthday.setText(Language.getInstance().getProperty("errordate1")); 
        }
        return checker;
    }

    /**
     * funcion para validar la fecha de contratacion
     * @return 
     */
    public static boolean askhiring_date() {
        boolean checker = false;
        date date = null;
        int difage = 0, difage2 = 0;
        String inserted_hiringDate = "";
        String dateBirthday = ((JTextFieldDateEditor) Create_Admin.birthdayField.getDateEditor()).getText();
        date date2 = new date(dateBirthday);

        inserted_hiringDate = ((JTextFieldDateEditor) Create_Admin.HiringDateField.getDateEditor()).getText();

        date = new date(inserted_hiringDate);

        difage = date.comparedate(date2);
        difage2 = date.comparedatesystem();
        if ((difage == 0) || (difage == 1)) {
            Create_Admin.errorHiringDate.setForeground(java.awt.Color.red);
            Create_Admin.errorHiringDate.setText(Language.getInstance().getProperty("errorhiring1")); 
            checker = false;
        } else if (difage2 == 1) {
             Create_Admin.errorHiringDate.setForeground(java.awt.Color.red);
            Create_Admin.errorHiringDate.setText(Language.getInstance().getProperty("errorhiring2")); 
            checker = false;
        } else if ((date2.substractdate(date) >= 16) && (date2.substractdate(date) <= 65)) {
            Create_Admin.errorHiringDate.setForeground(java.awt.Color.green);
            Create_Admin.errorHiringDate.setText(Language.getInstance().getProperty("hiringok")); 
            checker = true;
        } else {
             Create_Admin.errorHiringDate.setForeground(java.awt.Color.red);
            Create_Admin.errorHiringDate.setText(Language.getInstance().getProperty("errorhiring3")); 
            checker = false;
        } // fi 3 else // fi 2 else // fi 1 else  
        return checker;
    }

    /**
     * funcion para validar la actividad
     * @return 
     */
    public static boolean askactivity() {
        String inserted_activity = "";
        boolean checker = false;
        inserted_activity = Create_Admin.activityField.getText();

        checker = validate.validate_activity(inserted_activity);
        if (inserted_activity.isEmpty()) {
             Create_Admin.errorActivity.setForeground(java.awt.Color.red);
            Create_Admin.errorActivity.setText(Language.getInstance().getProperty("erroractivity1")); 
            checker = false;
        } else if (checker == false) {
              Create_Admin.errorActivity.setForeground(java.awt.Color.red);
            Create_Admin.errorActivity.setText(Language.getInstance().getProperty("erroractivity2"));
        } else {
              Create_Admin.errorActivity.setForeground(java.awt.Color.green);
            Create_Admin.errorActivity.setText(Language.getInstance().getProperty("activityok"));
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

        inserted_name = ChangeAdmin.nameField.getText();
        checker = validate.validate_name(inserted_name);
        if (checker == false) {
             ChangeAdmin.errorName.setForeground(java.awt.Color.red);
            ChangeAdmin.errorName.setText(Language.getInstance().getProperty("errorname2"));
        } else {
            ChangeAdmin.errorName.setForeground(java.awt.Color.green);
            ChangeAdmin.errorName.setText(Language.getInstance().getProperty("nameok"));
            checker = true;
        }
        return checker;
    }

    /**
     * funcion para validar un apellido de la ventana de modificar
     * @return 
     */
    public static boolean changeSurname() {
        String inserted_surname = "";
        boolean checker = false;

        inserted_surname = ChangeAdmin.surnameField.getText();
        checker = validate.validate_surname(inserted_surname);
        if (checker == false) {
            ChangeAdmin.errorSurname.setForeground(java.awt.Color.red);
            ChangeAdmin.errorSurname.setText(Language.getInstance().getProperty("errorsurname2"));
        } else {
            ChangeAdmin.errorSurname.setForeground(java.awt.Color.green);
            ChangeAdmin.errorSurname.setText(Language.getInstance().getProperty("surnameok"));
            checker = true;
        }
        return checker;
    }

    /**
     * funcion para validar el movil de la ventana de modificar
     * @return 
     */
    public static boolean changeMobile() {
        String inserted_mobile = "";
        boolean checker = false;

        inserted_mobile = ChangeAdmin.mobileField.getText();
        checker = validate.validate_mobile(inserted_mobile);
        if (checker == false) {
            ChangeAdmin.errorMobile.setForeground(java.awt.Color.red);
            ChangeAdmin.errorMobile.setText(Language.getInstance().getProperty("errormobile2"));
        } else {
            ChangeAdmin.errorMobile.setForeground(java.awt.Color.green);
            ChangeAdmin.errorMobile.setText(Language.getInstance().getProperty("mobileok"));
            checker = true;
        }
        return checker;
    }

    /**
     * funcion para validar el email en la ventana de modificar
     * @return 
     */
    public static boolean changeEmail() {
        String inserted_email = "";
        boolean checker = false;

        inserted_email = ChangeAdmin.emailField.getText();
        checker = validate.validate_email(inserted_email);
        if (checker == false) {
            ChangeAdmin.errorEmail.setForeground(java.awt.Color.red);
            ChangeAdmin.errorEmail.setText(Language.getInstance().getProperty("erroremail2"));
        } else {
            ChangeAdmin.errorEmail.setForeground(java.awt.Color.green);
            ChangeAdmin.errorEmail.setText(Language.getInstance().getProperty("emailok"));
            checker = true;
        }
        return checker;
    }

    /**
     * funcion para validar el usuario en la ventana de modificar
     * @return 
     */
    public static boolean changeUser() {
        String inserted_user = "";
        boolean checker = false;

        inserted_user = ChangeAdmin.userField.getText();
        checker = validate.validate_user(inserted_user);
        if (checker == false) {
            ChangeAdmin.errorUser.setForeground(java.awt.Color.red);
            ChangeAdmin.errorUser.setText(Language.getInstance().getProperty("erroruser2"));
        } else {
            ChangeAdmin.errorUser.setForeground(java.awt.Color.green);
            ChangeAdmin.errorUser.setText(Language.getInstance().getProperty("userok"));
            checker = true;
        }
        return checker;
    }

    /**
     * funcion para validar la contraseña en la ventana de modificar
     * @return 
     */
    public static boolean changePassword() {
        String inserted_password = "";
        boolean checker = false;

        inserted_password = ChangeAdmin.passField.getText();
        checker = validate.validate_password(inserted_password);
        if (checker == false) {
            ChangeAdmin.errorPass.setForeground(java.awt.Color.red);
            ChangeAdmin.errorPass.setText(Language.getInstance().getProperty("errorpass2"));
        } else {
            ChangeAdmin.errorPass.setForeground(java.awt.Color.green);
            ChangeAdmin.errorPass.setText(Language.getInstance().getProperty("passok"));
            checker = true;
        }
        return checker;
    }

   /**
    * funcion para validar la fecha de nacimiento en la ventana de modificar
    * @return 
    */
    public static boolean changeDate() {
        String inserted_date_birthday = "";
        int age = 0;
        boolean checker = false;

        inserted_date_birthday = ((JTextFieldDateEditor) ChangeAdmin.birthdayField.getDateEditor()).getText();
        checker = validate.validate_dateformat(inserted_date_birthday);
        date date = new date(inserted_date_birthday);
        checker = date.checkdate();

        age = date.substractdatesystem();
        if (age >= 18) {
            ChangeAdmin.errorBirthday.setForeground(java.awt.Color.green);
            ChangeAdmin.errorBirthday.setText(Language.getInstance().getProperty("dateok"));

        } else {
            ChangeAdmin.errorBirthday.setForeground(java.awt.Color.red);
            ChangeAdmin.errorBirthday.setText(Language.getInstance().getProperty("errordate1"));

        }
        return checker;
    }

    /**
     * funcion para validar la fecha de contratacion en la ventana de modificar
     * @return 
     */
    public static boolean changeHiring_date() {
        boolean checker = false;
        date hiringDate = null;
        int difage = 0, difage2 = 0;
        String inserted_hiringDate = "";

        String dateBirthday = ((JTextFieldDateEditor) ChangeAdmin.birthdayField.getDateEditor()).getText();
        date birthday = new date(dateBirthday);

        inserted_hiringDate = ((JTextFieldDateEditor) ChangeAdmin.HiringDateField.getDateEditor()).getText();
        checker = validate.validate_dateformat(inserted_hiringDate);

        hiringDate = new date(inserted_hiringDate);

        difage = hiringDate.comparedate(birthday);
        difage2 = hiringDate.comparedatesystem();
        if ((difage == 0) || (difage == 1)) {
            ChangeAdmin.errorHiringDate.setForeground(java.awt.Color.red);
            ChangeAdmin.errorHiringDate.setText(Language.getInstance().getProperty("errorhiring1"));
            checker = false;
        } else if (difage2 == 1) {
            ChangeAdmin.errorHiringDate.setForeground(java.awt.Color.red);
            ChangeAdmin.errorHiringDate.setText(Language.getInstance().getProperty("errorhiring2"));
            checker = false;
        } else if ((birthday.substractdate(hiringDate) >= 16) && (birthday.substractdate(hiringDate) <= 65)) {
            ChangeAdmin.errorHiringDate.setForeground(java.awt.Color.green);
            ChangeAdmin.errorHiringDate.setText(Language.getInstance().getProperty("hiringok"));
            checker = true;
        } else {
            ChangeAdmin.errorHiringDate.setForeground(java.awt.Color.red);
            ChangeAdmin.errorHiringDate.setText(Language.getInstance().getProperty("errorhiring3"));
            checker = false;
        }
        return checker;
    }

    /**
     * funcion para validar la actividad en la ventana de modificar
     * @return 
     */
    public static boolean changeActivity() {
        String inserted_activity = "";
        boolean checker = false;
        inserted_activity = ChangeAdmin.activityField.getText();

        checker = validate.validate_activity(inserted_activity);
        if (checker == false) {
            ChangeAdmin.errorActivity.setForeground(java.awt.Color.red);
            ChangeAdmin.errorActivity.setText(Language.getInstance().getProperty("erroractivity2"));
        } else {
            ChangeAdmin.errorActivity.setForeground(java.awt.Color.green);
            ChangeAdmin.errorActivity.setText(Language.getInstance().getProperty("activityok"));
            checker = true;
        }
        return checker;
    }
}
