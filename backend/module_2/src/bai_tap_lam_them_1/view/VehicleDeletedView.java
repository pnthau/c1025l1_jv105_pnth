package module_2.src.bai_tap_lam_them_1.view;

import module_2.src.bai_tap_lam_them_1.controller.IController;
import module_2.src.bai_tap_lam_them_1.entity.Vehicle;


import java.util.Map;
import java.util.Scanner;

public class VehicleDeletedView {
    private final Map<String, IController<? extends Vehicle>> controlllerMap;


    public VehicleDeletedView(Map<String, IController<? extends Vehicle>> controlllerMap) {
        this.controlllerMap = controlllerMap;
    }

    public void displayDeletedView() {
        Vehicle vehicle = null;
        boolean isDeleted = false;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter licensePlate : ");
        String licensePlate = scanner.nextLine();

        for (IController<? extends Vehicle> controller : controlllerMap.values()) {
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
