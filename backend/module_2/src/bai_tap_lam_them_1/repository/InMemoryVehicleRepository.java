package module_2.src.bai_tap_lam_them_1.repository;

import module_2.src.bai_tap_lam_them_1.entity.*;

import java.util.Collections;
import java.util.List;

public class InMemoryVehicleRepository implements IVehicleRepository {
    private final List<Vehicle> vehicleArrayList;

    public InMemoryVehicleRepository(List<Vehicle> vehicleList) {
        this.vehicleArrayList = vehicleList;
    }

    @Override
    public boolean add(Vehicle vehicle) {
        return this.vehicleArrayList.add(vehicle);
    }

    @Override
    public boolean deleteByLicensePlate(String plate) {
        return this.vehicleArrayList.removeIf(v -> v.getLicensePlate().equals(plate));
    }

    @Override
    public List<Vehicle> getAll() {
        return Collections.unmodifiableList(this.vehicleArrayList);
    }

    @Override
    public List<Vehicle> getType(VehicleType type) {
        return Collections.unmodifiableList(
                this.vehicleArrayList
                        .stream()
                        .filter(v -> v.getVehicleType().equals(type))
                        .toList()
        );
    }
}
