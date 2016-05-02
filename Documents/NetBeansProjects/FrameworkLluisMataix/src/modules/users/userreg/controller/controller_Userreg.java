/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.users.userreg.controller;

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
import modules.users.userreg.model.BLL.BLL_Userreg;
import modules.users.userreg.model.classes.miniSimpleTableModel_Userreg;
import modules.users.userreg.model.utils.autocomplete_userreg.AutocompleteJComboBox;
import modules.users.userreg.model.utils.autocomplete_userreg.StringSearchable;
import modules.users.userreg.model.utils.pagina_Userreg;
import modules.users.userreg.view.ChangeUserreg;
import modules.users.userreg.view.UserReg;
import modules.users.userreg.view.UserregMenu;
import modules.users.userreg.view.createUserreg;
import modules.users.users.singleton;

/**
 *
 * @author lluis
 */
public class controller_Userreg implements ActionListener, KeyListener, MouseListener {

    public static JTable tabla = null;
    public static TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(new miniSimpleTableModel_Userreg());
    public static UserReg menuUserreg;
    public static ChangeUserreg changeUserreg;
    public static createUserreg createUser;
    public static UserregMenu userregMenu;
    public static AutocompleteJComboBox combo = null;
    JTable table = null;

    public controller_Userreg(JFrame start, int o) {
        switch (o) {
            case 0:
                menuUserreg = (UserReg) start;
                break;
            case 1:
                createUser = (createUserreg) start;
                break;
            case 2:
                changeUserreg = (ChangeUserreg) start;
                break;
            case 3:
                userregMenu = (UserregMenu) start;
                break;
        }
    }

    public enum Action {

        //MENU USUARIO REGISTRADO
        CreateUserreg,
        modifyUserreg,
        deleteUserreg,
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
        comboUserreg,
        //Filtrar

        //CREAR USUARIO REGISTRADO 

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
        activityField,
        //MODIFICAR USUARIO REGISTRADO
        btnAceptar1,
        btnCancelar1,
        dniField1,
        nameField1,
        surnameField1,
        mobileField1,
        emailField1,
        userField1,
        passField1,
        birthdayField1,
        img1,
        optYes1,
        optNo1,
        activityField1,
        comboDni,
        //userregMenu
        etiSave,
        etiModify,
        etiConfig,
        etiExit,
        logOut,
        //////////////////////////////
    }

    public static DefaultComboBoxModel PlenaComboBox() {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        for (int i = 0; i < (singleton.userreg.size()); i++) {
            modelo.addElement(singleton.userreg.get(i).getDni());
        }
        return modelo;
    }

