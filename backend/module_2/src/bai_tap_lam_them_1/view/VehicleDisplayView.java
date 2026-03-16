package module_2.src.bai_tap_lam_them_1.view;

import module_2.src.bai_tap_lam_them_1.controller.VehicleController;
import module_2.src.bai_tap_lam_them_1.entity.Vehicle;
import module_2.src.bai_tap_lam_them_1.entity.VehicleType;
import module_2.src.bai_tap_lam_them_1.util.ConstantsVariables;
import module_2.src.bai_tap_lam_them_1.util.Menu;

import java.util.Map;
import java.util.Scanner;

public class VehicleDisplayView {
    private VehicleController controller = new VehicleController();

    public void displayViewMenu(String announceContent) {
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
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
                System.out.println(Menu.getOptionMenu(optionContents[0]));
                this.controller.displayType(VehicleType.TRUCK);
                break;
            case ConstantsVariables.CAR:
                System.out.println(Menu.getOptionMenu(optionContents[1]));
                this.controller.displayType(VehicleType.CAR);
                break;
            case ConstantsVariables.BIKE:
                System.out.println(Menu.getOptionMenu(optionContents[2]));
                this.controller.displayType(VehicleType.BIKE);
                break;
            case 4:
                System.out.println(Menu.getOptionMenu(optionContents[3]));
                break;
            default:
                System.out.println("No choice");
        }
    }


}
