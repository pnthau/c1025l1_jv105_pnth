package module_2.src.bai_tap_lam_them_1.reponsitory;

import module_2.src.bai_tap_lam_them_1.entity.Bike;
import module_2.src.bai_tap_lam_them_1.entity.Car;
import module_2.src.bai_tap_lam_them_1.entity.Manufacture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class CarRepository implements ICarRepository {
    private List<Car> carList;

    public CarRepository(List<Car> carList) {
        this.carList = carList;
    }

    public CarRepository() {
        this.carList = new ArrayList<>();
        this.carList.add(new Car("1C", new Manufacture("VN", "VietNam", "HCM"), "1", "1", 1, "1"));
        this.carList.add(new Car("1B", new Manufacture("VN", "VietNam", "HCM"), "1", "1", 1, "1"));
        this.carList.add(new Car("1E", new Manufacture("VN", "VietNam", "HCM"), "1", "1", 1, "1"));
    }

    @Override
    public void add(Car vehicle) {
        this.carList.add(vehicle);
    }

    @Override
    public Car findByLicensePlate(String plate) {
        return this.carList.stream().filter(new Predicate<Car>() {
            @Override
            public boolean test(Car car) {
                return car.getLicensePlate().equals(plate);
            }
        }).findFirst().orElse(null);
    }

    @Override
    public boolean deleteByLicensePlate(String plate) {
        return carList.removeIf(new Predicate<Car>() {
            @Override
            public boolean test(Car truck) {
                return truck.getLicensePlate().equals(plate);
            }
        });
    }

    public List<Car> getAll() {
        return Collections.unmodifiableList(this.carList);
    }
}
