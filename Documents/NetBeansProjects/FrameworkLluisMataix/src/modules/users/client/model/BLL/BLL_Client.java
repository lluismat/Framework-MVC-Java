/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.users.client.model.BLL;

import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modules.menu.model.Language;
import modules.users.client.controller.controller_Client;
import modules.users.client.model.DAO.DAO_Client;
import modules.users.client.model.classes.client;
import modules.users.client.model.classes.miniSimpleTableModel_Client;
import modules.users.client.model.utils.avatar_client;
import modules.users.client.model.utils.json_client;
import modules.users.client.model.utils.pagina_client;
import modules.users.client.model.utils.txt_client;
import modules.users.client.model.utils.xml_client;
import modules.users.client.view.ChangeClient;
import modules.users.client.view.Client;
import modules.users.client.view.CreateClient;
import modules.users.users.singleton;
import utils.menus;

/**
 *
 * @author lluis
 */
public class BLL_Client {

    public static boolean create_client() {
        int location;
        boolean checker = false;
        
        location = searchClientDNI();
        if (location != -1) {
            JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("erroradminexist"));
            checker = false;
        } else if ((askClient("dni") == true) && (askClient("name") == true) && (askClient("surname") == true)
                && (askClient("mobile") == true) && (askClient("email") == true) && (askClient("user")==true) 
                && (askClient("pass") == true)&& (askClient("birthday") == true) && (askClient("purchase") == true) 
                && (askClient("clienttype") == true)&& (askClient("dischargedate") == true)) {

            singleton.client = DAO_Client.pideClient();
            singleton.userclient.add(singleton.client);
            BLL_Mongo.insert_Client();
            checker = true;
        }
        return checker;
    }

    public static void viewClient() {
        int location;
        client client;
        location = searchClientModifyDNI();
        client = singleton.userclient.get(location);

        ChangeClient.nameField.setText(client.getName());
        ChangeClient.etiTitle.setText("Cliente: " + client.getName() + " " + client.getSurname());
        ChangeClient.surnameField.setText(client.getSurname());
        ChangeClient.mobileField.setText(client.getMobile());
        ChangeClient.emailField.setText(client.getEmail());
        ChangeClient.userField.setText(client.getUser());

        ImageIcon avatar = new ImageIcon(client.getAvatar());
        ChangeClient.img.setIcon(new ImageIcon(avatar.getImage().getScaledInstance(125, 125, Image.SCALE_SMOOTH)));
        ChangeClient.imgPath.setText(client.getAvatar());

        ChangeClient.passField.setText(client.getPass());

        if ("Si".equals(client.getState())) {
            ChangeClient.optYes.setSelected(true);
            ChangeClient.optionState.setText("Si");
        } else if ("No".equals(client.getState())) {
            ChangeClient.optNo.setSelected(true);
            ChangeClient.optionState.setText("No");
        }
        if("Si".equals(client.getPremium())){
            ChangeClient.optYes1.setSelected(true);
            ChangeClient.premium.setText("Si");
        }else if("No".equals(client.getPremium())){
            ChangeClient.optNo1.setSelected(true);
            ChangeClient.premium.setText("No");
        }
        ChangeClient.birthdayField.setCalendar(client.getDate_birthday().StringtoCalendar());
        ChangeClient.purchaseField.setText(String.valueOf(client.getPurchase()));
        ChangeClient.dischargeDateField.setCalendar(client.getDischarge_date().StringtoCalendar());
        ChangeClient.clientTypeField.setText(client.getClient_type());

    }

    public static void viewJTableClient(String dni) {
        int location;
        client client;
        client = new client(dni);
        location = searchClient(client);
        client = singleton.userclient.get(location);

        ChangeClient.dniField.setText(client.getDni());
        ChangeClient.nameField.setText(client.getName());
        ChangeClient.etiTitle.setText("Cliente: " + client.getName() + " " + client.getSurname());
        ChangeClient.surnameField.setText(client.getSurname());
        ChangeClient.mobileField.setText(client.getMobile());
        ChangeClient.emailField.setText(client.getEmail());
        ChangeClient.userField.setText(client.getUser());

        ImageIcon avatar = new ImageIcon(client.getAvatar());
        ChangeClient.img.setIcon(new ImageIcon(avatar.getImage().getScaledInstance(125, 125, Image.SCALE_SMOOTH)));
        ChangeClient.imgPath.setText(client.getAvatar());

        ChangeClient.passField.setText(client.getPass());

      if ("Si".equals(client.getState())) {
            ChangeClient.optYes.setSelected(true);
            ChangeClient.optionState.setText("Si");
        } else if ("No".equals(client.getState())) {
            ChangeClient.optNo.setSelected(true);
            ChangeClient.optionState.setText("No");
        }
        if("Si".equals(client.getPremium())){
            ChangeClient.optYes1.setSelected(true);
            ChangeClient.premium.setText("Si");
        }else if("No".equals(client.getPremium())){
            ChangeClient.optNo1.setSelected(true);
            ChangeClient.premium.setText("No");
        }
        
        ChangeClient.birthdayField.setCalendar(client.getDate_birthday().StringtoCalendar());
        ChangeClient.purchaseField.setText(String.valueOf(client.getPurchase()));
        ChangeClient.dischargeDateField.setCalendar(client.getDischarge_date().StringtoCalendar());
        ChangeClient.clientTypeField.setText(client.getClient_type());

    }

    //funcion modificar admin
    public static boolean update_Client() {
        int location;
        boolean checker = false;
        if ((changeClient("name") == true) && (changeClient("surname") == true)
                && (changeClient("mobile") == true) && (changeClient("email") == true) && (changeClient("user")==true)
                && (changeClient("pass") == true)&& (changeClient("birthday") == true) && (changeClient("purchase") == true)
                && (changeClient("clienttype") == true)&& (changeClient("dischargedate") == true)) {

            location = searchClientModifyDNI();
            singleton.client = singleton.userclient.get(location);
            singleton.client = DAO_Client.changeClient();
            singleton.userclient.set(location, singleton.client);
            BLL_Mongo.updateClient();
            checker = true;
        }
        return checker;
    }

    public static boolean modify_row() {
        String dni;
        boolean correcto;
        if (((miniSimpleTableModel_Client) Client.jTable_Client.getModel()).getRowCount() != 0) {
            int inicio = (pagina_client.currentPageIndex - 1) * pagina_client.itemsPerPage;
            int selec = Client.jTable_Client.getSelectedRow();
            int selec1 = inicio + selec; //nos situamos en la fila correspondiente de esa página

            if (selec1 == -1) {
                JOptionPane.showMessageDialog(null, "No hay una persona seleccionada", "Error!", 2);
                correcto = false;

            } else {

                dni = (String) Client.jTable_Client.getModel().getValueAt(selec1, 0);
                singleton.client = new client(dni);
                int location = searchClient(singleton.client);
                singleton.client = singleton.userclient.get(location);
                new controller_Client(new ChangeClient(singleton.client, Client.jTable_Client),2).start(2);
                singleton.client=null;
                correcto = true;
                
            }
        } else {
            JOptionPane.showMessageDialog(null, "lista vacía", "Error!", 2);
            correcto = false;
        }
        return correcto;
    }

    //comprueba que todos los campos esten bien antes de crear el admin
    public static boolean checkCreateClient() {
        boolean checker;
        checker = BLL_Client.create_client();
        if (checker == false) {
            CreateClient.confirm.setText("No se ha podido crear el Cliente");
            CreateClient.confirm.setBackground(Color.red);

        } else {
            CreateClient.confirm.setText("El Cliente ha sido creado con exito");
            CreateClient.confirm.setBackground(Color.green);
        }

        return checker;
    }

    //comprobar que todo este bien antes de modificar
    public static boolean checkChangeClient() {
        boolean checker;
        boolean dispose = false;

        checker = BLL_Client.update_Client();
        if (checker == false) {

            ChangeClient.confirm.setText("El Cliente no se ha podido modificar");
            ChangeClient.confirm.setBackground(Color.red);
        } else {
            dispose = true;
            ChangeClient.confirm.setText("El Cliente se ha modificado con exito");
            ChangeClient.confirm.setBackground(Color.green);
        }

        return dispose;
    }

    //Eliminar admin
    public static void deleteClient() {
        String dni;
        int location;

        int n = ((miniSimpleTableModel_Client) Client.jTable_Client.getModel()).getRowCount();
        if (n != 0) {
            int selec = Client.jTable_Client.getSelectedRow();
            int inicio = (pagina_client.currentPageIndex - 1) * pagina_client.itemsPerPage;
            int selec1 = inicio + selec; //nos situamos en la fila correspondiente de esa página
            if (selec1 == -1) {
                JOptionPane.showMessageDialog(null, "No se ha seleccionado a ningun Cliente", "Error!", 2);
            } else {
                dni = (String) Client.jTable_Client.getModel().getValueAt(selec1, 0);
                client client = new client(dni);
                location = searchClient(client);

                int opc = JOptionPane.showConfirmDialog(null, "Estas seguro que quieres eliminar al Cliente: " + dni,
                        "Info", JOptionPane.WARNING_MESSAGE);

                if (opc == 0) {
                    ((miniSimpleTableModel_Client) Client.jTable_Client.getModel()).removeRow(selec);
                    client = singleton.userclient.get(location);

                    singleton.userclient.remove(client);
                    miniSimpleTableModel_Client.auxdates1.remove(client);
                    BLL_Mongo.deleteClient(client.getDni());
                    

                }

                if (((miniSimpleTableModel_Client) Client.jTable_Client.getModel()).getRowCount() == 0) {
                    if (((miniSimpleTableModel_Client) Client.jTable_Client.getModel()).getRowCount() != 0) {

                    }
                }
                
            }

        } else {
            JOptionPane.showMessageDialog(null, "No hay clientes", "Error!", 2);
        }
    }

    //buscar client
    public static int searchClient(client client) {

        for (int i = 0; i <= (singleton.userclient.size() - 1); i++) {
            if ((singleton.userclient.get(i)).equals(client)) {
                return i;
            }
        }
        return -1;
    }

    //buscar admin por dni en la ventana de crear
    public static int searchClientDNI() {
        int location;
        client client;
        singleton.DNI = CreateClient.dniField.getText();
        client = new client(singleton.DNI);
        location = searchClient(client);
        return location;
    }

    //buscar admin por dni en la ventana de modificar
    public static int searchClientModifyDNI() {
        int location;
        client client;
        if (ChangeClient.comboDni.isVisible() == false) {
            singleton.DNI = ChangeClient.dniField.getText();
        } else {
            singleton.DNI = ChangeClient.comboDni.getSelectedItem().toString();
        }

        client = new client(singleton.DNI);
        location = searchClient(client);
        return location;
    }

    public static boolean askClient(String type) {
        boolean checker = false;
        switch (type) {
            case "dni":
                int location;
                checker = DAO_Client.askdni();
                if (checker == true) {
                    if (singleton.userclient.isEmpty()) {
                        checker = true;
                    } else {
                        location = searchClientDNI();
                        if (location != -1) {
                            CreateClient.errorDni.setText("<html><font color=red>El dni introducido ya existe</font></html>");
                            checker = false;
                        } else {
                            checker = true;
                        }
                    }
                }
                break;
            case "name":
                checker = DAO_Client.askname();
                break;
            case "surname":
                checker = DAO_Client.asksurname();
                break;
            case "mobile":
                checker = DAO_Client.askmobile();
                break;
            case "email":
                checker = DAO_Client.askemail();
                break;
            case "user":
                checker = DAO_Client.askuser();
                break;
            case "pass":
                checker = DAO_Client.askpassword();
                break;
            case "birthday":
                checker = DAO_Client.askdate();
                break;
            case "purchase":
                checker = DAO_Client.askpurchase();
                break;
            case "clienttype":
                checker = DAO_Client.askclient_type();
                break;
            case "dischargedate":
                checker = DAO_Client.askdischargedate();
                break;
        }
        return checker;
    }

    //change admin
    public static boolean changeClient(String type) {
        boolean checker = false;
        switch (type) {
            case "name":
                checker = DAO_Client.changeName();
                break;
            case "surname":
                checker = DAO_Client.changeSurname();
                break;
            case "mobile":
                checker = DAO_Client.changeMobile();
                break;
            case "email":
                checker = DAO_Client.changeEmail();
                break;
            case "user":
                checker = DAO_Client.changeUser();
                break;
            case "pass":
                checker = DAO_Client.changePassword();
                break;
            case "birthday":
                checker = DAO_Client.changeDate();
                break;
            case "purchase":
                checker = DAO_Client.changePurchase();
                break;
            case "clienttype":
                checker = DAO_Client.changeClient_type();
                break;
            case "dischargedate":
                checker = DAO_Client.changedischargedate();
                break;
        }
        return checker;
    }

    public static void SaveClient(String type) {
        if (singleton.userclient.size() != 0) {
            switch (type) {
                case "XML":
                    xml_client.saveClient();
                    break;
                case "JSON":
                    json_client.SaveClient();
                    break;
                case "TXT":
                    txt_client.saveClient();
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("errormessage"));
        }
    }

    public static void openClient(String type) {
        
            switch (type) {
                case "XML":
                    xml_client.openClient();
                    break;
                case "JSON":
                    json_client.OpenClient();
                    break;
                case "TXT":
                    txt_client.openClient();
                    break;
            }
    }

    public static void Exit() {
        JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("exitmessage"));
        System.exit(0);
    }

    public static void saveAutoClient() {
        xml_client.autosaveclient();
        txt_client.saveAutoClient();
        json_client.SaveAutoClient();
    }

    public static void createAvatar() {
        avatar_client.createAvatar();
    }

    public static void loadAvatar() {
        avatar_client.OpenAvatar();
    }

    public static void openAuto() {
        // admin
        xml_client.OpenAutoClient();

        txt_client.openAutoClient();

        json_client.OpenAutoClient();

    }

}
