package modules.users.client.model.utils;

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
import modules.users.client.model.classes.client;
import modules.users.users.singleton;

public class xml_client {
	private static final String ENCODING = "UTF-8";

        /**
         * guarda los clientes en XML
         */
	public static void saveClient() {
		String PATH = null;
		try {
			OutputStream os = new ByteArrayOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			XStream xstream = new XStream();
			Annotations.configureAliases(xstream, client.class);
                        
			String header = "<?xml version=\"1.0\" encoding=\"" + ENCODING + "\"?>\n";
			xstream.toXML(singleton.userclient, osw);
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
         * guarda un cliente en XML
         */
        public static void saveClient2() {
		String PATH = null;
		try {
			OutputStream os = new ByteArrayOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			XStream xstream = new XStream();
			Annotations.configureAliases(xstream, client.class);

			String header = "<?xml version=\"1.0\" encoding=\"" + ENCODING + "\"?>\n";
			xstream.toXML(singleton.client, osw);
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
         * abre un archivo en XML
         */
	public static void openClient() {
		String PATH = null;
		try {
			XStream xstream = new XStream();
			Annotations.configureAliases(xstream, client.class);

			JFileChooser fileChooser = new JFileChooser();

			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("XML (*.xml)", "xml"));

			int seleccion = fileChooser.showOpenDialog(null);
			if (seleccion == JFileChooser.APPROVE_OPTION) {
				File JFC = fileChooser.getSelectedFile();
				PATH = JFC.getAbsolutePath();
				singleton.userclient = (ArrayList<client>) xstream.fromXML(new FileReader(PATH));
			}

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("openerrorxml"),
					Language.getInstance().getProperty("errorfiles"), JOptionPane.ERROR_MESSAGE);
		}
	}

        /**
         * guarda automaticamente los clientes en XML
         */
	public static void autosaveclient() {
		String PATH = null;

		try {
			PATH = new java.io.File(".").getCanonicalPath() + "/src/modules/users/client/model/files/xml/proba.xml";
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (singleton.userclient.size() > 0) {
			try {
				OutputStream os = new ByteArrayOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(os);
				XStream xstream = new XStream();

				Annotations.configureAliases(xstream, client.class);
				String header = "<?xml version=\"1.0\" encoding=\"" + ENCODING + "\"?>\n";
				xstream.toXML(singleton.userclient, osw);
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
         * abre automaticamente un fichero XML
         */
	public static void OpenAutoClient() {
		String PATH;

		try {
			XStream xstream = new XStream();
			Annotations.configureAliases(xstream, client.class);

			PATH = new java.io.File(".").getCanonicalPath() + "/src/modules/users/client/model/files/xml/proba.xml";

			File path = new File(PATH);

			if (path.exists()) {
				singleton.userclient = (ArrayList<client>) xstream.fromXML(new FileReader(PATH));
			}

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("openerrorxml"),
					Language.getInstance().getProperty("errorfiles"), JOptionPane.ERROR_MESSAGE);
		}
	}
}
