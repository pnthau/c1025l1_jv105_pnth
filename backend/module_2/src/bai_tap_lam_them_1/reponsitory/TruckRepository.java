package module_2.src.bai_tap_lam_them_1.reponsitory;

import module_2.src.bai_tap_lam_them_1.entity.Manufacture;
import module_2.src.bai_tap_lam_them_1.entity.Truck;
import module_2.src.bai_tap_lam_them_1.until.ConstantsVariables;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class TruckRepository implements ITruckRepository {
    private List<Truck> truckList;

    public TruckRepository(List<Truck> truckList) {
        this.truckList = truckList;
    }

    public TruckRepository() {
        this.truckList = new ArrayList<>();
        this.truckList.add(new Truck("1C", new Manufacture("VN", "VietNam", "HCM"), "1", "1", 1));
        this.truckList.add(new Truck("1B", new Manufacture("VN", "VietNam", "HCM"), "1", "1", 1));
        this.truckList.add(new Truck("1E", new Manufacture("VN", "VietNam", "HCM"), "1", "1", 1));
    }

    @Override
    public void add(Truck vehicle) {
        this.truckList.add(vehicle);
    }

    @Override
    public Truck findByLicensePlate(String plate) {
        return this.truckList.stream()
                .filter(t -> t.getLicensePlate().equals(plate))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean deleteByLicensePlate(String plate) {
        return truckList.removeIf(new Predicate<Truck>() {
            @Override
            public boolean test(Truck truck) {
                return truck.getLicensePlate().equals(plate);
            }
        });
    }

    public List<Truck> getAll() {
        return Collections.unmodifiableList(this.truckList);
    }

}
