package module_2.src.bai_tap_lam_them_1.repository;

import module_2.src.bai_tap_lam_them_1.entity.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VehicleRepository implements IVehicleRepository {
    private List<Vehicle> vehicleArrayList = new ArrayList<>(List.of(
            new Truck("12C-99999", new Manufacture("", "", ""),
                    "1", "1", 1),
            new Truck("22F-00000", new Manufacture("2", "2", "2"),
                    "2", "2", 2),
            new Car("50F-22222", new Manufacture("2", "2", "2"),
                    "3", "3", 7, "7"),
            new Car("80F-33333", new Manufacture("4", "4", "4"),
                    "4", "4", 4, "4"),
            new Bike("90F-98989", new Manufacture("5", "5", "5"),
                    "5", "5", 5),
            new Bike("12K-12345", new Manufacture("6", "6", "6"),
                    "6", "6", 6)

    ));


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

    @Override
    public List<Vehicle> findByLicensePlate(String plate) {
        return this.vehicleArrayList.stream().filter(v -> v.contains(plate)).toList();
    }
}
