package module_2.src.bai_tap_lam_them_1.dto;

import module_2.src.bai_tap_lam_them_1.entity.Manufacture;
import module_2.src.bai_tap_lam_them_1.entity.VehicleType;

public class VehicleRequest {
    public String licensePlate;
    public Manufacture manufacture;
    public String manufactureYears;
    public String ownerName;

    public Double payloadCapacity;
    public Integer seats;
    public String type;
    public Double enginePower;

    public VehicleType vehicleType;

}
