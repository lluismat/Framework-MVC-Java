/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.users.userreg.model.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.Annotations;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import static javax.xml.transform.OutputKeys.ENCODING;
import modules.Config.model.Language;
import modules.users.userreg.model.classes.registered_user;
import modules.users.users.singleton;


/**
 *
 * @author lluis
 */
public class xml_Userreg {
    
    public static void saveUserreg() {
		String PATH = null;
		try {
			OutputStream os = new ByteArrayOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			XStream xstream = new XStream();
			Annotations.configureAliases(xstream, registered_user.class);

			String header = "<?xml version=\"1.0\" encoding=\"" + ENCODING + "\"?>\n";
			xstream.toXML(singleton.userreg, osw);
			StringBuffer xml = new StringBuffer();
			xml.append(header);
			xml.append(os.toString());

			JFileChooser fileChooser = new JFileChooser();

			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("XML (*.xml)", "xml"));

			int seleccion = fileChooser.showSaveDialog(null);
			if (seleccion == JFileChooser.APPROVE_OPTION) {
				File JFC = fileChooser.getSelectedFile();
				PATH = JFC.getAbsolutePath();
				PATH = PATH + ".xml";

				FileWriter fileXml = new FileWriter(PATH);
				fileXml.write(xml.toString());
				fileXml.close();
				osw.close();
				os.close();

			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("saveerrorxml"),
					Language.getInstance().getProperty("errorfiles"), JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void openUserreg() {
		String PATH = null;
		try {
			XStream xstream = new XStream();
			Annotations.configureAliases(xstream, registered_user.class);

			JFileChooser fileChooser = new JFileChooser();

			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("XML (*.xml)", "xml"));

			int seleccion = fileChooser.showOpenDialog(null);
			if (seleccion == JFileChooser.APPROVE_OPTION) {
				File JFC = fileChooser.getSelectedFile();
				PATH = JFC.getAbsolutePath();
				singleton.userreg = (ArrayList<registered_user>) xstream.fromXML(new FileReader(PATH));
			}

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("openerrorxml"),
					Language.getInstance().getProperty("errorfiles"), JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void autosaveUserreg() {
		String PATH = null;

		try {
			PATH = new java.io.File(".").getCanonicalPath()
					+ "/src/modules/users/userreg/model/files/xml/xml.xml";
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (singleton.userreg.size() > 0) {
			try {
				OutputStream os = new ByteArrayOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(os);
				XStream xstream = new XStream();

				Annotations.configureAliases(xstream, registered_user.class);
				String header = "<?xml version=\"1.0\" encoding=\"" + ENCODING + "\"?>\n";
				xstream.toXML(singleton.userreg, osw);
				StringBuffer xml = new StringBuffer();
				xml.append(header);
				xml.append(os.toString());

				FileWriter fileXml = new FileWriter(PATH);
				fileXml.write(xml.toString());
				fileXml.close();
				osw.close();
				os.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("saveerrorxml"),
						Language.getInstance().getProperty("errorfiles"), JOptionPane.ERROR_MESSAGE);
			}
		} else {
			File path = new File(PATH);

			path.delete();
		}
	}

	public static void OpenAutoUserreg() {
		String PATH;

		try {
			XStream xstream = new XStream();
			Annotations.configureAliases(xstream, registered_user.class);

			PATH = new java.io.File(".").getCanonicalPath()
					+ "/src/modules/users/userreg/model/files/xml/xml.xml";

			File path = new File(PATH);

			if (path.exists()) {
				singleton.userreg = (ArrayList<registered_user>) xstream.fromXML(new FileReader(PATH));
			}

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("openerrorxml"),
					Language.getInstance().getProperty("errorfiles"), JOptionPane.ERROR_MESSAGE);
		}
	}
}
