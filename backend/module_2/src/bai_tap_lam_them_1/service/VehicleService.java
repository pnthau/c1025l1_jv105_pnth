package module_2.src.bai_tap_lam_them_1.service;

import module_2.src.bai_tap_lam_them_1.criteria.VehicleCriteria;
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
        if (!this.vehicleValidator.isValidLicensePlate(vehicle.getLicensePlate())) {
            throw new IllegalArgumentException("the license plate is invalid");
        }
        return this.vehicleRepository.add(vehicle);
    }

    @Override
    public List<Vehicle> find(VehicleCriteria criteria) {
        if (!this.vehicleValidator.isValidLicensePlate(criteria.getLicensePlate())) {
            throw new IllegalArgumentException("the license plate is invalid");
        }
        return this.vehicleRepository.getAll().
                stream().
                filter(v -> v.contains(criteria.getLicensePlate())).
                toList();
    }

    public boolean delete(VehicleCriteria criteria) {
        if (!this.vehicleValidator.isValidLicensePlate(criteria.getLicensePlate())) {
            throw new IllegalArgumentException("the license plate is invalid");
        }
        return this.vehicleRepository.delete(criteria);
    }

    public List<Vehicle> getAll() {
        return this.vehicleRepository.getAll();
    }

    @Override
    public boolean add(IVehicleRequest request) {
        if (!this.vehicleValidator.isValidLicensePlate(request.toEntity().getLicensePlate())) {
            throw new IllegalArgumentException("the license plate is invalid");
        }
        Vehicle vehicle = request.toEntity();
        return vehicleRepository.add(vehicle);
    }

    public List<Vehicle> getType(VehicleType vehicleType) {
        return this.vehicleRepository.getType(vehicleType);
    }

}
