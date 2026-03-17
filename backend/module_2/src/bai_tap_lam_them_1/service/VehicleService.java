package module_2.src.bai_tap_lam_them_1.service;

import module_2.src.bai_tap_lam_them_1.entity.Vehicle;
import module_2.src.bai_tap_lam_them_1.entity.VehicleType;
import module_2.src.bai_tap_lam_them_1.repository.IVehicleRepository;
import module_2.src.bai_tap_lam_them_1.repository.VehicleRepository;

import java.util.List;

public class VehicleService implements IVehicleService {
    private final IVehicleRepository vehicleRepository;

    public VehicleService(IVehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public boolean add(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }
        return this.vehicleRepository.add(vehicle);

    }

    public List<Vehicle> findByLicensePlate(String plate) {
        return this.vehicleRepository.findByLicensePlate(plate);
    }

    public boolean deleteByLicensePlate(String plate) {
        return this.vehicleRepository.deleteByLicensePlate(plate);
    }


    public List<Vehicle> getAll() {
        return this.vehicleRepository.getAll();
    }

    public List<Vehicle> getType(VehicleType vehicleType) {
        return this.vehicleRepository.getType(vehicleType);
    }

}
