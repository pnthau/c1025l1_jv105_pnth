package repository;

import entity.Product;

import java.util.List;

public interface IProductRepository {
    List<Product> getProductList();

    boolean save(Product product);

    boolean update(Product product, int id);

    boolean remove(int id);

    Product viewProduct(int id);

    List<Product> findProductByName(String name, int categoryId);
}
