package modules.users.client.model.utils;

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
import modules.menu.model.Language;
import modules.users.client.model.classes.client;
import modules.users.users.singleton;

public class json_client {

/**
 * guarda los clientes en JSON
 */
    public static void SaveClient() {
        String PATH = null;
        try {
            XStream xstreamjson = new XStream(new JettisonMappedXmlDriver());
            xstreamjson.setMode(XStream.NO_REFERENCES);
            xstreamjson.alias("Clients", client.class);

            JFileChooser fileChooser = new JFileChooser();

            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JSON (*.json)", "json"));

            int seleccion = fileChooser.showSaveDialog(null);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                File JFC = fileChooser.getSelectedFile();
                PATH = JFC.getAbsolutePath();
                PATH = PATH + ".json";

                Gson gson = new Gson();
                String json = gson.toJson(singleton.userclient);
                FileWriter fileXml = new FileWriter(PATH);
                fileXml.write(json.toString());
                fileXml.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("saveerrorjson"),
                    Language.getInstance().getProperty("errorjson"), JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * guarda un cliente en JSON
     */
    public static void SaveClient2() {
        String PATH = null;
        try {
            XStream xstreamjson = new XStream(new JettisonMappedXmlDriver());
            xstreamjson.setMode(XStream.NO_REFERENCES);
            xstreamjson.alias("Clients", client.class);

            JFileChooser fileChooser = new JFileChooser();

            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JSON (*.json)", "json"));

            int seleccion = fileChooser.showSaveDialog(null);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                File JFC = fileChooser.getSelectedFile();
                PATH = JFC.getAbsolutePath();
                PATH = PATH + ".json";

                Gson gson = new Gson();
                String json = gson.toJson(singleton.client);
                FileWriter fileXml = new FileWriter(PATH);
                fileXml.write(json.toString());
                fileXml.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("saveerrorjson"),
                    Language.getInstance().getProperty("errorjson"), JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * abre un archivo con clientes en JSON
     */
    public static void OpenClient() {
        String PATH = null;
        client client = new client();

        try {
            XStream xstream = new XStream(new JettisonMappedXmlDriver());
            xstream.setMode(XStream.NO_REFERENCES);
            xstream.alias("Clients", client.class);

            JFileChooser fileChooser = new JFileChooser();

            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JSON (*.json)", "json"));

            int seleccion = fileChooser.showOpenDialog(null);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                File JFC = fileChooser.getSelectedFile();
                PATH = JFC.getAbsolutePath();

                singleton.userclient.clear();

                JsonReader lector = new JsonReader(new FileReader(PATH));
                JsonParser parseador = new JsonParser();
                JsonElement raiz = parseador.parse(lector);

                Gson json = new Gson();
                JsonArray lista = raiz.getAsJsonArray();
                for (JsonElement elemento : lista) {
                    client = json.fromJson(elemento, client.class);
                   singleton.userclient.add(client);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("openerrorjson"),
                    Language.getInstance().getProperty("errorjson"), JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * guarda automaticamente los clientes en JSON
     */
    public static void SaveAutoClient() {
        String PATH = null;
        try {
            PATH = new java.io.File(".").getCanonicalPath() + "/src/modules/users/client/model/files/json/client.json";
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            XStream xstreamjson = new XStream(new JettisonMappedXmlDriver());
            xstreamjson.setMode(XStream.NO_REFERENCES);
            xstreamjson.alias("Clients", client.class);

            Gson gson = new Gson();
            String json = gson.toJson(singleton.userclient);
            FileWriter fileXml = new FileWriter(PATH);
            fileXml.write(json.toString());
            fileXml.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("saveerrorjson"),
                    Language.getInstance().getProperty("errorjson"), JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * abre automaticamente los archivos de clientes en JSON
     */
    public static void OpenAutoClient() {
        String PATH = null;
        client client = new client("");
        try {
            PATH = new java.io.File(".").getCanonicalPath() + "/src/modules/users/client/model/files/json/client.json";
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            XStream xstream = new XStream(new JettisonMappedXmlDriver());
            xstream.setMode(XStream.NO_REFERENCES);
            xstream.alias("Clients", client.class);

            singleton.userclient.clear();

            JsonReader lector = new JsonReader(new FileReader(PATH));
            JsonParser parseador = new JsonParser();
            JsonElement raiz = parseador.parse(lector);

            Gson json = new Gson();
            JsonArray lista = raiz.getAsJsonArray();
            for (JsonElement elemento : lista) {
                client = json.fromJson(elemento, client.class);
                singleton.userclient.add(client);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("openerrorjson"),
                    Language.getInstance().getProperty("errorjson"), JOptionPane.ERROR_MESSAGE);
        }
    }

}
