/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.users.client.controller;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modules.Sign_In.controller.controller_Sign_In;
import modules.Sign_In.view.Sign_In;
import modules.menu.controller.controller_menu;
import modules.menu.model.Config;
import modules.menu.view.Config_jFrame;
import modules.menu.view.Menu;
import modules.users.client.model.BLL.BLL_Client;
import modules.users.client.model.utils.autocomplete.AutocompleteJComboBox;
import modules.users.client.model.classes.miniSimpleTableModel_Client;
import modules.users.client.model.utils.autocomplete.StringSearchable;
import modules.users.client.model.utils.pagina_client;
import modules.users.client.view.ChangeClient;
import modules.users.client.view.Client;
import static modules.users.client.view.Client.jTable_Client;
import modules.users.client.view.CreateClient;
import modules.users.users.singleton;
import static modules.users.client.view.Client.comboClient;
import modules.users.client.view.ClientMenu;
import utils.format;

/**
 *
 * @author lluis
 */
public class controller_Client implements ActionListener, KeyListener, MouseListener {

    public static JTable tabla = null;
    public static TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(new miniSimpleTableModel_Client());
    public static Client menuClient;
    public static ChangeClient changeClient;
    public static CreateClient createClient;
    public static ClientMenu clientMenu;
    public static AutocompleteJComboBox combo = null;
    JTable table = null;

    public controller_Client(JFrame start, int o) {
        switch (o) {
            case 0:
                menuClient = (Client) start;
                break;
            case 1:
                createClient = (CreateClient) start;
                break;
            case 2:
                changeClient = (ChangeClient) start;
                break;
            case 3:
                clientMenu = (ClientMenu) start;
                break;

        }
    }

    public enum Action {

        //MENU Cliente
        CreateClient,
        modifyClient,
        deleteClient,
        iconMenu,
        openJson,
        openXml,
        openTxt,
        saveJson,
        saveXml,
        saveTxt,
        //JTABLE
        jTable,
        primero,
        ANTERIOR,
        SIGUIENTE,
        ultimo,
        comboClient,
        //Filtrar

        //CREAR CLIENTE

        btnAceptar,
        btnCancelar,
        dniField,
        nameField,
        surnameField,
        mobileField,
        emailField,
        userField,
        passField,
        birthdayField,
        img,
        optYes,
        optNo,
        optYes1,
        optNo1,
        purchaseField,
        dichargeDateField,
        clientTypeField,
        //MODIFICAR CLIENTE
        btnAceptar1,
        btnCancelar1,
        nameField1,
        surnameField1,
        mobileField1,
        emailField1,
        userField1,
        passField1,
        birthdayField1,
        img1,
        optYesM,
        optNoM,
        optYesM1,
        optNoM1,
        purchaseField1,
        dichargeDateField1,
        clientTypeField1,
        comboDni,
        //clientMenu
        etiSave,
        etiModify,
        etiConfig,
        etiExit,
        logOut,
        //////////////////////////////
    }

