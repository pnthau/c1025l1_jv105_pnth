package module_2.src.bai_tap_lam_them_1.view;

import module_2.src.bai_tap_lam_them_1.controller.VehicleController;
import module_2.src.bai_tap_lam_them_1.entity.*;
import module_2.src.bai_tap_lam_them_1.service.VehicleFactory;
import module_2.src.bai_tap_lam_them_1.service.VehicleValidate;
import module_2.src.bai_tap_lam_them_1.util.ConstantsVariables;
import module_2.src.bai_tap_lam_them_1.util.MenuOptions;

import java.util.Scanner;

public class VehicleAddView {
    private final VehicleController controller;
    private final VehicleValidate validate;

    public VehicleAddView(VehicleController controller, VehicleValidate validate) {
        this.controller = controller;
        this.validate = validate;
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
                System.out.println(MenuOptions.getOptionMenu(optionContents[0]));
                vehicleInfor = inputFields();
                System.out.println("Enter payload capacity : ");
                payloadCapacityString = scanner.nextLine();
                payloadCapacity = Double.parseDouble(payloadCapacityString);

                Truck truck = (Truck) VehicleFactory.create(VehicleType.TRUCK);
                truck.setLicensePlate(vehicleInfor.getLicensePlate());
                truck.setManufacture(vehicleInfor.getManufacture());
                truck.setManufactureYears(vehicleInfor.getManufactureYears());
                truck.setOwnerName(vehicleInfor.getOwnerName());
                truck.setPayloadCapacity(payloadCapacity);

                isAdd = this.controller.addVehicle((Vehicle) truck);
                if (isAdd) {
                    System.out.println("Succeed");
                } else {
                    System.out.println("Fail");
                }
                break;
            case ConstantsVariables.CAR:
                System.out.println(MenuOptions.getOptionMenu(optionContents[1]));
                vehicleInfor = inputFields();

                System.out.println("Enter seats : ");
                seatsString = scanner.nextLine();
                seats = Integer.parseInt(seatsString);
                System.out.println("Enter type : ");
                type = scanner.nextLine();

                Car car = (Car) VehicleFactory.create(VehicleType.CAR);
                car.setLicensePlate(vehicleInfor.getLicensePlate());
                car.setManufacture(vehicleInfor.getManufacture());
                car.setManufactureYears(vehicleInfor.getManufactureYears());
                car.setOwnerName(vehicleInfor.getOwnerName());
                car.setSeats(seats);
                car.setType(type);

                isAdd = this.controller.addVehicle((Vehicle) car);
                if (isAdd) {
                    System.out.println("Succeed");
                } else {
                    System.out.println("Fail");
                }
                break;
            case ConstantsVariables.BIKE:
                System.out.println(MenuOptions.getOptionMenu(optionContents[2]));
                vehicleInfor = inputFields();
                System.out.println("Enter enginePower : ");
                enginePowerString = scanner.nextLine();
                enginePower = Integer.parseInt(enginePowerString);

                Bike bike = (Bike) VehicleFactory.create(VehicleType.BIKE);
                bike.setLicensePlate(vehicleInfor.getLicensePlate());
                bike.setManufacture(vehicleInfor.getManufacture());
                bike.setManufactureYears(vehicleInfor.getManufactureYears());
                bike.setOwnerName(vehicleInfor.getOwnerName());
                bike.setEnginePower(enginePower);

                isAdd = this.controller.addVehicle((Vehicle) bike);
                if (isAdd) {
                    System.out.println("Succeed");
                } else {
                    System.out.println("Fail");
                }
                break;

            case 4:
                System.out.println(MenuOptions.getOptionMenu(optionContents[3]));
                break;
            default:
                System.out.println("No choice");
        }
    }

    public Vehicle inputFields() {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        String licensePlateValidated = "";
        do {
            System.out.println("Enter licensePlate : ");
            licensePlateValidated = scanner.nextLine();
            boolean isValid = this.validate.isValidLicensePlate(licensePlateValidated);
            if (isValid) {
                flag = false;
            }
        } while (flag);
        String licensePlate = licensePlateValidated;

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

            @Override
            public VehicleType getVehicleType() {
                return null;
            }

        };

        return vehicleInfor;
    }
}
