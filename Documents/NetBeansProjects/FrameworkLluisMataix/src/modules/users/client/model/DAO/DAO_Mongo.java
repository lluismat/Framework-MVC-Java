/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.users.client.model.DAO;

import classes.Mongo_DB;
import classes.singletonMongo;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import modules.users.client.model.classes.client;
import modules.users.users.singleton;

/**
 *
 * @author lluis
 */
public class DAO_Mongo {
    
    public static void insert_Client() {
        
        singletonMongo.collection=Mongo_DB.getCollection();
        singletonMongo.collection.insert(singleton.client.client_to_Mongo());
    }
    /*
    public static void insert_Client() {
        for (int i = 0; i <= (singleton.userclient.size() - 1); i++) {
                
                singleton.client = singleton.userclient.get(i);
                singletonMongo.collection.insert(singleton.client.client_to_Mongo());
        }
        
    }
*/
    
    public static void viewTableClient() {
        DBCursor cursor = null;
        singleton.userclient.clear();
        client c = new client();
        try {
            cursor = singletonMongo.collection.find();
            if(cursor.count()!=0){
                while(cursor.hasNext()){
                    BasicDBObject document = (BasicDBObject) cursor.next();
                    singleton.client = c.Mongo_to_client(document);
                    singleton.userclient.add(singleton.client);
                    
                }
            }else{
                System.out.println("NOT DATA"); 
            }
        } finally {
            if (cursor != null){
		cursor.close();
            }
	}
    }
   
    
    public static void deleteClient_by_dni() {
        singletonMongo.collection.remove(new BasicDBObject().append("dni",singleton.client.getDni()));
    }
   
    public static void updateClient(){
        

        BasicDBObject updateClient = new BasicDBObject();

        updateClient.append("$set",singleton.client.client_to_Mongo());
        
        //Busca el/los registro/s con el dni indicado
        BasicDBObject searchByDni = new BasicDBObject();
        searchByDni.append("dni", singleton.client.getDni());
 
        //Realiza la actualización
       singletonMongo.collection.updateMulti(searchByDni, updateClient);
       
    }
}
    

