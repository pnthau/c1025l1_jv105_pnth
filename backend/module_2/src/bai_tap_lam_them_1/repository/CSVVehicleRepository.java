package module_2.src.bai_tap_lam_them_1.repository;

import module_2.src.bai_tap_lam_them_1.entity.Vehicle;
import module_2.src.bai_tap_lam_them_1.entity.VehicleType;
import module_2.src.bai_tap_lam_them_1.factory.VehicleFactory;
import module_2.src.bai_tap_lam_them_1.util.WriterAndReaderFile;

import java.io.IOException;
import java.util.*;

public class CSVVehicleRepository implements IVehicleRepository {
    private final List<Vehicle> vehicleList;

    public CSVVehicleRepository(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    @Override
    public boolean add(Vehicle vehicle) {
        try {
            WriterAndReaderFile.writeCSVFile(vehicle.getVehicleCSVToString(),
                    FileConfig.VEHICLE_FILE_MAP.get(vehicle.getVehicleType()));
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteByLicensePlate(String plate) {
        return false;
    }

    @Override
    public List<Vehicle> getAll() {
        for (VehicleType type : Arrays.asList(VehicleType.values())) {
            this.vehicleList.addAll(this.getType(type));
        }
        return this.vehicleList;
    }

    @Override
    public List<Vehicle> getType(VehicleType type) {
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
                VehicleType.TRUCK, "backend/module_2/src/bai_tap_lam_them_1/data/trucks.csv"
        );
    }
}
