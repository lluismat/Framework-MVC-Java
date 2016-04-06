/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.menu.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import modules.Config.model.Config;
import modules.Config.model.files_Config;
import modules.Config.view.Config_jFrame;
import static modules.menu.controller.controller_menu.config;
import modules.menu.view.Menu;
import modules.users.admin.controller.controller_Admin;
import modules.users.admin.model.BLL.BLL_Admin;
import modules.users.admin.view.Admin;
import modules.users.client.controller.controller_Client;
import modules.users.client.view.Client;
import modules.users.userreg.controller.controller_Userreg;
import modules.users.userreg.view.UserReg;
import utils.Themes;

/**
 *
 * @author lluis
 */
public class controller_menu implements ActionListener, MouseListener {

    public static Menu menu;
    public static Config_jFrame config;

    public controller_menu(JFrame start, int i) {

        if (i == 0) {
            menu = (Menu) start;
        } else if (i == 1) {
            config = (Config_jFrame) start;
        }
    }

    public enum Action {

        //botones Menu
        etiAdmin,
        etiClient,
        etiUserreg,
        etiConfig,
        etiExit,
        //botones Config
        btnSave,
        btnDefault,
        btnVolver,

    }

    public void start(int i) {
        if (i == 0) {
            menu.setSize(660, 450);//ancho x alto
            menu.setResizable(false);
            menu.setTitle("Menu Principal");
            menu.setVisible(true);
            Themes.themes();

            this.menu.etiAdmin.setName("etiAdmin");
            this.menu.etiAdmin.addMouseListener(this);
            this.menu.etiClient.setName("etiClient");
            this.menu.etiClient.addMouseListener(this);
            this.menu.etiUserreg.setName("etiUserreg");
            this.menu.etiUserreg.addMouseListener(this);
            this.menu.etiConfig.setName("etiConfig");
            this.menu.etiConfig.addMouseListener(this);
            this.menu.etiExit.setName("etiExit");
            this.menu.etiExit.addMouseListener(this);

            this.menu.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    BLL_Admin.Exit();
                }
            });
        } else if (i == 1) {

            config.setSize(660, 450);
            config.setResizable(false);
            config.setTitle("Menu Configuracion");
            config.setVisible(true);
            Themes.themes();

            
            //FORMATO FECHA
            switch (Config.getInstance().getFormatDate()) {
                case "dd/MM/yyyy":
                    config.optDate1.setSelected(true);
                    break;
                case "dd-MM-yyyy":
                    config.optDate2.setSelected(true);
                    break;
                case "yyyy/MM/dd":
                    config.optDate3.setSelected(true);
                    break;
                case "yyyy-MM-dd":
                    config.optDate4.setSelected(true);
                    break;
            }
            
            //DECIMALES
            switch (Config.getInstance().getDecimals()) {
                case 1:
                    config.optDecimals1.setSelected(true);
                    break;
                case 2:                    
                    config.optDecimals2.setSelected(true);
                    break;
                case 3:
                    config.optDecimals3.setSelected(true);
                    break;
            }
            
            //FORMATO MONEDA
            switch (Config.getInstance().getCurrency()) {
                case "€":
                    config.optEuros.setSelected(true);
                    break;
                case "$":
                    config.optDolars.setSelected(true);
                    break;
                case "£":
                    config.optLibras.setSelected(true);
                    break;
            }
            
            //TEMAS
            switch (Config.getInstance().getTheme()) {
                case "Metal":
                    config.optMetal.setSelected(true);
                    break;
                case "Windows":
                    config.optWindows.setSelected(true);
                    break;
                case "Motif":
                    config.optMotif.setSelected(true);
                    break;
                case "Nimbus":
                    config.optNimbus.setSelected(true);
                    break;
            }
            //IDIOMA
            switch (Config.getInstance().getLanguage()) {
                case "EN":
                    config.optEn.setSelected(true);
                    break;
                case "ES":
                    config.optEsp.setSelected(true);
                    break;
                case "VAL":
                    config.optVal.setSelected(true);
                    break;
            }

            //ARCHIVOS
            
            switch (Config.getInstance().getFiles()) {
                case "JSON":
                    config.optJson.setSelected(true);
                    break;
                case "XML":
                    config.optXml.setSelected(true);
                    break;
                case "TXT":
                    config.optTxt.setSelected(true);
                    break;
            }
            

            this.config.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {

                    new controller_menu(new Menu(), 0).start(0);
                }
            });
            this.config.btnSave.setActionCommand("btnSave");
            this.config.btnSave.addActionListener(this);
            this.config.btnDefault.setActionCommand("btnDefault");
            this.config.btnDefault.addActionListener(this);
            this.config.btnVolver.setActionCommand("btnVolver");
            this.config.btnVolver.addActionListener(this);

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (Action.valueOf(e.getActionCommand())) {
            case btnSave:
                BLL_Admin.changeConfig();
                files_Config.SaveConfig();
                config.dispose();
                new controller_menu(new Menu(), 0).start(0);
                break;
            case btnDefault:
                Config.getInstance().setFormatDate("dd/MM/yyyy");
                Config.getInstance().setDecimals(1);
                Config.getInstance().setCurrency("€");
                Config.getInstance().setLanguage("ES");
                Config.getInstance().setFiles("JSON");
                Config.getInstance().setTheme("Windows");
                files_Config.SaveConfig();
                Themes.themes();
                break;
            case btnVolver:
                config.dispose();
                new controller_menu(new Menu(), 0).start(0);
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        switch (Action.valueOf(e.getComponent().getName())) {
            case etiAdmin:
                menu.dispose();
                new controller_Admin(new Admin(), 0).start(0);//cerramos ventana actual
                break;
            case etiClient:
                menu.dispose();
                new controller_Client(new Client(), 0).start(0);
                break;
            case etiUserreg:
                menu.dispose();
                new controller_Userreg(new UserReg(), 0).start(0);
                break;
            case etiConfig:
                menu.dispose();
                new controller_menu(new Config_jFrame(), 1).start(1);
                break;
            case etiExit:
                BLL_Admin.Exit();
                break;

        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e1) {
        switch (Action.valueOf(e1.getComponent().getName())) {

            case etiAdmin:
                this.menu.etiAdmin.setText("<html><font color=red>Administrador</font></html>");
                break;
            case etiClient:
                this.menu.etiClient.setText("<html><font color=red>Cliente</font></html>");
                break;
            case etiUserreg:
                this.menu.etiUserreg.setText("<html><font color=red>Usuario Registrado</font></html>");
                break;
            case etiConfig:
                this.menu.etiConfig.setIcon(new ImageIcon("src/img/setings.png"));
                break;
            case etiExit:
                this.menu.etiExit.setIcon(new ImageIcon("src/img/salir.png"));
                break;

        }
    }

    @Override
    public void mouseExited(MouseEvent e2) {

        switch (Action.valueOf(e2.getComponent().getName())) {

            case etiAdmin:
                menu.etiAdmin.setText("<html><font color=black>Administrador</font></html>");
                break;
            case etiClient:
                menu.etiClient.setText("<html><font color=black>Cliente</font></html>");
                break;
            case etiUserreg:
                menu.etiUserreg.setText("<html><font color=black>Usuario Registrado</font></html>");
                break;
            case etiConfig:
                menu.etiConfig.setIcon(new ImageIcon("src/img/settings.png"));
                break;
            case etiExit:
                menu.etiExit.setIcon(new ImageIcon("src/img/logout.png"));
                break;

        }

    }

}
