package modules.users.users;

import java.util.ArrayList;
import modules.users.admin.model.classes.admin;
import modules.users.client.model.classes.client;
import modules.users.userreg.model.classes.registered_user;

public class singleton {

    public static ArrayList<admin> useradmin;
    public static ArrayList<client> userclient;
    public static ArrayList<registered_user> userreg;
    public static admin admin;
    public static client client;
    public static registered_user registered_user;
    public static String DNI;

}