    public static void comboActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("word selected: " + ((JComboBox) combo).getSelectedItem());
        pagina_client.currentPageIndex = 1;
        ((miniSimpleTableModel_Client) jTable_Client.getModel()).filtrar();
        combo.requestFocus();
    }

    private void timer_CC() {

        Timer timer = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createClient.dispose();
                new controller_Client(new Client(), 0).start(0);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void timer_MC() {

        Timer timer = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changeClient.dispose();
                if (singleton.user == "Admin") {
                    new controller_Client(new Client(), 0).start(0);
                } else {
                    new controller_Client(new ClientMenu(), 3).start(3);
                }
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    public static DefaultComboBoxModel PlenaComboBox() {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        for (int i = 0; i < (singleton.userclient.size()); i++) {
            modelo.addElement(singleton.userclient.get(i).getDni());
        }
        return modelo;
    }

    public void start(int o) {
        switch (o) {
            case 0:
                //MENU CLIENTE

                this.menuClient.setSize(1150, 600);
                this.menuClient.setResizable(false);
                this.menuClient.setTitle("Menu Cliente");
                this.menuClient.setVisible(true);

                this.menuClient.jTable_Client.setModel(new miniSimpleTableModel_Client());
                ((miniSimpleTableModel_Client) jTable_Client.getModel()).load();
                this.menuClient.jTable_Client.setFillsViewportHeight(true);
                this.menuClient.jTable_Client.setRowSorter(sorter);

                this.menuClient.jLabel3.setText(String.valueOf(singleton.userclient.size()));

                pagina_client.itemsPerPage = Integer.parseInt(comboClient.getSelectedItem().toString());
                pagina_client.currentPageIndex = 1;

                pagina_client.inicializa();
                pagina_client.initLinkBox();

                List<String> myWords = new ArrayList<String>();
                for (int i = 0; i <= singleton.userclient.size() - 1; i++) {
                    myWords.add(singleton.userclient.get(i).getName());
                }

                StringSearchable searchable = new StringSearchable(myWords);
                combo = new AutocompleteJComboBox(searchable);

                this.menuClient.jPanel3.setLayout(new java.awt.BorderLayout());
                this.menuClient.jPanel3.add(combo);

                this.menuClient.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        menuClient.dispose();
                        new controller_menu(new Menu(), 0).start(0);

                    }
                });

                combo.addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        comboActionPerformed(evt);
                    }
                });

                this.menuClient.CreateClient.setName("CreateClient");

                this.menuClient.CreateClient.addMouseListener(this);

                this.menuClient.modifyClient.setName("modifyClient");

                this.menuClient.modifyClient.addMouseListener(this);

                this.menuClient.deleteClient.setName("deleteClient");

                this.menuClient.deleteClient.addMouseListener(this);

                this.menuClient.iconMenu.setName("iconMenu");

                this.menuClient.iconMenu.addMouseListener(this);

                this.menuClient.openJson.setName("openJson");

                this.menuClient.openJson.addMouseListener(this);

                this.menuClient.openXml.setName("openXml");

                this.menuClient.openXml.addMouseListener(this);

                this.menuClient.openTxt.setName("openTxt");

                this.menuClient.openTxt.addMouseListener(this);

                this.menuClient.saveJson.setName("saveJson");

                this.menuClient.saveJson.addMouseListener(this);

                this.menuClient.saveXml.setName("saveXml");

                this.menuClient.saveXml.addMouseListener(this);

                this.menuClient.saveTxt.setName("saveTxt");

                this.menuClient.saveTxt.addMouseListener(this);

                this.menuClient.primero.setActionCommand("primero");

                this.menuClient.primero.addActionListener(this);

                this.menuClient.ANTERIOR.setActionCommand("ANTERIOR");

                this.menuClient.ANTERIOR.addActionListener(this);

                this.menuClient.SIGUIENTE.setActionCommand("SIGUIENTE");

                this.menuClient.SIGUIENTE.addActionListener(this);

                this.menuClient.ultimo.setActionCommand("ultimo");

                this.menuClient.ultimo.addActionListener(this);

                this.menuClient.comboClient.setActionCommand("comboClient");

                this.menuClient.comboClient.addActionListener(this);

                this.menuClient.jTable_Client.setName("jTable");

                this.menuClient.jTable_Client.addMouseListener(this);

                break;
            case 1:
                //CREAR CLIENTE
                this.createClient.setSize(942, 720);
                this.createClient.setResizable(false);
                this.createClient.setTitle("Modificar Cliente");
                this.createClient.setVisible(true);
                createClient.birthdayField.setDateFormatString(Config.getInstance().getFormatDate());
                createClient.dischargeDateField.setDateFormatString(Config.getInstance().getFormatDate());

                this.createClient.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {

                        new controller_Client(new Client(), 0).start(0);
                    }
                });
                this.createClient.optYes.setSelected(true);
                this.createClient.optYes1.setSelected(true);
                this.createClient.imgPath.setText("C:\\Users\\lluis\\Documents\\NetBeansProjects\\FrameworkLluisMataix\\src\\img\\avatar.png");

                this.createClient.btnAceptar.setActionCommand("btnAceptar");

                this.createClient.btnAceptar.addActionListener(this);

                this.createClient.btnCancelar.setActionCommand("btnCancelar");

                this.createClient.btnCancelar.addActionListener(this);

                this.createClient.dniField.setName("dniField");

                this.createClient.dniField.addMouseListener(this);

                this.createClient.dniField.addKeyListener(this);

                this.createClient.nameField.setName("nameField");

                this.createClient.nameField.addMouseListener(this);

                this.createClient.nameField.addKeyListener(this);

                this.createClient.surnameField.setName("surnameField");

                this.createClient.surnameField.addMouseListener(this);

                this.createClient.surnameField.addKeyListener(this);

                this.createClient.mobileField.setName("mobileField");

                this.createClient.mobileField.addMouseListener(this);

                this.createClient.mobileField.addKeyListener(this);

                this.createClient.emailField.setName("emailField");

                this.createClient.emailField.addMouseListener(this);

                this.createClient.emailField.addKeyListener(this);

                this.createClient.userField.setName("userField");

                this.createClient.userField.addMouseListener(this);

                this.createClient.userField.addKeyListener(this);

                this.createClient.passField.setName("passField");

                this.createClient.passField.addMouseListener(this);

                this.createClient.passField.addKeyListener(this);

                this.createClient.birthdayField.setName("birthdayField");

                this.createClient.birthdayField.addMouseListener(this);

                this.createClient.img.setName("img");

                this.createClient.img.addMouseListener(this);

                this.createClient.optYes.setName("optYes");

                this.createClient.optYes.addMouseListener(this);

                this.createClient.optNo.setName("optNo");

                this.createClient.optNo.addMouseListener(this);

                this.createClient.optYes1.setName("optYes1");

                this.createClient.optYes1.addMouseListener(this);

                this.createClient.optNo1.setName("optNo1");

                this.createClient.optNo1.addMouseListener(this);

                this.createClient.purchaseField.setName("purchaseField");

                this.createClient.purchaseField.addMouseListener(this);

                this.createClient.purchaseField.addKeyListener(this);

                this.createClient.dischargeDateField.setName("dischargeDateField");

                this.createClient.dischargeDateField.addMouseListener(this);

                this.createClient.clientTypeField.setName("clientTypeField");

                this.createClient.clientTypeField.addMouseListener(this);

                this.createClient.clientTypeField.addKeyListener(this);

                break;
            case 2:
                //MODIFICAR CLIENTE

                this.changeClient.etiDNI.setVisible(false);
                this.changeClient.dniField.setVisible(false);
                this.changeClient.dniField.setEditable(false);
                changeClient.birthdayField.setDateFormatString(Config.getInstance().getFormatDate());
                changeClient.dischargeDateField.setDateFormatString(Config.getInstance().getFormatDate());
                
                if(singleton.user.equals("Client")){
                    this.changeClient.dischargeDateField.setEnabled(false);
                }
                
                if (singleton.client != null) {
                    this.changeClient.etiDNI.setVisible(true);
                    this.changeClient.dniField.setVisible(true);
                    this.changeClient.etiDNI1.setVisible(false);
                    this.changeClient.comboDni.setVisible(false);
                    this.changeClient.comboDni.setEnabled(true);
                    BLL_Client.viewJTableClient(singleton.client.getDni());

                }
                this.changeClient.setSize(942, 720);
                this.changeClient.setResizable(false);
                this.changeClient.setTitle("Modificar Cliente");
                this.changeClient.setVisible(true);

                this.changeClient.comboDni.setModel(PlenaComboBox());
                this.changeClient.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {

                        new controller_Client(new Client(), 0).start(0);
                    }
                });

                this.changeClient.btnAceptar.setActionCommand("btnAceptar1");

                this.changeClient.btnAceptar.addActionListener(this);

                this.changeClient.btnCancelar.setActionCommand("btnCancelar1");

                this.changeClient.btnCancelar.addActionListener(this);

                this.changeClient.comboDni.setActionCommand("comboDni");

                this.changeClient.comboDni.addActionListener(this);

                this.changeClient.nameField.setName("nameField1");

                this.changeClient.nameField.addMouseListener(this);

                this.changeClient.nameField.addKeyListener(this);

                this.changeClient.surnameField.setName("surnameField1");

                this.changeClient.surnameField.addMouseListener(this);

                this.changeClient.surnameField.addKeyListener(this);

                this.changeClient.mobileField.setName("mobileField1");

                this.changeClient.mobileField.addMouseListener(this);

                this.changeClient.mobileField.addKeyListener(this);

                this.changeClient.emailField.setName("emailField1");

                this.changeClient.emailField.addMouseListener(this);

                this.changeClient.emailField.addKeyListener(this);

                this.changeClient.userField.setName("userField1");

                this.changeClient.userField.addMouseListener(this);

                this.changeClient.userField.addKeyListener(this);

                this.changeClient.passField.setName("passField1");

                this.changeClient.passField.addMouseListener(this);

                this.changeClient.passField.addKeyListener(this);

                this.changeClient.birthdayField.setName("birthdayField1");

                this.changeClient.birthdayField.addMouseListener(this);

                this.changeClient.img.setName("img1");

                this.changeClient.img.addMouseListener(this);

                this.changeClient.optYes.setName("optYesM");

                this.changeClient.optYes.addMouseListener(this);

                this.changeClient.optNo.setName("optNoM");

                this.changeClient.optNo.addMouseListener(this);

                this.changeClient.optYes1.setName("optYesM1");

                this.changeClient.optYes1.addMouseListener(this);

                this.changeClient.optNo1.setName("optNoM1");

                this.changeClient.optNo1.addMouseListener(this);

                this.changeClient.purchaseField.setName("purchaseField1");

                this.changeClient.purchaseField.addMouseListener(this);

                this.changeClient.purchaseField.addKeyListener(this);

                this.changeClient.dischargeDateField.setName("dischargeDateField1");

                this.changeClient.dischargeDateField.addMouseListener(this);

                this.changeClient.clientTypeField.setName("clientTypeField1");

                this.changeClient.clientTypeField.addMouseListener(this);

                this.changeClient.clientTypeField.addKeyListener(this);

                break;

            case 3:
                //MENU CLIENTE
                this.clientMenu.setSize(680, 500);
                this.clientMenu.setResizable(false);
                this.clientMenu.setTitle("Menu Cliente");
                this.clientMenu.setVisible(true);

                ImageIcon avatar = new ImageIcon(singleton.client.getAvatar());
                this.clientMenu.etiAvatar.setIcon(new ImageIcon(avatar.getImage().getScaledInstance(125, 125, Image.SCALE_SMOOTH)));
                this.clientMenu.etiName.setText(singleton.client.getDni() + ": " + singleton.client.getName() + " " + singleton.client.getSurname());
                this.clientMenu.mobileField.setText(singleton.client.getMobile());
                this.clientMenu.emailField.setText(singleton.client.getEmail());
                this.clientMenu.userField.setText(singleton.client.getUser());
                this.clientMenu.passField.setText(singleton.client.getPass());
                this.clientMenu.premiumField.setText(singleton.client.getPremium());
                this.clientMenu.birthdayField.setText(singleton.client.getDate_birthday().toString(Config.getInstance().getFormatDate()));
                this.clientMenu.purchaseField.setText(String.valueOf(format.formatcurrency(singleton.client.getPurchase(), Config.getInstance())));
                this.clientMenu.dischargedateField.setText(singleton.client.getDischarge_date().toString(Config.getInstance().getFormatDate()));
                this.clientMenu.clientTypeField.setText(singleton.client.getClient_type());
                this.clientMenu.antiquityField.setText(String.valueOf(singleton.client.getAntiquity()));
                this.clientMenu.stateField.setText(singleton.client.getState());
                this.clientMenu.ageField.setText(String.valueOf(singleton.client.getAge()));
                this.clientMenu.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {

                        new controller_Sign_In(new Sign_In()).start();
                    }
                });
                this.clientMenu.etiSave.setName("etiSave");
                this.clientMenu.etiSave.addMouseListener(this);
                this.clientMenu.etiModify.setName("etiModify");
                this.clientMenu.etiModify.addMouseListener(this);
                this.clientMenu.etiConfig.setName("etiConfig");
                this.clientMenu.etiConfig.addMouseListener(this);
                this.clientMenu.etiExit.setName("etiExit");
                this.clientMenu.etiExit.addMouseListener(this);
                this.clientMenu.logOut.setName("logOut");
                this.clientMenu.logOut.addMouseListener(this);

                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (Action.valueOf(e.getActionCommand())) {
            case primero:
                pagina_client.currentPageIndex = 1;
                pagina_client.initLinkBox();
                break;

            case ANTERIOR:
                pagina_client.currentPageIndex -= 1;
                pagina_client.initLinkBox();
                break;

            case SIGUIENTE:
                pagina_client.currentPageIndex += 1;
                pagina_client.initLinkBox();
                break;

            case ultimo:
                pagina_client.currentPageIndex = pagina_client.maxPageIndex;
                pagina_client.initLinkBox();
                break;

            case comboClient:
                pagina_client.itemsPerPage = Integer.parseInt(comboClient.getSelectedItem().toString());
                pagina_client.currentPageIndex = 1;
                pagina_client.initLinkBox();
                break;

            case btnAceptar:

                boolean disp = BLL_Client.checkCreateClient();
                if (disp == true) {
                    timer_CC();
                }
                break;
            case btnCancelar:
                createClient.dispose();
                new controller_Client(new Client(), 0).start(0);
                break;

            case btnAceptar1:
                boolean disp1 = BLL_Client.checkChangeClient();
                if (disp1 == true) {
                    timer_MC();
                }
                break;

            case btnCancelar1:
                changeClient.dispose();
                if (singleton.user.equals("Admin")) {
                    new controller_Client(new Client(), 0).start(0);
                } else {
                    new controller_Client(new ClientMenu(), 3).start(3);
                }
                break;

            case comboDni:
                BLL_Client.viewClient();
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (Action.valueOf(e.getComponent().getName())) {

            //CREAR CLIENTE
            case dniField:
                BLL_Client.askClient("dni");
                break;
            case nameField:
                BLL_Client.askClient("name");
                break;
            case surnameField:
                BLL_Client.askClient("surname");
                break;
            case mobileField:
                BLL_Client.askClient("mobile");
                break;
            case emailField:
                BLL_Client.askClient("email");
                break;
            case userField:
                BLL_Client.askClient("user");
                break;
            case passField:
                BLL_Client.askClient("pass");
                break;
            case purchaseField:
                BLL_Client.askClient("purchase");
                break;
            case clientTypeField:
                BLL_Client.askClient("clienttype");
                break;

            //MODIFICAR CLIENTE
            case nameField1:
                BLL_Client.changeClient("name");
                break;
            case surnameField1:
                BLL_Client.changeClient("surname");
                break;
            case mobileField1:
                BLL_Client.changeClient("mobile");
                break;
            case emailField1:
                BLL_Client.changeClient("email");
                break;
            case userField1:
                BLL_Client.changeClient("user");
                break;
            case passField1:
                BLL_Client.changeClient("pass");
                break;
            case purchaseField1:
                BLL_Client.changeClient("purchase");
                break;
            case clientTypeField1:
                BLL_Client.changeClient("clienttype");
                break;
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (Action.valueOf(e.getComponent().getName())) {
            case CreateClient:
                menuClient.dispose();
                new controller_Client(new CreateClient(), 1).start(1);
                break;
            case modifyClient:
                singleton.client = null;
                int selec = Client.jTable_Client.getSelectedRow();
                if (selec == -1) {
                    menuClient.dispose();
                    new controller_Client(new ChangeClient(singleton.client, table), 2).start(2);
                } else {
                    boolean modify = BLL_Client.modify_row();
                    if (modify == true) {
                        menuClient.dispose();
                    }
                }
                break;
            case deleteClient:
                BLL_Client.deleteClient();
                ((miniSimpleTableModel_Client) Client.jTable_Client.getModel()).load();
                break;
            case iconMenu:
                menuClient.dispose();
                new controller_menu(new Menu(), 0).start(0);
                break;

            //CREAR CLIENTE
            case dniField:
                this.createClient.dniField.setText("");
                break;
            case nameField:
                this.createClient.nameField.setText("");
                break;
            case surnameField:
                this.createClient.surnameField.setText("");
                break;
            case mobileField:
                this.createClient.mobileField.setText("");
                break;
            case emailField:
                this.createClient.emailField.setText("");
                break;
            case userField:
                this.createClient.userField.setText("");
                break;
            case passField:
                this.createClient.passField.setText("");
                break;
            case purchaseField:
                this.createClient.purchaseField.setText("");
                break;
            case clientTypeField:
                this.createClient.clientTypeField.setText("");
                break;
            case img:
                BLL_Client.createAvatar();
                break;
            case optYes:
                this.createClient.optionState.setText("Si");
                break;
            case optNo:
                this.createClient.optionState.setText("No");
                break;
            case optYes1:
                this.createClient.premium.setText("Si");
                break;
            case optNo1:
                this.createClient.premium.setText("No");
                break;

            //MODIFICAR CLIENTE
            case nameField1:
                this.changeClient.nameField.setText("");
                break;
            case surnameField1:
                this.changeClient.surnameField.setText("");
                break;
            case mobileField1:
                this.changeClient.mobileField.setText("");
                break;
            case emailField1:
                this.changeClient.emailField.setText("");
                break;
            case userField1:
                this.changeClient.userField.setText("");
                break;
            case passField1:
                this.changeClient.passField.setText("");
                break;
            case purchaseField1:
                this.changeClient.purchaseField.setText("");
                break;
            case clientTypeField1:
                this.changeClient.clientTypeField.setText("");
                break;
            case img1:
                BLL_Client.loadAvatar();
                break;
            case optYesM:
                this.changeClient.optionState.setText("Si");
                break;
            case optNoM:
                this.changeClient.optionState.setText("No");
                break;
            case optYesM1:
                this.changeClient.premium.setText("Si");
                break;
            case optNoM1:
                this.changeClient.premium.setText("No");
                break;
            //MENU CLIENTE
            case etiSave:
                BLL_Client.SaveClient2(Config.getInstance().getFiles());
                break;
            case etiModify:
                clientMenu.dispose();
                new controller_Client(new ChangeClient(singleton.client, table), 2).start(2);
                break;
            case etiConfig:
                clientMenu.dispose();
                new controller_menu(new Config_jFrame(), 1).start(1);
                break;
            case etiExit:
                //BLL_Client.exitOption();
                JOptionPane.showMessageDialog(null, "Saliendo de la Aplicación");
                System.exit(0);

                break;
            case logOut:
                singleton.client = null;
                new controller_Sign_In(new Sign_In()).start();
                clientMenu.dispose();
                break;

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (Action.valueOf(e.getComponent().getName())) {
            case openJson:
                BLL_Client.openClient("JSON");
                menuClient.dispose();
                new controller_Client(new Client(), 0).start(0);
                break;
            case openXml:
                BLL_Client.openClient("XML");
                menuClient.dispose();
                new controller_Client(new Client(), 0).start(0);
                break;
            case openTxt:
                BLL_Client.openClient("TXT");
                menuClient.dispose();
                new controller_Client(new Client(), 0).start(0);
                break;
            case saveJson:
                BLL_Client.SaveClient("JSON");
                break;
            case saveXml:
                BLL_Client.SaveClient("XML");
                break;
            case saveTxt:
                BLL_Client.SaveClient("TXT");
                break;
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        switch (Action.valueOf(e.getComponent().getName())) {
            case CreateClient:
                menuClient.CreateClient.setText("<html><font color=red>Crear Cliente</font></html>");
                break;
            case modifyClient:
                menuClient.modifyClient.setText("<html><font color=red>Modificar Cliente</font></html>");
                break;
            case deleteClient:
                menuClient.deleteClient.setText("<html><font color=red>Borrar Cliente</font></html>");
                break;
            case iconMenu:
                this.menuClient.iconMenu.setIcon(new ImageIcon("src/img/homered.png"));
                break;
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

        switch (Action.valueOf(e.getComponent().getName())) {
            case CreateClient:
                menuClient.CreateClient.setText("<html><font color=black>Crear Cliente</font></html>");
                break;
            case modifyClient:
                menuClient.modifyClient.setText("<html><font color=black>Modificar Cliente</font></html>");
                break;
            case deleteClient:
                menuClient.deleteClient.setText("<html><font color=black>Borrar Cliente</font></html>");
                break;
            case iconMenu:
                this.menuClient.iconMenu.setIcon(new ImageIcon("src/img/home_32.png"));
                break;
        }

    }

}
