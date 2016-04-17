/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.menu.model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author lluis
 */
public class files_Config {
    
    public static void SaveConfig() {
        String PATH = null;
        try {
            PATH = new java.io.File(".").getCanonicalPath() + "/src/modules/menu/model/ConfigFiles/config.json";
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            XStream xstreamjson = new XStream(new JettisonMappedXmlDriver());
            xstreamjson.setMode(XStream.NO_REFERENCES);
            xstreamjson.alias("Config", Config.class);

            Gson gson = new Gson();
            String json = gson.toJson(Config.getInstance());
            FileWriter fileXml = new FileWriter(PATH);
            fileXml.write(json.toString());
            fileXml.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("saveerrorjson"),
                    Language.getInstance().getProperty("errorjson"), JOptionPane.ERROR_MESSAGE);
        }

    }

    public static void OpenConfig() {
        String PATH = null;
        Config c = null;
        try {
            PATH = new java.io.File(".").getCanonicalPath() + "/src/modules/menu/model/ConfigFiles/config.json";
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            XStream xstream = new XStream(new JettisonMappedXmlDriver());
            xstream.setMode(XStream.NO_REFERENCES);
            xstream.alias("Config", Config.class);

            JsonReader lector = new JsonReader(new FileReader(PATH));
            JsonParser parseador = new JsonParser();
            JsonElement raiz = parseador.parse(lector);

            Gson json = new Gson();
            c = json.fromJson(raiz, Config.class);
            Config.getInstance().setCurrency(c.getCurrency());
            Config.getInstance().setDecimals(c.getDecimals());
            Config.getInstance().setFiles(c.getFiles());
            Config.getInstance().setFormatDate(c.getFormatDate());
            Config.getInstance().setLanguage(c.getLanguage());
            Config.getInstance().setTheme(c.getTheme());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, Language.getInstance().getProperty("openerrorjson"),
                    Language.getInstance().getProperty("errorjson"), JOptionPane.ERROR_MESSAGE);
        }
    }

}
