package service;

import entity.Product;
import repository.IProductRepository;
import repository.ProductRepository;

import java.util.List;

public class ProductService implements IProductService {
    private final IProductRepository productRepository = new ProductRepository();

    @Override
    public List<Product> getProductList() {
        return productRepository.getProductList();
    }

    @Override
    public boolean save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public boolean update(Product product, int id) {
        return productRepository.update(product, id);
    }

    @Override
    public boolean remove(int id) {
        return productRepository.remove(id);
    }

    @Override
    public Product viewProduct(int id) {
        return productRepository.viewProduct(id);
    }

    @Override
    public List<Product> findProductByName(String name, int categoryId) {
        return productRepository.findProductByName(name, categoryId);
    }
}
