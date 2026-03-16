package module_2.src.bai_tap_lam_them_1.entity;

public abstract class Vehicle {
    String licensePlate;
    Manufacture manufacture;
    String manufactureYears;
    String ownerName;

    public Vehicle() {
    }

    public Vehicle(String licensePlate, Manufacture manufacture, String manufactureYears, String ownerName) {
        this.licensePlate = licensePlate;
        this.manufacture = manufacture;
        this.manufactureYears = manufactureYears;
        this.ownerName = ownerName;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Manufacture getManufacture() {
        return manufacture;
    }

    public void setManufacture(Manufacture manufacture) {
        this.manufacture = manufacture;
    }

    public String getManufactureYears() {
        return manufactureYears;
    }

    public void setManufactureYears(String manufactureYears) {
        this.manufactureYears = manufactureYears;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Override
    public boolean equals(Object obj) {
        Vehicle vehicle = (Vehicle) obj;
        return this.licensePlate.equals(vehicle.licensePlate);
    }

    public boolean contain(String keyword) {
        return this.licensePlate.contains(keyword);
    }

    @Override
    public String toString() {
        return String.join(" - ", this.getLicensePlate(), this.getManufacture().toString(), this.getManufactureYears(), this.getOwnerName());
    }
}
