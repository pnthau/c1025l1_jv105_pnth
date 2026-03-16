package module_2.src.bai_tap_lam_them_1.service;

import module_2.src.bai_tap_lam_them_1.entity.Bike;
import module_2.src.bai_tap_lam_them_1.entity.Truck;
import module_2.src.bai_tap_lam_them_1.entity.Vehicle;

public interface ITruckService extends IService<Truck, Integer> {
    Truck findTruckByLicensePlate(String licensePlate);

    boolean delete(Vehicle element);

}
