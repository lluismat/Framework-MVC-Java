/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.users.admin.model.DAO;

import classes.date;
import com.toedter.calendar.JTextFieldDateEditor;
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

    // funcion crear admin
    public static admin pideAdmin() {

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

        admin admin = new admin(dni, name, surname, mobile, email, user, pass, avatar, state, date_birthday, activity,
                hiring_date);

        return admin;
    }

    // funcion modificar admin
    public static admin changeAdmin() {

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

        admin admin = new admin(dni, name, surname, mobile, email, user, pass, avatar, state, date_birthday, activity,
                hiring_date);

        return admin;
    }
    // pide dni
    public static boolean askdni() {
        String inserted_dni = "";
        boolean checker = false;

        inserted_dni = Create_Admin.dniField.getText();
        inserted_dni = inserted_dni.toUpperCase();
        Create_Admin.dniField.setText(inserted_dni.toUpperCase());
        checker = validate.validate_dni(inserted_dni);
        if (inserted_dni.isEmpty()) {
            Create_Admin.errorDni.setText("<html><font color=red>No ha introducido ningun dni</font></html>");
            checker = false;
        } else if (checker == false) {
            Create_Admin.errorDni.setText("<html><font color=red>El dni introducido no es valido</font></html>");
        } else {
            Create_Admin.errorDni.setText("<html><font color=green>DNI Valido</font></html>");
        }
        return checker;
    }

    // pide nombre
    public static boolean askname() {
        String inserted_name = "";
        boolean checker = false;

        inserted_name = Create_Admin.nameField.getText();
        checker = validate.validate_name(inserted_name);
        if (inserted_name.isEmpty()) {
            Create_Admin.errorName.setText("<html><font color=red>No ha introducido ningun Nombre</font></html>");
            checker = false;
        } else if (checker == false) {
            Create_Admin.errorName.setText("<html><font color=red>El nombre introducido no es valido</font></html>");
        } else {
            Create_Admin.errorName.setText("<html><font color=green>El nombre es Valido</font></html>");
        }
        return checker;
    }

    // pide apellidos
    public static boolean asksurname() {
        String inserted_surname = "";
        boolean checker = false;

        inserted_surname = Create_Admin.surnameField.getText();
        checker = validate.validate_surname(inserted_surname);
        if (inserted_surname.isEmpty()) {
            Create_Admin.errorSurname.setText("<html><font color=red>No ha introducido ningun Nombre</font></html>");
            checker = false;
        } else if (checker == false) {
            Create_Admin.errorSurname.setText("<html><font color=red>El apellido introducido no es valido</font></html>");
        } else {
            Create_Admin.errorSurname.setText("<html><font color=green>El apellido es Valido</font></html>");
        }
        return checker;
    }

    // pide mobil
    public static boolean askmobile() {
        String inserted_mobile = "";
        boolean checker = false;

        inserted_mobile = Create_Admin.mobileField.getText();
        checker = validate.validate_mobile(inserted_mobile);
        if (inserted_mobile.isEmpty()) {
            Create_Admin.errorMobile.setText("<html><font color=red>No ha introducido ningun numero de telefono</font></html>");
            checker = false;
        } else if (checker == false) {
            Create_Admin.errorMobile.setText("<html><font color=red>El telefono introducido no es valido</font></html>");
        } else {
            Create_Admin.errorMobile.setText("<html><font color=green>El telefono es Valido</font></html>");
        }
        return checker;
    }

    // pide email
    public static boolean askemail() {
        String inserted_email = "";
        boolean checker = false;

        inserted_email = Create_Admin.emailField.getText();
        checker = validate.validate_email(inserted_email);
        if (inserted_email.isEmpty()) {
            Create_Admin.errorEmail.setText("<html><font color=red>No ha introducido ningun email</font></html>");
            checker = false;
        } else if (checker == false) {
            Create_Admin.errorEmail.setText("<html><font color=red>El Email introducido no es valido</font></html>");
        } else {
            Create_Admin.errorEmail.setText("<html><font color=green>El Email es Valido</font></html>");
        }
        return checker;
    }

    // pide user
    public static boolean askuser() {
        String inserted_user = "";
        boolean checker = false;

        inserted_user = Create_Admin.userField.getText();
        checker = validate.validate_user(inserted_user);
        if (inserted_user.isEmpty()) {
            Create_Admin.errorUser.setText("<html><font color=red>No ha introducido ningun usuario</font></html>");
            checker = false;
        } else if (checker == false) {
            Create_Admin.errorUser.setText("<html><font color=red>El usuario introducido no es valido</font></html>");
        } else {
            Create_Admin.errorUser.setText("<html><font color=green>El usuario es Valido</font></html>");
        }
        return checker;
    }

    // pide password
    public static boolean askpassword() {
        String inserted_password = "";
        boolean checker = false;

        inserted_password = Create_Admin.passField.getText();
        checker = validate.validate_password(inserted_password);
        if (inserted_password.isEmpty()) {
            Create_Admin.errorPass.setText("<html><font color=red>No has introducido ninguna contraseña</font></html>");
            checker = false;
        } else if (checker == false) {
            Create_Admin.errorPass.setText("<html><font color=red>La contraseña introducida no es valida</font></html>");
        } else {
            Create_Admin.errorPass.setText("<html><font color=green>La contraseña es Valida</font></html>");
        }
        return checker;
    }

    //pide date birthday
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
            Create_Admin.errorBirthday.setText("<html><font color=green>La fecha de nacimiento introducida es correcta</font></html>");

        } else {
            Create_Admin.errorBirthday.setText("<html><font color=red>La contraseña introducida no es valida</font></html>");
        }
        return checker;
    }

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
            Create_Admin.errorHiringDate.setText("<html><font color=red>La fecha de contratacion introducida es incorrecta,<br>"
                    + "la fecha de contratacion debe ser posterior a la fecha de nacimiento</br></font></html>");
            checker = false;
        } else if (difage2 == 1) {
            Create_Admin.errorHiringDate.setText("<html><font color=red>La fecha de contratacion introducida es incorrecta,<br> "
                    + "la fecha de contratacion debe ser anterior a la fecha actual</br></font></html>");
            checker = false;
        } else if ((date2.substractdate(date) >= 16) && (date2.substractdate(date) <= 65)) {
            Create_Admin.errorHiringDate.setText("<html><font color=green>La fecha de contratacion introducida es "
                    + "correcta</font></html>");
            checker = true;
        } else {
            Create_Admin.errorHiringDate.setText("<html><font color=red>La fecha de contratacion introducida "
                    + "es incorrecta,<br> "
                    + "debes tener al menos 16 años de edad y menos de 65</br></font></html>");
            checker = false;
        } // fi 3 else // fi 2 else // fi 1 else  
        return checker;
    }

    public static boolean askactivity() {
        String inserted_activity = "";
        boolean checker = false;
        inserted_activity = Create_Admin.activityField.getText();

        checker = validate.validate_activity(inserted_activity);
        if (inserted_activity.isEmpty()) {
            Create_Admin.errorActivity.setText("<html><font color=red>No ha introducido ninguna actividad</font></html>");
            checker = false;
        } else if (checker == false) {
            Create_Admin.errorActivity.setText("<html><font color=red>La Actividad introducida no es valida</font></html>");
        } else {
            Create_Admin.errorActivity.setText("<html><font color=green>La Actividad introducida es valida</font></html>");
            checker = true;
        }
        return checker;
    }

    // pide nombre
    public static boolean changeName() {
        String inserted_name = "";
        boolean checker = false;

        inserted_name = ChangeAdmin.nameField.getText();
        checker = validate.validate_name(inserted_name);
        if (checker == false) {
            ChangeAdmin.errorName.setText("<html><font color=red>El nombre introducido no es valido</font></html>");
        } else {
            ChangeAdmin.errorName.setText("<html><font color=green>El nombre es Valido</font></html>");
            checker = true;
        }
        return checker;
    }

    // pide apellidos
    public static boolean changeSurname() {
        String inserted_surname = "";
        boolean checker = false;

        inserted_surname = ChangeAdmin.surnameField.getText();
        checker = validate.validate_surname(inserted_surname);
        if (checker == false) {
            ChangeAdmin.errorSurname.setText("<html><font color=red>El apellido introducido no es valido</font></html>");
        } else {
            ChangeAdmin.errorSurname.setText("<html><font color=green>El apellido es Valido</font></html>");
            checker = true;
        }
        return checker;
    }

    // pide mobil
    public static boolean changeMobile() {
        String inserted_mobile = "";
        boolean checker = false;

        inserted_mobile = ChangeAdmin.mobileField.getText();
        checker = validate.validate_mobile(inserted_mobile);
        if (checker == false) {
            ChangeAdmin.errorMobile.setText("<html><font color=red>El telefono introducido no es valido</font></html>");
        } else {
            ChangeAdmin.errorMobile.setText("<html><font color=green>El telefono es Valido</font></html>");
            checker = true;
        }
        return checker;
    }

    // pide email
    public static boolean changeEmail() {
        String inserted_email = "";
        boolean checker = false;

        inserted_email = ChangeAdmin.emailField.getText();
        checker = validate.validate_email(inserted_email);
        if (checker == false) {
            ChangeAdmin.errorEmail.setText("<html><font color=red>El Email introducido no es valido</font></html>");
        } else {
            ChangeAdmin.errorEmail.setText("<html><font color=green>El Email es Valido</font></html>");
            checker = true;
        }
        return checker;
    }

    // pide user
    public static boolean changeUser() {
        String inserted_user = "";
        boolean checker = false;

        inserted_user = ChangeAdmin.userField.getText();
        checker = validate.validate_user(inserted_user);
        if (checker == false) {
            ChangeAdmin.errorUser.setText("<html><font color=red>El usuario introducido no es valido</font></html>");
        } else {
            ChangeAdmin.errorUser.setText("<html><font color=green>El usuario es Valido</font></html>");
            checker = true;
        }
        return checker;
    }

    // pide password
    public static boolean changePassword() {
        String inserted_password = "";
        boolean checker = false;

        inserted_password = ChangeAdmin.passField.getText();
        checker = validate.validate_password(inserted_password);
        if (checker == false) {
            ChangeAdmin.errorPass.setText("<html><font color=red>La contraseña introducida no es valida</font></html>");
        } else {
            ChangeAdmin.errorPass.setText("<html><font color=green>La contraseña es Valida</font></html>");
            checker = true;
        }
        return checker;
    }

    //pide date birthday
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
            ChangeAdmin.errorBirthday.setText("<html><font color=green>La fecha de nacimiento introducida es correcta</font></html>");

        } else {
            ChangeAdmin.errorBirthday.setText("<html><font color=red>La contraseña introducida no es valida</font></html>");

        }
        return checker;
    }

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
            ChangeAdmin.errorHiringDate.setText("<html><font color=red>La fecha de contratacion introducida es incorrecta,<br> "
                    + "la fecha de contratacion debe ser posterior a la fecha de nacimiento<br></font></html>");
            checker = false;
        } else if (difage2 == 1) {
            ChangeAdmin.errorHiringDate.setText("<html><font color=red>La fecha de contratacion introducida es incorrecta,<br> "
                    + "la fecha de contratacion debe ser anterior a la fecha actual</br></font></html>");
            checker = false;
        } else if ((birthday.substractdate(hiringDate) >= 16) && (birthday.substractdate(hiringDate) <= 65)) {
            ChangeAdmin.errorHiringDate.setText("<html><font color=green>La fecha de contratacion introducida es "
                    + "correcta</font></html>");
            checker = true;
        } else {
            ChangeAdmin.errorHiringDate.setText("<html><font color=red>La fecha de contratacion introducida "
                    + "es incorrecta,<br> "
                    + "debes tener al menos 16 años de edad y menos de 65</br></font></html>");
            checker = false;
        }
        return checker;
    }

    public static boolean changeActivity() {
        String inserted_activity = "";
        boolean checker = false;
        inserted_activity = ChangeAdmin.activityField.getText();

        checker = validate.validate_activity(inserted_activity);
        if (checker == false) {
            ChangeAdmin.errorActivity.setText("<html><font color=red>La Actividad introducida no es valida</font></html>");
        } else {
            ChangeAdmin.errorActivity.setText("<html><font color=green>La Actividad introducida es valida</font></html>");
            checker = true;
        }
        return checker;
    }
}