    public static void comboActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("word selected: " + ((JComboBox) combo).getSelectedItem());
        pagina_Userreg.currentPageIndex = 1;
        ((miniSimpleTableModel_Userreg) menuUserreg.jTable.getModel()).filtrar();
        combo.requestFocus();
    }

    private void timer_NU() {

        Timer timer = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createUser.dispose();
                new controller_Userreg(new UserReg(), 0).start(0);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void timer_CU() {

        Timer timer = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changeUserreg.dispose();
                if (singleton.user.equals("Admin")) {
                    new controller_Userreg(new UserReg(), 0).start(0);
                } else {
                    new controller_Userreg(new UserregMenu(), 3).start(3);
                }

            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    public void start(int o) {
        switch (o) {
            case 0:
                //MENU USUARIO REGISTRADO//

                menuUserreg.setSize(1150, 600);
                menuUserreg.setResizable(false);
                menuUserreg.setTitle("Menu Usuario Registrado");
                menuUserreg.setVisible(true);
                menuUserreg.jTable.setModel(new miniSimpleTableModel_Userreg());

                ((miniSimpleTableModel_Userreg) menuUserreg.jTable.getModel()).load();
                menuUserreg.jTable.setFillsViewportHeight(true);
                menuUserreg.jTable.setRowSorter(sorter);

                pagina_Userreg.itemsPerPage = Integer.parseInt(menuUserreg.comboUserreg.getSelectedItem().toString());
                pagina_Userreg.currentPageIndex = 1;

                pagina_Userreg.inicializa();
                pagina_Userreg.initLinkBox();

                menuUserreg.jLabel3.setText(String.valueOf(singleton.useradmin.size()));

                this.menuUserreg.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        menuUserreg.dispose();
                        new controller_menu(new Menu(), 0).start(0);
                    }
                });

                List<String> myWords = new ArrayList<String>();

                for (int i = 0; i <= singleton.useradmin.size() - 1; i++) {
                    myWords.add(singleton.useradmin.get(i).getName());
                }

                StringSearchable searchable = new StringSearchable(myWords);
                combo = new AutocompleteJComboBox(searchable);
                //JPanel5 se utiliza solamente para que JPanel3 que contendrá combo, no se redimensione

                menuUserreg.jPanel3.setLayout(
                        new java.awt.BorderLayout());
                menuUserreg.jPanel3.add(combo);

                combo.addActionListener(
                        new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt
                    ) {
                        comboActionPerformed(evt);
                    }
                }
                );

                this.menuUserreg.CreateUserreg.setName("CreateUserreg");

                this.menuUserreg.CreateUserreg.addMouseListener(this);

                this.menuUserreg.modifyUserreg.setName("modifyUserreg");

                this.menuUserreg.modifyUserreg.addMouseListener(this);

                this.menuUserreg.deleteUserreg.setName("deleteUserreg");

                this.menuUserreg.deleteUserreg.addMouseListener(this);

                this.menuUserreg.iconMenu.setName("iconMenu");

                this.menuUserreg.iconMenu.addMouseListener(this);

                this.menuUserreg.openJson.setName("openJson");

                this.menuUserreg.openJson.addMouseListener(this);

                this.menuUserreg.openXml.setName("openXml");

                this.menuUserreg.openXml.addMouseListener(this);

                this.menuUserreg.openTxt.setName("openTxt");

                this.menuUserreg.openTxt.addMouseListener(this);

                this.menuUserreg.saveJson.setName("saveJson");

                this.menuUserreg.saveJson.addMouseListener(this);

                this.menuUserreg.saveXml.setName("saveXml");

                this.menuUserreg.saveXml.addMouseListener(this);

                this.menuUserreg.saveTxt.setName("saveTxt");

                this.menuUserreg.saveTxt.addMouseListener(this);

                this.menuUserreg.primero.setActionCommand("primero");

                this.menuUserreg.primero.addActionListener(this);

                this.menuUserreg.ANTERIOR.setActionCommand("ANTERIOR");

                this.menuUserreg.ANTERIOR.addActionListener(this);

                this.menuUserreg.SIGUIENTE.setActionCommand("SIGUIENTE");

                this.menuUserreg.SIGUIENTE.addActionListener(this);

                this.menuUserreg.ultimo.setActionCommand("ultimo");

                this.menuUserreg.ultimo.addActionListener(this);

                this.menuUserreg.comboUserreg.setActionCommand("comboAdmin");

                this.menuUserreg.comboUserreg.addActionListener(this);

                this.menuUserreg.jTable.setName("jTable");

                this.menuUserreg.jTable.addMouseListener(this);

                break;

            //CREAR USUARIO REGISTRADO
            case 1:

                this.createUser.setSize(942, 720);
                this.createUser.setResizable(false);
                this.createUser.setTitle("Nuevo Usuario Registrado");
                this.createUser.setVisible(true);
                createUser.birthdayField.setDateFormatString(Config.getInstance().getFormatDate());

                this.createUser.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {

                        new controller_Userreg(new UserReg(), 0).start(0);
                    }
                });

                createUser.optYes.setSelected(true);
                createUser.imgPath.setText("C:\\Users\\lluis\\Documents\\NetBeansProjects\\FrameworkLluisMataix\\src\\img\\avatar.png");

                this.createUser.btnAceptar.setActionCommand("btnAceptar");

                this.createUser.btnAceptar.addActionListener(this);

                this.createUser.btnCancelar.setActionCommand("btnCancelar");

                this.createUser.btnCancelar.addActionListener(this);

                this.createUser.dniField.setName("dniField");

                this.createUser.dniField.addMouseListener(this);

                this.createUser.dniField.addKeyListener(this);

                this.createUser.nameField.setName("nameField");

                this.createUser.nameField.addMouseListener(this);

                this.createUser.nameField.addKeyListener(this);

                this.createUser.surnameField.setName("surnameField");

                this.createUser.surnameField.addMouseListener(this);

                this.createUser.surnameField.addKeyListener(this);

                this.createUser.mobileField.setName("mobileField");

                this.createUser.mobileField.addMouseListener(this);

                this.createUser.mobileField.addKeyListener(this);

                this.createUser.emailField.setName("emailField");

                this.createUser.emailField.addMouseListener(this);

                this.createUser.emailField.addKeyListener(this);

                this.createUser.userField.setName("userField");

                this.createUser.userField.addMouseListener(this);

                this.createUser.userField.addKeyListener(this);

                this.createUser.passField.setName("passField");

                this.createUser.passField.addMouseListener(this);

                this.createUser.passField.addKeyListener(this);

                this.createUser.birthdayField.setName("birthdayField");

                this.createUser.birthdayField.addMouseListener(this);

                this.createUser.img.setName("img");

                this.createUser.img.addMouseListener(this);

                this.createUser.optYes.setName("optYes");

                this.createUser.optYes.addMouseListener(this);

                this.createUser.optNo.setName("optNo");

                this.createUser.optNo.addMouseListener(this);

                this.createUser.activityField.setName("activityField");

                this.createUser.activityField.addMouseListener(this);

                this.createUser.activityField.addKeyListener(this);

                break;

            //MODIFICAR USUARIO REGISTRADO
            case 2:

                this.changeUserreg.etiDNI.setVisible(false);
                this.changeUserreg.dniField.setVisible(false);
                this.changeUserreg.dniField.setEditable(false);
                this.changeUserreg.birthdayField.setDateFormatString(Config.getInstance().getFormatDate());
                if (singleton.user.equals("Userreg")) {
                    this.changeUserreg.activityField.setEnabled(false);
                }

                if (singleton.registered_user != null) {
                    this.changeUserreg.etiDNI.setVisible(true);
                    this.changeUserreg.dniField.setVisible(true);
                    this.changeUserreg.etiDNI1.setVisible(false);
                    this.changeUserreg.comboDni.setVisible(false);
                    this.changeUserreg.comboDni.setEnabled(true);
                    BLL_Userreg.viewJTableUserreg(singleton.registered_user.getDni());
                }

                this.changeUserreg.setSize(942, 720);
                this.changeUserreg.setResizable(false);
                this.changeUserreg.setTitle("Modificar Usuario Registrado");
                this.changeUserreg.setVisible(true);

                this.changeUserreg.comboDni.setModel(PlenaComboBox());
                this.changeUserreg.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        if (singleton.user.equals("Admin")) {
                            new controller_Userreg(new UserReg(), 0).start(0);
                        } else {
                            new controller_Userreg(new UserregMenu(), 3).start(3);
                        }
                    }
                });

                this.changeUserreg.btnAceptar.setActionCommand("btnAceptar1");

                this.changeUserreg.btnAceptar.addActionListener(this);

                this.changeUserreg.btnCancelar.setActionCommand("btnCancelar1");

                this.changeUserreg.btnCancelar.addActionListener(this);

                this.changeUserreg.comboDni.setActionCommand("comboDni");

                this.changeUserreg.comboDni.addActionListener(this);

                this.changeUserreg.dniField.setName("dniField1");

                this.changeUserreg.dniField.addMouseListener(this);

                this.changeUserreg.dniField.addKeyListener(this);

                this.changeUserreg.nameField.setName("nameField1");

                this.changeUserreg.nameField.addMouseListener(this);

                this.changeUserreg.nameField.addKeyListener(this);

                this.changeUserreg.surnameField.setName("surnameField1");

                this.changeUserreg.surnameField.addMouseListener(this);

                this.changeUserreg.surnameField.addKeyListener(this);

                this.changeUserreg.mobileField.setName("mobileField1");

                this.changeUserreg.mobileField.addMouseListener(this);

                this.changeUserreg.mobileField.addKeyListener(this);

                this.changeUserreg.emailField.setName("emailField1");

                this.changeUserreg.emailField.addMouseListener(this);

                this.changeUserreg.emailField.addKeyListener(this);

                this.changeUserreg.userField.setName("userField1");

                this.changeUserreg.userField.addMouseListener(this);

                this.changeUserreg.userField.addKeyListener(this);

                this.changeUserreg.passField.setName("passField1");

                this.changeUserreg.passField.addMouseListener(this);

                this.changeUserreg.passField.addKeyListener(this);

                this.changeUserreg.birthdayField.setName("birthdayField1");

                this.changeUserreg.birthdayField.addMouseListener(this);

                this.changeUserreg.img.setName("img1");

                this.changeUserreg.img.addMouseListener(this);

                this.changeUserreg.optYes.setName("optYes1");

                this.changeUserreg.optYes.addMouseListener(this);

                this.changeUserreg.optNo.setName("optNo1");

                this.changeUserreg.optNo.addMouseListener(this);

                this.changeUserreg.activityField.setName("activityField1");

                this.changeUserreg.activityField.addMouseListener(this);

                this.changeUserreg.activityField.addKeyListener(this);

                break;

            case 3:
                //MENU USUARIO REGISTRADO
                this.userregMenu.setSize(680, 500);
                this.userregMenu.setResizable(false);
                this.userregMenu.setTitle("Menu Cliente");
                this.userregMenu.setVisible(true);

                ImageIcon avatar = new ImageIcon(singleton.registered_user.getAvatar());
                this.userregMenu.etiAvatar.setIcon(new ImageIcon(avatar.getImage().getScaledInstance(125, 125, Image.SCALE_SMOOTH)));
                this.userregMenu.etiName.setText(singleton.registered_user.getDni() + ": " + singleton.registered_user.getName()
                        + " " + singleton.registered_user.getSurname());
                this.userregMenu.mobileField.setText(singleton.registered_user.getMobile());
                this.userregMenu.emailField.setText(singleton.registered_user.getEmail());
                this.userregMenu.userField.setText(singleton.registered_user.getUser());
                this.userregMenu.passField.setText(singleton.registered_user.getPass());
                this.userregMenu.activityField.setText(String.valueOf(singleton.registered_user.getActivity()));
                this.userregMenu.birthdayField.setText(singleton.registered_user.getDate_birthday().toString(Config.getInstance().getFormatDate()));
                this.userregMenu.karmaField.setText(singleton.registered_user.getKarma());
                this.userregMenu.pointsField.setText(String.valueOf(singleton.registered_user.getBenefits()));
                this.userregMenu.stateField.setText(singleton.registered_user.getState());
                this.userregMenu.ageField.setText(String.valueOf(singleton.registered_user.getAge()));

                this.userregMenu.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {

                        JOptionPane.showMessageDialog(null, "Saliendo de la aplicación");
                        System.exit(0);
                        //new controller_Sign_In(new Sign_In()).start();
                    }
                });
                this.userregMenu.etiSave.setName("etiSave");
                this.userregMenu.etiSave.addMouseListener(this);
                this.userregMenu.etiModify.setName("etiModify");
                this.userregMenu.etiModify.addMouseListener(this);
                this.userregMenu.etiConfig.setName("etiConfig");
                this.userregMenu.etiConfig.addMouseListener(this);
                this.userregMenu.etiExit.setName("etiExit");
                this.userregMenu.etiExit.addMouseListener(this);
                this.userregMenu.logOut.setName("logOut");
                this.userregMenu.logOut.addMouseListener(this);
                break;

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (Action.valueOf(e.getActionCommand())) {
            case primero:
                pagina_Userreg.currentPageIndex = 1;
                pagina_Userreg.initLinkBox();
                break;
            case ANTERIOR:
                pagina_Userreg.currentPageIndex -= 1;
                pagina_Userreg.initLinkBox();
                break;
            case SIGUIENTE:
                pagina_Userreg.currentPageIndex += 1;
                pagina_Userreg.initLinkBox();
                break;
            case ultimo:
                break;
            case comboUserreg:
                pagina_Userreg.itemsPerPage = Integer.parseInt(menuUserreg.comboUserreg.getSelectedItem().toString());
                pagina_Userreg.currentPageIndex = 1;
                pagina_Userreg.initLinkBox();
                break;

            case btnAceptar:
                boolean disp = false;
                disp = BLL_Userreg.checkCreateUserreg();
                if (disp == true) {
                    timer_NU();
                }
                break;
            case btnCancelar:
                this.createUser.dispose();
                new controller_Userreg(new UserReg(), 0).start(0);
                break;

            case btnAceptar1:
                boolean disp1 = false;
                disp1 = BLL_Userreg.checkChangeUserreg();
                if (disp1 == true) {
                    timer_CU();
                }
                break;

            case btnCancelar1:
                this.changeUserreg.dispose();
                if (singleton.user.equals("Admin")) {
                    new controller_Userreg(new UserReg(), 0).start(0);
                } else {
                    new controller_Userreg(new UserregMenu(), 3).start(3);
                }

                break;

            case comboDni:
                BLL_Userreg.viewUserreg();
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

            case dniField:
                BLL_Userreg.askUserreg("dni");
                break;
            case nameField:
                BLL_Userreg.askUserreg("name");
                break;
            case surnameField:
                BLL_Userreg.askUserreg("surname");
                break;
            case mobileField:
                BLL_Userreg.askUserreg("mobile");
                break;
            case emailField:
                BLL_Userreg.askUserreg("email");
                break;
            case userField:
                BLL_Userreg.askUserreg("user");
                break;
            case passField:
                BLL_Userreg.askUserreg("pass");
                break;
            case activityField:
                BLL_Userreg.askUserreg("activity");
                break;
            case nameField1:
                BLL_Userreg.changeUserreg("name");
                break;
            case surnameField1:
                BLL_Userreg.changeUserreg("surname");
                break;
            case mobileField1:
                BLL_Userreg.changeUserreg("mobile");
                break;
            case emailField1:
                BLL_Userreg.changeUserreg("email");
                break;
            case userField1:
                BLL_Userreg.changeUserreg("user");
                break;
            case passField1:
                BLL_Userreg.changeUserreg("pass");
                break;
            case activityField1:
                BLL_Userreg.changeUserreg("activity");
                break;
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (Action.valueOf(e.getComponent().getName())) {
            case CreateUserreg:
                menuUserreg.dispose();
                new controller_Userreg(new createUserreg(), 1).start(1);
                break;
            case modifyUserreg:
                singleton.registered_user = null;
                int selec = UserReg.jTable.getSelectedRow();
                if (selec == -1) {
                    menuUserreg.dispose();
                    new controller_Userreg(new ChangeUserreg(singleton.registered_user, table), 2).start(2);
                } else {
                    boolean modify = BLL_Userreg.modify_row();
                    if (modify == true) {
                        menuUserreg.dispose();
                    }
                }

                break;
            case deleteUserreg:
                BLL_Userreg.deleteAdmin();
                menuUserreg.dispose();
                new controller_Userreg(new UserReg(), 0).start(0);
                break;
            case iconMenu:
                menuUserreg.dispose();
                new controller_menu(new Menu(), 0).start(0);
                break;

            //CREAR USUARIO REGISTRADO
            case dniField:
                this.createUser.dniField.setText("");
                break;
            case nameField:
                this.createUser.nameField.setText("");
                break;
            case surnameField:
                this.createUser.surnameField.setText("");
                break;
            case mobileField:
                this.createUser.mobileField.setText("");
                break;
            case emailField:
                this.createUser.emailField.setText("");
                break;
            case userField:
                this.createUser.userField.setText("");
                break;
            case passField:
                this.createUser.passField.setText("");
                break;
            case activityField:
                this.createUser.activityField.setText("");
                break;
            case img:
                BLL_Userreg.createAvatar();
                break;
            case optYes:
                this.createUser.optionState.setText("Si");
                break;
            case optNo:
                this.createUser.optionState.setText("No");
                break;

            //MODIFICAR USUARIO REGISTRADO
            case nameField1:
                this.changeUserreg.nameField.setText("");
                break;
            case surnameField1:
                this.changeUserreg.surnameField.setText("");
                break;
            case mobileField1:
                this.changeUserreg.mobileField.setText("");
                break;
            case emailField1:
                this.changeUserreg.emailField.setText("");
                break;
            case userField1:
                this.changeUserreg.userField.setText("");
                break;
            case passField1:
                this.changeUserreg.passField.setText("");
                break;
            case activityField1:
                if (singleton.user.equals("Admin")) {
                    this.changeUserreg.activityField.setText("");
                } else {

                }
                break;
            case img1:
                BLL_Userreg.loadAvatar();
                break;
            case optNo1:
                this.changeUserreg.optionState.setText("No");
                break;
            case optYes1:
                this.changeUserreg.optionState.setText("Si");
                break;
            //UserregMenu
            case etiSave:
                BLL_Userreg.SaveUserreg2(Config.getInstance().getFiles());
                break;
            case etiModify:
                userregMenu.dispose();
                new controller_Userreg(new ChangeUserreg(singleton.registered_user, table), 2).start(2);
                break;
            case etiConfig:
                userregMenu.dispose();
                new controller_menu(new Config_jFrame(), 1).start(1);
                break;
            case etiExit:
                //BLL_Userreg.exitOption();
                JOptionPane.showMessageDialog(null, "Saliendo de la Aplicación");
                System.exit(0);

                break;
            case logOut:
                singleton.client = null;
                new controller_Sign_In(new Sign_In()).start();
                userregMenu.dispose();
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

        switch (Action.valueOf(e.getComponent().getName())) {
            case openJson:
                BLL_Userreg.openUserreg("JSON");
                menuUserreg.dispose();
                new controller_Userreg(new UserReg(), 0).start(0);
                break;
            case openXml:
                BLL_Userreg.openUserreg("XML");
                menuUserreg.dispose();
                new controller_Userreg(new UserReg(), 0).start(0);
                break;
            case openTxt:
                BLL_Userreg.openUserreg("TXT");
                menuUserreg.dispose();
                new controller_Userreg(new UserReg(), 0).start(0);
                break;
            case saveJson:
                BLL_Userreg.SaveUserreg("JSON");
                break;
            case saveXml:
                BLL_Userreg.SaveUserreg("XML");
                break;
            case saveTxt:
                BLL_Userreg.SaveUserreg("TXT");
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        switch (Action.valueOf(e.getComponent().getName())) {
            case CreateUserreg:
                menuUserreg.CreateUserreg.setText("<html><font color=red>Crear Usuario Registrado</font></html>");
                break;
            case modifyUserreg:
                menuUserreg.modifyUserreg.setText("<html><font color=red>Modificar Usuario Registrado</font></html>");
                break;
            case deleteUserreg:
                menuUserreg.deleteUserreg.setText("<html><font color=red>Borrar Usuario Registrado</font></html>");
                break;
            case iconMenu:
                this.menuUserreg.iconMenu.setIcon(new ImageIcon("src/img/homered.png"));
                break;
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

        switch (Action.valueOf(e.getComponent().getName())) {
            case CreateUserreg:
                menuUserreg.CreateUserreg.setText("<html><font color=black>Crear Usuario Registrado</font></html>");
                break;
            case modifyUserreg:
                menuUserreg.modifyUserreg.setText("<html><font color=black>Modificar Usuario Registrado</font></html>");
                break;
            case deleteUserreg:
                menuUserreg.deleteUserreg.setText("<html><font color=black>Borrar Usuario Registrado</font></html>");
                break;
            case iconMenu:
                this.menuUserreg.iconMenu.setIcon(new ImageIcon("src/img/home_32.png"));
                break;
        }
    }
}
