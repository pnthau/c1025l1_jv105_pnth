package module_2.src.bai_thi.view;

import module_2.src.bai_thi.controller.ExportProductController;
import module_2.src.bai_thi.entity.ExportProduct;
import module_2.src.bai_thi.util.ConstantsVariables;

import java.util.Scanner;

public class MenuView {
    //    private final VehicleController controller;
//    private final VehicleAddView vehicleAddView;
//    private final VehicleDisplayView vehicleDisplayView;
//    private final VehicleDeletedView vehicleDeletedView;
//    private final VehicleFoundView vehicleFoundView;
//
//    public MenuView(VehicleController controller) {
//        this.controller = controller;
//        this.vehicleAddView = new VehicleAddView(controller);
//        this.vehicleDisplayView = new VehicleDisplayView(controller);
//        this.vehicleDeletedView = new VehicleDeletedView(controller);
//        this.vehicleFoundView = new VehicleFoundView(controller);
//    }
//    private final ExportProductController exportProductController = new ExportProductController();

    public void start() {
        boolean flag = true;
        Scanner scanner = ConstantsVariables.scanner;
        byte choice = 0;
        while (flag) {
            System.out.println("" +
                    "1. Add a new product \n" +
                    "2. Delete product\n" +
                    "3. Display product\n" +
                    "4. Find by the name\n" +
                    "5. Exit ");
            choice = Byte.parseByte(scanner.nextLine());
            createMenu(choice);
            if (choice == ConstantsVariables.EXIT) {
                flag = false;
            }
        }
    }

    private void createMenu(byte choice) {
        switch (choice) {
            case ConstantsVariables.ADD:
                System.out.println("Add a new product");

//                vehicleAddView.displayAddMenu(
//                        "1. Add a truck \n" +
//                                "2. Add a car \n" +
//                                "3. Add a bike \n" +
//                                "4. Add a electric \n" +
//                                "5. Back the main menu \n");
                break;
            case ConstantsVariables.DISPLAY:
                System.out.println("Display vehicle");
//                vehicleDisplayView.displayViewMenu(
//                        "1. Display trucks \n" +
//                                "2. Display cars \n" +
//                                "3. Display bikes \n" +
//                                "4. Display electrics \n" +
//                                "5. Back the main menu \n");
                break;
            case ConstantsVariables.DELETE:
                System.out.println("Delete vehicle");
//                vehicleDeletedView.displayDeletedView();
                break;
            case ConstantsVariables.FIND:
                System.out.println("Find by license plate");
//                vehicleFoundView.displayFoundVehicleView();
                break;
            case ConstantsVariables.EXIT:
                System.out.println("Exit");
                break;
            default:
                System.out.println("No choice");
        }
    }

}
