package data;

import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.List;

public class TableModelFlight extends AbstractTableModel {
    private data.GroupFlight data;

    public TableModelFlight(data.GroupFlight group) {
        this.data = group;
    }


    @Override
    public int getRowCount() {
        int rowCount = 0;
        for (int i = 0; i < data.getCount(); i++) {
            rowCount++;
        }
        return rowCount;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Flight flight = data.getFlight(rowIndex);
        switch (columnIndex) {
            case 0:
                return flight.getId();
            case 1:
                return flight.getDeparting();
            case 2:
                return flight.getArrival();
            case 3:
                return flight.getBoat_id();
        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Id";
            case 1:
                return "Отправление";
            case 2:
                return "Прибытие";
            case 3:
                return "Id корабля";
        }
        return "";
    }

    public Flight getFlight(int selectedRow) {
        return data.getFlight(selectedRow);
    }
    public void reload_table() {
        fireTableDataChanged();
    }
    public void delete(int ind) throws SQLException {
        this.data.remove(ind);
        fireTableDataChanged(); //Обновление таблицы после удаления
    }
}
