package module_2.src.bai_thi.service;

import module_2.src.bai_thi.entity.Product;
import module_2.src.bai_thi.repository.ExportProductRepository;
import module_2.src.bai_thi.repository.ProductRepository;

import java.io.IOException;
import java.util.List;

public class ImportProductRepository {
    private final int NOT_FOUND = -1;
    private List<Product> products;
    private ProductRepository productRepository = new ExportProductRepository();

    public ImportProductRepository() {
        try {
            this.products = productRepository.getAll();
        } catch (IOException e) {
            throw new RuntimeException("Not read filePath", e);
        }

    }

    //    @Override
    public List<Product> getAll() {
        return this.products;
    }

    //    @Override
    public void add(Product product) {
        productRepository.add(product);
    }
}
