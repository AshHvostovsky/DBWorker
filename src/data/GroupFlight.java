
package data;


import DB.DBWorker;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;

public class GroupFlight {


    private static ArrayList<Flight> flights = new ArrayList<>();


    public GroupFlight() {
        try {
            flights.addAll(DBWorker.getAllFlights());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //Методы добавления

    public static boolean addFlight(int id, String departing, String arrival, int boat_id) {
        // Проверка наличия корабля с таким же id в списке
        for (Flight flight : flights) {
            if (flight.getId() == id) {
                System.out.println("Рейс с таким ID уже существует.");
                return false; // Завершаем метод, так как корабль уже существует
            }
        }
        Flight flight = new Flight(id, departing, arrival, boat_id);
        flights.add(flight);
        //boats.put(id, steamboat);
        System.out.println("Рейс успешно добавлен.");
        try {
            DBWorker.addFlight(flight);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    //Методы работы со списком
    public static boolean deleteFlight(int id) {
        int indexToRemove = -1; // Инициализируем переменную для хранения индекса элемента для удаления
        for (int i = 0; i < flights.size(); i++) {
            if (flights.get(i).getBoat_id() == id) {
                indexToRemove = i; // Нашли индекс элемента для удаления
                break;
            }
        }
        if (indexToRemove != -1) { // Если был найден элемент для удаления
            flights.remove(indexToRemove); // Удаляем элемент по найденному индексу
            System.out.println("Рейс успешно удален.");
            return true;
        } else {
            System.out.println("Рейс с таким ID не найден.");
            return false;
        }
    }

    public int getCount() {
        return this.flights.size();
    }

    public static Flight getFlight(int index) {
        return flights.get(index);
    }
    public static Flight getFlightByID(int index) {
        for (Flight flight : flights) {
            if (flight.getId() == index)
                return flight;
        }
        return null;
    }




    public void remove(int ind) throws SQLException {

        //Тут айди соответствует индексу строки
        //А надо айди корабля
        Flight flight = getFlight(ind);
        int ind_BD = flight.getId();
        DBWorker.deleteFlight(ind_BD);
        this.flights.remove(ind);
    }
    public static String getNextAvailableId() {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Flight flight : flights) {
            ids.add(flight.getId());
        }
        int nextId = 0;
        while (ids.contains(nextId)) {
            nextId++;
        }
        return String.valueOf(nextId);
    }

    public static void sortlist() {
        for (int i = 0; i < flights.size() - 1; i++) {
            for (int j = i + 1; j < flights.size(); j++) {
                Flight flight1 = flights.get(i);
                Flight flight2 = flights.get(j);
                if (flight1.getId() > flight2.getId()) {
                    // Поменять объекты местами
                    flights.set(i, flight2);
                    flights.set(j, flight1);
                }
            }
        }
    }
    public static boolean searchMethod(int id) {
        for (int i = 0; i < flights.size(); i++) {
            Flight flight = flights.get(i);
            if (flight.getId() == id) {
                // Обменять местами объекты
                Flight firstFlight = flights.get(0);
                flights.set(0, flight);
                flights.set(i, firstFlight);
                System.out.println(flights.get(0));
                return true; // Завершить метод после перемещения объекта
            }
        }
        // Если объект с заданным id не найден, можно вывести сообщение или выполнить другие действия
        System.out.println("Рейс с id " + id + " не найден.");
        return false;
    }

    public static boolean str_is_null(String str) {
        return str.isEmpty();

    }
}
