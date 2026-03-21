package module_2.src.bai_tap_lam_them_1.dto;

import module_2.src.bai_tap_lam_them_1.entity.Manufacture;

public abstract class VehicleRequest implements IVehicleRequest {
    public String licensePlate;
    public Manufacture manufacture;
    public String manufactureYears;
    public String ownerName;
}
