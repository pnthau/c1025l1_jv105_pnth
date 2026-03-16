package module_2.src.bai_tap_lam_them_1.reponsitory;

import module_2.src.bai_tap_lam_them_1.entity.Manufacture;
import module_2.src.bai_tap_lam_them_1.entity.Bike;
import module_2.src.bai_tap_lam_them_1.entity.Bike;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class BikeRepository implements IBikeRepository {
    private List<Bike> bikeList;

    public BikeRepository(List<Bike> truckList) {
        this.bikeList = truckList;
    }

    public BikeRepository() {
        this.bikeList = new ArrayList<>();
        this.bikeList.add(new Bike("1C", new Manufacture("VN", "VietNam", "HCM"), "1", "1", 1));
        this.bikeList.add(new Bike("1B", new Manufacture("VN", "VietNam", "HCM"), "1", "1", 1));
        this.bikeList.add(new Bike("1E", new Manufacture("VN", "VietNam", "HCM"), "1", "1", 1));

    }

    @Override
    public void add(Bike vehicle) {
        this.bikeList.add(vehicle);
    }

    @Override
    public Bike findByLicensePlate(String plate) {
        return bikeList.stream()
                .filter(b -> b.getLicensePlate().equals(plate))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean deleteByLicensePlate(String plate) {
        return bikeList.removeIf(new Predicate<Bike>() {
            @Override
            public boolean test(Bike truck) {
                return truck.getLicensePlate().equals(plate);
            }
        });
    }

    public List<Bike> getAll() {
        return Collections.unmodifiableList(this.bikeList);
    }
}
