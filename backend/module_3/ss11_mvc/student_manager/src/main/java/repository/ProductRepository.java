package repository;

import entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class ProductRepository implements IProductRepository {
    // static → list tồn tại xuyên suốt vòng đời ứng dụng, không bị reset mỗi request
    private List<Product> productList = new ArrayList<>();

    public ProductRepository() {
        productList.add(new Product(1, 50000, "Bánh mì"));
        productList.add(new Product(2, 25000, "Cà phê sữa"));
        productList.add(new Product(3, 120000, "Cơm tấm"));
    }

    @Override
    public List<Product> getProductList() {
        return productList;
    }

    @Override
    public boolean save(Product product) {
        return this.productList.add(product);
    }

    @Override
    public boolean update(Product product, int id) {
        boolean flag = false;
        for (Product p : this.productList) {
            if (p.getId() == id) {
                p.setPrice(product.getPrice());
                p.setName(product.getName());
                p.setId(product.getId());
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public boolean remove(int id) {
        return this.productList.removeIf(new Predicate<Product>() {
            @Override
            public boolean test(Product product) {
                return product.getId() == id;
            }
        });
    }

    @Override
    public Product viewProduct(int id) {
        for (Product product : this.productList) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    @Override
    public List<Product> findProductByName(String name) {
        return this.productList.stream()
                .filter(product -> product.getName().toLowerCase().contains(name))
                .toList();
    }
}
