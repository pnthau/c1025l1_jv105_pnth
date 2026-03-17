package module_2.src.bai_tap_lam_them_1.view;

import module_2.src.bai_tap_lam_them_1.controller.VehicleController;
import module_2.src.bai_tap_lam_them_1.dto.VehicleRequest;
import module_2.src.bai_tap_lam_them_1.entity.*;
import module_2.src.bai_tap_lam_them_1.service.VehicleValidator;
import module_2.src.bai_tap_lam_them_1.util.ConstantsVariables;
import module_2.src.bai_tap_lam_them_1.util.MenuOptions;

import java.util.Scanner;

public class VehicleAddView {
    private final VehicleController controller;

    public VehicleAddView(VehicleController controller) {
        this.controller = controller;
    }

    public void displayAddMenu(String announceContent) {
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
        VehicleRequest request = null;
        boolean result = false;
        switch (choice) {
            case ConstantsVariables.TRUCK:
                System.out.println(MenuOptions.getOptionMenu(optionContents[0]));
                request = inputData(VehicleType.TRUCK);
                result = this.controller.addVehicle(request);
                break;
            case ConstantsVariables.CAR:
                System.out.println(MenuOptions.getOptionMenu(optionContents[1]));
                request = inputData(VehicleType.CAR);
                result = this.controller.addVehicle(request);
                break;
            case ConstantsVariables.BIKE:
                System.out.println(MenuOptions.getOptionMenu(optionContents[2]));
                request = inputData(VehicleType.BIKE);
                result = this.controller.addVehicle(request);
                break;
        }
        System.out.println(result ? "Succeed" : "Fail");
    }

    private VehicleRequest inputData(VehicleType vehicleType) {
        Scanner scanner = ConstantsVariables.scanner;
        VehicleRequest request = new VehicleRequest();

        System.out.println("Enter licensePlate: ");
        request.licensePlate = scanner.nextLine();

        System.out.println("Enter manufacture id: ");
        String id = scanner.nextLine();

        System.out.println("Enter manufacture name: ");
        String name = scanner.nextLine();

        System.out.println("Enter manufacture country: ");
        String country = scanner.nextLine();

        request.manufacture = new Manufacture(id, name, country);

        System.out.println("Enter manufactureYears: ");
        request.manufactureYears = scanner.nextLine();

        System.out.println("Enter owner name: ");
        request.ownerName = scanner.nextLine();

        request.vehicleType = vehicleType;

        switch (vehicleType) {
            case TRUCK:
                System.out.println("Enter payload capacity:");
                request.payloadCapacity = Double.parseDouble(scanner.nextLine());
                break;
            case CAR:
                System.out.println("Enter seats:");
                request.seats = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter type:");
                request.type = scanner.nextLine();
                break;
            case BIKE:
                System.out.println("Enter engine power:");
                request.enginePower = Double.parseDouble(scanner.nextLine());
                break;
        }
        return request;
    }
}
