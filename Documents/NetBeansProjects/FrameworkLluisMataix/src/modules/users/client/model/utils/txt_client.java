package modules.users.client.model.utils;

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
import modules.users.client.model.classes.client;
import modules.users.users.singleton;

public class txt_client {
	
    /**
     * guarda los clientes en un TXT
     */
	public static void saveClient() {
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
				o.writeObject(singleton.userclient);
				o.close();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("saveerrortxt"),
					Language.getInstance().getProperty("errorfiles"), JOptionPane.ERROR_MESSAGE);
		}
	}

        /**
         * guarda un cliente en TXT
         */
        public static void saveClient2() {
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
				o.writeObject(singleton.client);
				o.close();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("saveerrortxt"),
					Language.getInstance().getProperty("errorfiles"), JOptionPane.ERROR_MESSAGE);
		}
	}
        
        /**
         * abre los clientes en TXT
         */
	public static void openClient() {
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
				singleton.userclient = (ArrayList<client>) oi.readObject();
				oi.close();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("openerrortxt"),
					Language.getInstance().getProperty("errorfiles"), JOptionPane.ERROR_MESSAGE);
		}
	}

        /**
         * abre automaticamente un archvo en TXT
         */
	public static void openAutoClient() {
		String PATH = null;
		try {
			File f;

			PATH = new java.io.File(".").getCanonicalPath() + "/src/modules/users/client/model/files/txt/proba.txt";

			f = new File(PATH);
			FileInputStream fi = new FileInputStream(f);
			ObjectInputStream oi = new ObjectInputStream(fi);
			singleton.userclient = (ArrayList<client>) oi.readObject();
			oi.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("openerrortxt"),
					Language.getInstance().getProperty("errorfiles"), JOptionPane.ERROR_MESSAGE);
		}
	}

        /**
         * guarda automaticamente un archivo TXT con los clientes
         */
	public static void saveAutoClient() {
		String PATH = null;
		try {
			File f;

			PATH = new java.io.File(".").getCanonicalPath() + "/src/modules/users/client/model/files/txt/proba.txt";
			f = new File(PATH);

			FileOutputStream fo = new FileOutputStream(f);
			ObjectOutputStream o = new ObjectOutputStream(fo);
			o.writeObject(singleton.userclient);
			o.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("saveerrortxt"),
					Language.getInstance().getProperty("errorfiles"), JOptionPane.ERROR_MESSAGE);
		}
	}
}
