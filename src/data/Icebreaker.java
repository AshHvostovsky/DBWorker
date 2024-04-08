package data;

public class Icebreaker extends Boat { //Ледокол
    private int power;
    private int max_thickness_of_ice;
    public Icebreaker(String name, int id, int power, int max_thickness_of_ice) {
        super(name, id);
        this.power = power;
        this.max_thickness_of_ice = max_thickness_of_ice;
    }

    public int getPower() {
        return power;
    }
    public void setPower(int power) {
        this.power = power;
    }
    public void setMax_thickness_of_ice(int maxThicknessOfIce) {
        this.max_thickness_of_ice = maxThicknessOfIce;
    }
    public int getMax_thickness_of_ice() {
        return max_thickness_of_ice;
    }

    @Override
    public void sail() {
        System.out.println("Ледокол могуче пробивает путь через толщу льда, оставляя за собой протопленный канал");
    }
}
