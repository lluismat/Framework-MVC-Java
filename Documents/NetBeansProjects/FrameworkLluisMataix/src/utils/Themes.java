package utils;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import modules.menu.model.Config;

public class Themes {

    /**
     * funcion para mostrar un tema segun el tema elegido
     */
	public static void themes() {

		try {
			switch (Config.getInstance().getTheme()) {
			case "Metal":
				// Metal
				UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
				break;
			case "Windows":
				// GTK - WINDOWS
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				break;
			case "Motif":
				// CDE/Motif
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
				break;
			case "Nimbus":
				UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
				break;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No pudo cargarse la apariencia deseada", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
