package modules.users.admin.model.classes;

import modules.users.users.singleton;
import classes.date;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.table.AbstractTableModel;
import static modules.users.admin.controller.controller_Admin.combo;
import modules.users.admin.model.classes.admin;
import modules.users.admin.model.utils.pagina;
import modules.users.admin.view.Admin;
import utils.format;
import modules.Config.model.Config;
import modules.users.admin.model.BLL.BLL_BBDD;

public class miniSimpleTableModel_Admin extends AbstractTableModel {

    public static ArrayList<admin> dates = new ArrayList<admin>();
    public static ArrayList<admin> auxdates = new ArrayList<admin>();
    String[] columnas = {"DNI", "Nombre", "Apellido", "Fecha de Contratacion", "Salario"};

    ////////////////////estos métodos son necesarios para que jtable funcione/////////////////////
    @Override
    public String getColumnName(int col) {
        return columnas[col].toString();
    }

    //Devuelve el numero de filas
    @Override
    public int getRowCount() {
        return dates.size();
    }

    //Devuelve el numero de columnas
    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    //Devuelve el valor del objeto en la fila y columna
    @Override
    public Object getValueAt(int row, int col) {

        Object dev = null;
        admin fila = (admin) dates.get(row);

        switch (col) {
            case 0:
                dev = fila.getDni();
                break;

            case 1:
                dev = fila.getName();
                break;

            case 2:
                dev = fila.getSurname();
                break;

            case 3:
                
                dev = fila.getHiring_date().toString(Config.getInstance().getFormatDate());
                break;

            case 4:
                dev = format.formatcurrency(fila.getSalary(),Config.getInstance());
                break;

        }
        return dev;
    }

    //Determina si una fila y columna ha de ser editable
    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    //Actualiza un objeto de una fila y columna
    @Override
    public void setValueAt(Object value, int row, int col) {
        admin fila = (admin) dates.get(row);

        switch (col) {
            case 0:
                fila.setDni(value.toString());
                break;

            case 1:
                fila.setName(value.toString());
                break;

            case 2:
                fila.setSurname(value.toString());
                break;

            case 3:
                fila.setHiring_date(new date(value.toString()));
                break;

            case 4:
                fila.setSalary((Float) value);
                break;

        }
        fireTableCellUpdated(row, col);
    }

    public void addRow(admin usu) {
        dates.add(usu);
        fireTableDataChanged();
    }

    public void removeRow(int fila) {
        dates.remove(fila);
        fireTableDataChanged();
        //Admin.jLabel3.setText(String.valueOf(String.valueOf(dates.size())));
        //pagina.initLinkBox();
        
    }

    public void load() {
        dates.clear();
        auxdates.clear();
        BLL_BBDD.ViewAdmin();

        admin admin = null;
        for (int i = 0; i <= (singleton.useradmin.size() - 1); i++) {
            admin = singleton.useradmin.get(i);
            addRow(admin);
            auxdates.add(admin);

            try {
                Thread.sleep(1); //1 milliseconds
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void filtrar() {
        dates.clear();
        int cont = 0;

        String nom = (String) ((JComboBox) combo).getSelectedItem();
        if (nom != null) {
            for (int i = 0; i < auxdates.size(); i++) {
                //if(datosaux.get(i).getFirst_name().contains(nom)){
                if (auxdates.get(i).getName().toLowerCase().startsWith(nom.toLowerCase())) {
                    addRow(auxdates.get(i));
                    cont++;
                }
            }
            Admin.jLabel3.setText(String.valueOf(cont));
            System.out.println("word selected: " + nom);
            pagina.initLinkBox();
        }
    }

    public admin buscar(String u) {
        dates.clear();
        load();

        String res;
        for (int i = 0; i < dates.size(); i++) {
            res = dates.get(i).toString();
            if (res.contains(u)) {
                return dates.get(i);
            }
        }
        return null;
    }

    public int buscaUsuario(admin u) {
        dates.clear();
        load();

        for (int i = 0; i < dates.size(); i++) {
            if (dates.get(i).equals(u)) {
                return i;
            }
        }
        return -1;
    }

}
