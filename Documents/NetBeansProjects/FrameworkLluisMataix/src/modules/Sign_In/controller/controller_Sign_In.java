/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.Sign_In.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.Timer;
import modules.Sign_In.model.BLL_SignIn.BLL_SignIn;
import modules.Sign_In.view.Sign_In;
import modules.menu.controller.controller_menu;
import modules.menu.model.Language;
import modules.menu.view.Menu;
import modules.users.admin.model.BLL.BLL_Admin;
import modules.users.client.controller.controller_Client;
import modules.users.client.view.ClientMenu;
import modules.users.users.singleton;
import utils.Themes;

/**
 *
 * @author lluis
 */
public class controller_Sign_In implements ActionListener, KeyListener, MouseListener {

    public static Sign_In signIn;

    public controller_Sign_In(JFrame start) {

        signIn = (Sign_In) start;

    }

    public enum Action {

        //botones SignIn
        dniField,
        passField,
        btnAceptar,
        btnCancelar,

    }

    public void start() {

        signIn.setSize(660, 450);//ancho x alto
        signIn.setResizable(false);
        signIn.setTitle(Language.getInstance().getProperty("mainmenu"));
        signIn.setVisible(true);

        Themes.themes();

        this.signIn.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                BLL_Admin.Exit();
            }
        });

        this.signIn.btnAceptar.setActionCommand("btnAceptar");
        this.signIn.btnAceptar.addActionListener(this);
        this.signIn.btnCancelar.setActionCommand("btnCancelar");
        this.signIn.btnCancelar.addActionListener(this);
        this.signIn.dniField.setName("dniField"); 
        this.signIn.dniField.addKeyListener(this);
    }

    private void timer() {

        Timer timer = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                signIn.dispose();
                switch(singleton.user){
                    case "Admin":
                        new controller_menu(new Menu(), 0).start(0);
                        break;
                    case "Client":
                        new controller_Client(new ClientMenu(),3).start(3);
                        break;
                    case "Userreg":
                        break;
                }
                
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (Action.valueOf(ae.getActionCommand())) {

            case btnAceptar:
                boolean disp = false;
                disp = BLL_SignIn.searchUser();
                if (disp == true) {
                    timer();
                }
                break;
            case btnCancelar:

                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {

    }

    @Override
    public void mousePressed(MouseEvent me) {

    }

    @Override
    public void mouseReleased(MouseEvent me) {

    }

    @Override
    public void mouseEntered(MouseEvent me) {

    }

    @Override
    public void mouseExited(MouseEvent me) {

    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {

    }

    @Override
    public void keyReleased(KeyEvent ke) {
        switch (Action.valueOf(ke.getComponent().getName())) {
            case dniField:
                BLL_SignIn.askDni();
                break;
        }
    }

}
