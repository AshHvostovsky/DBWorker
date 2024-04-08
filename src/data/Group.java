package data;


import DB.DBWorker;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;

public class Group {
    //private static Map<Integer, Group.Boat> boats = new HashMap<>();
    private static ArrayList<Boat> boats = new ArrayList<>();


    //private ArrayList<Boat> boats;
    public Group() {
        try {
            //Добавляем в список все корабли из БД
            boats.addAll(DBWorker.getAllSailboats());
            boats.addAll(DBWorker.getAllSteamboats());
            boats.addAll(DBWorker.getAllIcebreakers());

            //Добавить в список все рейсы из БД
            //flights.addAll(DBWorker.getAllFlights());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Методы добавления в список кораблей
    public static boolean addSteamboat(String name, int id, String type_of_fuel, int speed) {

        // Проверка наличия корабля с таким же id в списке
        for (Boat boat : boats) {
            if (boat.getId() == id) {
                System.out.println("Корабль с таким ID уже существует.");
                return false; // Завершаем метод, так как корабль уже существует
            }
        }
        Steamboat steamboat = new Steamboat(name, id, type_of_fuel, speed);
        boats.add(steamboat);
        //boats.put(id, steamboat);
        System.out.println("Пароход успешно добавлен.");
        try {
            DBWorker.addSteamboat(steamboat);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
    public static boolean addSailboat(String name, int id, String type_of_sails, int number_of_sails) {
        // Проверка наличия корабля с таким же id в списке
        for (Boat boat : boats) {
            if (boat.getId() == id) {
                System.out.println("Корабль с таким ID уже существует.");
                return false; // Завершаем метод, так как корабль уже существует
            }
        }
        Sailboat sailboat = new Sailboat(name, id, type_of_sails, number_of_sails);
        boats.add(sailboat);
        //boats.put(id, steamboat);
        System.out.println("Парусник успешно добавлен.");
        try {
            DBWorker.addSailboat(sailboat);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public static boolean addIcebreaker(String name, int id, int power, int max_thickness_of_ice) {
        // Проверка наличия корабля с таким же id в списке
        for (Boat boat : boats) {
            if (boat.getId() == id) {
                System.out.println("Корабль с таким ID уже существует.");
                return false; // Завершаем метод, так как корабль уже существует
            }
        }
        Icebreaker icebreaker = new Icebreaker(name, id, power, max_thickness_of_ice);
        boats.add(icebreaker);
        //boats.put(id, steamboat);
        System.out.println("Ледоход успешно добавлен.");
        try {
            DBWorker.addIcebreaker(icebreaker);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    //Методы работы со списком
    public static void deleteBoat(int id) {
        int indexToRemove = -1; // Инициализируем переменную для хранения индекса элемента для удаления
        for (int i = 0; i < boats.size(); i++) {
            if (boats.get(i).getId() == id) {
                indexToRemove = i; // Нашли индекс элемента для удаления
                break;
            }
        }
        if (indexToRemove != -1) { // Если был найден элемент для удаления
            boats.remove(indexToRemove); // Удаляем элемент по найденному индексу
            System.out.println("Корабль успешно удален.");
        } else {
            System.out.println("Корабль с таким ID не найден.");
        }
    }
    public static void printBoats(ArrayList<Boat> boats) {
        boolean hasBoat = false;
        for (Boat boat : boats) {
            if (boat instanceof Sailboat) {
                Sailboat sailboat = (Sailboat) boat;
                System.out.println("Название парусника: " + sailboat.getName() + ", ID: " + sailboat.getId() + ", Количество парусов: " + sailboat.getNumber_of_sails() + ", Тип парусов: " + sailboat.getType_of_sails());
                sailboat.sail();
                hasBoat = true;
            } else if (boat instanceof Steamboat) {
                Steamboat steamboat = (Steamboat) boat;
                System.out.println("Название парохода: " + steamboat.getName() + ", ID: " + steamboat.getId() + ", Тип топлива: " + steamboat.getType_of_fuel() + ", Скорость: " + steamboat.getSpeed());
                steamboat.sail();
                hasBoat = true;
            } else if (boat instanceof Icebreaker) {
                Icebreaker icebreaker = (Icebreaker) boat;
                System.out.println("Название ледохода: " + icebreaker.getName() + ", ID: " + icebreaker.getId() + ", Мощность: " + icebreaker.getPower() + ", Максимальная толщина льда: " + icebreaker.getMax_thickness_of_ice());
                icebreaker.sail();
                hasBoat = true;
            }
        }
        if (!hasBoat) {
            System.out.println("Список кораблей пуст.");
        }
    }

    public static void searchBoat(ArrayList<Boat> boats, int id) {
        boolean found = false;
        for (Boat boat : boats) {
            if (boat.getId() == id) {
                found = true;
                if (boat instanceof Sailboat) {
                    Sailboat sailboat = (Sailboat) boat;
                    sailboat.sail();
                    System.out.println("Корабль найден:");
                    System.out.println("Название парусника : " + sailboat.getName() + ", ID: " + sailboat.getId() + ", Количество парусов: " + sailboat.getNumber_of_sails() + ", Тип парусов: " + sailboat.getType_of_sails());
                } else if (boat instanceof Steamboat) {
                    Steamboat steamboat = (Steamboat) boat;
                    steamboat.sail();
                    System.out.println("Корабль найден:");
                    System.out.println("Название парохода: " + steamboat.getName() + ", ID: " + steamboat.getId() + ", Тип топлива: " + steamboat.getType_of_fuel() + ", Скорость: " + steamboat.getSpeed());
                } else if (boat instanceof Icebreaker) {
                    Icebreaker icebreaker = (Icebreaker) boat;
                    icebreaker.sail();
                    System.out.println("Корабль найден:");
                    System.out.println("Название ледохода: " + icebreaker.getName() + ", ID: " + icebreaker.getId() + ", Мощность: " + icebreaker.getPower() + ", Максимальная толщина льда: " + icebreaker.getMax_thickness_of_ice());
                }
                break;
            }
        }
        if (!found) {
            System.out.println("Корабль с таким ID не найден.");
        }
    }
    public int getCount() {
        return this.boats.size();
    }

    public static Boat getBoat(int index) {
        return boats.get(index);
    }
    public static Boat getBoatByID(int index) {
        for (Boat boat : boats) {
            if (boat.getId() == index)
                return boat;
        }
        return null;
    }




    public void remove(int ind) {

        //Тут айди соответствует индексу строки
        //А надо айди корабля
        Boat boat = getBoat(ind);
        int ind_BD = boat.getId();
        if (boat instanceof Icebreaker) {
            try {
                DBWorker.deleteIcebreaker(ind_BD);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else if (boat instanceof Sailboat) {
            try {
                DBWorker.deleteSailboat(ind_BD);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else if (boat instanceof Steamboat) {
            try {
                DBWorker.deleteSteamboat(ind_BD);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            System.out.println("Неизвестный объект для удаления");
        }
        this.boats.remove(ind);
    }
    public static String getNextAvailableId() {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Boat boat : boats) {
            ids.add(boat.getId());
        }
        int nextId = 0;
        while (ids.contains(nextId)) {
            nextId++;
        }
        return String.valueOf(nextId);
    }

    public static void sortlist() {
        for (int i = 0; i < boats.size() - 1; i++) {
            for (int j = i + 1; j < boats.size(); j++) {
                Boat boat1 = boats.get(i);
                Boat boat2 = boats.get(j);
                if (boat1.getId() > boat2.getId()) {
                    // Поменять объекты местами
                    boats.set(i, boat2);
                    boats.set(j, boat1);
                }
            }
        }
    }
    public static boolean searchMethod(int id) {
        for (int i = 0; i < boats.size(); i++) {
            Boat boat = boats.get(i);
            if (boat.getId() == id) {
                // Обменять местами объекты
                Boat firstBoat = boats.get(0);
                boats.set(0, boat);
                boats.set(i, firstBoat);
                System.out.println(boats.get(0));
                return true; // Завершить метод после перемещения объекта
            }
        }
        // Если объект с заданным id не найден, можно вывести сообщение или выполнить другие действия
        System.out.println("Корабль с id " + id + " не найден.");
        return false;
    }


    public static boolean str_is_null(String str) {
        return str.isEmpty();

    }
    public static void sortList(int sort) {
        switch (sort) {
            case 1:
                boats.sort(Comparator.comparingInt(Boat::getBoatType_Steamboat));
                break;
            case 2:
                boats.sort(Comparator.comparingInt(Boat::getBoatType_Sailboat));
                break;
            case 3:
                boats.sort(Comparator.comparingInt(Boat::getBoatType_Icebreaker));
                break;
        }

    }
    //Получение всех айдишников
    public static String[] getIdBoats() {
        String[] str = new String[boats.size()];

        int i = 0;
        for (Boat boat : boats) {
            str[i] = Integer.toString(boat.getId());
            i++;
        }
        return str;
    }





}
