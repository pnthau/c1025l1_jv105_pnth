package module_2.src.bai_tap_lam_them_1.dto;

import module_2.src.bai_tap_lam_them_1.entity.Car;
import module_2.src.bai_tap_lam_them_1.entity.Manufacture;
import module_2.src.bai_tap_lam_them_1.entity.Truck;
import module_2.src.bai_tap_lam_them_1.entity.Vehicle;

public class CarRequest extends VehicleRequest {

    public Integer seats;
    public String type;

    @Override
    public Vehicle toEntity() {
        Car car = new Car();
        car.setLicensePlate(licensePlate);
        car.setManufacture(manufacture);
        car.setManufactureYears(manufactureYears);
        car.setOwnerName(ownerName);

        car.setType(type);
        car.setSeats(seats);
        return car;
    }


}
