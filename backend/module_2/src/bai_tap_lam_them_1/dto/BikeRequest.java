package module_2.src.bai_tap_lam_them_1.dto;

import module_2.src.bai_tap_lam_them_1.entity.*;

public class BikeRequest extends VehicleRequest {
    public Double enginePower;

    @Override
    public Vehicle toEntity() {
        Bike bike = new Bike();
        bike.setLicensePlate(licensePlate);
        bike.setManufacture(manufacture);
        bike.setManufactureYears(manufactureYears);
        bike.setOwnerName(ownerName);

        bike.setEnginePower(enginePower);
        return bike;
    }
}
