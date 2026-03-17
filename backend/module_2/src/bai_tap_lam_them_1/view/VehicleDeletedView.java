package module_2.src.bai_tap_lam_them_1.view;

import module_2.src.bai_tap_lam_them_1.controller.VehicleController;
import module_2.src.bai_tap_lam_them_1.entity.Vehicle;
import module_2.src.bai_tap_lam_them_1.service.VehicleValidate;


import java.util.Map;
import java.util.Scanner;

public class VehicleDeletedView {
    private final VehicleController controller;
    private final VehicleValidate validate;

    public VehicleDeletedView(VehicleController controller, VehicleValidate validate) {
        this.controller = controller;
        this.validate = validate;
    }

    public void displayDeletedView() {
        Vehicle vehicle = null;
        boolean isDeleted = false;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter licensePlate : ");
        String licensePlate = scanner.nextLine();
        boolean isLicensePlateValid = this.validate.isValidLicensePlate(licensePlate);
        if (!isLicensePlateValid) {
            System.out.println("the license plate is invalid");
            return;
        }

        isDeleted = this.controller.deleteByLicensePlate(licensePlate);

        if (isDeleted) {
            System.out.println("Succeed");
        } else {
            System.out.println("Fail");
        }
    }
}
