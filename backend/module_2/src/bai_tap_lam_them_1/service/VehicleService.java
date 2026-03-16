package module_2.src.bai_tap_lam_them_1.service;

import module_2.src.bai_tap_lam_them_1.entity.Truck;
import module_2.src.bai_tap_lam_them_1.entity.Vehicle;
import module_2.src.bai_tap_lam_them_1.repository.VehicleRepository;

import java.util.List;

public class VehicleService implements IVehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleService() {
        this.vehicleRepository = new VehicleRepository();
    }

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public void add(Vehicle vehicle) {

    }

    @Override
    public Vehicle findByLicensePlate(String plate) {
        return this.vehicleRepository.findByLicensePlate(plate);
    }


    @Override
    public boolean deleteByLicensePlate(String plate) {
        return this.vehicleRepository.deleteByLicensePlate(plate);
    }

    @Override
    public List getAll() {
        return this.vehicleRepository.getAll();
    }
}
