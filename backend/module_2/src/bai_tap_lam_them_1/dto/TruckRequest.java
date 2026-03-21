package module_2.src.bai_tap_lam_them_1.dto;

import module_2.src.bai_tap_lam_them_1.entity.Manufacture;
import module_2.src.bai_tap_lam_them_1.entity.Truck;
import module_2.src.bai_tap_lam_them_1.entity.Vehicle;

public class TruckRequest extends VehicleRequest {
    public Double payloadCapacity;

    @Override
    public Vehicle toEntity() {
        Truck truck = new Truck();
        truck.setLicensePlate(licensePlate);
        truck.setManufacture(manufacture);
        truck.setManufactureYears(manufactureYears);
        truck.setOwnerName(ownerName);

        truck.setPayloadCapacity(payloadCapacity);
        return truck;
    }
}
