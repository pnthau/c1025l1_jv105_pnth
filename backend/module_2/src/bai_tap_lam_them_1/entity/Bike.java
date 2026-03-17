package module_2.src.bai_tap_lam_them_1.entity;

public class Bike extends Vehicle {
    private double enginePower;

    public Bike() {
        this.enginePower = 0;
    }

    public Bike(String licensePlate, Manufacture manufacture, String manufactureYears, String ownerName, double enginePower) {
        super(licensePlate, manufacture, manufactureYears, ownerName);
        this.enginePower = enginePower;
    }

    public double getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(double enginePower) {
        this.enginePower = enginePower;
    }

    @Override
    public String toString() {
        return String.join(" - ", super.toString(), this.getEnginePower() + "");
    }

    @Override
    public VehicleType getVehicleType() {
        return VehicleType.BIKE;
    }
}
