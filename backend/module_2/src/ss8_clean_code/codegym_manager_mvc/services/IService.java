package module_2.src.ss8_clean_code.codegym_manager_mvc.services;

import java.util.List;

public interface IService<T> {
    List<T> getAll();

    void add(T entity);

    boolean delete(String id);

    T update(String id, T entity);

    List<T> find(String fullName);
}
