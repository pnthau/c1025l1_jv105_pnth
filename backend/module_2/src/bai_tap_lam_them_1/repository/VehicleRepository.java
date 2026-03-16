package module_2.src.bai_tap_lam_them_1.repository;

import module_2.src.bai_tap_lam_them_1.entity.Vehicle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VehicleRepository implements IVehicleRepository {
    private final List<Vehicle> vehicleArrayList = new ArrayList<>();

    @Override
    public void add(Vehicle vehicle) {
        this.vehicleArrayList.add(vehicle);
    }

    @Override
    public Vehicle findByLicensePlate(String plate) {
        return this.vehicleArrayList.stream()
                .filter(v -> v.getLicensePlate().equals(plate))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean deleteByLicensePlate(String plate) {
        return this.vehicleArrayList.removeIf(v -> v.getLicensePlate().equals(plate));
    }

    @Override
    public List<Vehicle> getAll() {
        return Collections.unmodifiableList(this.vehicleArrayList);
    }
}
