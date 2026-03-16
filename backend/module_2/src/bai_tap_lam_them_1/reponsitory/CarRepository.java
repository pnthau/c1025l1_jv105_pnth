package module_2.src.bai_tap_lam_them_1.reponsitory;

import module_2.src.bai_tap_lam_them_1.entity.Manufacture;
import module_2.src.bai_tap_lam_them_1.entity.Car;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CarRepository implements ICarRepository {
    private List<Car> carList;

    public CarRepository(List<Car> list) {
        this.carList = list;
    }

    public CarRepository() {
        this.carList = new ArrayList<>();
        this.carList.add(new Car("1C", new Manufacture("VN", "VietNam", "HCM"), "1", "1", 5, "4"));
        this.carList.add(new Car("1B", new Manufacture("VN", "VietNam", "HCM"), "1", "1", 5, "8"));
        this.carList.add(new Car("1E", new Manufacture("VN", "VietNam", "HCM"), "1", "1", 16, "16"));

    }

    public List<Car> getAll() {
        return this.carList;
    }

    public boolean save(Car car) {
        return this.carList.add(car);
    }
}
