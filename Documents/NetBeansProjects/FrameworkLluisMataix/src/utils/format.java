package utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import modules.Config.model.Config;

public class format {

	// Elegir el numero de decimales
	public static String formatdecimals(float number, int option) {
		String format = "";
		switch (option) {

		case 0:
			// 1 decimal
			DecimalFormat format1 = new DecimalFormat(".#");
			format = format1.format(number);
			break;
		case 1:
			// 2 decimals
			DecimalFormat format2 = new DecimalFormat(".##");
			format = format2.format(number);
			break;
		case 2:
			// 3 decimals
			DecimalFormat format3 = new DecimalFormat(".###");
			format = format3.format(number);
			break;
		}

		return format;
	}

	// Elegir formato de la moneda
	public static String formatcurrency(float coin, Config c) {

		String format = "";
		switch (c.getCurrency()) {
		case "€":
			// Euros
			NumberFormat euro = NumberFormat.getCurrencyInstance(Locale.FRANCE);
			format = euro.format(coin);

			break;
		case "$":
			// Dolar
			NumberFormat dolar = NumberFormat.getCurrencyInstance(Locale.US);
			format = dolar.format(coin * 1.08);
			break;
		case "£":
			// Pounds
			NumberFormat libra = NumberFormat.getCurrencyInstance(Locale.UK);
			format = libra.format(coin * 0.72);
			break;
		}
		return format;

	}

}
