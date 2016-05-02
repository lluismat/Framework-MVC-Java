package utils;

import modules.menu.model.Config;

public class validate {

    /**
     * funciones regulares
     */
    private static final String name_expresion = "[a-zA-Z��\\s]*$";
    private static final String surname_expresion = "[a-zA-Z��\\s]*$";
    private static final String mobile_expresion = "[0-9]{9}";
    private static final String email_expresion = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String user_expresion = "[a-zA-Z��0-9\\s]{4,}";
    private static final String password_expresion = "[a-zA-Z0-9\\s.]{4,}";
    private static final String avatar_expresion = "[a-zA-Z��\\s]*$";
    private static final String date1 = "([0-9]{2})/([0-9]{2})/([0-9]{4})";
    private static final String date2 = "([0-9]{2})-([0-9]{2})-([0-9]{4})";
    private static final String date3 = "([0-9]{4})/([0-9]{2})/([0-9]{2})";
    private static final String date4 = "([0-9]{4})-([0-9]{2})-([0-9]{2})";
    private static final String purchase_expresion = "^[0-9]+(\\.[0-9]{0,2})*$";
    private static final String activity_expresion = "^[0-9]*$";
    private static final String client_type_expresion = "[a-zA-Z��\\s]*$";

    /**
     * valida el dni
     *
     * @param DNI
     * @return
     */
    public static boolean validate_dni(String DNI) {
        String s = "[0-9]{8}[a-zA-Z]";
        String letters = "TRWAGMYFPDXBNJZSQVHLCKET";
        boolean result = true;

        result = DNI.matches(s);

        if (result) {
            int module = (Integer.parseInt(DNI.substring(0, 8))) % 23;
            char letra = letters.charAt(module);

            if (letra != DNI.substring(8).toUpperCase().charAt(0)) {
                result = false;
            }
        }

        return result;
    }

    /**
     * valida las fechas
     * @param date
     * @return 
     */
    public static boolean validate_dateformat(String date) {
        String datesformat = "";
        switch (Config.getInstance().getFormatDate()) {
            case "dd/mm/yyyy":
                datesformat = date1;
                break;
            case "dd-mm-yyyy":
                datesformat = date2;
                break;
            case "yyyy/mm/dd":
                datesformat = date3;
                break;
            case "yyyy-mm-dd":
                datesformat = date4;
                break;
        }
        return date.matches(datesformat);
    }

    /**
     * valida el nombre
     * @param name
     * @return 
     */
    public static boolean validate_name(String name) {
        return name.matches(name_expresion);
    }

    /**
     * valida el apellido
     * @param surname
     * @return 
     */
    public static boolean validate_surname(String surname) {
        return surname.matches(surname_expresion);
    }

    /**
     * valida el numero de mobil
     * @param mobile
     * @return 
     */
    public static boolean validate_mobile(String mobile) {
        return mobile.matches(mobile_expresion);
    }

    /**
     * valida el email
     * @param email
     * @return 
     */
    public static boolean validate_email(String email) {
        return email.matches(email_expresion);
    }

    /**
     * valida el nombre de usuario
     * @param user
     * @return 
     */
    public static boolean validate_user(String user) {
        return user.matches(user_expresion);
    }

    /**
     * valida la contraseña
     * @param password
     * @return 
     */
    public static boolean validate_password(String password) {
        return password.matches(password_expresion);
    }

    /**
     * valida el avatar
     * @param avatar
     * @return 
     */
    public static boolean validate_avatar(String avatar) {
        return avatar.matches(avatar_expresion);
    }

    /**
     * valida las compras
     * @param purchase
     * @return 
     */
    public static boolean validate_purchase(String purchase) {
        return purchase.matches(purchase_expresion);
    }

    /**
     * valida la actividad
     * @param activity
     * @return 
     */
    public static boolean validate_activity(String activity) {
        return activity.matches(activity_expresion);
    }

    /**
     * valida el client type
     * @param client_type
     * @return 
     */
    public static boolean validate_client_type(String client_type) {
        return client_type.matches(client_type_expresion);
    }

}
