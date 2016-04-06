/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.users.userreg.model.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
public class json_Userreg {
    
    public static void SaveUserreg() {
        String PATH = null;
        try {
            XStream xstreamjson = new XStream(new JettisonMappedXmlDriver());
            xstreamjson.setMode(XStream.NO_REFERENCES);
            xstreamjson.alias("Registered_user", registered_user.class);

            JFileChooser fileChooser = new JFileChooser();

            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JSON (*.json)", "json"));

            int seleccion = fileChooser.showSaveDialog(null);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                File JFC = fileChooser.getSelectedFile();
                PATH = JFC.getAbsolutePath();
                PATH = PATH + ".json";

                Gson gson = new Gson();
                String json = gson.toJson(singleton.userreg);
                FileWriter fileXml = new FileWriter(PATH);
                fileXml.write(json.toString());
                fileXml.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("saveerrorjson"),
                    Language.getInstance().getProperty("errorjson"), JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void OpenUserreg() {
        String PATH = null;
        registered_user userreg = new registered_user("");

        try {
            XStream xstream = new XStream(new JettisonMappedXmlDriver());
            xstream.setMode(XStream.NO_REFERENCES);
            xstream.alias("Registered_user", registered_user.class);

            JFileChooser fileChooser = new JFileChooser();

            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JSON (*.json)", "json"));

            int seleccion = fileChooser.showOpenDialog(null);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                File JFC = fileChooser.getSelectedFile();
                PATH = JFC.getAbsolutePath();

                singleton.userreg.clear();

                JsonReader lector = new JsonReader(new FileReader(PATH));
                JsonParser parseador = new JsonParser();
                JsonElement raiz = parseador.parse(lector);

                Gson json = new Gson();
                JsonArray lista = raiz.getAsJsonArray();
                for (JsonElement elemento : lista) {
                    userreg = json.fromJson(elemento, registered_user.class);
                    singleton.userreg.add(userreg);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("openerrorjson"),
                    Language.getInstance().getProperty("errorjson"), JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void SaveAutoUserreg() {
        String PATH = null;
        try {
            PATH = new java.io.File(".").getCanonicalPath()
                    + "/src/modules/users/userreg/model/files/json/json.json";
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            XStream xstreamjson = new XStream(new JettisonMappedXmlDriver());
            xstreamjson.setMode(XStream.NO_REFERENCES);
            xstreamjson.alias("Registered_user", registered_user.class);

            Gson gson = new Gson();
            String json = gson.toJson(singleton.userreg);
            FileWriter fileXml = new FileWriter(PATH);
            fileXml.write(json.toString());
            fileXml.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("saveerrorjson"),
                    Language.getInstance().getProperty("errorjson"), JOptionPane.ERROR_MESSAGE);
        }

    }

    public static void OpenAutoUserreg() {
        String PATH = null;
        registered_user userreg = new registered_user("");
        try {
            PATH = new java.io.File(".").getCanonicalPath()
                    + "/src/modules/users/userreg/model/files/json/json.json";
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            XStream xstream = new XStream(new JettisonMappedXmlDriver());
            xstream.setMode(XStream.NO_REFERENCES);
            xstream.alias("Registered_user", registered_user.class);

            singleton.userreg.clear();

            JsonReader lector = new JsonReader(new FileReader(PATH));
            JsonParser parseador = new JsonParser();
            JsonElement raiz = parseador.parse(lector);

            Gson json = new Gson();
            JsonArray lista = raiz.getAsJsonArray();
            for (JsonElement elemento : lista) {
                userreg = json.fromJson(elemento, registered_user.class);
                singleton.userreg.add(userreg);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("openerrorjson"),
                    Language.getInstance().getProperty("errorjson"), JOptionPane.ERROR_MESSAGE);
        }
    }

}
