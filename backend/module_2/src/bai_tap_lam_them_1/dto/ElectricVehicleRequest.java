package module_2.src.bai_tap_lam_them_1.dto;

import module_2.src.bai_tap_lam_them_1.entity.ElectricVehicle;
import module_2.src.bai_tap_lam_them_1.entity.Manufacture;
import module_2.src.bai_tap_lam_them_1.entity.Vehicle;

public class ElectricVehicleRequest extends VehicleRequest{

    public Double batteryCapacity;

    @Override
    public Vehicle toEntity() {
        ElectricVehicle electricVehicle = new ElectricVehicle();
        electricVehicle.setLicensePlate(licensePlate);
        electricVehicle.setManufacture(manufacture);
        electricVehicle.setManufactureYears(manufactureYears);
        electricVehicle.setOwnerName(ownerName);

        electricVehicle.setBatteryCapacity(batteryCapacity);
        return electricVehicle;
    }
}
