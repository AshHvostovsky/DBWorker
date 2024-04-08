package data;
import DB.DBWorker;
import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;

public class MyTableModel_v2 extends AbstractTableModel {
    private data.Group data;

    private static int boat = -1; //Если 1 - пароход, 2 - парусник, 3 - ледоход

    public MyTableModel_v2(data.Group group) {
        this.data = group;
    }

    @Override
    public int getRowCount() {
        int rowCount = 0;
        for (int i = 0; i < data.getCount(); i++) {
            Boat currentBoat = data.getBoat(i);
            switch (boat) {
                case 1:
                    if (currentBoat instanceof Steamboat) {
                        rowCount++;
                    }
                    break;
                case 2:
                    if (currentBoat instanceof Sailboat) {
                        rowCount++;
                    }
                    break;
                case 3:
                    if (currentBoat instanceof Icebreaker) {
                        rowCount++;
                    }
            }
        }
        return rowCount;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                data.getBoat(rowIndex).setName((String) aValue);
                break;
            case 1:
                Boat b1 = data.getBoat(rowIndex);
                if (b1 instanceof Icebreaker) {
                    ((Icebreaker) b1).setPower((int) aValue);
                }
                break;
            case 2:
                Boat b2 = data.getBoat(rowIndex);
                if (b2 instanceof Sailboat) {
                    ((Sailboat) b2).setNumber_of_sails((int) aValue);
                }
                break;
            case 3:
                Boat b3 = data.getBoat(rowIndex);
                if (b3 instanceof Steamboat) {
                    ((Steamboat) b3).setSpeed((int) aValue);
                }
                break;
        }

    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Название";
            case 1:
                return "id";
            case 2:
                switch (boat) {
                    case 1:
                        return "Тип топлива";
                    case 2:
                        return "Тип парусов";
                    case 3:
                        return "Мощность";
                }
            case 3:
                switch (boat) {
                    case 1:
                        return "Скорость";
                    case 2:
                        return "Количество парусов";
                    case 3:
                        return "Максимальная тольщина льда";
                }
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return data.getBoat(rowIndex).getName();
            case 1: {
                return data.getBoat(rowIndex).getId();
            }
            case 2: {
                switch (boat) {
                    case 1:
                        Boat b1 = data.getBoat(rowIndex);
                        if (b1 instanceof Steamboat)
                            return ((Steamboat) b1).getType_of_fuel();
                        else
                            return null;
                    case 2:
                        Boat b2 = data.getBoat(rowIndex);
                        if (b2 instanceof Sailboat)
                            return ((Sailboat) b2).getType_of_sails();
                        else
                            return null;
                    case 3:
                        Boat b3 = data.getBoat(rowIndex);
                        if (b3 instanceof Icebreaker)
                            return ((Icebreaker) b3).getPower();
                        else
                            return null;
                }

            }
            case 3: {
                switch (boat) {
                    case 1:
                        Boat b1 = data.getBoat(rowIndex);
                        if (b1 instanceof Steamboat)
                            return ((Steamboat) b1).getSpeed();
                        else
                            return null;
                    case 2:
                        Boat b2 = data.getBoat(rowIndex);
                        if (b2 instanceof Sailboat)
                            return ((Sailboat) b2).getNumber_of_sails();
                        else
                            return null;
                    case 3:
                        Boat b3 = data.getBoat(rowIndex);
                        if (b3 instanceof Icebreaker)
                            return ((Icebreaker) b3).getMax_thickness_of_ice();
                        else
                            return null ;
                }

            }

        }
        return "default";
    }

    public void delete(int ind) {
        this.data.remove(ind);
        fireTableDataChanged(); //Обновление таблицы после удаления
    }

    public void reload_table() {
        fireTableDataChanged();
    }
    public static void setBoat(int boatIN) {
        boat = boatIN;
    }


}