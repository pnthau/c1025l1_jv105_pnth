package module_2.src.bai_tap_lam_them_1.service;

import module_2.src.bai_tap_lam_them_1.dto.VehicleRequest;
import module_2.src.bai_tap_lam_them_1.entity.*;

public class VehicleFactory {
    public static Vehicle create(VehicleRequest request) {
        switch (request.vehicleType) {
            case CAR:
                Car car = new Car();
                car.setSeats(request.seats);
                car.setType(request.type);
                return car;
            case BIKE:
                Bike bike = new Bike();
                bike.setEnginePower(request.enginePower);
                return bike;
            case TRUCK:
                Truck truck = new Truck();
                truck.setPayloadCapacity(request.payloadCapacity);
                return truck;
            default:
                throw new IllegalArgumentException("Unknown type");
        }
    }
}
