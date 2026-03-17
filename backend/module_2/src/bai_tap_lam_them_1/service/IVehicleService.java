package module_2.src.bai_tap_lam_them_1.service;

import module_2.src.bai_tap_lam_them_1.dto.VehicleRequest;
import module_2.src.bai_tap_lam_them_1.entity.Vehicle;
import module_2.src.bai_tap_lam_them_1.entity.VehicleType;

import java.util.List;

public interface IVehicleService {
    boolean add(Vehicle vehicle);

    public List<Vehicle> findByLicensePlate(String plate);

    boolean deleteByLicensePlate(String plate);

    public List<Vehicle> getAll();

    boolean add(VehicleRequest request);

    public List<Vehicle> getType(VehicleType vehicleType);
}
