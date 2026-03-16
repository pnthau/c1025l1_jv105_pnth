package module_2.src.bai_tap_lam_them_1.controller;

import module_2.src.bai_tap_lam_them_1.entity.Car;
import module_2.src.bai_tap_lam_them_1.entity.Vehicle;
import module_2.src.bai_tap_lam_them_1.service.CarService;
import module_2.src.bai_tap_lam_them_1.service.ICarService;

import java.util.List;

public class CarController implements IVehicleController<Car> {
    private ICarService service;

    public CarController() {
        service = new CarService();
    }

    public CarController(ICarService service) {
        this.service = service;
    }

    public void display() {
        for (Car Car : service.getAll()) {
            System.out.println(Car.toString());
        }
    }


    @Override
    public boolean delete(Vehicle car) {
        return this.service.delete(car);
    }

    @Override
    public boolean add(Car Car) {
        return this.service.add(Car);
    }

    public boolean delete(Integer index) {

        return this.service.delete(index);
    }

    @Override
    public List<Car> findVehicleByLicensePlate(String licensePlate) {
        return this.service.findVehicleByLicensePlate(licensePlate);
    }

    @Override
    public Car findOnlyVehicleByLicensePlate(String licensePlate) {
        return this.service.findCarByLicensePlate(licensePlate);
    }
}
