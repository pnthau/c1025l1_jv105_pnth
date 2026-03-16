package module_2.src.bai_tap_lam_them_1.view;

import module_2.src.bai_tap_lam_them_1.controller.BikeController;
import module_2.src.bai_tap_lam_them_1.controller.CarController;
import module_2.src.bai_tap_lam_them_1.controller.IController;
import module_2.src.bai_tap_lam_them_1.controller.TruckController;
import module_2.src.bai_tap_lam_them_1.entity.*;
import module_2.src.bai_tap_lam_them_1.until.ConstantsVariables;
import module_2.src.bai_tap_lam_them_1.until.Menu;

import java.util.Map;
import java.util.Scanner;

public class VehicleAddView {
    private final Map<String, IController<? extends Vehicle>> controlllerMap;

    public VehicleAddView(Map<String, IController<? extends Vehicle>> controlllerMap) {
        this.controlllerMap = controlllerMap;
    }

    public void displayAddMenu(String announceContent) {
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
        Scanner scanner = new Scanner(System.in);
        boolean isAdd = false;
        Vehicle vehicleInfor = null;

        String payloadCapacityString = "";
        double payloadCapacity = 0;

        String seatsString = "";
        int seats = 0;
        String type = "";

        String enginePowerString = "";
        double enginePower = 0;
        switch (choice) {
            case ConstantsVariables.TRUCK:
                System.out.println(Menu.getOptionMenu(optionContents[0]));
                vehicleInfor = inputFields();

                System.out.println("Enter payload capacity : ");
                payloadCapacityString = scanner.nextLine();
                payloadCapacity = Double.parseDouble(payloadCapacityString);

                isAdd = ((TruckController) this.controlllerMap.get("truck")).add(
                        new Truck(vehicleInfor.getLicensePlate(),
                                vehicleInfor.getManufacture(),
                                vehicleInfor.getManufactureYears(),
                                vehicleInfor.getOwnerName(),
                                payloadCapacity));
                if (isAdd) {
                    System.out.println("Succeed");
                } else {
                    System.out.println("Fail");
                }
                break;
            case ConstantsVariables.CAR:
                System.out.println(Menu.getOptionMenu(optionContents[1]));
                vehicleInfor = inputFields();

                System.out.println("Enter seats : ");
                seatsString = scanner.nextLine();
                seats = Integer.parseInt(seatsString);
                System.out.println("Enter type : ");
                type = scanner.nextLine();

                isAdd = ((CarController) this.controlllerMap.get("car")).add(
                        new Car(vehicleInfor.getLicensePlate(),
                                vehicleInfor.getManufacture(),
                                vehicleInfor.getManufactureYears(),
                                vehicleInfor.getOwnerName(),
                                seats, type));
                if (isAdd) {
                    System.out.println("Succeed");
                } else {
                    System.out.println("Fail");
                }
                break;
            case ConstantsVariables.BIKE:
                System.out.println(Menu.getOptionMenu(optionContents[2]));
                vehicleInfor = inputFields();

                System.out.println("Enter enginePower : ");
                enginePowerString = scanner.nextLine();
                enginePower = Integer.parseInt(enginePowerString);

                isAdd = ((BikeController) this.controlllerMap.get("car")).add(
                        new Bike(vehicleInfor.getLicensePlate(),
                                vehicleInfor.getManufacture(),
                                vehicleInfor.getManufactureYears(),
                                vehicleInfor.getOwnerName(), enginePower));
                if (isAdd) {
                    System.out.println("Succeed");
                } else {
                    System.out.println("Fail");
                }
                break;
            case 4:
                System.out.println(Menu.getOptionMenu(optionContents[3]));
                break;
            default:
                System.out.println("No choice");
        }
    }

    public static Vehicle inputFields() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter licensePlate : ");
        String licensePlate = scanner.nextLine();

        System.out.println("Enter manufacture id : ");
        String manufactureID = scanner.nextLine();
        System.out.println("Enter manufacture name : ");
        String manufactureName = scanner.nextLine();
        System.out.println("Enter manufacture country: ");
        String manufactureCountry = scanner.nextLine();
        Manufacture manufacture = new Manufacture(manufactureID, manufactureName, manufactureCountry);


        System.out.println("Enter manufactureYears : ");
        String manufactureYears = scanner.nextLine();

        System.out.println("Enter owner name : ");
        String ownerName = scanner.nextLine();

        Vehicle vehicleInfor = new Vehicle() {
            @Override
            public String getLicensePlate() {
                return licensePlate;
            }

            @Override
            public Manufacture getManufacture() {
                return manufacture;
            }

            @Override
            public String getManufactureYears() {
                return manufactureYears;
            }

            @Override
            public String getOwnerName() {
                return ownerName;
            }

        };

        return vehicleInfor;
    }
}
