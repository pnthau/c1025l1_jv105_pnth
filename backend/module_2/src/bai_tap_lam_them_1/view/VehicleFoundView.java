package module_2.src.bai_tap_lam_them_1.view;

import module_2.src.bai_tap_lam_them_1.controller.IVehicleController;
import module_2.src.bai_tap_lam_them_1.entity.*;

import java.util.*;

public class VehicleFoundView {
    Map<String, IVehicleController<? extends Vehicle>> controllerMap;

    public VehicleFoundView() {
        this.controllerMap = new HashMap<>();
    }

    public VehicleFoundView(Map<String, IVehicleController<? extends Vehicle>> controllerMap) {
        this.controllerMap = controllerMap;
    }

    public void displayFoundVehicleView() {
        Scanner scanner = new Scanner(System.in);
        String licensePlate = scanner.nextLine();

        List<Vehicle> vehiclesList = new ArrayList<>();
        for (IVehicleController<? extends Vehicle> controller : controllerMap.values()) {
            vehiclesList.addAll(controller.findVehicleByLicensePlate(licensePlate));
        }

        displayVehicleList(vehiclesList);

        if (vehiclesList.isEmpty()) {
            System.out.println("Not found any vehicles");
        }
    }

    private void displayVehicleList(List<? extends Vehicle> vehicles) {
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }
    }
}
