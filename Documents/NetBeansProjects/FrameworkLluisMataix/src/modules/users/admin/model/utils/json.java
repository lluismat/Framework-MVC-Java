package modules.users.admin.model.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

import modules.Config.model.Config;
import modules.Config.model.Language;
import modules.users.admin.model.classes.admin;
import modules.users.users.singleton;

public class json {

    public static void SaveAdmin() {
        String PATH = null;
        try {
            XStream xstreamjson = new XStream(new JettisonMappedXmlDriver());
            xstreamjson.setMode(XStream.NO_REFERENCES);
            xstreamjson.alias("Admin", admin.class);

            JFileChooser fileChooser = new JFileChooser();

            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JSON (*.json)", "json"));

            int seleccion = fileChooser.showSaveDialog(null);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                File JFC = fileChooser.getSelectedFile();
                PATH = JFC.getAbsolutePath();
                PATH = PATH + ".json";

                Gson gson = new Gson();
                String json = gson.toJson(singleton.useradmin);
                FileWriter fileXml = new FileWriter(PATH);
                fileXml.write(json.toString());
                fileXml.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("saveerrorjson"),
                    Language.getInstance().getProperty("errorjson"), JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void OpenAdmin() {
        String PATH = null;
        admin admin = new admin("");

        try {
            XStream xstream = new XStream(new JettisonMappedXmlDriver());
            xstream.setMode(XStream.NO_REFERENCES);
            xstream.alias("Admin", admin.class);

            JFileChooser fileChooser = new JFileChooser();

            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JSON (*.json)", "json"));

            int seleccion = fileChooser.showOpenDialog(null);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                File JFC = fileChooser.getSelectedFile();
                PATH = JFC.getAbsolutePath();

                singleton.useradmin.clear();

                JsonReader lector = new JsonReader(new FileReader(PATH));
                JsonParser parseador = new JsonParser();
                JsonElement raiz = parseador.parse(lector);

                Gson json = new Gson();
                JsonArray lista = raiz.getAsJsonArray();
                for (JsonElement elemento : lista) {
                    admin = json.fromJson(elemento, admin.class);
                    singleton.useradmin.add(admin);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("openerrorjson"),
                    Language.getInstance().getProperty("errorjson"), JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void SaveAutoAdmin() {
        String PATH = null;
        try {
			XStream xstreamjson = new XStream(new JettisonMappedXmlDriver());
			xstreamjson.setMode(XStream.NO_REFERENCES);
			xstreamjson.alias("Admin", admin.class);

			PATH = new java.io.File(".").getCanonicalPath()
					+ "/src/modules/users/admin/model/files/json/admin.json";

			Gson gson = new Gson();
			String json1 = gson.toJson(singleton.useradmin);
			FileWriter fileXml = new FileWriter(PATH);
			fileXml.write(json1.toString());
			fileXml.close();

		} catch (Exception e) {
                    JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("openerrorjson"),
                    Language.getInstance().getProperty("errorjson"), JOptionPane.ERROR_MESSAGE);
		}

    }

    public static void OpenAutoAdmin() {
        String PATH = null;
        admin admin = new admin("");
        try {
            PATH = new java.io.File(".").getCanonicalPath() + "/src/modules/users/admin/model/files/json/admin.json";
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            XStream xstream = new XStream(new JettisonMappedXmlDriver());
            xstream.setMode(XStream.NO_REFERENCES);
            xstream.alias("Admin", admin.class);

            singleton.useradmin.clear();

            JsonReader lector = new JsonReader(new FileReader(PATH));
            JsonParser parseador = new JsonParser();
            JsonElement raiz = parseador.parse(lector);

            Gson json = new Gson();
            JsonArray lista = raiz.getAsJsonArray();
            for (JsonElement elemento : lista) {
                admin = json.fromJson(elemento, admin.class);
                singleton.useradmin.add(admin);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("openerrorjson"),
                    Language.getInstance().getProperty("errorjson"), JOptionPane.ERROR_MESSAGE);
        }
    }
}
