package module_2.src.bai_tap_lam_them_1.view;

import module_2.src.bai_tap_lam_them_1.controller.VehicleController;
import module_2.src.bai_tap_lam_them_1.entity.*;
import module_2.src.bai_tap_lam_them_1.service.VehicleValidator;
import module_2.src.bai_tap_lam_them_1.util.ConstantsVariables;

import java.util.*;

public class VehicleFoundView {
    private final VehicleController controller;

    public VehicleFoundView(VehicleController controller) {
        this.controller = controller;
    }

    public void displayFoundVehicleView() {
        Scanner scanner = ConstantsVariables.scanner;
        String licensePlate = scanner.nextLine();

        List<Vehicle> vehicleFoundLicensePlateList = this.controller.findByLicensePlate(licensePlate);
        vehicleFoundLicensePlateList.forEach(System.out::println);

        if (vehicleFoundLicensePlateList.isEmpty()) {
            System.out.println("Not found any vehicles");
        }
    }

}
