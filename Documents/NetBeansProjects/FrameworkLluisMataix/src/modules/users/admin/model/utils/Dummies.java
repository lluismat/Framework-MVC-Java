package modules.users.admin.model.utils;

import classes.date;
import modules.users.admin.model.classes.admin;
import modules.users.users.singleton;

public class Dummies {

	public static String dni[] = { "21695921K", "70674946V", "72259145E", "07261935F", "48604856Z", "48294155C",
			"00000005M", "73099467Q", "23261935F", "48604356Z", "20099467Q", "73125345M", "20294155C", "23561935Q",
			"73604356F", "21099767Y", "20125345V", "73294155Z", "12345678Z", "20457259R" };// 20

	public static String name[] = { "Lluis", "Vicent", "Joan", "Josep", "Sara", "Jaume", "Sergio", "Paco", "Hector",
			"Rober", "Alicia", "David", "Miguel", "Lara", "Blanca", "Ismael", "Jordi", "Carlos", "Javi", "Elena" };// 20

	public static String[] surname = { "Mataix", "Mu�oz", "Sanchez", "Tormo", "Gandia", "Puerto", "Torres", "Calabuig",
			"Vega", "Diaz", "Santonja", "Crespo", "Garcia", "Molina", "Domenech", "Pascual", "Martinez", "Ramos", // 20
			"Campos", "Rivera" };

	public static String mobile[] = { "656197251", "651598745", "659874523", "651235944", "643589175", "656893210",
			"685110895", "657905189", "656304110", "689457568", "686120255", "689212351", "646239992", "632587786",
			"698145578", "568561549", "655325684", "656905233", "565509881", "869452332" };// 20

	public static String email[] = { "mataix.lluis@gmail.com", "vincentSanchez@gmail.com", "Joan@gmail.com",
			"Josep@gmail.com", "Sara@gmail.com", "Jaume@gmail.com", "Sergio@gmail.com", "Paco@gmail.com",
			"Hector@gmail.com", "Rober@gmail.com", "Alicia@gmail.com", "David@gmail.com", "Miguel@gmail.com",
			"Lara@gmail.com", "Blanca@gmail.com", "Ismael@gmail.com", "Jordi@gmail.com", "Carlos@gmail.com",
			"Javi@gmail.com", "Elena@gmail.com" };

	public static String user[] = { "Lluis", "Vicent", "Joan", "Josep", "Sara", "Jaume", "Sergio", "Paco", "Hector",
			"Rober", "Alicia", "David", "Miguel", "Lara", "Blanca", "Ismael", "Jordi", "Carlos", "Javi", "Elena" };
	public static String password[] = { "65612", "6584", "659823", "6344", "64917", "6320", "68515", "65789", "6510",
			"68758", "68625", "12351", "6992", "6876", "69578", "565619", "65684", "6233", "50281", "80232" };
	public static String avatar[] = { "Mataix", "Mu�oz", "Sanchez", "Tormo", "Gandia", "Puerto", "Torres", "Calabuig",
			"Vega", "Diaz", "Santonja", "Crespo", "Garcia", "Molina", "Domenech", "Pascual", "Martinez", "Ramos", // 20
			"Campos", "Rivera" };
	public static String state[] = { "Conected", "No Conected", "Conected", "No Conected", "Conected", "No Conected",
			"Conected", "No Conected", "Conected", "No Conected", "Conected", "Conected", "Conected", "Conected",
			"Conected", "Conected", "Conected", "Conected", "Conected", "Conected" };
	public static date date_birthday[] = { new date("15/04/1994"), new date("11/05/1994"), new date("21/11/1993"),
			new date("15/09/1995"), new date("08/01/1992"), new date("19/12/1993"), new date("26/10/1990"),
			new date("15/11/1996"), new date("13/02/1994"), new date("29/01/1991"), new date("05/01/1996"),
			new date("29/12/1994"), new date("03/05/1996"), new date("17/10/1992"), new date("14/08/1989"),
			new date("29/01/1991"), new date("07/04/1994"), new date("07/03/1994"), new date("10/03/1992"),
			new date("11/12/1996") };
	// admin y user_reg
	public static int activity[] = { 10, 20, 30, 54, 65, 32, 21, 35, 45, 50, 31, 45, 65, 42, 10, 40, 42, 39, 37, 47 };
	// admin
	public static date hiring_date[] = { new date("15/04/2012"), new date("11/05/2011"), new date("21/11/2013"),
			new date("15/09/2010"), new date("08/01/2011"), new date("19/12/2011"), new date("26/10/2010"),
			new date("15/11/2013"), new date("13/02/2012"), new date("29/01/2012"), new date("05/01/2012"),
			new date("29/12/2012"), new date("03/05/2012"), new date("17/11/2012"), new date("14/08/2012"),
			new date("29/01/2012"), new date("07/04/2012"), new date("07/03/2012"), new date("10/03/2012"),
			new date("11/12/2012") };

	// client
	public static float purchases[] = { 1500, 2300, 1359, 953, 3015, 358, 168, 54, 785, 1879, 230, 20, 35, 45, 2000,
			1233, 156, 987, 99, 208 };
	public static String client_type[] = { "user1", "user2", "user3", "user1", "user2", "user3", "user1", "user2",
			"user3", "user1", "user2", "user3", "user1", "user2", "user3", "user1", "user2", "user3", "user1",
			"user2" };
	public static date discharge_date[] = { new date("15/04/2012"), new date("11/05/2011"), new date("21/11/2013"),
			new date("15/09/2010"), new date("08/01/2011"), new date("19/12/2011"), new date("26/10/2010"),
			new date("15/11/2013"), new date("13/02/2012"), new date("29/01/2012"), new date("05/01/2012"),
			new date("29/12/2012"), new date("03/05/2012"), new date("17/11/2012"), new date("14/08/2012"),
			new date("29/01/2012"), new date("07/04/2012"), new date("07/03/2012"), new date("10/03/2012"),
			new date("11/12/2012") };
	public static String premium[] = { "yes", "No", "yes", "No", "yes", "yes", "No", "No", "yes", "yes", "No", "No",
			"yes", "yes", "No", "No", "No", "yes", "No", "No" };

	public static void LoadDummies() {
		int dummies = 20;
		admin admin = null;
		
		for (int i = 0; i < dummies; i++) {
			admin = new admin(dni[i], name[i], surname[i], mobile[i], email[i], user[i], password[i], avatar[i],
					state[i], date_birthday[i], activity[i], hiring_date[i]);
			singleton.useradmin.add(admin);
		}
	}
}
