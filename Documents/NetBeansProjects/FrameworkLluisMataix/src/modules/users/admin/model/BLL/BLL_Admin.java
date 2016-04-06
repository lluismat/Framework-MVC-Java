/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.users.admin.model.BLL;

import modules.Config.model.Language;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modules.Config.view.Config_jFrame;
import modules.users.admin.controller.controller_Admin;
import modules.users.admin.model.DAO.DAO_Admin;
import modules.users.admin.model.classes.admin;
import modules.users.admin.view.Admin;
import modules.users.users.singleton;
import modules.users.admin.model.utils.avatar;
import modules.users.admin.model.utils.json;
import modules.users.admin.model.utils.txt;
import modules.users.admin.model.utils.xml;
import static modules.users.admin.view.ChangeAdmin.etiTitle;
import modules.users.admin.view.ChangeAdmin;
import modules.users.admin.view.Create_Admin;
import modules.users.admin.model.classes.miniSimpleTableModel_Admin;
import modules.users.admin.model.utils.pagina;
import utils.menus;
import modules.Config.model.Config;
import utils.Themes;

/**
 *
 * @author lluis
 */
public class BLL_Admin {

    public static boolean create_admin() {
        int location;
        boolean checker = false;
        admin admin;
        location = searchAdminDNI();
        if (location != -1) {
            JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("erroradminexist"));
            checker = false;
        } else if ((askAdmin("dni") == true) && (askAdmin("name") == true) && (askAdmin("surname") == true)
                && (askAdmin("mobile") == true) && (askAdmin("email") == true) && (askAdmin("user")) && (askAdmin("pass") == true)
                && (askAdmin("birthday") == true) && (askAdmin("activity") == true) && (askAdmin("hiringdate") == true)) {

            admin = DAO_Admin.pideAdmin();
            singleton.useradmin.add(admin);
            checker = true;
        }
        return checker;
    }

    public static void viewAdmin() {
        int location;
        admin admin;
        location = searchAdminModifyDNI();
        admin = singleton.useradmin.get(location);

        ChangeAdmin.nameField.setText(admin.getName());
        etiTitle.setText("Administrador: " + admin.getName() + " " + admin.getSurname());
        ChangeAdmin.surnameField.setText(admin.getSurname());
        ChangeAdmin.mobileField.setText(admin.getMobile());
        ChangeAdmin.emailField.setText(admin.getEmail());
        ChangeAdmin.userField.setText(admin.getUser());

        ImageIcon avatar = new ImageIcon(admin.getAvatar());
        ChangeAdmin.img.setIcon(new ImageIcon(avatar.getImage().getScaledInstance(125, 125, Image.SCALE_SMOOTH)));
        ChangeAdmin.imgPath.setText(admin.getAvatar());

        ChangeAdmin.passField.setText(admin.getPass());

        if ("Si".equals(admin.getState())) {
            ChangeAdmin.optYes.setSelected(true);
            ChangeAdmin.optionState.setText("Si");
        } else if ("No".equals(admin.getState())) {
            ChangeAdmin.optNo.setSelected(true);
            ChangeAdmin.optionState.setText("No");
        }

        //ChangeAdmin.birthdayField.setDateFormatString(Config.getInstance().getFormatDate());
        ChangeAdmin.birthdayField.setCalendar(admin.getDate_birthday().StringtoCalendar());
        ChangeAdmin.activityField.setText(String.valueOf(admin.getActivity()));
        //ChangeAdmin.HiringDateField.setDateFormatString(Config.getInstance().getFormatDate());
        ChangeAdmin.HiringDateField.setCalendar(admin.getHiring_date().StringtoCalendar());

    }

    public static void viewJTableAdmin(String dni) {
        int location;
        admin admin;
        admin = new admin(dni);
        location = searchAdmin(admin);
        admin = singleton.useradmin.get(location);

        ChangeAdmin.dniField.setText(admin.getDni());
        ChangeAdmin.nameField.setText(admin.getName());
        etiTitle.setText("Administrador: " + admin.getName() + " " + admin.getSurname());
        ChangeAdmin.surnameField.setText(admin.getSurname());
        ChangeAdmin.mobileField.setText(admin.getMobile());
        ChangeAdmin.emailField.setText(admin.getEmail());
        ChangeAdmin.userField.setText(admin.getUser());

        ImageIcon avatar = new ImageIcon(admin.getAvatar());
        ChangeAdmin.img.setIcon(new ImageIcon(avatar.getImage().getScaledInstance(125, 125, Image.SCALE_SMOOTH)));
        ChangeAdmin.imgPath.setText(admin.getAvatar());

        ChangeAdmin.passField.setText(admin.getPass());

        if ("Si".equals(admin.getState())) {
            ChangeAdmin.optYes.setSelected(true);
            ChangeAdmin.optionState.setText("Si");
        } else if ("No".equals(admin.getState())) {
            ChangeAdmin.optNo.setSelected(true);
        }
        ChangeAdmin.optionState.setText("No");
        ChangeAdmin.birthdayField.setDateFormatString(Config.getInstance().getFormatDate());
        ChangeAdmin.birthdayField.setCalendar(admin.getDate_birthday().StringtoCalendar());
        ChangeAdmin.activityField.setText(String.valueOf(admin.getActivity()));
        ChangeAdmin.HiringDateField.setDateFormatString(Config.getInstance().getFormatDate());
        ChangeAdmin.HiringDateField.setCalendar(admin.getHiring_date().StringtoCalendar());

    }

    //funcion modificar admin
    public static boolean update_admin() {
        int location;
        boolean checker = false;
        admin admin;
        if ((changeAdmin("name") == true) && (changeAdmin("surname") == true)
                && (changeAdmin("mobile") == true) && (changeAdmin("email") == true) && (changeAdmin("user") == true)
                && (changeAdmin("pass") == true)
                && (changeAdmin("birthday") == true) && (changeAdmin("activity") == true) && (changeAdmin("hiringdate") == true)) {

            location = searchAdminModifyDNI();
            admin = singleton.useradmin.get(location);
            admin = DAO_Admin.changeAdmin();
            singleton.useradmin.set(location, admin);
            checker = true;
        }
        return checker;
    }

    public static boolean modify_row() {
        String dni;
        boolean correcto;
        if (((miniSimpleTableModel_Admin) Admin.jTable.getModel()).getRowCount() != 0) {

            int inicio = (pagina.currentPageIndex - 1) * pagina.itemsPerPage;
            int selec = Admin.jTable.getSelectedRow();
            int selec1 = inicio + selec; //nos situamos en la fila correspondiente de esa página

            if (selec1 == -1) {
                JOptionPane.showMessageDialog(null, "No hay una persona seleccionada", "Error!", 2);
                correcto = false;

            } else {

                dni = (String) Admin.jTable.getModel().getValueAt(selec1, 0);
                singleton.admin = new admin(dni);
                int location = searchAdmin(singleton.admin);
                singleton.admin = singleton.useradmin.get(location);
                new controller_Admin(new ChangeAdmin(singleton.admin, Admin.jTable), 2).start(2);
                singleton.admin = null;
                correcto = true;

            }
        } else {
            JOptionPane.showMessageDialog(null, "lista vacía", "Error!", 2);
            correcto = false;
        }
        return correcto;
    }

    //comprueba que todos los campos esten bien antes de crear el admin
    public static boolean checkCreateAdmin() {
        boolean checker;
        checker = BLL_Admin.create_admin();
        if (checker == false) {
            Create_Admin.confirm.setText("No se ha podido crear el Administrador");
            Create_Admin.confirm.setBackground(Color.red);

        } else {
            Create_Admin.confirm.setText("El Administrador ha sido creado con exito");
            Create_Admin.confirm.setBackground(Color.green);
            saveAutoAdmin();
        }

        return checker;
    }

    //comprobar que todo este bien antes de modificar
    public static boolean checkChangeAdmin() {
        boolean checker;
        boolean dispose = false;

        checker = BLL_Admin.update_admin();
        if (checker == false) {

            ChangeAdmin.confirm.setText("El Administrador no se ha podido modificar");
            ChangeAdmin.confirm.setBackground(Color.red);
        } else {
            dispose = true;
            ChangeAdmin.confirm.setText("El Administrador se ha modificado con exito");
            ChangeAdmin.confirm.setBackground(Color.green);
            saveAutoAdmin();
        }

        return dispose;
    }

    //Eliminar admin
    public static void deleteAdmin() {

        String dni;
        int location;

        int n = ((miniSimpleTableModel_Admin) Admin.jTable.getModel()).getRowCount();
        if (n != 0) {
            int selec = Admin.jTable.getSelectedRow();
            int inicio = (pagina.currentPageIndex - 1) * pagina.itemsPerPage;
            int selec1 = inicio + selec; //nos situamos en la fila correspondiente de esa página

            if (selec1 == -1) {
                JOptionPane.showMessageDialog(null, "No se ha seleccionado a ningun administrador", "Error!", 2);
            } else {
                dni = (String) Admin.jTable.getModel().getValueAt(selec1, 0);
                admin admin = new admin(dni);
                location = searchAdmin(admin);

                int opc = JOptionPane.showConfirmDialog(null, "Estas seguro que quieres eliminar al Admin: " + dni,
                        "Info", JOptionPane.WARNING_MESSAGE);
                if (opc == 0) {

                    ((miniSimpleTableModel_Admin) Admin.jTable.getModel()).removeRow(selec1);
                    admin = singleton.useradmin.get(location);

                    singleton.useradmin.remove(admin);
                    miniSimpleTableModel_Admin.auxdates.remove(admin);

                    saveAutoAdmin();

                }

                if (((miniSimpleTableModel_Admin) Admin.jTable.getModel()).getRowCount() == 0) {
                    if (((miniSimpleTableModel_Admin) Admin.jTable.getModel()).getRowCount() != 0) {
                    }
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "No hay administradores", "Error!", 2);
        }
    }

    //buscar admin
    public static int searchAdmin(admin admin) {

        for (int i = 0; i <= (singleton.useradmin.size() - 1); i++) {
            if ((singleton.useradmin.get(i)).equals(admin)) {
                return i;
            }
        }
        return -1;
    }

    //buscar admin por dni en la ventana de crear
    public static int searchAdminDNI() {
        int location;
        admin admin;
        singleton.DNI = Create_Admin.dniField.getText();
        admin = new admin(singleton.DNI);
        location = searchAdmin(admin);
        return location;
    }

    //buscar admin por dni en la ventana de modificar
    public static int searchAdminModifyDNI() {
        int location;
        admin admin;
        if (ChangeAdmin.comboDni.isVisible() == false) {
            singleton.DNI = ChangeAdmin.dniField.getText();
        } else {
            singleton.DNI = ChangeAdmin.comboDni.getSelectedItem().toString();
        }

        admin = new admin(singleton.DNI);
        location = searchAdmin(admin);
        return location;
    }

    public static boolean askAdmin(String type) {
        boolean checker = false;
        switch (type) {
            case "dni":
                int location;
                checker = DAO_Admin.askdni();
                if (checker == true) {
                    if (singleton.useradmin.isEmpty()) {
                        checker = true;
                    } else {
                        location = searchAdminDNI();
                        if (location != -1) {
                            Create_Admin.errorDni.setText("<html><font color=red>El dni introducido ya existe</font></html>");
                            checker = false;
                        } else {
                            checker = true;
                        }
                    }
                }
                break;
            case "name":
                checker = DAO_Admin.askname();
                break;
            case "surname":
                checker = DAO_Admin.asksurname();
                break;
            case "mobile":
                checker = DAO_Admin.askmobile();
                break;
            case "email":
                checker = DAO_Admin.askemail();
                break;
            case "user":
                checker = DAO_Admin.askuser();
                break;
            case "pass":
                checker = DAO_Admin.askpassword();
                break;
            case "birthday":
                checker = DAO_Admin.askdate();
                break;
            case "activity":
                checker = DAO_Admin.askactivity();
                break;
            case "hiringdate":
                checker = DAO_Admin.askhiring_date();
                break;
        }
        return checker;
    }

    //change admin
    public static boolean changeAdmin(String type) {
        boolean checker = false;
        switch (type) {
            case "name":
                checker = DAO_Admin.changeName();
                break;
            case "surname":
                checker = DAO_Admin.changeSurname();
                break;
            case "mobile":
                checker = DAO_Admin.changeMobile();
                break;
            case "email":
                checker = DAO_Admin.changeEmail();
                break;
            case "user":
                checker = DAO_Admin.changeUser();
                break;
            case "pass":
                checker = DAO_Admin.changePassword();
                break;
            case "birthday":
                checker = DAO_Admin.changeDate();
                break;
            case "activity":
                checker = DAO_Admin.changeActivity();
                break;
            case "hiringdate":
                checker = DAO_Admin.changeHiring_date();
                break;
        }
        return checker;
    }

    public static void SaveAdmin(String type) {
        if (singleton.useradmin.size() != 0) {
            switch (type) {
                case "XML":
                    xml.saveAdmin();
                    break;
                case "JSON":
                    json.SaveAdmin();
                    break;
                case "TXT":
                    txt.saveAdmin();
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("errormessage"));
        }
    }

    public static void openAdmin(String type) {
        if (singleton.useradmin.isEmpty()) {
            switch (type) {
                case "XML":
                    xml.openAdmin();
                    break;
                case "JSON":
                    json.OpenAdmin();
                    break;
                case "TXT":
                    txt.openAdmin();
                    break;
            }
        } else {
            Boolean option = menus.menuS_N(Language.getInstance().getProperty("openmessage"),
                    Language.getInstance().getProperty("exit"), Language.getInstance().getProperty("yes"),
                    Language.getInstance().getProperty("no"));
            if (option == true) {
                switch (type) {
                    case "XML":
                        xml.saveAdmin();
                        break;
                    case "JSON":
                        json.SaveAdmin();
                        break;
                    case "TXT":
                        txt.saveAdmin();
                        break;
                }
            } else {
                switch (type) {
                    case "XML":
                        xml.openAdmin();
                        break;
                    case "JSON":
                        json.OpenAdmin();
                        break;
                    case "TXT":
                        txt.openAdmin();
                        break;
                }
            }
        }
    }

    public static void Exit() {
        JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("exitmessage"));
        System.exit(0);
    }

    public static void saveAutoAdmin() {
        xml.autosaveadmin();
        txt.saveAutoAdmin();
        json.SaveAutoAdmin();
    }

    public static void createAvatar() {
        avatar.createAvatar();
    }

    public static void loadAvatar() {
        avatar.OpenAvatar();
    }

    public static void openAuto() {
        // admin
        xml.OpenAutoAdmin();

        txt.openAutoAdmin();

        json.OpenAutoAdmin();

    }

    public static void changeConfig() {

        String dateformat = "", themes = "", files="", currency="",language="";
        int decimals=0;

        //DATES
        if (Config_jFrame.optDate1.isSelected()) {
            dateformat = "dd/MM/yyyy";
        } else if (Config_jFrame.optDate2.isSelected()) {
            dateformat = "dd-MM-yyyy";
        } else if (Config_jFrame.optDate3.isSelected()) {
            dateformat = "yyyy/MM/dd";
        } else if (Config_jFrame.optDate4.isSelected()) {
            dateformat = "yyyy-MM-dd";
        }
        Config.getInstance().setFormatDate(dateformat);

        //THEMES
       if (Config_jFrame.optMetal.isSelected()) {
            themes = "Metal";
        } else if (Config_jFrame.optWindows.isSelected()) {
            themes = "Windows";
        } else if (Config_jFrame.optMotif.isSelected()) {
            themes = "Motif";
        } else if (Config_jFrame.optNimbus.isSelected()) {
            themes = "Nimbus";
        }
        Config.getInstance().setTheme(themes);
        Themes.themes();

        //DECIMALS
        if (Config_jFrame.optDecimals1.isSelected()) {
            decimals=1;
        } else if (Config_jFrame.optDecimals2.isSelected()) {
            decimals=2;
        } else if (Config_jFrame.optDecimals3.isSelected()) {
            decimals =3;
        }
        Config.getInstance().setDecimals(decimals);
         
        //MONEDAS
        if (Config_jFrame.optEuros.isSelected()) {
            currency ="€";
        } else if (Config_jFrame.optDolars.isSelected()) {
            currency ="$";
        } else if (Config_jFrame.optLibras.isSelected()) {
            currency ="⁭£";
        }
        Config.getInstance().setCurrency(currency);

        //ARCHIVOS
        if (Config_jFrame.optJson.isSelected()) {
            files = "JSON";
        } else if (Config_jFrame.optXml.isSelected()) {
            files ="XML";
        } else if (Config_jFrame.optTxt.isSelected()) {
            files ="TXT";
        }
        Config.getInstance().setFiles(files);

        //IDIOMA
        if(Config_jFrame.optEn.isSelected()){
            language="EN";
        }else if(Config_jFrame.optEsp.isSelected()){
            language="ES";
        }else if(Config_jFrame.optVal.isSelected()){
            language="VAL";
        }
        Config.getInstance().setLanguage(language);
    }

}
