package module_2.src.bai_tap_lam_them_1.view;

import module_2.src.bai_tap_lam_them_1.controller.VehicleController;
import module_2.src.bai_tap_lam_them_1.dto.*;
import module_2.src.bai_tap_lam_them_1.entity.*;
import module_2.src.bai_tap_lam_them_1.util.ConstantsVariables;
import module_2.src.bai_tap_lam_them_1.util.MenuOptions;

import java.util.Scanner;

public class VehicleAddView {
    private final VehicleController controller;
    private final Scanner scanner = ConstantsVariables.scanner;

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

        IVehicleRequest request = inputData(choice, optionContents);

        if (request == null) {
            System.out.println("Invalid choice!");
            return;
        }

        boolean result = controller.addVehicle(request);
        System.out.println(result ? "Succeed" : "Fail");

    }

    private IVehicleRequest inputData(int choice, String[] optionContents) {
        switch (choice) {
            case ConstantsVariables.TRUCK:
                System.out.println(MenuOptions.getOptionMenu(optionContents[0]));
                return inputTruck();
            case ConstantsVariables.CAR:
                System.out.println(MenuOptions.getOptionMenu(optionContents[1]));
                return inputCar();
            case ConstantsVariables.BIKE:
                System.out.println(MenuOptions.getOptionMenu(optionContents[2]));
                return inputBike();
            default:
                return null;
        }
    }

    private void inputCommon(VehicleRequest request) {
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
    }

    private IVehicleRequest inputTruck() {
        TruckRequest request = new TruckRequest();
        inputCommon(request);

        System.out.println("Enter payload capacity: ");
        request.payloadCapacity = Double.parseDouble(scanner.nextLine());

        return request;
    }

    private IVehicleRequest inputCar() {
        CarRequest request = new CarRequest();
        inputCommon(request);

        System.out.println("Enter seats: ");
        request.seats = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter car type: ");
        request.type = scanner.nextLine();

        return request;
    }

    private IVehicleRequest inputBike() {
        BikeRequest request = new BikeRequest();
        inputCommon(request);

        System.out.println("Enter engine power: ");
        request.enginePower = Double.parseDouble(scanner.nextLine());

        return request;
    }
}
