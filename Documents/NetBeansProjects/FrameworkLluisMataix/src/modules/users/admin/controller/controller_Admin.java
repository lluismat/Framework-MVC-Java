/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.users.admin.controller;

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
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modules.menu.model.Config;
import modules.menu.controller.controller_menu;
import modules.menu.model.Language;
import modules.menu.view.Menu;
import static modules.users.admin.controller.controller_Admin.menuAdmin;
import modules.users.admin.model.BLL.BLL_Admin;
import modules.users.admin.model.classes.miniSimpleTableModel_Admin;
import modules.users.admin.model.utils.autocomplete_admin.AutocompleteJComboBox;
import modules.users.admin.model.utils.autocomplete_admin.StringSearchable;
import modules.users.admin.model.utils.pagina;
import modules.users.admin.view.Admin;
import modules.users.admin.view.ChangeAdmin;
import modules.users.admin.view.Create_Admin;
import static modules.users.admin.view.Create_Admin.imgPath;
import static modules.users.admin.view.Create_Admin.optYes;
import modules.users.users.singleton;
import utils.Themes;

/**
 *
 * @author lluis
 */
public class controller_Admin implements ActionListener, KeyListener, MouseListener {

    public static JTable tabla = null;
    public static TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(new miniSimpleTableModel_Admin());
    public static Admin menuAdmin;
    public static ChangeAdmin changeAdmin;
    public static Create_Admin createAdmin;
    public static AutocompleteJComboBox combo = null;
    JTable table = null;

    public controller_Admin(JFrame start, int o) {
        switch (o) {
            case 0:
                menuAdmin = (Admin) start;
                break;
            case 1:
                createAdmin = (Create_Admin) start;
                break;
            case 2:
                changeAdmin = (ChangeAdmin) start;
                break;

        }
    }

    public enum Action {

        //MENU ADMIN
        CreateAdmin,
        modifyAdmin,
        deleteAdmin,
        iconMenu,
        open,
        save,
        //JTABLE
        jTable,
        primero,
        ANTERIOR,
        SIGUIENTE,
        ultimo,
        comboAdmin,
        //Filtrar

        //CREAR ADMIN 

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
        HiringDateField,
        //MODIFICAR ADMIN
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
        HiringDateField1,
        comboDni,

        //////////////////////////////
    }

    public static DefaultComboBoxModel PlenaComboBox() {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        for (int i = 0; i < (singleton.useradmin.size()); i++) {
            modelo.addElement(singleton.useradmin.get(i).getDni());
        }
        return modelo;
    }

