package module_2.src.bai_tap_lam_them_1.controller;

import module_2.src.bai_tap_lam_them_1.criteria.VehicleCriteria;
import module_2.src.bai_tap_lam_them_1.dto.IVehicleRequest;
import module_2.src.bai_tap_lam_them_1.entity.Vehicle;
import module_2.src.bai_tap_lam_them_1.entity.VehicleType;
import module_2.src.bai_tap_lam_them_1.service.IVehicleService;

import java.util.List;

public class VehicleController {
    private final IVehicleService service;

    public VehicleController(IVehicleService service) {
        this.service = service;
    }

    public boolean addVehicle(Vehicle vehicle) {
        return service.add(vehicle);
    }

    public boolean addVehicle(IVehicleRequest request) {
        return service.add(request);
    }

    public List<Vehicle> displayType(VehicleType vehicleType) {
        return service.getType(vehicleType);
    }

    public boolean delete(VehicleCriteria criteria) {
        return service.delete(criteria);
    }

    public List<Vehicle> find(VehicleCriteria criteria) {
        return this.service.find(criteria);
    }
}
