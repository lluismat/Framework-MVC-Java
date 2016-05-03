package modules.users.client.model.classes;

import modules.users.users.singleton;
import classes.date;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.table.AbstractTableModel;
import modules.menu.model.Config;
import static modules.users.client.controller.controller_Client.combo;
import modules.users.client.model.BLL.BLL_Mongo;
import modules.users.client.model.utils.pagina_client;
import modules.users.client.view.Client;
import utils.format;

public class miniSimpleTableModel_Client extends AbstractTableModel {

    public static ArrayList<client> dates1 = new ArrayList<client>();
    public static ArrayList<client> auxdates1 = new ArrayList<client>();
    String[] columnas = {"DNI", "Nombre", "Apellido", "Fecha de Alta", "Compras"};

    ////////////////////estos métodos son necesarios para que jtable funcione/////////////////////
    @Override
    public String getColumnName(int col) {
        return columnas[col].toString();
    }

    /**
     * devuelve el numero de fial
     * @return 
     */
    @Override
    public int getRowCount() {
        return dates1.size();
    }

    /**
     * devuelve el numero de columnas
     * @return 
     */
    @Override
    public int getColumnCount() {
        return columnas.length;
    }


    /**
     * Devuelve el valor del objeto en la fila y columna
     * @param row
     * @param col
     * @return 
     */
    @Override
    public Object getValueAt(int row, int col) {

        Object dev = null;
        client fila = (client) dates1.get(row);

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
                dev = fila.getDischarge_date().toString(Config.getInstance().getFormatDate());
                break;

            case 4:
                dev = format.formatcurrency(fila.getPurchase());
                break;

        }
        return dev;
    }

    
    /**
     * Determina si una fila y columna ha de ser editable
     * @param row
     * @param col
     * @return 
     */
    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    
    /**
     * Actualiza un objeto de una fila y columna
     * @param value
     * @param row
     * @param col 
     */
    @Override
    public void setValueAt(Object value, int row, int col) {
        client fila = (client) dates1.get(row);

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
                fila.setDischarge_date(new date(value.toString()));
                break;

            case 4:
                fila.setPurchase((Float) value);
                break;

        }
        fireTableCellUpdated(row, col);
    }

    /**
     * añade un fila
     * @param usu 
     */
    public void addRow(client usu) {
        dates1.add(usu);
        fireTableDataChanged();
    }

    /**
     * elimina una fila
     * @param fila 
     */
    public void removeRow(int fila) {
        dates1.remove(fila);
        fireTableDataChanged();
    }

    /**
     * carga en el jTable los clientes de mongoDB
     */
    public void load() {
        dates1.clear();
        auxdates1.clear();
        BLL_Mongo.viewClient();

        //client client = null;
        for (int i = 0; i <= (singleton.userclient.size() - 1); i++) {
            singleton.client = singleton.userclient.get(i);
            addRow(singleton.client);
            auxdates1.add(singleton.client);
            try {
                Thread.sleep(1); //1 milliseconds
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        
    }
    
    /**
     * filtra los clientes por nombre
     * 
     */
    public void filtrar() {
        dates1.clear();
        int cont=0;
        
        String nom=(String) ((JComboBox)combo).getSelectedItem();   
        if(nom!=null){
            for(int i=0;i<auxdates1.size();i++) {
                //if(datosaux.get(i).getFirst_name().contains(nom)){
                if(auxdates1.get(i).getName().toLowerCase().startsWith(nom.toLowerCase())){
                    addRow(auxdates1.get(i));
                    cont++;
                }
            }
            Client.jLabel3.setText(String.valueOf(cont));
            System.out.println("word selected: " + nom);
            pagina_client.initLinkBox();
        }
    }

    /**
     * 
     * @param u
     * @return 
     */
    public client buscar(String u) {
        dates1.clear();
        load();

        String res;
        for (int i = 0; i < dates1.size(); i++) {
            res = dates1.get(i).toString();
            if (res.contains(u)) {
                return dates1.get(i);
            }
        }
        return null;
    }

    /**
     * 
     * @param u
     * @return 
     */
    public int buscaUsuario(client u) {
        dates1.clear();
        load();

        for (int i = 0; i < dates1.size(); i++) {
            if (dates1.get(i).equals(u)) {
                return i;
            }
        }
        return -1;
    }
   
}