    public static void comboActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("word selected: " + ((JComboBox) combo).getSelectedItem());
        pagina.currentPageIndex = 1;
        ((miniSimpleTableModel_Admin) menuAdmin.jTable.getModel()).filtrar();
        combo.requestFocus();
    }

    private void timer_NA() {

        Timer timer = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createAdmin.dispose();
                new controller_Admin(new Admin(), 0).start(0);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void timer_CA() {

        Timer timer = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changeAdmin.dispose();
                new controller_Admin(new Admin(), 0).start(0);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    public void start(int o) {
        switch (o) {
            case 0:
                //MENU ADMIN//

                menuAdmin.setSize(1150, 600);
                menuAdmin.setResizable(false);
                menuAdmin.setTitle(Language.getInstance().getProperty("menuadmin"));
                menuAdmin.setVisible(true);
                menuAdmin.CreateAdmin.setText(Language.getInstance().getProperty("createadmin"));
                menuAdmin.modifyAdmin.setText(Language.getInstance().getProperty("modifyadmin"));
                menuAdmin.deleteAdmin.setText(Language.getInstance().getProperty("deleteadmin"));
                menuAdmin.openAdmin.setText(Language.getInstance().getProperty("open"));
                menuAdmin.Open.setText(Language.getInstance().getProperty("open"));
                menuAdmin.saveAdmin.setText(Language.getInstance().getProperty("save"));
                menuAdmin.Save.setText(Language.getInstance().getProperty("save"));
                menuAdmin.CreateAdmin.setText(Language.getInstance().getProperty("createadmin"));
                menuAdmin.jLabel1.setText(Language.getInstance().getProperty("filter"));
                Themes.themes();
                menuAdmin.jTable.setModel(new miniSimpleTableModel_Admin());

                ((miniSimpleTableModel_Admin) menuAdmin.jTable.getModel()).load();
                menuAdmin.jTable.setFillsViewportHeight(true);
                menuAdmin.jTable.setRowSorter(sorter);

                pagina.itemsPerPage = Integer.parseInt(menuAdmin.comboAdmin.getSelectedItem().toString());
                pagina.currentPageIndex = 1;

                pagina.inicializa();
                pagina.initLinkBox();

                menuAdmin.jLabel3.setText(String.valueOf(singleton.useradmin.size()));

                this.menuAdmin.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        menuAdmin.dispose();
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

                menuAdmin.jPanel3.setLayout(
                        new java.awt.BorderLayout());
                menuAdmin.jPanel3.add(combo);

                combo.addActionListener(
                        new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt
                    ) {
                        comboActionPerformed(evt);
                    }
                }
                );

                this.menuAdmin.CreateAdmin.setName("CreateAdmin");

                this.menuAdmin.CreateAdmin.addMouseListener(this);

                this.menuAdmin.modifyAdmin.setName("modifyAdmin");

                this.menuAdmin.modifyAdmin.addMouseListener(this);

                this.menuAdmin.deleteAdmin.setName("deleteAdmin");

                this.menuAdmin.deleteAdmin.addMouseListener(this);

                this.menuAdmin.iconMenu.setName("iconMenu");

                this.menuAdmin.iconMenu.addMouseListener(this);

                this.menuAdmin.Open.setName("open");

                this.menuAdmin.Open.addMouseListener(this);

                this.menuAdmin.Save.setName("save");

                this.menuAdmin.Save.addMouseListener(this);

                this.menuAdmin.primero.setActionCommand("primero");

                this.menuAdmin.primero.addActionListener(this);

                this.menuAdmin.ANTERIOR.setActionCommand("ANTERIOR");

                this.menuAdmin.ANTERIOR.addActionListener(this);

                this.menuAdmin.SIGUIENTE.setActionCommand("SIGUIENTE");

                this.menuAdmin.SIGUIENTE.addActionListener(this);

                this.menuAdmin.ultimo.setActionCommand("ultimo");

                this.menuAdmin.ultimo.addActionListener(this);

                this.menuAdmin.comboAdmin.setActionCommand("comboAdmin");

                this.menuAdmin.comboAdmin.addActionListener(this);

                this.menuAdmin.jTable.setName("jTable");

                this.menuAdmin.jTable.addMouseListener(this);

                break;

            //CREAR ADMINISRTRADOR
            case 1:

                this.createAdmin.setSize(942, 720);
                this.createAdmin.setResizable(false);
                this.createAdmin.setTitle(Language.getInstance().getProperty("createadmin"));
                createAdmin.birthdayField.setDateFormatString(Config.getInstance().getFormatDate());
                createAdmin.HiringDateField.setDateFormatString(Config.getInstance().getFormatDate());
                Themes.themes();

                this.createAdmin.btnAceptar.setText(Language.getInstance().getProperty("modify"));
                this.createAdmin.btnCancelar.setText(Language.getInstance().getProperty("cancel"));
                this.createAdmin.dniField.setText(Language.getInstance().getProperty("dnim"));
                this.createAdmin.nameField.setText(Language.getInstance().getProperty("namem"));
                this.createAdmin.surnameField.setText(Language.getInstance().getProperty("surnamem"));
                this.createAdmin.mobileField.setText(Language.getInstance().getProperty("mobilem"));
                this.createAdmin.emailField.setText(Language.getInstance().getProperty("emailm"));
                this.createAdmin.userField.setText(Language.getInstance().getProperty("userm"));
                this.createAdmin.jLabel14.setText(Language.getInstance().getProperty("statem"));
                this.createAdmin.optYes.setText(Language.getInstance().getProperty("yes"));
                this.createAdmin.optNo.setText(Language.getInstance().getProperty("no"));
                this.createAdmin.activityField.setText(Language.getInstance().getProperty("activitym"));
                this.createAdmin.jLabel2.setText(Language.getInstance().getProperty("dni"));
                this.createAdmin.jLabel3.setText(Language.getInstance().getProperty("user"));
                this.createAdmin.jLabel4.setText(Language.getInstance().getProperty("name"));
                this.createAdmin.jLabel5.setText(Language.getInstance().getProperty("surname"));
                this.createAdmin.jLabel6.setText(Language.getInstance().getProperty("mobile"));
                this.createAdmin.jLabel7.setText(Language.getInstance().getProperty("email"));
                this.createAdmin.jLabel8.setText(Language.getInstance().getProperty("pass"));
                this.createAdmin.jLabel9.setText(Language.getInstance().getProperty("birthday"));
                this.createAdmin.jLabel13.setText(Language.getInstance().getProperty("state"));
                this.createAdmin.jLabel10.setText(Language.getInstance().getProperty("activity"));
                this.createAdmin.jLabel11.setText(Language.getInstance().getProperty("hiringdate"));
                this.createAdmin.jLabel15.setText(Language.getInstance().getProperty("createadmin"));
                this.createAdmin.jLabel1.setText(Language.getInstance().getProperty("avatar"));

                this.createAdmin.setVisible(true);

                this.createAdmin.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {

                        new controller_Admin(new Admin(), 0).start(0);
                    }
                });

                optYes.setSelected(true);
                imgPath.setText("C:\\Users\\lluis\\Documents\\NetBeansProjects\\FrameworkLluisMataix\\src\\img\\avatar.png");

                this.createAdmin.btnAceptar.setActionCommand("btnAceptar");

                this.createAdmin.btnAceptar.addActionListener(this);

                this.createAdmin.btnCancelar.setActionCommand("btnCancelar");

                this.createAdmin.btnCancelar.addActionListener(this);

                this.createAdmin.dniField.setName("dniField");

                this.createAdmin.dniField.addMouseListener(this);

                this.createAdmin.dniField.addKeyListener(this);

                this.createAdmin.nameField.setName("nameField");

                this.createAdmin.nameField.addMouseListener(this);

                this.createAdmin.nameField.addKeyListener(this);

                this.createAdmin.surnameField.setName("surnameField");

                this.createAdmin.surnameField.addMouseListener(this);

                this.createAdmin.surnameField.addKeyListener(this);

                this.createAdmin.mobileField.setName("mobileField");

                this.createAdmin.mobileField.addMouseListener(this);

                this.createAdmin.mobileField.addKeyListener(this);

                this.createAdmin.emailField.setName("emailField");

                this.createAdmin.emailField.addMouseListener(this);

                this.createAdmin.emailField.addKeyListener(this);

                this.createAdmin.userField.setName("userField");

                this.createAdmin.userField.addMouseListener(this);

                this.createAdmin.userField.addKeyListener(this);

                this.createAdmin.passField.setName("passField");

                this.createAdmin.passField.addMouseListener(this);

                this.createAdmin.passField.addKeyListener(this);

                this.createAdmin.birthdayField.setName("birthdayField");

                this.createAdmin.birthdayField.addMouseListener(this);

                this.createAdmin.img.setName("img");

                this.createAdmin.img.addMouseListener(this);

                this.createAdmin.optYes.setName("optYes");

                this.createAdmin.optYes.addMouseListener(this);

                this.createAdmin.optNo.setName("optNo");

                this.createAdmin.optNo.addMouseListener(this);

                this.createAdmin.activityField.setName("activityField");

                this.createAdmin.activityField.addMouseListener(this);

                this.createAdmin.activityField.addKeyListener(this);

                this.createAdmin.HiringDateField.setName("HiringDateField");

                this.createAdmin.HiringDateField.addMouseListener(this);

                break;

            //MODIFICAR ADMINISTRADOR
            case 2:

                this.changeAdmin.etiDNI.setVisible(false);
                this.changeAdmin.dniField.setVisible(false);
                this.changeAdmin.dniField.setEditable(false);
                this.changeAdmin.birthdayField.setDateFormatString(Config.getInstance().getFormatDate());
                this.changeAdmin.HiringDateField.setDateFormatString(Config.getInstance().getFormatDate());
                Themes.themes();

                this.changeAdmin.btnAceptar.setText(Language.getInstance().getProperty("create"));
                this.changeAdmin.btnCancelar.setText(Language.getInstance().getProperty("cancel"));
                this.changeAdmin.dniField.setText(Language.getInstance().getProperty("dnim"));
                this.changeAdmin.nameField.setText(Language.getInstance().getProperty("namem"));
                this.changeAdmin.surnameField.setText(Language.getInstance().getProperty("surnamem"));
                this.changeAdmin.mobileField.setText(Language.getInstance().getProperty("mobilem"));
                this.changeAdmin.emailField.setText(Language.getInstance().getProperty("emailm"));
                this.changeAdmin.userField.setText(Language.getInstance().getProperty("userm"));
                this.changeAdmin.jLabel14.setText(Language.getInstance().getProperty("statem"));
                this.changeAdmin.optYes.setText(Language.getInstance().getProperty("yes"));
                this.changeAdmin.optNo.setText(Language.getInstance().getProperty("no"));
                this.changeAdmin.activityField.setText(Language.getInstance().getProperty("activitym"));
                this.changeAdmin.etiDNI.setText(Language.getInstance().getProperty("dni"));
                this.changeAdmin.etiDNI1.setText(Language.getInstance().getProperty("dni"));
                this.changeAdmin.jLabel3.setText(Language.getInstance().getProperty("user"));
                this.changeAdmin.jLabel4.setText(Language.getInstance().getProperty("name"));
                this.changeAdmin.jLabel5.setText(Language.getInstance().getProperty("surname"));
                this.changeAdmin.jLabel6.setText(Language.getInstance().getProperty("mobile"));
                this.changeAdmin.jLabel7.setText(Language.getInstance().getProperty("email"));
                this.changeAdmin.jLabel8.setText(Language.getInstance().getProperty("pass"));
                this.changeAdmin.jLabel9.setText(Language.getInstance().getProperty("birthday"));
                this.changeAdmin.jLabel13.setText(Language.getInstance().getProperty("state"));
                this.changeAdmin.jLabel10.setText(Language.getInstance().getProperty("activity"));
                this.changeAdmin.jLabel11.setText(Language.getInstance().getProperty("hiringdate"));
                this.changeAdmin.jLabel1.setText(Language.getInstance().getProperty("avatar"));

                if (singleton.adminPager != null) {
                    this.changeAdmin.etiDNI.setVisible(true);
                    this.changeAdmin.dniField.setVisible(true);
                    this.changeAdmin.etiDNI1.setVisible(false);
                    this.changeAdmin.comboDni.setVisible(false);
                    this.changeAdmin.comboDni.setEnabled(true);
                    BLL_Admin.viewJTableAdmin(singleton.adminPager.getDni());
                }

                this.changeAdmin.setSize(942, 720);
                this.changeAdmin.setResizable(false);
                this.changeAdmin.setTitle(Language.getInstance().getProperty("modifyadmin"));
                this.changeAdmin.setVisible(true);

                this.changeAdmin.comboDni.setModel(PlenaComboBox());
                this.changeAdmin.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {

                        new controller_Admin(new Admin(), 0).start(0);
                    }
                });

                this.changeAdmin.btnAceptar.setActionCommand("btnAceptar1");

                this.changeAdmin.btnAceptar.addActionListener(this);

                this.changeAdmin.btnCancelar.setActionCommand("btnCancelar1");

                this.changeAdmin.btnCancelar.addActionListener(this);

                this.changeAdmin.comboDni.setActionCommand("comboDni");

                this.changeAdmin.comboDni.addActionListener(this);

                this.changeAdmin.dniField.setName("dniField1");

                this.changeAdmin.dniField.addMouseListener(this);

                this.changeAdmin.dniField.addKeyListener(this);

                this.changeAdmin.nameField.setName("nameField1");

                this.changeAdmin.nameField.addMouseListener(this);

                this.changeAdmin.nameField.addKeyListener(this);

                this.changeAdmin.surnameField.setName("surnameField1");

                this.changeAdmin.surnameField.addMouseListener(this);

                this.changeAdmin.surnameField.addKeyListener(this);

                this.changeAdmin.mobileField.setName("mobileField1");

                this.changeAdmin.mobileField.addMouseListener(this);

                this.changeAdmin.mobileField.addKeyListener(this);

                this.changeAdmin.emailField.setName("emailField1");

                this.changeAdmin.emailField.addMouseListener(this);

                this.changeAdmin.emailField.addKeyListener(this);

                this.changeAdmin.userField.setName("userField1");

                this.changeAdmin.userField.addMouseListener(this);

                this.changeAdmin.userField.addKeyListener(this);

                this.changeAdmin.passField.setName("passField1");

                this.changeAdmin.passField.addMouseListener(this);

                this.changeAdmin.passField.addKeyListener(this);

                this.changeAdmin.birthdayField.setName("birthdayField1");

                this.changeAdmin.birthdayField.addMouseListener(this);

                this.changeAdmin.img.setName("img1");

                this.changeAdmin.img.addMouseListener(this);

                this.changeAdmin.optYes.setName("optYes1");

                this.changeAdmin.optYes.addMouseListener(this);

                this.changeAdmin.optNo.setName("optNo1");

                this.changeAdmin.optNo.addMouseListener(this);

                this.changeAdmin.activityField.setName("activityField1");

                this.changeAdmin.activityField.addMouseListener(this);

                this.changeAdmin.activityField.addKeyListener(this);

                this.changeAdmin.HiringDateField.setName("HiringDateField1");

                this.changeAdmin.HiringDateField.addMouseListener(this);

                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (Action.valueOf(e.getActionCommand())) {
            case primero:
                pagina.currentPageIndex = 1;
                pagina.initLinkBox();
                break;
            case ANTERIOR:
                pagina.currentPageIndex -= 1;
                pagina.initLinkBox();
                break;
            case SIGUIENTE:
                pagina.currentPageIndex += 1;
                pagina.initLinkBox();
                break;
            case ultimo:
                break;
            case comboAdmin:
                pagina.itemsPerPage = Integer.parseInt(menuAdmin.comboAdmin.getSelectedItem().toString());
                pagina.currentPageIndex = 1;
                pagina.initLinkBox();
                break;

            case btnAceptar:
                boolean disp = false;
                disp = BLL_Admin.checkCreateAdmin();
                if (disp == true) {
                    timer_NA();
                }
                break;
            case btnCancelar:
                this.createAdmin.dispose();
                new controller_Admin(new Admin(), 0).start(0);
                break;

            case btnAceptar1:
                boolean disp1 = false;
                disp1 = BLL_Admin.checkChangeAdmin();
                if (disp1 == true) {
                    timer_CA();
                }
                break;

            case btnCancelar1:
                this.changeAdmin.dispose();
                new controller_Admin(new Admin(), 0).start(0);
                break;

            case comboDni:
                BLL_Admin.viewAdmin();
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
                BLL_Admin.askAdmin("dni");
                break;
            case nameField:
                BLL_Admin.askAdmin("name");
                break;
            case surnameField:
                BLL_Admin.askAdmin("surname");
                break;
            case mobileField:
                BLL_Admin.askAdmin("mobile");
                break;
            case emailField:
                BLL_Admin.askAdmin("email");
                break;
            case userField:
                BLL_Admin.askAdmin("user");
                break;
            case passField:
                BLL_Admin.askAdmin("pass");
                break;
            case activityField:
                BLL_Admin.askAdmin("activity");
                break;
            case nameField1:
                BLL_Admin.changeAdmin("name");
                break;
            case surnameField1:
                BLL_Admin.changeAdmin("surname");
                break;
            case mobileField1:
                BLL_Admin.changeAdmin("mobile");
                break;
            case emailField1:
                BLL_Admin.changeAdmin("email");
                break;
            case userField1:
                BLL_Admin.changeAdmin("user");
                break;
            case passField1:
                BLL_Admin.changeAdmin("pass");
                break;
            case activityField1:
                BLL_Admin.changeAdmin("activity");
                break;
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (Action.valueOf(e.getComponent().getName())) {
            case CreateAdmin:
                menuAdmin.dispose();
                new controller_Admin(new Create_Admin(), 1).start(1);
                break;
            case modifyAdmin:
                menuAdmin.dispose();
                new controller_Admin(new ChangeAdmin(singleton.admin, table), 2).start(2);
                break;
            case deleteAdmin:
                BLL_Admin.deleteAdmin();
                ((miniSimpleTableModel_Admin) Admin.jTable.getModel()).load();
                break;
            case iconMenu:
                menuAdmin.dispose();
                new controller_menu(new Menu(), 0).start(0);
                break;
            case jTable:
                if (e.getClickCount() == 2) {
                    boolean modify = BLL_Admin.modify_row();
                    if (modify == true) {
                        menuAdmin.dispose();
                    }
                }

                break;

            //CREAR ADMIN
            case dniField:
                this.createAdmin.dniField.setText("");
                break;
            case nameField:
                this.createAdmin.nameField.setText("");
                break;
            case surnameField:
                this.createAdmin.surnameField.setText("");
                break;
            case mobileField:
                this.createAdmin.mobileField.setText("");
                break;
            case emailField:
                this.createAdmin.emailField.setText("");
                break;
            case userField:
                this.createAdmin.userField.setText("");
                break;
            case passField:
                this.createAdmin.passField.setText("");
                break;
            case activityField:
                this.createAdmin.activityField.setText("");
                break;
            case img:
                BLL_Admin.createAvatar();
                break;
            case optYes:
                this.createAdmin.optionState.setText("Si");
                break;
            case optNo:
                this.createAdmin.optionState.setText("No");
                break;

            //MODIFICAR ADMIN
            case nameField1:
                this.changeAdmin.nameField.setText("");
                break;
            case surnameField1:
                this.changeAdmin.surnameField.setText("");
                break;
            case mobileField1:
                this.changeAdmin.mobileField.setText("");
                break;
            case emailField1:
                this.changeAdmin.emailField.setText("");
                break;
            case userField1:
                this.changeAdmin.userField.setText("");
                break;
            case passField1:
                this.changeAdmin.passField.setText("");
                break;
            case activityField1:
                this.changeAdmin.activityField.setText("");
                break;
            case img1:
                BLL_Admin.loadAvatar();
                break;
            case optNo1:
                this.changeAdmin.optionState.setText("No");
                break;
            case optYes1:
                this.changeAdmin.optionState.setText("Si");
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

        switch (Action.valueOf(e.getComponent().getName())) {
            case open:
                BLL_Admin.openAdmin(Config.getInstance().getFiles());
                menuAdmin.dispose();
                new controller_Admin(new Admin(), 0).start(0);
                break;
            case save:
                BLL_Admin.SaveAdmin(Config.getInstance().getFiles());
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        switch (Action.valueOf(e.getComponent().getName())) {
            case CreateAdmin:
                menuAdmin.CreateAdmin.setForeground(java.awt.Color.red);
                break;
            case modifyAdmin:
                menuAdmin.modifyAdmin.setForeground(java.awt.Color.red);
                break;
            case deleteAdmin:
                menuAdmin.deleteAdmin.setForeground(java.awt.Color.red);
                break;
            case iconMenu:
                this.menuAdmin.iconMenu.setIcon(new ImageIcon("src/img/homered.png"));
                break;
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

        switch (Action.valueOf(e.getComponent().getName())) {
            case CreateAdmin:
                menuAdmin.CreateAdmin.setForeground(java.awt.Color.black);
                break;
            case modifyAdmin:
                menuAdmin.modifyAdmin.setForeground(java.awt.Color.black);
                break;
            case deleteAdmin:
                menuAdmin.deleteAdmin.setForeground(java.awt.Color.black);
                break;
            case iconMenu:
                this.menuAdmin.iconMenu.setIcon(new ImageIcon("src/img/home_32.png"));
                break;
        }
    }

}
