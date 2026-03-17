package module_2.src.bai_tap_lam_them_1.view;

import module_2.src.bai_tap_lam_them_1.controller.VehicleController;
import module_2.src.bai_tap_lam_them_1.service.VehicleValidate;
import module_2.src.bai_tap_lam_them_1.util.ConstantsVariables;

import java.util.Scanner;

public class MenuView {
    private final VehicleController controller;
    private final VehicleValidate vehicleValidate;
    private final VehicleAddView vehicleAddView;
    private final VehicleDisplayView vehicleDisplayView;
    private final VehicleDeletedView vehicleDeletedView;
    private final VehicleFoundView vehicleFoundView;

    public MenuView(VehicleController controller, VehicleValidate vehicleValidate) {
        this.controller = controller;
        this.vehicleValidate = vehicleValidate;
        this.vehicleAddView = new VehicleAddView(controller, vehicleValidate);
        this.vehicleDisplayView = new VehicleDisplayView(controller);
        this.vehicleDeletedView = new VehicleDeletedView(controller, vehicleValidate);
        this.vehicleFoundView = new VehicleFoundView(controller, vehicleValidate);
    }

    public void start() {
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

    private  void createMenu(byte choice) {
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
