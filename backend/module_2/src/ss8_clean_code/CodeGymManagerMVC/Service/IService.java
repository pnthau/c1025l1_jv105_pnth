package module_2.src.ss8_clean_code.CodeGymManagerMVC.Service;

import java.util.List;

public interface IService<T> {
    void getAll();

    void add(T entity);

    void delete(String id);

    T update(String id);

    List<T> find(String fullName);
}
