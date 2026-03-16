package module_2.src.bai_tap_lam_them_1.view;

import module_2.src.bai_tap_lam_them_1.controller.BikeController;
import module_2.src.bai_tap_lam_them_1.controller.CarController;
import module_2.src.bai_tap_lam_them_1.controller.IVehicleController;
import module_2.src.bai_tap_lam_them_1.controller.TruckController;
import module_2.src.bai_tap_lam_them_1.entity.Vehicle;
import module_2.src.bai_tap_lam_them_1.until.ConstantsVariables;
import module_2.src.bai_tap_lam_them_1.until.Menu;

import java.util.Map;
import java.util.Scanner;

public class VehicleDisplayView {
    private final Map<String, IVehicleController<? extends Vehicle>> controlllerMap;

    public VehicleDisplayView(Map<String, IVehicleController<? extends Vehicle>> controlllerMap) {
        this.controlllerMap = controlllerMap;
    }

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
                if (controlllerMap.containsKey("truck")) {
                    controlllerMap.get("truck").display();
                }
                break;
            case ConstantsVariables.CAR:
                System.out.println(Menu.getOptionMenu(optionContents[1]));
                if (controlllerMap.containsKey("car")) {
                    controlllerMap.get("car").display();
                }
                break;
            case ConstantsVariables.BIKE:
                System.out.println(Menu.getOptionMenu(optionContents[2]));
                if (controlllerMap.containsKey("bike")) {
                    controlllerMap.get("bike").display();
                }
                break;
            case 4:
                System.out.println(Menu.getOptionMenu(optionContents[3]));
                break;
            default:
                System.out.println("No choice");
        }
    }


}
