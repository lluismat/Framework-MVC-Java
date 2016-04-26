/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.users.client.model.BLL;

import classes.Mongo_DB;
import classes.singletonMongo;
import modules.users.client.model.DAO.DAO_Mongo;

/**
 *
 * @author lluis
 */
public class BLL_Mongo {
    
    public static void insert_Client(){
        singletonMongo.client=Mongo_DB.connect();
        DAO_Mongo.insert_Client();
        Mongo_DB.disconnect();
    }
    
    public static void viewClient(){
        singletonMongo.client=Mongo_DB.connect();
        DAO_Mongo.viewTableClient();
        Mongo_DB.disconnect();
    }
    
    public static void updateClient(){
        singletonMongo.client=Mongo_DB.connect();
        DAO_Mongo.updateClient();
        Mongo_DB.disconnect();
    }
    public static void deleteClient(){
        singletonMongo.client=Mongo_DB.connect();
        DAO_Mongo.deleteClient_by_dni();
        Mongo_DB.disconnect();
    }
}
