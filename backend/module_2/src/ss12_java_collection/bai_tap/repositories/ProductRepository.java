package module_2.src.ss12_java_collection.bai_tap.repositories;

import module_2.src.ss12_java_collection.bai_tap.entities.Product;
import module_2.src.ss12_java_collection.bai_tap.utils.WriterAndReaderFile;


import java.io.IOException;
import java.util.List;

public class ProductRepository {
    private final String filePath = "backend/module_2/src/ss12_java_collection/bai_tap/data/product.dat";

    public List<Product> getAll() {
        try {
            return WriterAndReaderFile.readBinaryFileToObject(filePath);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveAll(List<Product> productList) {
        try {
            WriterAndReaderFile.writeObjectToBinaryFile(filePath, productList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
