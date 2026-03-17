package module_2.src.bai_tap_lam_them_1.view;

import module_2.src.bai_tap_lam_them_1.controller.VehicleController;
import module_2.src.bai_tap_lam_them_1.entity.VehicleType;
import module_2.src.bai_tap_lam_them_1.util.ConstantsVariables;
import module_2.src.bai_tap_lam_them_1.util.MenuOptions;

import java.util.Scanner;

public class VehicleDisplayView {
    private VehicleController controller;

    public VehicleDisplayView(VehicleController controller) {
        this.controller = controller;
    }

    public void displayViewMenu(String announceContent) {
        boolean flag = true;
        Scanner scanner = ConstantsVariables.scanner;
        byte choice = 0;
        while (flag) {
            System.out.println(announceContent);
            choice = Byte.parseByte(scanner.nextLine());
            createMenu(choice, announceContent);
            if (choice == ConstantsVariables.RETURN) {
                flag = false;
            }
        }
    }

    private void createMenu(byte choice, String announceContent) {
        String[] optionContents = announceContent.split("\n");

        switch (choice) {
            case ConstantsVariables.TRUCK:
                System.out.println(MenuOptions.getOptionMenu(optionContents[0]));
                this.controller.displayType(VehicleType.TRUCK).forEach(System.out::println);
                break;
            case ConstantsVariables.CAR:
                System.out.println(MenuOptions.getOptionMenu(optionContents[1]));
                this.controller.displayType(VehicleType.CAR).forEach(System.out::println);
                break;
            case ConstantsVariables.BIKE:
                System.out.println(MenuOptions.getOptionMenu(optionContents[2]));
                this.controller.displayType(VehicleType.BIKE).forEach(System.out::println);
                break;
            case ConstantsVariables.ELECTRIC:
                System.out.println(MenuOptions.getOptionMenu(optionContents[3]));
                this.controller.displayType(VehicleType.ELECTRIC_VEHICLE).forEach(System.out::println);
                break;
            default:
                System.out.println("No choice");
        }
    }


}
