package classes;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;
import static classes.singletonMongo.*;

public class Mongo_DB {
   
   
    public Mongo_DB() {
        try {
            input = new FileInputStream("src/classes/mongo.properties"); 
            try {
                prop.load(input);
            } catch (Exception e) {
                System.out.println("Unable to open mongo.properties");
                e.printStackTrace();
            }
            machine = prop.getProperty("machine");
            port = prop.getProperty("port");
            nom_bd = prop.getProperty("db");
            nom_table = prop.getProperty("collection");      
        } catch (FileNotFoundException e) {
            System.out.println("Unable to open mongo.properties");
            e.printStackTrace();
        }finally {
            if (input != null) {
		try {
                    input.close();
		} catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Unable to close mongo.properties"); 
		}
            }
	}
    }
    
    public static Mongo connect() {
        try {
            client = new Mongo(getMachine(), Integer.parseInt(getPort()));
            db = client.getDB(getNom_bd());
            collection = db.getCollection(getNom_table());
        } catch (Exception e) {
            System.out.println("Unable to open client");
            e.printStackTrace();
            if (client != null) {
		try {
                    client.close();
		} catch (Exception ex) {
                    System.out.println("Unable to close client");
                    ex.printStackTrace();
		}
            }
	}
        return client;
    }
    
    public static void disconnect() {
	client.close();
    }

    public static Properties getProp() {
        return prop;
    }

    public static InputStream getInput() {
        return input;
    }

    public static Mongo getClient() {
        return client;
    }

    public static DB getDb() {
        return db;
    }

    public static DBCollection getCollection() {
        return collection;
    }

    public static void setProp(Properties prop) {
        prop = prop;
    }

    public static void setInput(InputStream input) {
        input = input;
    }

    public static void setClient(Mongo client) {
        client = client;
    }

    public static void setDb(DB db) {
        db = db;
    }

    public static void setCollection(DBCollection collection) {
        collection = collection;
    }

    public static String getNom_bd() {
        return nom_bd;
    }

    public static void setNom_bd(String nom_bd) {
        nom_bd = nom_bd;
    }

    public static String getNom_table() {
        return nom_table;
    }

    public static void setNom_table(String nom_table) {
        nom_table = nom_table;
    }

    public static String getMachine() {
        return machine;
    }

    public static String getPort() {
        return port;
    }

    public static void setMachine(String machine) {
        machine = machine;
    }

    public static void setPort(String port) {
        port = port;
    }
}
