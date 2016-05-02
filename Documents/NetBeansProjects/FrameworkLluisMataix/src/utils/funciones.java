package utils;

import javax.swing.JOptionPane;

public class funciones {

    /**
     * funcion pideint
     *
     * @param mensaje
     * @param titulo
     * @return
     */
    public static int pideint(String mensaje, String titulo) {

        int a = 0;
        String s = "";
        boolean correcto = true;

        do {
            try {
                s = JOptionPane.showInputDialog(null, mensaje, titulo,
                        JOptionPane.QUESTION_MESSAGE);
                if (s == null) {
                    // JOptionPane.showMessageDialog(null,
                    // "Saliendo de la aplicaci�n", "Saliendo",
                    // JOptionPane.INFORMATION_MESSAGE);
                } else {
                    a = Integer.parseInt(s);
                    correcto = true;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "No has introducido un num int", "Error",
                        JOptionPane.ERROR_MESSAGE);
                correcto = false;
            }
        } while (correcto == false);

        return a;
    }

    /**
     * funcion pidechar
     * @param mensaje
     * @param titulo
     * @return 
     */
    public static char pidechar(String mensaje, String titulo) {

        char c = 0;
        String s = "";
        boolean correcto = true;

        do {
            try {
                s = JOptionPane.showInputDialog(null, mensaje, titulo,
                        JOptionPane.QUESTION_MESSAGE);
                if (s == null) {
                    // JOptionPane.showMessageDialog(null,
                    // "Saliendo de la aplicaci�n", "Saliendo",
                    // JOptionPane.INFORMATION_MESSAGE);
                } else {
                    c = s.charAt(0);
                    correcto = true;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "No has introducido una letra", "Error",
                        JOptionPane.ERROR_MESSAGE);
                correcto = false;
            }
        } while (correcto == false);

        return c;
    }

    
    /**
     * funcion pidefloat
     * @param mensaje
     * @param titulo
     * @return 
     */
    public static float pidefloat(String mensaje, String titulo) {

        float a = 0.0f;
        String s = "";
        boolean correcto = true;

        do {
            try {
                s = JOptionPane.showInputDialog(null, mensaje, titulo,
                        JOptionPane.QUESTION_MESSAGE);
                if (s == null) {
                    // JOptionPane.showMessageDialog(null,
                    // "Saliendo de la aplicaci�n", "Saliendo",
                    // JOptionPane.INFORMATION_MESSAGE);
                } else {
                    a = Float.parseFloat(s);
                    correcto = true;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "No has introducido un num float", "Error",
                        JOptionPane.ERROR_MESSAGE);
                correcto = false;
            }
        } while (correcto == false);
        return a;
    }

    /**
     * funcion pide s/n botones
     * @param mensaje
     * @return 
     */
    public static int confirmado(String mensaje) {

        int confirmado = JOptionPane.showConfirmDialog(null, mensaje);

        return confirmado;
    }

    /**
     * funcion pidestring
     * @param mensaje
     * @param titulo
     * @return 
     */
    public static String pidestring(String mensaje, String titulo) {
        String s = "";
        boolean correcto = true;

        do {
            try {
                s = JOptionPane.showInputDialog(null, mensaje, titulo,
                        JOptionPane.QUESTION_MESSAGE);
                correcto = true;
                if (s == null) {
                    // JOptionPane.showMessageDialog(null,
                    // "Saliendo de la aplicaci�n", "Saliendo",
                    // JOptionPane.INFORMATION_MESSAGE);
                }
                if (s.equals("")) {
                    JOptionPane.showMessageDialog(null,
                            "Error de introducci�n de datos", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    correcto = false;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "No has introducido una cadena", "Error",
                        JOptionPane.ERROR_MESSAGE);
                correcto = false;
            }
        } while (correcto == false);

        return s;
    }

}
