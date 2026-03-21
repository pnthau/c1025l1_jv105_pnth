package module_2.src.bai_tap_lam_them_1.service;

import module_2.src.bai_tap_lam_them_1.dto.IVehicleRequest;
import module_2.src.bai_tap_lam_them_1.entity.*;
import module_2.src.bai_tap_lam_them_1.repository.IVehicleRepository;

import java.util.List;

public class VehicleService implements IVehicleService {
    private final IVehicleRepository vehicleRepository;
    private final VehicleValidator vehicleValidator;


    public VehicleService(IVehicleRepository vehicleRepository, VehicleValidator vehicleValidator) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleValidator = vehicleValidator;
    }

    public boolean add(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }
        return this.vehicleRepository.add(vehicle);
    }

    @Override
    public List<Vehicle> findByLicensePlate(String plate) {
        return this.vehicleRepository.getAll().
                stream().
                filter(v -> v.contains(plate)).
                toList();
    }

    public boolean deleteByLicensePlate(String plate) {
        if (!this.vehicleValidator.isValidLicensePlate(plate)) {
            throw new IllegalArgumentException("the license plate is invalid");
        }
        return this.vehicleRepository.deleteByLicensePlate(plate);
    }

    public List<Vehicle> getAll() {
        return this.vehicleRepository.getAll();
    }

    @Override
    public boolean add(IVehicleRequest request) {
        Vehicle vehicle = request.toEntity();

//        vehicle.setLicensePlate(vehicle.getLicensePlate());
//        vehicle.setManufacture(vehicle.getManufacture());
//        vehicle.setManufactureYears(vehicle.getManufactureYears());
//        vehicle.setOwnerName(vehicle.getOwnerName());

        return vehicleRepository.add(vehicle);
    }

    public List<Vehicle> getType(VehicleType vehicleType) {
        return this.vehicleRepository.getType(vehicleType);
    }

}
