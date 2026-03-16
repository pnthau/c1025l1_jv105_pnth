package module_2.src.bai_tap_lam_them_1.service;

import module_2.src.bai_tap_lam_them_1.entity.*;

public class VehicleFactory {
    public static Vehicle create(VehicleType type) {
        switch (type) {
            case CAR:
                return new Car();
            case BIKE:
                return new Bike();
            case TRUCK:
                return new Truck();
            default:
                throw new IllegalArgumentException("Unknown type");
        }
    }
}
