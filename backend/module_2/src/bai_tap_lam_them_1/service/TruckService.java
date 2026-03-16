package module_2.src.bai_tap_lam_them_1.service;

import module_2.src.bai_tap_lam_them_1.entity.Car;
import module_2.src.bai_tap_lam_them_1.entity.Truck;
import module_2.src.bai_tap_lam_them_1.entity.Vehicle;
import module_2.src.bai_tap_lam_them_1.reponsitory.TruckRepository;
import module_2.src.bai_tap_lam_them_1.until.ConstantsVariables;

import java.util.ArrayList;
import java.util.List;

public class TruckService implements ITruckService {
    private final TruckRepository truckRepository;

    public TruckService() {
        this.truckRepository = new TruckRepository();
    }

    public TruckService(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }

    @Override
    public List<Truck> getAll() {
        return truckRepository.getAll();
    }

    @Override
    public boolean add(Truck truck) {
        return this.truckRepository.save(truck);
    }

    @Override
    public boolean delete(Integer index) {
        if (index == ConstantsVariables.NOT_FOUND) {
            return false;
        }

        this.truckRepository.getAll().remove(index);
        return true;
    }

    private int findIndexByLicensePlate(String licensePlate) {
        List<Truck> truckList = this.truckRepository.getAll();
        for (int index = 0; index < truckList.size(); index++) {
            if (truckList.get(index).getLicensePlate().equals(licensePlate)) {
                return index;
            }
        }
        return ConstantsVariables.NOT_FOUND;
    }

    public List<Truck> findVehicleByLicensePlate(String licensePlate) {
        List<Truck> foundList = new ArrayList<>();

        for (Truck truck : this.truckRepository.getAll()) {
            if (truck.contain(licensePlate)) {
                foundList.add(truck);
            }
        }
        return foundList;
    }

    @Override
    public Truck findTruckByLicensePlate(String licensePlate) {
        for (Truck truck : this.truckRepository.getAll()) {
            if (truck.getLicensePlate().equals(licensePlate)) {
                return truck;
            }
        }
        return null;
    }

    @Override
    public boolean delete(Vehicle element) {
        if (element == null) {
            return false;
        }
        return this.truckRepository.getAll().remove(element);
    }
}
