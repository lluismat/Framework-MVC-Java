package modules.users.admin.model.utils;

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
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.Annotations;
import modules.menu.model.Language;
import modules.users.admin.model.classes.admin;
import modules.users.users.singleton;

public class xml {
	private static final String ENCODING = "UTF-8";

        /**
         * guarda un admin en XML
         */
	public static void saveAdmin() {
		String PATH = null;
		try {
			OutputStream os = new ByteArrayOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			XStream xstream = new XStream();
			Annotations.configureAliases(xstream, admin.class);

			String header = "<?xml version=\"1.0\" encoding=\"" + ENCODING + "\"?>\n";
			xstream.toXML(singleton.useradmin, osw);
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

        /**
         * abre un XML 
         */
	public static void openAdmin() {
		String PATH = null;
		try {
			XStream xstream = new XStream();
			Annotations.configureAliases(xstream, admin.class);

			JFileChooser fileChooser = new JFileChooser();

			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("XML (*.xml)", "xml"));

			int seleccion = fileChooser.showOpenDialog(null);
			if (seleccion == JFileChooser.APPROVE_OPTION) {
				File JFC = fileChooser.getSelectedFile();
				PATH = JFC.getAbsolutePath();
				singleton.useradmin = (ArrayList<admin>) xstream.fromXML(new FileReader(PATH));
			}

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("openerrorxml"),
					Language.getInstance().getProperty("errorfiles"), JOptionPane.ERROR_MESSAGE);
		}
	}

        /**
         * guarda automaticamente un archivo XML 
         */
	public static void autosaveadmin() {
		String PATH = null;

		try {
			PATH = new java.io.File(".").getCanonicalPath() + "/src/modules/users/admin/model/files/xml/admin.xml";
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (singleton.useradmin.size() > 0) {
			try {
				OutputStream os = new ByteArrayOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(os);
				XStream xstream = new XStream();

				Annotations.configureAliases(xstream, admin.class);
				String header = "<?xml version=\"1.0\" encoding=\"" + ENCODING + "\"?>\n";
				xstream.toXML(singleton.useradmin, osw);
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

        /**
         * abre automaticamente un archivo XML
         */
	public static void OpenAutoAdmin() {
		String PATH;

		try {
			XStream xstream = new XStream();
			Annotations.configureAliases(xstream, admin.class);

			PATH = new java.io.File(".").getCanonicalPath() + "/src/modules/users/admin/model/files/xml/admin.xml";

			File path = new File(PATH);

			if (path.exists()) {
				singleton.useradmin = (ArrayList<admin>) xstream.fromXML(new FileReader(PATH));
			}

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("openerrorxml"),
					Language.getInstance().getProperty("errorfiles"), JOptionPane.ERROR_MESSAGE);
		}
	}
}
