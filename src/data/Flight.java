package data;

import java.util.ArrayList;

public class Flight { //Полет
    private int id;
    private String departing;
    private String arrival;
    private int boat_id;
    public Flight(int id, String departing, String arrival, int boat_id) {
        this.id = id;
        this.departing = departing;
        this.arrival = arrival;
        this.boat_id = boat_id;
    }

    public int getBoat_id() {
        return boat_id;
    }

    public int getId() {
        return id;
    }

    public String getArrival() {
        return arrival;
    }

    public String getDeparting() {
        return departing;
    }
}
