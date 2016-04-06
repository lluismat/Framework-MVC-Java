/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.users.userreg.model.BLL;

import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modules.Config.model.Language;
import modules.users.userreg.controller.controller_Userreg;
import modules.users.userreg.model.DAO.DAO_Userreg;
import modules.users.userreg.model.classes.miniSimpleTableModel_Userreg;
import modules.users.userreg.model.classes.registered_user;
import modules.users.userreg.model.utils.avatar_Userreg;
import modules.users.userreg.model.utils.json_Userreg;
import modules.users.userreg.model.utils.pagina_Userreg;
import modules.users.userreg.model.utils.txt_Userreg;
import modules.users.userreg.model.utils.xml_Userreg;
import modules.users.userreg.view.ChangeUserreg;
import modules.users.userreg.view.UserReg;
import modules.users.userreg.view.createUserreg;
import modules.users.users.singleton;
import utils.menus;

/**
 *
 * @author lluis
 */
public class BLL_Userreg {
    
    //Funcion validar y crear usuario registrado
    public static boolean create_Userreg() {
        int location;
        boolean checker = false;
        registered_user userreg;
        location = searchUserregDNI();
        if (location != -1) {
            JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("erroradminexist"));
            checker = false;
        } else if ((askUserreg("dni") == true) && (askUserreg("name") == true) && (askUserreg("surname") == true)
                && (askUserreg("mobile") == true) && (askUserreg("email") == true) && (askUserreg("user")) && (askUserreg("pass") == true)
                && (askUserreg("birthday") == true) && (askUserreg("activity") == true)) {

            userreg = DAO_Userreg.pideUserreg();
            singleton.userreg.add(userreg);
            checker = true;
        }
        return checker;
    }
    
    
    public static void viewUserreg() {
        int location;
        registered_user userreg;
        location = searchUserregModifyDNI();
        userreg = singleton.userreg.get(location);

        ChangeUserreg.nameField.setText(userreg.getName());
        ChangeUserreg.etiTitle.setText("Administrador: " + userreg.getName() + " " + userreg.getSurname());
        ChangeUserreg.surnameField.setText(userreg.getSurname());
        ChangeUserreg.mobileField.setText(userreg.getMobile());
        ChangeUserreg.emailField.setText(userreg.getEmail());
        ChangeUserreg.userField.setText(userreg.getUser());

        ImageIcon avatar = new ImageIcon(userreg.getAvatar());
        ChangeUserreg.img.setIcon(new ImageIcon(avatar.getImage().getScaledInstance(125, 125, Image.SCALE_SMOOTH)));
        ChangeUserreg.imgPath.setText(userreg.getAvatar());

        ChangeUserreg.passField.setText(userreg.getPass());

        if ("Si".equals(userreg.getState())) {
            ChangeUserreg.optYes.setSelected(true);
            ChangeUserreg.optionState.setText("Si");
        } else if ("No".equals(userreg.getState())) {
            ChangeUserreg.optNo.setSelected(true);
            ChangeUserreg.optionState.setText("No");
        }
        
        ChangeUserreg.birthdayField.setCalendar(userreg.getDate_birthday().StringtoCalendar());
        ChangeUserreg.activityField.setText(String.valueOf(userreg.getActivity()));

    }

    public static void viewJTableUserreg(String dni) {
        int location;
        registered_user userreg;
        userreg = new registered_user(dni);
        location = searchUserreg(userreg);
        userreg = singleton.userreg.get(location);

        ChangeUserreg.dniField.setText(userreg.getDni());
        ChangeUserreg.nameField.setText(userreg.getName());
        ChangeUserreg.etiTitle.setText("Administrador: " + userreg.getName() + " " + userreg.getSurname());
        ChangeUserreg.surnameField.setText(userreg.getSurname());
        ChangeUserreg.mobileField.setText(userreg.getMobile());
        ChangeUserreg.emailField.setText(userreg.getEmail());
        ChangeUserreg.userField.setText(userreg.getUser());

        ImageIcon avatar = new ImageIcon(userreg.getAvatar());
        ChangeUserreg.img.setIcon(new ImageIcon(avatar.getImage().getScaledInstance(125, 125, Image.SCALE_SMOOTH)));
        ChangeUserreg.imgPath.setText(userreg.getAvatar());

        ChangeUserreg.passField.setText(userreg.getPass());

        if ("Si".equals(userreg.getState())) {
            ChangeUserreg.optYes.setSelected(true);
            ChangeUserreg.optionState.setText("Si");
        } else if ("No".equals(userreg.getState())) {
            ChangeUserreg.optNo.setSelected(true);
            ChangeUserreg.optionState.setText("No");
        }
        
        ChangeUserreg.birthdayField.setCalendar(userreg.getDate_birthday().StringtoCalendar());
        ChangeUserreg.activityField.setText(String.valueOf(userreg.getActivity()));

    }

    //funcion modificar admin
    public static boolean update_Userreg() {
        int location;
        boolean checker = false;
        registered_user userreg;
        if ((changeUserreg("name") == true) && (changeUserreg("surname") == true)
                && (changeUserreg("mobile") == true) && (changeUserreg("email") == true) && (changeUserreg("user") == true)
                && (changeUserreg("pass") == true)&& (changeUserreg("birthday") == true) && (changeUserreg("activity") == true)) {

            location = searchUserregModifyDNI();
            userreg = singleton.userreg.get(location);
            userreg = DAO_Userreg.changeUserreg();
            singleton.userreg.set(location, userreg);
            checker = true;
        }
        return checker;
    }

    public static boolean modify_row() {
        String dni;
        boolean correcto;
        if (((miniSimpleTableModel_Userreg) UserReg.jTable.getModel()).getRowCount() != 0) {
            
            int inicio = (pagina_Userreg.currentPageIndex - 1) * pagina_Userreg.itemsPerPage;
            int selec = UserReg.jTable.getSelectedRow();
            int selec1 = inicio + selec; //nos situamos en la fila correspondiente de esa página

            if (selec1 == -1) {
                JOptionPane.showMessageDialog(null, "No hay una persona seleccionada", "Error!", 2);
                correcto = false;

            } else {

                dni = (String) UserReg.jTable.getModel().getValueAt(selec1, 0);
                singleton.registered_user = new registered_user(dni);
                int location = searchUserreg(singleton.registered_user);
                singleton.registered_user = singleton.userreg.get(location);
                new controller_Userreg(new ChangeUserreg(singleton.registered_user, UserReg.jTable),2).start(2);
                singleton.admin=null;
                correcto = true;
                

            }
        } else {
            JOptionPane.showMessageDialog(null, "lista vacía", "Error!", 2);
            correcto = false;
        }
        return correcto;
    }

    //comprueba que todos los campos esten bien antes de crear el admin
    public static boolean checkCreateUserreg() {
        boolean checker;
        checker = BLL_Userreg.create_Userreg();
        if (checker == false) {
            createUserreg.confirm.setText("No se ha podido crear el Administrador");
            createUserreg.confirm.setBackground(Color.red);

        } else {
            createUserreg.confirm.setText("El Administrador ha sido creado con exito");
            createUserreg.confirm.setBackground(Color.green);
            saveAutoUserreg();
        }

        return checker;
    }

    //comprobar que todo este bien antes de modificar
    public static boolean checkChangeUserreg() {
        boolean checker;
        boolean dispose = false;

        checker = BLL_Userreg.update_Userreg();
        if (checker == false) {

            ChangeUserreg.confirm.setText("El Administrador no se ha podido modificar");
            ChangeUserreg.confirm.setBackground(Color.red);
        } else {
            dispose = true;
            ChangeUserreg.confirm.setText("El Administrador se ha modificado con exito");
            ChangeUserreg.confirm.setBackground(Color.green);
            saveAutoUserreg();
        }

        return dispose;
    }

    //Eliminar admin
    public static void deleteAdmin() {

        String dni;
        int location;

        int n = ((miniSimpleTableModel_Userreg) UserReg.jTable.getModel()).getRowCount();
        if (n != 0) {
            int selec = UserReg.jTable.getSelectedRow();
            int inicio = (pagina_Userreg.currentPageIndex - 1) * pagina_Userreg.itemsPerPage;
            int selec1 = inicio + selec; //nos situamos en la fila correspondiente de esa página

            if (selec1 == -1) {
                JOptionPane.showMessageDialog(null, "No se ha seleccionado a ningun administrador", "Error!", 2);
            } else {
                dni = (String) UserReg.jTable.getModel().getValueAt(selec1, 0);
                registered_user userreg = new registered_user(dni);
                location = searchUserreg(userreg);
               
                int opc = JOptionPane.showConfirmDialog(null, "Estas seguro que quieres eliminar al Admin: " + dni,
                        "Info", JOptionPane.WARNING_MESSAGE);
                if (opc == 0) {
                   
                    ((miniSimpleTableModel_Userreg) UserReg.jTable.getModel()).removeRow(selec1);
                    userreg = singleton.userreg.get(location);

                    singleton.useradmin.remove(userreg);
                    miniSimpleTableModel_Userreg.auxdates.remove(userreg);

                    saveAutoUserreg();

                }
                
                if (((miniSimpleTableModel_Userreg) UserReg.jTable.getModel()).getRowCount() == 0) {
                    if (((miniSimpleTableModel_Userreg) UserReg.jTable.getModel()).getRowCount() != 0) {
                    }
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "No hay administradores", "Error!", 2);
        }
    }

    
    
     //buscar usuario registrado
    public static int searchUserreg(registered_user userreg) {

        for (int i = 0; i <= (singleton.userreg.size() - 1); i++) {
            if ((singleton.userreg.get(i)).equals(userreg)) {
                return i;
            }
        }
        return -1;
    }

    //buscar usuario registrado por dni en la ventana de crear
    public static int searchUserregDNI() {
        int location;
        registered_user userreg;
        singleton.DNI = createUserreg.dniField.getText();
        userreg = new registered_user(singleton.DNI);
        location = searchUserreg(userreg);
        return location;
    }

    //buscar usuario registrado por dni en la ventana de modificar
    public static int searchUserregModifyDNI() {
        int location;
        registered_user userreg;
        if (ChangeUserreg.comboDni.isVisible() == false) {
            singleton.DNI = ChangeUserreg.dniField.getText();
        } else {
            singleton.DNI = ChangeUserreg.comboDni.getSelectedItem().toString();
        }

        userreg = new registered_user(singleton.DNI);
        location = searchUserreg(userreg);
        return location;
    }

    public static boolean askUserreg(String type) {
        boolean checker = false;
        switch (type) {
            case "dni":
                int location;
                checker = DAO_Userreg.askdni();
                if (checker == true) {
                    if (singleton.useradmin.isEmpty()) {
                        checker = true;
                    } else {
                        location = searchUserregDNI();
                        if (location != -1) {
                            createUserreg.errorDni.setText("<html><font color=red>El dni introducido ya existe</font></html>");
                            checker = false;
                        } else {
                            checker = true;
                        }
                    }
                }
                break;
            case "name":
                checker = DAO_Userreg.askname();
                break;
            case "surname":
                checker = DAO_Userreg.asksurname();
                break;
            case "mobile":
                checker = DAO_Userreg.askmobile();
                break;
            case "email":
                checker = DAO_Userreg.askemail();
                break;
            case "user":
                checker = DAO_Userreg.askuser();
                break;
            case "pass":
                checker = DAO_Userreg.askpassword();
                break;
            case "birthday":
                checker = DAO_Userreg.askdate();
                break;
            case "activity":
                checker = DAO_Userreg.askactivity();
                break;
        }
        return checker;
    }

    //change admin
    public static boolean changeUserreg(String type) {
        boolean checker = false;
        switch (type) {
            case "name":
                checker = DAO_Userreg.changeName();
                break;
            case "surname":
                checker = DAO_Userreg.changeSurname();
                break;
            case "mobile":
                checker = DAO_Userreg.changeMobile();
                break;
            case "email":
                checker = DAO_Userreg.changeEmail();
                break;
            case "user":
                checker = DAO_Userreg.changeUser();
                break;
            case "pass":
                checker = DAO_Userreg.changePassword();
                break;
            case "birthday":
                checker = DAO_Userreg.changeDate();
                break;
            case "activity":
                checker = DAO_Userreg.changeActivity();
                break;
        }
        return checker;
    }
    
    public static void SaveUserreg(String type) {
        if (singleton.userreg.size() != 0) {
            switch (type) {
                case "XML":
                    xml_Userreg.saveUserreg();
                    break;
                case "JSON":
                    json_Userreg.SaveUserreg();
                    break;
                case "TXT":
                    txt_Userreg.saveUserreg();
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("errormessage"));
        }
    }

    public static void openUserreg(String type) {
        if (singleton.useradmin.isEmpty()) {
            switch (type) {
                case "XML":
                    xml_Userreg.openUserreg();
                    break;
                case "JSON":
                    json_Userreg.OpenUserreg();
                    break;
                case "TXT":
                    txt_Userreg.openUserreg();
                    break;
            }
        } else {
            Boolean option = menus.menuS_N(Language.getInstance().getProperty("openmessage"),
                    Language.getInstance().getProperty("exit"), Language.getInstance().getProperty("yes"),
                    Language.getInstance().getProperty("no"));
            if (option == true) {
                switch (type) {
                    case "XML":
                        xml_Userreg.saveUserreg();
                        break;
                    case "JSON":
                        json_Userreg.SaveUserreg();
                        break;
                    case "TXT":
                        txt_Userreg.saveUserreg();
                        break;
                }
            } else {
                switch (type) {
                    case "XML":
                        xml_Userreg.openUserreg();
                        break;
                    case "JSON":
                        json_Userreg.OpenUserreg();
                        break;
                    case "TXT":
                        txt_Userreg.openUserreg();
                        break;
                }
            }
        }
    }

    public static void Exit() {
        JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("exitmessage"));
        System.exit(0);
    }

    public static void saveAutoUserreg() {
        xml_Userreg.autosaveUserreg();
        txt_Userreg.saveAutoUserreg();
        json_Userreg.SaveAutoUserreg();
    }

    public static void createAvatar() {
        avatar_Userreg.createAvatar();
    }

    public static void loadAvatar() {
        avatar_Userreg.OpenAvatar();
    }

    public static void openAuto() {
        // admin
        xml_Userreg.OpenAutoUserreg();

        txt_Userreg.openAutoUserreg();

        json_Userreg.OpenAutoUserreg();

    }
    
    
}
