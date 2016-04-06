/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.users.userreg.model.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import modules.Config.model.Language;
import modules.users.userreg.model.classes.registered_user;
import modules.users.users.singleton;

/**
 *
 * @author lluis
 */
public class txt_Userreg {
    public static void saveUserreg() {
		String PATH = null;
		try {
			File f;
			JFileChooser fileChooser = new JFileChooser();

			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Texto (*.txt)", "txt"));

			int seleccion = fileChooser.showSaveDialog(null);
			if (seleccion == JFileChooser.APPROVE_OPTION) {
				File JFC = fileChooser.getSelectedFile();
				PATH = JFC.getAbsolutePath();
				PATH = PATH + ".txt";
				f = new File(PATH);

				FileOutputStream fo = new FileOutputStream(f);
				ObjectOutputStream o = new ObjectOutputStream(fo);
				o.writeObject(singleton.userreg);
				o.close();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("saveerrortxt"),
					Language.getInstance().getProperty("errorfiles"), JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void openUserreg() {
		String PATH = null;
		try {
			File f;
			JFileChooser fileChooser = new JFileChooser();

			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Texto (*.txt)", "txt"));

			int seleccion = fileChooser.showOpenDialog(null);
			if (seleccion == JFileChooser.APPROVE_OPTION) {
				File JFC = fileChooser.getSelectedFile();
				PATH = JFC.getAbsolutePath();
				f = new File(PATH);

				FileInputStream fi = new FileInputStream(f);
				ObjectInputStream oi = new ObjectInputStream(fi);
				singleton.userreg = (ArrayList<registered_user>) oi.readObject();
				oi.close();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("openerrortxt"),
					Language.getInstance().getProperty("errorfiles"), JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void openAutoUserreg() {
		String PATH = null;
		try {
			File f;

			PATH = new java.io.File(".").getCanonicalPath()
					+ "/src/modules/users/userreg/model/files/txt/txt.txt";

			f = new File(PATH);
			FileInputStream fi = new FileInputStream(f);
			ObjectInputStream oi = new ObjectInputStream(fi);
			singleton.userreg = (ArrayList<registered_user>) oi.readObject();
			oi.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("openerrortxt"),
					Language.getInstance().getProperty("errorfiles"), JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void saveAutoUserreg() {
		String PATH = null;
		try {
			File f;

			PATH = new java.io.File(".").getCanonicalPath()
					+ "/src/modules/users/userreg/model/files/txt/txt.txt";
			f = new File(PATH);

			FileOutputStream fo = new FileOutputStream(f);
			ObjectOutputStream o = new ObjectOutputStream(fo);
			o.writeObject(singleton.userreg);
			o.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("saveerrortxt"),
					Language.getInstance().getProperty("errorfiles"), JOptionPane.ERROR_MESSAGE);
		}
	}
}
