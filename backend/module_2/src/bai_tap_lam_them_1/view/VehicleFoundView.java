package module_2.src.bai_tap_lam_them_1.view;

import module_2.src.bai_tap_lam_them_1.controller.VehicleController;
import module_2.src.bai_tap_lam_them_1.entity.*;
import module_2.src.bai_tap_lam_them_1.service.VehicleValidate;

import java.util.*;

public class VehicleFoundView {
    private final VehicleController controller;
    private final VehicleValidate validate;

    public VehicleFoundView(VehicleController controller, VehicleValidate validate) {
        this.controller = controller;
        this.validate = validate;
    }

    public void displayFoundVehicleView() {
        Scanner scanner = new Scanner(System.in);
        String licensePlate = scanner.nextLine();
//        boolean isLicensePlateValid = this.validate.isValidLicensePlate(licensePlate);
//        if (!isLicensePlateValid) {
//            System.out.println("The license plate is invalid");
//            return;
//        }

        List<Vehicle> vehicleFoundLicensePlateList = this.controller.findByLicensePlate(licensePlate);
        vehicleFoundLicensePlateList.forEach(System.out::println);

        if (vehicleFoundLicensePlateList.isEmpty()) {
            System.out.println("Not found any vehicles");
        }
    }

}
