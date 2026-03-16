package module_2.src.bai_tap_lam_them_1.repository;

import module_2.src.bai_tap_lam_them_1.entity.Vehicle;
import module_2.src.bai_tap_lam_them_1.entity.VehicleType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VehicleRepository {
    private final List<Vehicle> vehicleArrayList = new ArrayList<>();

    public boolean add(Vehicle vehicle) {
        return this.vehicleArrayList.add(vehicle);
    }

//    public Vehicle findByLicensePlate(String plate) {
//        return this.vehicleArrayList.stream()
//                .filter(v -> v.getLicensePlate().equals(plate))
//                .findFirst()
//                .orElse(null);
//    }

    public boolean deleteByLicensePlate(String plate) {
        return this.vehicleArrayList.removeIf(v -> v.getLicensePlate().equals(plate));
    }

    public List<Vehicle> getAll() {
        return Collections.unmodifiableList(this.vehicleArrayList);
    }

    public List<Vehicle> getType(VehicleType type) {
        return Collections.unmodifiableList(
                this.vehicleArrayList
                        .stream()
                        .filter(v -> v.getVehicleType().equals(type))
                        .toList()
        );
    }

    public List<Vehicle> findByLicensePlate(String plate) {
        return this.vehicleArrayList.stream().filter(v -> v.contains(plate)).toList();
    }
}
