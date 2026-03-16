package module_2.src.bai_tap_lam_them_1.repository;

import module_2.src.bai_tap_lam_them_1.entity.Vehicle;

import java.util.List;

public interface IRepository<T extends Vehicle> {
    void add(T vehicle);

    T findByLicensePlate(String plate);

    boolean deleteByLicensePlate(String plate);

    List<T> getAll();
}
