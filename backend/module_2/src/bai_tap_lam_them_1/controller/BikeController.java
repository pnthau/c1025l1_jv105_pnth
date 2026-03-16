package module_2.src.bai_tap_lam_them_1.controller;

import module_2.src.bai_tap_lam_them_1.entity.Bike;
import module_2.src.bai_tap_lam_them_1.entity.Vehicle;
import module_2.src.bai_tap_lam_them_1.service.BikeService;
import module_2.src.bai_tap_lam_them_1.service.IBikeService;

import java.util.List;

public class BikeController implements IVehicleController<Bike> {
    private IBikeService service;

    public BikeController() {
        service = new BikeService();
    }

    public BikeController(IBikeService service) {
        this.service = service;
    }

    public void display() {
        for (Bike bike : service.getAll()) {
            System.out.println(bike.toString());
        }
    }


    @Override
    public boolean delete(Vehicle bike) {
        return this.service.delete(bike);
    }

    @Override
    public boolean add(Bike bike) {
        return this.service.add(bike);
    }

    public boolean delete(Integer index) {
        return this.service.delete(index);
    }

    @Override
    public List<Bike> findVehicleByLicensePlate(String licensePlate) {
        return this.service.findVehicleByLicensePlate(licensePlate);
    }

    @Override
    public Bike findOnlyVehicleByLicensePlate(String licensePlate) {
        return this.service.findBikeByLicensePlate(licensePlate);
    }
}
