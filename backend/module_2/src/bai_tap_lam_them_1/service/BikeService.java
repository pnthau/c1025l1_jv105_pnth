package module_2.src.bai_tap_lam_them_1.service;

import module_2.src.bai_tap_lam_them_1.entity.Bike;

import module_2.src.bai_tap_lam_them_1.entity.Truck;
import module_2.src.bai_tap_lam_them_1.entity.Vehicle;
import module_2.src.bai_tap_lam_them_1.reponsitory.BikeRepository;

import module_2.src.bai_tap_lam_them_1.reponsitory.TruckRepository;
import module_2.src.bai_tap_lam_them_1.until.ConstantsVariables;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BikeService implements IBikeService {
    private final BikeRepository bikeRepository;

    public BikeService() {
        this.bikeRepository = new BikeRepository();
    }

    public BikeService(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }


    @Override
    public void add(Vehicle vehicle) {

    }

    @Override
    public Bike findByLicensePlate(String plate) {
        return null;
    }

    @Override
    public boolean deleteByLicensePlate(String plate) {
        return false;
    }

    @Override
    public List getAll() {
        return this.bikeRepository.getAll();
    }


//    @Override
//    public List<Bike> getAll() {
//        return bikeRepository.getAll();
//    }
//
//    @Override
//    public boolean add(Bike bike) {
//        return this.bikeRepository.save(bike);
//    }
//
//    @Override
//    public boolean delete(Integer index) {
//        if (index == ConstantsVariables.NOT_FOUND) {
//            return false;
//        }
//
//        this.bikeRepository.getAll().remove(index);
//        return true;
//    }
//
//    private int findIndexByLicensePlate(String licensePlate) {
//        List<Bike> bikeList = this.bikeRepository.getAll();
//        for (int index = 0; index < bikeList.size(); index++) {
//            if (bikeList.get(index).getLicensePlate().equals(licensePlate)) {
//                return index;
//            }
//        }
//        return ConstantsVariables.NOT_FOUND;
//    }
//
//    public List<Bike> findVehicleByLicensePlate(String licensePlate) {
//        List<Bike> foundList = new ArrayList<>();
//
//        for (Bike bike : this.bikeRepository.getAll()) {
//            if (bike.contain(licensePlate)) {
//                foundList.add(bike);
//            }
//        }
//        return foundList;
//    }
//
//    public Bike findBikeByLicensePlate(String licensePlate) {
//        for (Bike bike : this.bikeRepository.getAll()) {
//            if (bike.getLicensePlate().equals(licensePlate)) {
//                return bike;
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public boolean delete(Vehicle element) {
//        if (element == null) {
//            return false;
//        }
//        return this.bikeRepository.getAll().remove(element);
//    }
}
