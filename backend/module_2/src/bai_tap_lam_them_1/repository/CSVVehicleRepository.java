package module_2.src.bai_tap_lam_them_1.repository;

import module_2.src.bai_tap_lam_them_1.criteria.VehicleCriteria;
import module_2.src.bai_tap_lam_them_1.entity.Vehicle;
import module_2.src.bai_tap_lam_them_1.entity.VehicleType;
import module_2.src.bai_tap_lam_them_1.factory.VehicleFactory;
import module_2.src.bai_tap_lam_them_1.util.WriterAndReaderFile;

import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class CSVVehicleRepository implements IVehicleRepository {
    private List<Vehicle> vehicleList;

    public CSVVehicleRepository(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    @Override
    public boolean add(Vehicle vehicle) {
        try {
            WriterAndReaderFile.writeCSVFile(vehicle.getVehicleCSVToString(),
                    FileConfig.VEHICLE_FILE_MAP.get(vehicle.getVehicleType()), true);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(VehicleCriteria criteria) {
        List<Vehicle> allVehicleList = this.getAll();
        int deletedIndex = -1;
        for (int i = 0; i < allVehicleList.size(); i++) {
            boolean isContains = allVehicleList.get(i).contains(criteria.getLicensePlate());
            if (isContains) {
                deletedIndex = i;
                break;
            }
        }
        if (deletedIndex == -1) {
            return false;
        }
        allVehicleList.remove(deletedIndex);
        String vehicleString;
        try {
            for (Vehicle vehicle : allVehicleList) {
                vehicleString = vehicle.getVehicleCSVToString();
                WriterAndReaderFile.writeCSVFile(vehicleString, FileConfig.VEHICLE_FILE_MAP.get(vehicle.getVehicleType()), false);
            }
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Vehicle> getAll() {
        List<Vehicle> allVehicleList = new ArrayList<>();
        for (VehicleType type : Arrays.asList(VehicleType.values())) {
            allVehicleList.addAll(this.getType(type));
        }
        return allVehicleList;
    }

    @Override
    public List<Vehicle> getType(VehicleType type) {
        this.vehicleList = new ArrayList<>();
        String filePath = FileConfig.VEHICLE_FILE_MAP.get(type);
        try {
            List<String> vehiclesStringList = WriterAndReaderFile.readCSVFile(filePath);
            for (String vehicleString : vehiclesStringList) {
                String[] stringArray = vehicleString.split(",");
                Vehicle vehicle = VehicleFactory.create(type, stringArray);
                vehicleList.add(vehicle);
            }

            return Collections.unmodifiableList(vehicleList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public class FileConfig {
        public static final Map<VehicleType, String> VEHICLE_FILE_MAP = Map.of(
                VehicleType.TRUCK, "backend/module_2/src/bai_tap_lam_them_1/data/trucks.csv",
                VehicleType.CAR, "backend/module_2/src/bai_tap_lam_them_1/data/cars.csv",
                VehicleType.BIKE, "backend/module_2/src/bai_tap_lam_them_1/data/bikes.csv",
                VehicleType.ELECTRIC_VEHICLE, "backend/module_2/src/bai_tap_lam_them_1/data/electrics.csv"
        );
    }
}
