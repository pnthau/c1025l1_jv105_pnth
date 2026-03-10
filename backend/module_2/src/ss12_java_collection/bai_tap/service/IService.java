package module_2.src.ss12_java_collection.bai_tap.service;

import java.io.IOException;
import java.util.List;

public interface IService<T, V> {
    boolean add(T t);

    T update(V v, T t);

    boolean delete(V v);

    List<T> getAll();
}
