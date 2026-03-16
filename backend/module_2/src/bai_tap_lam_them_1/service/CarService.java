package module_2.src.bai_tap_lam_them_1.service;


import module_2.src.bai_tap_lam_them_1.entity.Car;

import module_2.src.bai_tap_lam_them_1.entity.Vehicle;
import module_2.src.bai_tap_lam_them_1.reponsitory.CarRepository;

import module_2.src.bai_tap_lam_them_1.until.ConstantsVariables;

import java.util.ArrayList;
import java.util.List;

public class CarService implements ICarService {
    private final CarRepository carRepository;

    public CarService() {
        this.carRepository = new CarRepository();
    }

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void add(Car vehicle) {

    }

    @Override
    public Car findByLicensePlate(String plate) {
        return null;
    }

    @Override
    public boolean deleteByLicensePlate(String plate) {
        return false;
    }

    @Override
    public List<Car> getAll() {
        return List.of();
    }

//    @Override
//    public List<Car> getAll() {
//        return carRepository.getAll();
//    }
//
//    @Override
//    public boolean add(Car truck) {
//        return this.carRepository.save(truck);
//    }
//
//    @Override
//    public boolean delete(Integer index) {
//        if (index == ConstantsVariables.NOT_FOUND) {
//            return false;
//        }
//
//        this.carRepository.getAll().remove(index);
//        return true;
//    }
//
//    private int findIndexByLicensePlate(String licensePlate) {
//        List<Car> truckList = this.carRepository.getAll();
//        for (int index = 0; index < truckList.size(); index++) {
//            if (truckList.get(index).getLicensePlate().equals(licensePlate)) {
//                return index;
//            }
//        }
//        return ConstantsVariables.NOT_FOUND;
//    }
//
//    public List<Car> findVehicleByLicensePlate(String licensePlate) {
//        List<Car> foundList = new ArrayList<>();
//
//        for (Car car : this.carRepository.getAll()) {
//            if (car.contain(licensePlate)) {
//                foundList.add(car);
//            }
//        }
//        return foundList;
//    }
//
//
//    @Override
//    public Car findCarByLicensePlate(String licensePlate) {
//        for (Car car : this.carRepository.getAll()) {
//            if (car.getLicensePlate().equals(licensePlate)) {
//                return car;
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
//        return this.carRepository.getAll().remove(element);
//    }
}
