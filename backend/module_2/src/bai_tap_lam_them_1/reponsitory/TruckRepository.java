package module_2.src.bai_tap_lam_them_1.reponsitory;

import module_2.src.bai_tap_lam_them_1.entity.Manufacture;
import module_2.src.bai_tap_lam_them_1.entity.Truck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public List<Truck> getAll() {
        return this.truckList;
    }

    public boolean save(Truck truck) {
        return this.truckList.add(truck);
    }
}
