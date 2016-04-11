/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.SQLException;
import modules.Config.model.Config;
import modules.users.admin.model.BLL.BLL_Admin;
import modules.menu.view.Timer_Class;
import static modules.users.admin.model.BLL.BLL_Admin.searchAdmin;
import modules.users.admin.model.BLL.BLL_BBDD;
import modules.users.admin.model.BLL.Proba_BBDD;
import modules.users.client.model.BLL.BLL_Client;
import modules.users.userreg.model.BLL.BLL_Userreg;
import modules.users.users.singleton;

/**
 *
 * @author lluis
 */
public class Demo {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws SQLException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Timer_Class.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Timer_Class.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Timer_Class.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Timer_Class.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        Config.getInstance();
        BLL_Admin.openAuto();
        BLL_Client.openAuto();
        BLL_Userreg.openAuto();

        
        //Proba_BBDD.loadDates();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Timer_Class().setVisible(true);
            }
        });
    }

}
