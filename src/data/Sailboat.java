package data;

public class Sailboat extends Boat { //Парусник
    private String type_of_sails;
    private int number_of_sails;
    public Sailboat(String name, int id, String type_of_sails, int number_of_sails) {
        super(name, id);
        this.type_of_sails = type_of_sails;
        this.number_of_sails = number_of_sails;
    }
    public void setType_of_sails(String type_of_sails) {
        this.type_of_sails = type_of_sails;
    }
    public void setNumber_of_sails(int number_of_sails) {
        this.number_of_sails = number_of_sails;
    }
    public String getType_of_sails() {
        return type_of_sails;
    }
    public int getNumber_of_sails() {
        return number_of_sails;
    }

    @Override
    public void sail() {
        System.out.println("Парусник скользит по волнам, поднимая свои паруса на ветру");
    };
}
