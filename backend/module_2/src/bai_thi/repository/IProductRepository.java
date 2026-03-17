package module_2.src.bai_thi.repository;


import module_2.src.bai_thi.entity.Product;
import module_2.src.bai_thi.entity.ProductType;

import java.io.IOException;
import java.util.List;

public interface IProductRepository {
    boolean add(Product product);

    public List<Product> getAll() throws IOException;
}
