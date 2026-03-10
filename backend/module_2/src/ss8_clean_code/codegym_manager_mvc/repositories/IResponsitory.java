package module_2.src.ss8_clean_code.codegym_manager_mvc.repositories;

import java.io.IOException;
import java.util.List;

public interface IResponsitory<T> {
    List<T> getAll() throws IOException;
}
