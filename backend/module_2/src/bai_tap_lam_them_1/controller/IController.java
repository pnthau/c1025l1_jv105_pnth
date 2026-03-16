package module_2.src.bai_tap_lam_them_1.controller;

import module_2.src.bai_tap_lam_them_1.entity.Vehicle;

import java.util.List;

public interface IController<T extends Vehicle> {
    List<T> findVehicleByLicensePlate(String licensePlate);

    boolean add(T vehicle);

    void display();

    boolean delete(Vehicle vehicle);

    T findOnlyVehicleByLicensePlate(String licensePlate);
}
