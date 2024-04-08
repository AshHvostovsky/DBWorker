package data;

public abstract class Boat {
    private String name;
    private int id;

    public Boat(String name, int id) {
        this.name = name;
        this.id = id;
    }



    public void setName(String name) {
        this.name = name;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public abstract void sail();

    public int getId() {
        return id;
    }
    public static int getBoatType_Steamboat(Boat boat) {
        //Если 1 - пароход, 2 - парусник, 3 - ледоход
        if (boat instanceof Steamboat) {
            return 1; // Пароход
        } else if (boat instanceof Sailboat) {
            return 2; // Парусник
        } else if (boat instanceof Icebreaker) {
            return 3; // Ледокол
        } else {
            return 0; // Неизвестный тип
        }

    }
    public static int getBoatType_Sailboat(Boat boat) {
        //Если 1 - пароход, 2 - парусник, 3 - ледоход
        if (boat instanceof Sailboat) {
            return 1; // Пароход
        } else if (boat instanceof Steamboat) {
            return 2; // Парусник
        } else if (boat instanceof Icebreaker) {
            return 3; // Ледокол
        } else {
            return 0; // Неизвестный тип
        }

    }
    public static int getBoatType_Icebreaker(Boat boat) {
        //Если 1 - пароход, 2 - парусник, 3 - ледоход
        if (boat instanceof Icebreaker) {
            return 1; // Пароход
        } else if (boat instanceof Steamboat) {
            return 2; // Парусник
        } else if (boat instanceof Sailboat) {
            return 3; // Ледокол
        } else {
            return 0; // Неизвестный тип
        }

    }



}