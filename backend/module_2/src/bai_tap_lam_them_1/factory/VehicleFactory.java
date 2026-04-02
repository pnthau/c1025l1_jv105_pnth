package module_2.src.bai_tap_lam_them_1.factory;

import module_2.src.bai_tap_lam_them_1.dto.IVehicleRequest;
import module_2.src.bai_tap_lam_them_1.dto.VehicleRequest;
import module_2.src.bai_tap_lam_them_1.entity.*;

public class VehicleFactory {
    public static Vehicle create(VehicleType type, String[] data) {
        Vehicle vehicle = null;
        Manufacture manufacture = new Manufacture(data[1], data[2], data[3]);
        switch (type) {
            case TRUCK:
                vehicle = new Truck(data[0], manufacture, data[4], data[5], Double.parseDouble(data[6]));
                break;
            case CAR:
                vehicle = new Car(data[0], manufacture, data[4], data[5], Integer.parseInt(data[6]), data[7]);
                break;
            case ELECTRIC_VEHICLE:
                vehicle = new ElectricVehicle(data[0], manufacture, data[4], data[5], Double.parseDouble(data[6]));
                break;
            case BIKE:
                vehicle = new Bike(data[0], manufacture, data[4], data[5], Double.parseDouble(data[6]));
                break;
            default:
                break;

        }
        return vehicle;
    }
}
