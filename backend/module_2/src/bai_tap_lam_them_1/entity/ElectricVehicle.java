package module_2.src.bai_tap_lam_them_1.entity;

public class ElectricVehicle extends Vehicle {
    private double batteryCapacity;

    public ElectricVehicle() {
    }

    public ElectricVehicle(String licensePlate, Manufacture manufacture, String manufactureYears, String ownerName, double batteryCapacity) {
        super(licensePlate, manufacture, manufactureYears, ownerName);
        this.batteryCapacity = batteryCapacity;
    }

    public double getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(double batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    @Override
    public VehicleType getVehicleType() {
        return VehicleType.ELECTRIC_VEHICLE;
    }

    @Override
    public String toString() {
        return String.join(" - ", this.getLicensePlate(), this.getManufacture().toString(), this.getManufactureYears(), this.getOwnerName(), this.batteryCapacity + "");
    }
}
