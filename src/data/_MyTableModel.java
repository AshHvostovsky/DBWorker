package data;
import javax.swing.table.AbstractTableModel;
public class _MyTableModel extends AbstractTableModel {
    private data.Group data;
    private static int index = -1;

    public _MyTableModel(data.Group group) {
        this.data = group;
    }

    @Override
    public int getRowCount() {
        if (index != -1)
            return 1;
        return data.getCount();
    }

    @Override
    public int getColumnCount() {
        return 8;
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
                return "Тип топлива";
            case 3:
                return "Скорость";
            case 4:
                return "Тип парусов";
            case 5:
                return "Количество парусов";
            case 6:
                return "Мощность";
            case 7: return "Максимальная тольщина льда";
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
                Boat b = data.getBoat(rowIndex);
                if (b instanceof Steamboat) {
                    return ((Steamboat) b).getType_of_fuel();
                } else {
                    return "-";
                }
            }
            case 3: {
                Boat b = data.getBoat(rowIndex);
                if (b instanceof Steamboat) {
                    return ((Steamboat) b).getSpeed();
                } else {
                    return "-";
                }
            }
            case 4: {
                Boat b = data.getBoat(rowIndex);
                if (b instanceof Sailboat) {
                    return ((Sailboat) b).getType_of_sails();
                } else {
                    return "-";
                }
            }
            case 5: {
                Boat b = data.getBoat(rowIndex);
                if (b instanceof Sailboat) {
                    return ((Sailboat) b).getNumber_of_sails();
                } else {
                    return "-";
                }
            }
            case 6: {
                Boat b = data.getBoat(rowIndex);
                if (b instanceof Icebreaker) {
                    return ((Icebreaker) b).getPower();
                } else {
                    return "-";
                }
            }
            case 7: {
                Boat b = data.getBoat(rowIndex);
                if (b instanceof Icebreaker) {
                    return ((Icebreaker) b).getMax_thickness_of_ice();
                } else {
                    return "-";
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
    public void sort() {
        Group.sortlist();
    }
public static void setIndex(int indexIN) {
        index = indexIN;
}


}