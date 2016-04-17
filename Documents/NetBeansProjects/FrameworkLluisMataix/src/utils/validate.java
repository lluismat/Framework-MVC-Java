package utils;

import modules.menu.model.Config;

public class validate {

	// expresiones regulares
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

	// validate dni

	public static boolean validate_dni(String DNI) {
		String s = "[0-9]{8}[a-zA-Z]";
		String letters = "TRWAGMYFPDXBNJZSQVHLCKET";
		boolean result = true;

		result = DNI.matches(s);

		if (result) {
			int module = (Integer.parseInt(DNI.substring(0, 8))) % 23;
			char letra = letters.charAt(module);

			if (letra != DNI.substring(8).toUpperCase().charAt(0))
				result = false;
		}

		return result;
	}

	// validate fechas
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

	// validate nombre

	public static boolean validate_name(String name) {
		return name.matches(name_expresion);
	}

	// validate surname
	public static boolean validate_surname(String surname) {
		return surname.matches(surname_expresion);
	}

	// validate mobile
	public static boolean validate_mobile(String mobile) {
		return mobile.matches(mobile_expresion);
	}

	// validate email
	public static boolean validate_email(String email) {
		return email.matches(email_expresion);
	}

	// validate user
	public static boolean validate_user(String user) {
		return user.matches(user_expresion);
	}

	// validate password
	public static boolean validate_password(String password) {
		return password.matches(password_expresion);
	}

	// validate avatar
	public static boolean validate_avatar(String avatar) {
		return avatar.matches(avatar_expresion);
	}

	// validate purchase
	public static boolean validate_purchase(String purchase) {
		return purchase.matches(purchase_expresion);
	}

	// validate activity
	public static boolean validate_activity(String activity) {
		return activity.matches(activity_expresion);
	}

	// validate client_type
	public static boolean validate_client_type(String client_type) {
		return client_type.matches(client_type_expresion);
	}

}
