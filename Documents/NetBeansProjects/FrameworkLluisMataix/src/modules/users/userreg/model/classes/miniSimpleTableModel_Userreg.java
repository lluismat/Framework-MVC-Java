package modules.users.userreg.model.classes;


import modules.users.users.singleton;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.table.AbstractTableModel;
import modules.users.admin.model.utils.pagina;
import static modules.users.userreg.controller.controller_Userreg.combo;
import modules.users.userreg.model.utils.pagina_Userreg;
import modules.users.userreg.view.UserReg;

public class miniSimpleTableModel_Userreg extends AbstractTableModel {

    public static ArrayList<registered_user> dates = new ArrayList<registered_user>();
    public static ArrayList<registered_user> auxdates = new ArrayList<registered_user>();
    String[] columnas = {"DNI", "Nombre", "Apellido", "Karma", "Points"};

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
        registered_user fila = (registered_user) dates.get(row);

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
                dev = fila.getKarma().toString();
                break;

            case 4:
                dev = fila.getBenefits();
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
        registered_user fila = (registered_user) dates.get(row);

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
                fila.setKarma(value.toString());
                break;

            case 4:
                fila.setBenefits((float)value);
                break;

        }
        fireTableCellUpdated(row, col);
    }

    public void addRow(registered_user usu) {
        dates.add(usu);
        fireTableDataChanged();
    }

    public void removeRow(int fila) {
        dates.remove(fila-1);
        fireTableDataChanged();
        UserReg.jLabel3.setText(String.valueOf(String.valueOf(dates.size())));
        pagina.initLinkBox();
        
    }

    public void load() {
        dates.clear();
        auxdates.clear();

        registered_user userreg = null;
        for (int i = 0; i <= (singleton.userreg.size() - 1); i++) {
            userreg = singleton.userreg.get(i);
            addRow(userreg);
            auxdates.add(userreg);

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
            UserReg.jLabel3.setText(String.valueOf(cont));
            System.out.println("word selected: " + nom);
            pagina_Userreg.initLinkBox();
        }
    }

    public registered_user buscar(String u) {
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

    public int buscaUsuario(registered_user u) {
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
