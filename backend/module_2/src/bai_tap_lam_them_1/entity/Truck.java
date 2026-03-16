package module_2.src.bai_tap_lam_them_1.entity;

import module_2.src.bai_tap_lam_them_1.util.ConstantsVariables;

public class Truck extends Vehicle {
    private double payloadCapacity;

    public Truck() {
    }

    public Truck(String licensePlate, Manufacture manufacture, String manufactureYears, String ownerName, double payloadCapacity) {
        super(licensePlate, manufacture, manufactureYears, ownerName);
        this.payloadCapacity = payloadCapacity;
    }

    public double getPayloadCapacity() {
        return payloadCapacity;
    }

    public void setPayloadCapacity(double payloadCapacity) {
        this.payloadCapacity = payloadCapacity;
    }

    @Override
    public String toString() {
        return String.join(ConstantsVariables.SPACE_CHAR, super.toString(), this.getPayloadCapacity() + "");
    }
}

