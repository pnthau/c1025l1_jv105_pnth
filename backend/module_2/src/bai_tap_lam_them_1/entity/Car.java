package module_2.src.bai_tap_lam_them_1.entity;

import module_2.src.bai_tap_lam_them_1.util.ConstantsVariables;

public class Car extends Vehicle {
    private int seats;
    private String type;

    public Car() {
    }

    public Car(String licensePlate, Manufacture manufacture, String manufactureYears, String ownerName, int seats, String type) {
        super(licensePlate, manufacture, manufactureYears, ownerName);
        this.seats = seats;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return String.join(ConstantsVariables.SPACE_CHAR, super.toString(), this.seats + "", this.type);
    }
}
