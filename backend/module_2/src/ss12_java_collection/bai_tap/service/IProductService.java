package module_2.src.ss12_java_collection.bai_tap.service;

import module_2.src.ss12_java_collection.bai_tap.entities.Product;

import java.util.List;

public interface IProductService extends IService<Product, Integer> {
    @Override
    boolean add(Product product);

    Product findProductByName(String name);

    List<Product> sortProductsByPriceAscending();

    List<Product> sortProductsByPriceDescending();

}
