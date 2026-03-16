package module_2.src.bai_tap_lam_them_1.view;

import module_2.src.bai_tap_lam_them_1.controller.BikeController;
import module_2.src.bai_tap_lam_them_1.controller.CarController;
import module_2.src.bai_tap_lam_them_1.controller.IVehicleController;
import module_2.src.bai_tap_lam_them_1.controller.TruckController;
import module_2.src.bai_tap_lam_them_1.entity.Bike;
import module_2.src.bai_tap_lam_them_1.entity.Car;
import module_2.src.bai_tap_lam_them_1.entity.Truck;
import module_2.src.bai_tap_lam_them_1.entity.Vehicle;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VehicleDeletedView {
    private final Map<String, IVehicleController<? extends Vehicle>> controlllerMap;


    public VehicleDeletedView(Map<String, IVehicleController<? extends Vehicle>> controlllerMap) {
        this.controlllerMap = controlllerMap;
    }

    public void displayDeletedView() {
        Vehicle vehicle = null;
        boolean isDeleted = false;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter licensePlate : ");
        String licensePlate = scanner.nextLine();

        for (IVehicleController<? extends Vehicle> controller : controlllerMap.values()) {
            vehicle = controller.findOnlyVehicleByLicensePlate(licensePlate);
            if (vehicle != null) {
                System.out.println("Enter yes/no : ");
                String answer = scanner.nextLine();
                if (answer.equals("yes")) {
                    controller.delete(vehicle);
                    isDeleted = true;
                    break;
                }
                isDeleted = false;
                break;
            }
        }

        if (isDeleted) {
            System.out.println("Succeed");
        } else {
            System.out.println("Fail");
        }
    }
}
