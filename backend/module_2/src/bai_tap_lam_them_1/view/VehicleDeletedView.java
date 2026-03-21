package module_2.src.bai_tap_lam_them_1.view;

import module_2.src.bai_tap_lam_them_1.controller.VehicleController;
import module_2.src.bai_tap_lam_them_1.entity.Vehicle;
import module_2.src.bai_tap_lam_them_1.service.VehicleValidator;
import module_2.src.bai_tap_lam_them_1.util.ConstantsVariables;


import java.util.Scanner;

public class VehicleDeletedView {
    private final VehicleController controller;

    public VehicleDeletedView(VehicleController controller) {
        this.controller = controller;
    }

    public void displayDeletedView() {
        Vehicle vehicle = null;
        boolean isDeleted = false;
        Scanner scanner = ConstantsVariables.scanner;

        System.out.println("Enter licensePlate : ");
        String licensePlate = scanner.nextLine();

        try {
            isDeleted = this.controller.deleteByLicensePlate(licensePlate);
            if (isDeleted) {
                System.out.println("Succeed");
            } else {
                System.out.println("Fail");
            }
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }


    }
}
