/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.users.userreg.model.utils;

import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import modules.users.userreg.view.ChangeUserreg;
import modules.users.userreg.view.createUserreg;

/**
 *
 * @author lluis
 */
public class avatar_Userreg {
    
    /**
     * funcion para elegir el avatar y guardar la imagen en el proyecto en la ventana de crear
     */
    public static void createAvatar() {

        JFileChooser fileChooser = new JFileChooser();

        int selection = fileChooser.showOpenDialog(null);

        if (selection == JFileChooser.APPROVE_OPTION) {
            ImageIcon avatar = new ImageIcon(fileChooser.getSelectedFile().getAbsolutePath());
            createUserreg.img.setIcon(new ImageIcon(avatar.getImage().getScaledInstance(125, 125, Image.SCALE_SMOOTH)));
            
            try {
                String nameImg = fileChooser.getSelectedFile().getName();
                String PATH = new java.io.File(".").getCanonicalPath()
                        + "/src/modules/users/admin/model/utils/avatars/" + nameImg;
                createUserreg.imgPath.setText(PATH);
                FileInputStream fis = new FileInputStream(fileChooser.getSelectedFile().getAbsolutePath()); //Archivo a copiar
                FileOutputStream fos = new FileOutputStream(PATH); //Copia del archivo
                FileChannel inChannel = fis.getChannel();
                FileChannel outChannel = fos.getChannel();
                inChannel.transferTo(0, inChannel.size(), outChannel);
                fis.close();
                fos.close();
            } catch (Exception e) {
                

            }
        } else if (selection == JFileChooser.CANCEL_OPTION) {

        }
    }
    
    /**
     * funcion para elegir el avatar y guardar la imagen en el proyecto en la ventana de modificar
     */
    public static void OpenAvatar() {

        JFileChooser fileChooser = new JFileChooser();

        int selection = fileChooser.showOpenDialog(null);

        if (selection == JFileChooser.APPROVE_OPTION) {
            ImageIcon avatar = new ImageIcon(fileChooser.getSelectedFile().getAbsolutePath());
            ChangeUserreg.img.setIcon(new ImageIcon(avatar.getImage().getScaledInstance(125, 125, Image.SCALE_SMOOTH)));
            
            try {
                String nameImg = fileChooser.getSelectedFile().getName();
                String PATH = new java.io.File(".").getCanonicalPath()
                        + "/src/modules/users/userreg/model/utils/avatars/" + nameImg;
                ChangeUserreg.imgPath.setText(PATH);
                FileInputStream fis = new FileInputStream(fileChooser.getSelectedFile().getAbsolutePath()); //Archivo a copiar
                FileOutputStream fos = new FileOutputStream(PATH); //Copia del archivo
                FileChannel inChannel = fis.getChannel();
                FileChannel outChannel = fos.getChannel();
                inChannel.transferTo(0, inChannel.size(), outChannel);
                fis.close();
                fos.close();
            } catch (Exception e) {

            }
        } else if (selection == JFileChooser.CANCEL_OPTION) {

        }
    }
    
}
