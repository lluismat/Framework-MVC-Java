package classes;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import java.io.InputStream;
import java.util.Properties;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author lluis
 */
public class singletonMongo {

    public static Mongo_DB mongo=null;
    public static Properties prop = new Properties();
    public static InputStream input = null;
    public static String machine = null;
    public static String port = null;
    public static Mongo client = null;
    public static DB db = null;
    public static String nom_bd = null;
    public static DBCollection collection = null;
    public static String nom_table = null;

}
