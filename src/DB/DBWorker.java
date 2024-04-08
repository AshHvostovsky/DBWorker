package DB;

import data.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DBWorker {
    public static final String PATH_TO_DB_FILE = "boat.db";
    public static final String URL = "jdbc:sqlite:" + PATH_TO_DB_FILE;
    public static Connection conn;

    public static void closeConnection() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }
    public static void initDB() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(URL);
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("Драйвер: " + meta.getDriverName());
                //createDB();
            }
        } catch (SQLException ex) {
            System.out.println("Ошибка подключения к БД: " + ex);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Sailboat> getAllSailboats() throws SQLException {
        initDB();
        Statement statement = conn.createStatement();
        List<Sailboat> list = new ArrayList<Sailboat>();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM sailboat");
        while (resultSet.next()) {
            list.add(new Sailboat(resultSet.getString("name"), resultSet.getInt("id"),
                    resultSet.getString("type_of_sails"),
                    resultSet.getInt("number_of_sails")));
        }
        resultSet.close();
        statement.close();
        closeConnection();
        return list;
    }
    public static List<Icebreaker> getAllIcebreakers() throws SQLException {
        initDB();
        Statement statement = conn.createStatement();
        List<Icebreaker> list = new ArrayList<Icebreaker>();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM icebreaker");
        while (resultSet.next()) {
            list.add(new Icebreaker(resultSet.getString("name"), resultSet.getInt("id"),
                    resultSet.getInt("power"),
                    resultSet.getInt("max_thickness_of_ice")));
        }
        resultSet.close();
        statement.close();
        closeConnection();
        return list;
    }
    public static List<Steamboat> getAllSteamboats() throws SQLException {
        initDB();
        Statement statement = conn.createStatement();
        List<Steamboat> list = new ArrayList<Steamboat>();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM steamboat");
        while (resultSet.next()) {
            list.add(new Steamboat(resultSet.getString("name"), resultSet.getInt("id"),
                    resultSet.getString("type_of_fuel"),
                    resultSet.getInt("speed")));
        }
        resultSet.close();
        statement.close();
        closeConnection();
        return list;
    }
    public static Sailboat getSailboat(int sailboatid) throws SQLException {
        initDB();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM sailboat WHERE sailboat.id ="+sailboatid);
        Sailboat sailboat = new Sailboat(resultSet.getString("name"), resultSet.getInt("id"),
                resultSet.getString("type_of_sails"),
                resultSet.getInt("number_of_sails"));
        resultSet.close();
        statement.close();
        closeConnection();
        return sailboat;
    }
    public static Steamboat getSteamboat(int steamboatid) throws SQLException {
        initDB();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM steamboat WHERE steamboat.id ="+steamboatid);
        Steamboat steamboat = new Steamboat(resultSet.getString("name"), resultSet.getInt("id"),
                resultSet.getString("type_of_fuel"),
                resultSet.getInt("speed"));
        resultSet.close();
        statement.close();
        closeConnection();
        return steamboat;
    }
    public static Icebreaker getIcebreaker(int icebreakerid) throws SQLException {
        initDB();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM steamboat WHERE icebreaker.id ="+icebreakerid);
        Icebreaker icebreaker = new Icebreaker(resultSet.getString("name"), resultSet.getInt("id"),
                resultSet.getInt("power"),
                resultSet.getInt("max_thickness_of_ice"));
        resultSet.close();
        statement.close();
        closeConnection();
        return icebreaker;
    }
    public static void deleteSailboat(int id) throws SQLException {
        initDB();
        Statement statement = conn.createStatement();
        System.out.println("id="+id);
        statement.execute("DELETE FROM sailboat WHERE sailboat.id ="+id);
        System.out.println("Парусник удалён из БД");
        statement.close();

        //Удаление рейсов, в которых участвовал удаляемый корабль
        while (GroupFlight.deleteFlight(id));
        Statement statement2 = conn.createStatement();
        statement2.execute("DELETE FROM flight WHERE flight.boat_id ="+id);
        statement2.close();
        closeConnection();
    }
    public static void deleteSteamboat(int id) throws SQLException {
        initDB();
        Statement statement = conn.createStatement();
        System.out.println("id="+id);
        statement.execute("DELETE FROM steamboat WHERE steamboat.id ="+id);
        System.out.println("Пароход удалён из БД");
        statement.close();

        //Удаление рейсов, в которых участвовал удаляемый корабль
        while (GroupFlight.deleteFlight(id));
        Statement statement2 = conn.createStatement();
        statement2.execute("DELETE FROM flight WHERE flight.boat_id ="+id);
        statement2.close();
        closeConnection();
    }
    public static void deleteIcebreaker(int id) throws SQLException {
        initDB();
        Statement statement = conn.createStatement();
        System.out.println("id="+id);
        statement.execute("DELETE FROM icebreaker WHERE icebreaker.id ="+id);
        System.out.println("Ледокол удалён из БД");
        statement.close();

        //Удаление рейсов, в которых участвовал удаляемый корабль
        while (GroupFlight.deleteFlight(id));
        Statement statement2 = conn.createStatement();
        statement2.execute("DELETE FROM flight WHERE flight.boat_id ="+id);
        statement2.close();
        closeConnection();
    }

    public static void addSailboat(Sailboat sailboat) throws SQLException {
        initDB();
        PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO sailboat('id','name','type_of_sails', 'number_of_sails') " +
                        "VALUES(?,?,?,?)");
        statement.setObject(1, sailboat.getId());
        statement.setObject(2, sailboat.getName());
        statement.setObject(3, sailboat.getType_of_sails());
        statement.setObject(4, sailboat.getNumber_of_sails());
        statement.execute();
        statement.close();
        System.out.println("Парусник добавлен в БД");
        closeConnection();
    }
    public static void addSteamboat(Steamboat steamboat) throws SQLException {
        initDB();
        PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO steamboat('id','name','type_of_fuel', 'speed') " +
                        "VALUES(?,?,?,?)");
        statement.setObject(1, steamboat.getId());
        statement.setObject(2, steamboat.getName());
        statement.setObject(3, steamboat.getType_of_fuel());
        statement.setObject(4, steamboat.getSpeed());
        statement.execute();
        statement.close();
        System.out.println("Пароход добавлен в БД");
        closeConnection();
    }
    public static void addIcebreaker(Icebreaker icebreaker) throws SQLException {
        initDB();
        PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO icebreaker('id','name','power', 'max_thickness_of_ice') " +
                        "VALUES(?,?,?,?)");
        statement.setObject(1, icebreaker.getId());
        statement.setObject(2, icebreaker.getName());
        statement.setObject(3, icebreaker.getPower());
        statement.setObject(4, icebreaker.getMax_thickness_of_ice());
        statement.execute();
        statement.close();
        System.out.println("Ледокол добавлен в БД");
        closeConnection();
    }




    public static List<Flight> getAllFlights() throws SQLException {
        initDB();
        Statement statement = conn.createStatement();
        List<Flight> list = new ArrayList<Flight>();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM flight");
        while (resultSet.next()) {
            list.add(new Flight(resultSet.getInt("id"), resultSet.getString("departing"),
                    resultSet.getString("arrival"),
                    resultSet.getInt("boat_id")));
        }
        resultSet.close();
        statement.close();
        closeConnection();
        return list;
    }
    public static void deleteFlight(int id) throws SQLException {
        initDB();
        Statement statement = conn.createStatement();
        System.out.println("id="+id);
        statement.execute("DELETE FROM flight WHERE flight.id ="+id);
        System.out.println("Рейс удалён из БД");
        closeConnection();
        statement.close();
    }
    public static void addFlight(Flight flight) throws SQLException {
        initDB();
        PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO flight('id','departing','arrival', 'boat_id') " +
                        "VALUES(?,?,?,?)");
        statement.setObject(1, flight.getId());
        statement.setObject(2, flight.getDeparting());
        statement.setObject(3, flight.getArrival());
        statement.setObject(4, flight.getBoat_id());
        statement.execute();
        statement.close();
        closeConnection();
        System.out.println("Рейс добавлен в БД");
    }
}
