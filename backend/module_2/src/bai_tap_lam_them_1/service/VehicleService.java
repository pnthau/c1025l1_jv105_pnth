package module_2.src.bai_tap_lam_them_1.service;

import module_2.src.bai_tap_lam_them_1.dto.VehicleRequest;
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

    public List<Vehicle> findByLicensePlate(String plate) {
        return this.vehicleRepository.findByLicensePlate(plate);
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
    public boolean add(VehicleRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }

        if (!this.vehicleValidator.isValidLicensePlate(request.licensePlate)) {
            throw new IllegalArgumentException("the license plate is invalid");
        }
        
        Vehicle vehicle = VehicleFactory.create(request.vehicleType);

        vehicle.setLicensePlate(request.licensePlate);
        vehicle.setManufacture(request.manufacture);
        vehicle.setManufactureYears(request.manufactureYears);
        vehicle.setOwnerName(request.ownerName);

        switch (request.vehicleType) {
            case TRUCK:
                ((Truck) vehicle).setPayloadCapacity(request.payloadCapacity);
                break;
            case CAR:
                ((Car) vehicle).setSeats(request.seats);
                ((Car) vehicle).setType(request.type);
                break;
            case BIKE:
                ((Bike) vehicle).setEnginePower(request.enginePower);
                break;
        }
        return vehicleRepository.add(vehicle);
    }

    public List<Vehicle> getType(VehicleType vehicleType) {
        return this.vehicleRepository.getType(vehicleType);
    }

}
