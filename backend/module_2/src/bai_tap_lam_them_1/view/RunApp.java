package module_2.src.bai_tap_lam_them_1.view;

import module_2.src.bai_tap_lam_them_1.controller.BikeController;
import module_2.src.bai_tap_lam_them_1.controller.CarController;
import module_2.src.bai_tap_lam_them_1.controller.TruckController;
import module_2.src.bai_tap_lam_them_1.controller.IVehicleController;
import module_2.src.bai_tap_lam_them_1.entity.Vehicle;
import module_2.src.bai_tap_lam_them_1.until.ConstantsVariables;

import java.util.*;

public class RunApp {
    private static final CarController carController = new CarController();
    private static final BikeController bikeController = new BikeController();
    private static final TruckController truckController = new TruckController();

    private static final VehicleAddView vehicleAddView;
    private static final VehicleDisplayView vehicleDisplayView;
    private static final VehicleDeletedView vehicleDeletedView;
    private static final VehicleFoundView vehicleFoundView;

    private static final Map<String, IVehicleController<? extends Vehicle>> controlllerMap;

    static {
        controlllerMap = new HashMap<>();
        controlllerMap.put("car", carController);
        controlllerMap.put("bike", bikeController);
        controlllerMap.put("truck", truckController);

        vehicleAddView = new VehicleAddView(controlllerMap);
        vehicleDisplayView = new VehicleDisplayView(controlllerMap);
        vehicleDeletedView = new VehicleDeletedView(controlllerMap);
        vehicleFoundView = new VehicleFoundView(controlllerMap);
    }


    public static void main(String[] args) {
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        byte choice = 0;
        while (flag) {
            System.out.println("" +
                    "1. Add a new vehicle \n" +
                    "2. Display vehicle\n" +
                    "3. Delete vehicle\n" +
                    "4. Find by the license plate\n" +
                    "5. Exit ");
            choice = Byte.parseByte(scanner.nextLine());
            createMenu(choice);
            if (choice == ConstantsVariables.EXIT) {
                flag = false;
            }
        }
    }

    private static void createMenu(byte choice) {

        switch (choice) {
            case 1:
                System.out.println("Add a new vehicle");
                vehicleAddView.displayAddMenu(
                        "1. Add a truck \n" +
                                "2. Add a car \n" +
                                "3. Add a bike \n" +
                                "4. Back the main menu \n");
                break;
            case 2:
                System.out.println("Display vehicle");
                vehicleDisplayView.displayViewMenu(
                        "1. Display truck \n" +
                                "2. Display car \n" +
                                "3. Display bike \n" +
                                "4. Back the main menu \n");
                break;
            case 3:
                System.out.println("Delete vehicle");
                vehicleDeletedView.displayDeletedView();
                break;
            case 4:
                System.out.println("Find by license plate");
                vehicleFoundView.displayFoundVehicleView();
                break;
            case 5:
                System.out.println("Exit");
                break;
            default:
                System.out.println("No choice");
        }
    }
}
