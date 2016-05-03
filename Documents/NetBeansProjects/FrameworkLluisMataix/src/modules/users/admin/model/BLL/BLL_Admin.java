/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.users.admin.model.BLL;

import modules.menu.model.Language;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modules.menu.view.Config_jFrame;
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
import modules.menu.model.Config;
import utils.Themes;

/**
 *
 * @author lluis
 */
public class BLL_Admin {

    /**
     * funcion que valida todos los campos estan bien y crea el administrador y lo inserta en BBDD y en el arraylist
     * @return 
     */
    public static boolean create_admin() {
        int location;
        boolean checker = false;
        location = searchAdminDNI();
        if (location != -1) {
            JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("erroradminexist"));
            checker = false;
        } else if ((askAdmin("dni") == true) && (askAdmin("name") == true) && (askAdmin("surname") == true)
                && (askAdmin("mobile") == true) && (askAdmin("email") == true) && (askAdmin("user")) && (askAdmin("pass") == true)
                && (askAdmin("birthday") == true) && (askAdmin("activity") == true) && (askAdmin("hiringdate") == true)) {

            DAO_Admin.pideAdmin();
            singleton.useradmin.add(singleton.admin);
            BLL_BBDD.NewAdmin();

            checker = true;
        }
        return checker;
    }

    /**
     * funcion para mostrar los datos del administrador
     */
    public static void viewAdmin() {
        int location;
        location = searchAdminModifyDNI();
        singleton.admin = singleton.useradmin.get(location);

        ChangeAdmin.nameField.setText(singleton.admin.getName());
        etiTitle.setText(Language.getInstance().getProperty("admin")+": " + singleton.admin.getName() + " " + singleton.admin.getSurname());
        ChangeAdmin.surnameField.setText(singleton.admin.getSurname());
        ChangeAdmin.mobileField.setText(singleton.admin.getMobile());
        ChangeAdmin.emailField.setText(singleton.admin.getEmail());
        ChangeAdmin.userField.setText(singleton.admin.getUser());

        ImageIcon avatar = new ImageIcon(singleton.admin.getAvatar());
        ChangeAdmin.img.setIcon(new ImageIcon(avatar.getImage().getScaledInstance(125, 125, Image.SCALE_SMOOTH)));
        ChangeAdmin.imgPath.setText(singleton.admin.getAvatar());

        ChangeAdmin.passField.setText(singleton.admin.getPass());

        if ("Si".equals(singleton.admin.getState())) {
            ChangeAdmin.optYes.setSelected(true);
            ChangeAdmin.optionState.setText("Si");
        } else if ("No".equals(singleton.admin.getState())) {
            ChangeAdmin.optNo.setSelected(true);
            ChangeAdmin.optionState.setText("No");
        }

        ChangeAdmin.birthdayField.setCalendar(singleton.admin.getDate_birthday().StringtoCalendar());
        ChangeAdmin.activityField.setText(String.valueOf(singleton.admin.getActivity()));

        ChangeAdmin.HiringDateField.setCalendar(singleton.admin.getHiring_date().StringtoCalendar());

    }

    /**
     * funcion para mostrar datos del administrador cuando seleccionas del pager
     * @param dni 
     */
    public static void viewJTableAdmin(String dni) {
        int location;
        singleton.admin = new admin(dni);
        location = searchAdmin(singleton.admin);
        singleton.admin = singleton.useradmin.get(location);

        ChangeAdmin.dniField.setText(singleton.admin.getDni());
        ChangeAdmin.nameField.setText(singleton.admin.getName());
        etiTitle.setText(Language.getInstance().getProperty("admin")+": " + singleton.admin.getName() + " " + singleton.admin.getSurname());
        ChangeAdmin.surnameField.setText(singleton.admin.getSurname());
        ChangeAdmin.mobileField.setText(singleton.admin.getMobile());
        ChangeAdmin.emailField.setText(singleton.admin.getEmail());
        ChangeAdmin.userField.setText(singleton.admin.getUser());

        ImageIcon avatar = new ImageIcon(singleton.admin.getAvatar());
        ChangeAdmin.img.setIcon(new ImageIcon(avatar.getImage().getScaledInstance(125, 125, Image.SCALE_SMOOTH)));
        ChangeAdmin.imgPath.setText(singleton.admin.getAvatar());

        ChangeAdmin.passField.setText(singleton.admin.getPass());

        if ("Si".equals(singleton.admin.getState())) {
            ChangeAdmin.optYes.setSelected(true);
            ChangeAdmin.optionState.setText("Si");
        } else if ("No".equals(singleton.admin.getState())) {
            ChangeAdmin.optNo.setSelected(true);
        }
        ChangeAdmin.optionState.setText("No");
        ChangeAdmin.birthdayField.setDateFormatString(Config.getInstance().getFormatDate());
        ChangeAdmin.birthdayField.setCalendar(singleton.admin.getDate_birthday().StringtoCalendar());
        ChangeAdmin.activityField.setText(String.valueOf(singleton.admin.getActivity()));
        ChangeAdmin.HiringDateField.setDateFormatString(Config.getInstance().getFormatDate());
        ChangeAdmin.HiringDateField.setCalendar(singleton.admin.getHiring_date().StringtoCalendar());

    }

    
    /**
     * funcion que valida los campos y modifica el administrador en BBDD
     * @return 
     */
    public static boolean update_admin() {
        int location;
        boolean checker = false;
        if ((changeAdmin("name") == true) && (changeAdmin("surname") == true)
                && (changeAdmin("mobile") == true) && (changeAdmin("email") == true) && (changeAdmin("user") == true)
                && (changeAdmin("pass") == true)
                && (changeAdmin("birthday") == true) && (changeAdmin("activity") == true) && (changeAdmin("hiringdate") == true)) {

            location = searchAdminModifyDNI();
            singleton.admin = singleton.useradmin.get(location);
            DAO_Admin.changeAdmin();
            singleton.useradmin.set(location, singleton.admin);
            BLL_BBDD.modifyAdminBLL();
            checker = true;
        }
        return checker;
    }

    /**
     * funcion modificar si seleccionan un administrador desde el pager
     * @return 
     */
    public static boolean modify_row() {
        String dni;
        boolean correcto;
        if (((miniSimpleTableModel_Admin) Admin.jTable.getModel()).getRowCount() != 0) {

            int inicio = (pagina.currentPageIndex - 1) * pagina.itemsPerPage;
            int selec = Admin.jTable.getSelectedRow();
            int selec1 = inicio + selec; //nos situamos en la fila correspondiente de esa página

            if (selec1 == -1) {
                JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("errorpager1"), "Error!", 2);
                correcto = false;

            } else {

                dni = (String) Admin.jTable.getModel().getValueAt(selec1, 0);
                singleton.admin = new admin(dni);
                int location = searchAdmin(singleton.admin);
                singleton.admin = singleton.useradmin.get(location);
                singleton.adminPager = singleton.admin;
                new controller_Admin(new ChangeAdmin(singleton.adminPager, Admin.jTable), 2).start(2);

                correcto = true;
                singleton.adminPager = null;
            }
        } else {
            JOptionPane.showMessageDialog(null,Language.getInstance().getProperty("errorpager2"), "Error!", 2);
            correcto = false;
        }
        return correcto;
    }

    /**
     * funcion para mostrar al usuario si el admin se ha creado o no
     * @return 
     */
    public static boolean checkCreateAdmin() {
        boolean checker;
        checker = BLL_Admin.create_admin();
        if (checker == false) {
            Create_Admin.confirm.setText(Language.getInstance().getProperty("errorcreateadmin"));
            Create_Admin.confirm.setBackground(Color.red);

        } else {
            Create_Admin.confirm.setText(Language.getInstance().getProperty("createadminok"));
            Create_Admin.confirm.setBackground(Color.green);

        }

        return checker;
    }

 /**
  * funcion que muestra al usuario si se ha modificado con exito el administrador o no
  * @return 
  */
    public static boolean checkChangeAdmin() {
        boolean checker;
        boolean dispose = false;

        checker = BLL_Admin.update_admin();
        if (checker == false) {

            ChangeAdmin.confirm.setText(Language.getInstance().getProperty("errormodifyadmin"));
            ChangeAdmin.confirm.setBackground(Color.red);
        } else {
            dispose = true;
            ChangeAdmin.confirm.setText(Language.getInstance().getProperty("modifyadminok"));
            ChangeAdmin.confirm.setBackground(Color.green);

        }

        return dispose;
    }

    /**
     * funcion para eliminar el administrador
     */
    public static void deleteAdmin() {

        String dni;
        int location;

        int n = ((miniSimpleTableModel_Admin) Admin.jTable.getModel()).getRowCount();
        if (n != 0) {

            int inicio = (pagina.currentPageIndex - 1) * pagina.itemsPerPage;
            int selec = Admin.jTable.getSelectedRow();
            int selec1 = inicio + selec; //nos situamos en la fila correspondiente de esa página

            if (selec1 == -1) {
                JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("errorpager1"), "Error!", 2);
            } else {
                dni = (String) Admin.jTable.getModel().getValueAt(selec1, 0);
                singleton.admin = new admin(dni);
                location = searchAdmin(singleton.admin);

                int opc = JOptionPane.showConfirmDialog(null, Language.getInstance().getProperty("deletemessage") + dni+"?",
                        "Info", JOptionPane.WARNING_MESSAGE);
                if (opc == 0) {

                    ((miniSimpleTableModel_Admin) Admin.jTable.getModel()).removeRow(selec1);
                    singleton.admin = singleton.useradmin.get(location);
                    singleton.useradmin.remove(singleton.admin);
                    miniSimpleTableModel_Admin.auxdates.remove(singleton.admin);
                    BLL_BBDD.DeleteAdminBLL();
                }
                if (((miniSimpleTableModel_Admin) Admin.jTable.getModel()).getRowCount() == 0) {
                    if (((miniSimpleTableModel_Admin) Admin.jTable.getModel()).getRowCount() != 0) {
                    }
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("errordelete"), "Error!", 2);
        }
    }

    /**
     * funcion para buscar el admin
     * @param admin
     * @return 
     */
    public static int searchAdmin(admin admin) {

        for (int i = 0; i <= (singleton.useradmin.size() - 1); i++) {
            if ((singleton.useradmin.get(i)).equals(admin)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * funcion para buscar por dni un administrador en la ventana de crear
     * @return 
     */
    public static int searchAdminDNI() {
        int location;
        admin admin;
        singleton.DNI = Create_Admin.dniField.getText();
        admin = new admin(singleton.DNI);
        location = searchAdmin(admin);
        return location;
    }

    /**
     * funcion para buscar por dni un administrador en la ventana de modificar
     * @return 
     */
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

    /**
     * funcion que llama a las funciones del DAO para validar los campos al crear el administrador
     * @param type
     * @return 
     */
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
                            Create_Admin.errorDni.setForeground(Color.red);
                            Create_Admin.errorDni.setText(Language.getInstance().getProperty("errordni3"));
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

   /**
    * funcion que llama a las funciones del DAO para validar los campos de la ventana de modificar un administrador
    * @param type
    * @return 
    */
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

    /**
     * funcion para guardar un administrador en JSON,XML o TXT 
     * @param type 
     */
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

    /**
     * funcion para abrir archivos de un administrador en JSON,XML o TXT
     * @param type 
     */
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

    /**
     * funcion para salir
     */
    public static void Exit() {
        JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("exitmessage"));
        System.exit(0);
    }

    /**
     * funcion que guarda automaticamente un administrador
     */
    public static void saveAutoAdmin() {
        xml.autosaveadmin();
        txt.saveAutoAdmin();
        json.SaveAutoAdmin();
    }

    /**
     * funcion para seleccionar un avatar
     */
    public static void createAvatar() {
        avatar.createAvatar();
    }

    /**
     * funcion para cargar un avatar
     */
    public static void loadAvatar() {
        avatar.OpenAvatar();
    }

    /**
     * funcion para abrir automaticamente ficheros de un administrador
     */
    public static void openAuto() {
        // admin
        xml.OpenAutoAdmin();

        txt.openAutoAdmin();

        json.OpenAutoAdmin();

    }

    /**
     * funcion para cambiar la configuracion 
     */
    public static void changeConfig() {

        String dateformat = "", themes = "", files = "", currency = "", language = "";
        int decimals = 0;

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
            decimals = 1;
        } else if (Config_jFrame.optDecimals2.isSelected()) {
            decimals = 2;
        } else if (Config_jFrame.optDecimals3.isSelected()) {
            decimals = 3;
        }
        Config.getInstance().setDecimals(decimals);

        //MONEDAS
        if (Config_jFrame.optEuros.isSelected()) {
            currency = "€";
        } else if (Config_jFrame.optDolars.isSelected()) {
            currency = "$";
        } else if (Config_jFrame.optLibras.isSelected()) {
            currency = "₤";
        }
        Config.getInstance().setCurrency(currency);

        //ARCHIVOS
        if (Config_jFrame.optJson.isSelected()) {
            files = "JSON";
        } else if (Config_jFrame.optXml.isSelected()) {
            files = "XML";
        } else if (Config_jFrame.optTxt.isSelected()) {
            files = "TXT";
        }
        Config.getInstance().setFiles(files);

        //IDIOMA
        if (Config_jFrame.optEn.isSelected()) {
            language = "EN";
        } else if (Config_jFrame.optEsp.isSelected()) {
            language = "ES";
        } else if (Config_jFrame.optVal.isSelected()) {
            language = "VAL";
        }
        Config.getInstance().setLanguage(language);
    }

}
