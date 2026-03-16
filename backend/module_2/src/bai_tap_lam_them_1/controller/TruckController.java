package module_2.src.bai_tap_lam_them_1.controller;

import module_2.src.bai_tap_lam_them_1.entity.Truck;
import module_2.src.bai_tap_lam_them_1.entity.Vehicle;
import module_2.src.bai_tap_lam_them_1.service.ITruckService;
import module_2.src.bai_tap_lam_them_1.service.TruckService;

import java.util.List;

public class TruckController implements IVehicleController<Truck> {
    private ITruckService service;

    public TruckController() {
        service = new TruckService();
    }

    public TruckController(ITruckService service) {
        this.service = service;
    }

    public void display() {
        for (Truck truck : service.getAll()) {
            System.out.println(truck.toString());
        }
    }

    @Override
    public boolean delete(Vehicle truck) {
        return this.service.delete(truck);
    }

    @Override
    public boolean add(Truck truck) {
        return this.service.add(truck);
    }

    public boolean delete(Integer index) {
        return this.service.delete(index);
    }

    @Override
    public List<Truck> findVehicleByLicensePlate(String licensePlate) {
        return this.service.findVehicleByLicensePlate(licensePlate);
    }

    @Override
    public Truck findOnlyVehicleByLicensePlate(String licensePlate) {
        return this.service.findTruckByLicensePlate(licensePlate);
    }
}
