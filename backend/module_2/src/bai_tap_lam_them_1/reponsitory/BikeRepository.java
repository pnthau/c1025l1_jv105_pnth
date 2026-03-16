package module_2.src.bai_tap_lam_them_1.reponsitory;

import module_2.src.bai_tap_lam_them_1.entity.Manufacture;
import module_2.src.bai_tap_lam_them_1.entity.Bike;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BikeRepository implements IBikeRepository {
    private List<Bike> bikeList;

    public BikeRepository(List<Bike> list) {
        this.bikeList = list;
    }

    public BikeRepository() {
        this.bikeList = new ArrayList<>();
        this.bikeList.add(new Bike("1ABC-12", new Manufacture("VN", "VietNam", "HCM"), "1", "1", 1));
        this.bikeList.add(new Bike("2ABC-13", new Manufacture("VN", "VietNam", "HCM"), "1", "1", 1));
        this.bikeList.add(new Bike("3ABC-11", new Manufacture("VN", "VietNam", "HCM"), "1", "1", 1));
    }

    public List<Bike> getAll() {
        return this.bikeList;
    }

    public boolean save(Bike Bike) {
        return this.bikeList.add(Bike);
    }
}
