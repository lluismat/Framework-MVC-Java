package modules.users.admin.model.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import modules.menu.model.Language;
import modules.users.admin.model.classes.admin;
import modules.users.users.singleton;

public class txt {
    /**
     * guarda un admin en TXT
     */
	public static void saveAdmin() {
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
				o.writeObject(singleton.useradmin);
				o.close();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("saveerrortxt"),
					Language.getInstance().getProperty("errorfiles"), JOptionPane.ERROR_MESSAGE);
		}
	}

        /**
         * abre un fichero en TXT
         */
	public static void openAdmin() {
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
				singleton.useradmin = (ArrayList<admin>) oi.readObject();
				oi.close();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("openerrortxt"),
					Language.getInstance().getProperty("errorfiles"), JOptionPane.ERROR_MESSAGE);
		}
	}

        /**
         * abre automaticamente un txt
         */
	public static void openAutoAdmin() {
		String PATH = null;
		try {
			File f;

			PATH = new java.io.File(".").getCanonicalPath() + "/src/modules/users/admin/model/files/txt/admin.txt";

			f = new File(PATH);
			FileInputStream fi = new FileInputStream(f);
			ObjectInputStream oi = new ObjectInputStream(fi);
			singleton.useradmin = (ArrayList<admin>) oi.readObject();
			oi.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("openerrortxt"),
					Language.getInstance().getProperty("errorfiles"), JOptionPane.ERROR_MESSAGE);
		}
	}

        /**
         * guarda automaticamente un administrador en TXT
         */
	public static void saveAutoAdmin() {
		String PATH = null;
		try {
			File f;

			PATH = new java.io.File(".").getCanonicalPath() + "/src/modules/users/admin/model/files/txt/admin.txt";
			f = new File(PATH);

			FileOutputStream fo = new FileOutputStream(f);
			ObjectOutputStream o = new ObjectOutputStream(fo);
			o.writeObject(singleton.useradmin);
			o.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("saveerrortxt"),
					Language.getInstance().getProperty("errorfiles"), JOptionPane.ERROR_MESSAGE);
		}
	}
}
