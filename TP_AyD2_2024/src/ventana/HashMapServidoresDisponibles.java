package ventana;

import java.util.HashMap;

import javax.swing.table.AbstractTableModel;

public class HashMapServidoresDisponibles extends AbstractTableModel {

    private HashMap<Integer, Boolean> data;
    private String[] columnNames = {"Puerto del Servidor", "¿Es el servidor principal?"};

    public HashMapServidoresDisponibles(HashMap<Integer, Boolean> data) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Integer key = (Integer) data.keySet().toArray()[rowIndex];
        if (columnIndex == 0) {
            return key;
        } else if (columnIndex == 1) {
            return data.get(key);
        }
        return null;
    }
    
    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
        fireTableStructureChanged(); // Notificar a la tabla sobre el cambio en la estructura
    }
}

