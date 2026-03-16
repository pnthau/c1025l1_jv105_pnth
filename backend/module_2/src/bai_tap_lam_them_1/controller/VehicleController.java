package module_2.src.bai_tap_lam_them_1.controller;

import module_2.src.bai_tap_lam_them_1.entity.Vehicle;
import module_2.src.bai_tap_lam_them_1.entity.VehicleType;
import module_2.src.bai_tap_lam_them_1.service.VehicleService;

import java.util.List;

public class VehicleController {
    private final VehicleService service = new VehicleService();

    public boolean addVehicle(Vehicle vehicle) {
        return service.add(vehicle);

    }

    public void displayAll() {
        service.getAll().forEach(System.out::println);
    }

    public void displayType(VehicleType vehicleType) {
        service.getType(vehicleType);
    }

    public boolean deleteByLicensePlate(String plate) {
        return service.deleteByLicensePlate(plate);
    }

    public List<Vehicle> findByLicensePlate(String plate) {
        return this.service.findByLicensePlate(plate);
    }

}
