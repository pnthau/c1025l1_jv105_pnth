package module_2.src.ss12_java_collection.bai_tap.service;

import module_2.src.ss12_java_collection.bai_tap.entities.Product;
import module_2.src.ss12_java_collection.bai_tap.repositories.ProductRepository;

import java.io.IOException;
import java.util.*;

public class ProductService implements IProductService {
    private final List<Product> products;
    private final int NOT_FOUND = -1;
    private ProductRepository productRepository;

    public ProductService(List<Product> products) {
        this.products = products;
        this.productRepository = new ProductRepository();

    }

    public ProductService() {
        //        this.products = new LinkedList<>();
        this.productRepository = new ProductRepository();
        this.products = this.productRepository.getAll();
    }

    @Override
    public Product findProductByName(String name) {
        for (Product product : this.products) {
            if (product.getName().compareTo(name) == 0) {
                return product;
            }
        }
        return null;
    }

    @Override
    public List<Product> sortProductsByPriceAscending() {
        this.products.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.compareTo(o2);
            }
        });
        return Collections.unmodifiableList(this.products);
    }

    @Override
    public List<Product> sortProductsByPriceDescending() {
        this.products.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o2.compareTo(o1);
            }
        });
        return Collections.unmodifiableList(this.products);
    }

    @Override
    public boolean add(Product product) {
        this.products.add(product);
        this.productRepository.saveAll(this.products);
        return true;
    }

    public void add(List<Product> productList) {
        this.products.addAll(productList);
        this.productRepository.saveAll(this.products);
    }

    @Override
    public Product update(Integer id, Product updatedProduct) {
        int index = this.findProductByID(id);
        if (index == NOT_FOUND) {
            return null;
        }
        Product oldProduct = this.products.get(index);
        oldProduct.setName(updatedProduct.getName());
        oldProduct.setPrice(updatedProduct.getPrice());
        this.productRepository.saveAll(this.products);
        return updatedProduct;
    }

    @Override
    public boolean delete(Integer id) {
        int index = this.findProductByID(id);
        if (index == NOT_FOUND) {
            return false;
        }
        this.products.remove(index);
        this.productRepository.saveAll(this.products);
        return true;
    }

    @Override
    public List<Product> getAll() {
        return Collections.unmodifiableList(this.products);
    }

    private int findProductByID(int id) {
        for (int i = 0; i < this.products.size(); i++) {
            if (this.products.get(i).getId() == id) {
                return i;
            }
        }
        return NOT_FOUND;
    }
}
