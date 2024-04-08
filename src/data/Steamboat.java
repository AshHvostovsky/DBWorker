package data;

public class Steamboat extends Boat { //Пароход
    private String type_of_fuel;
    private int speed;

    public Steamboat(String name, int id, String type_of_fuel, int speed) {
        super(name, id);
        this.type_of_fuel = type_of_fuel;
        this.speed = speed;
    }
    public void setType_of_fuel(String type_of_fuel) {
        this.type_of_fuel = type_of_fuel;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public String getType_of_fuel() {
        return type_of_fuel;
    }
    public int getSpeed() {
        return speed;
    }
    @Override
    public void sail() {
        System.out.println("Пароход медленно двигается вперед, выделяя облака пара из своих труб");
    }
}
