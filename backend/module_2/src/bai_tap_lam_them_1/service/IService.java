package module_2.src.bai_tap_lam_them_1.service;

import module_2.src.bai_tap_lam_them_1.entity.Bike;
import module_2.src.bai_tap_lam_them_1.entity.Vehicle;

import java.util.ArrayList;
import java.util.List;

public interface IService<V, ID> {
    List<V> getAll();

    boolean add(V v);

    boolean delete(ID id);

    List<V> findVehicleByLicensePlate(String licensePlate);
}
