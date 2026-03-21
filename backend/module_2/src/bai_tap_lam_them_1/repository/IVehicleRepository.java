package module_2.src.bai_tap_lam_them_1.repository;

import module_2.src.bai_tap_lam_them_1.entity.Vehicle;
import module_2.src.bai_tap_lam_them_1.entity.VehicleType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public interface IVehicleRepository {
    boolean add(Vehicle vehicle);

    boolean deleteByLicensePlate(String plate);

    List<Vehicle> getType(VehicleType type);

    List<Vehicle> getAll();
}
