package module_2.src.product_example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductCSVReader {
    public static List<Product> readProductsFromCSV(String filePath) throws IOException {
        List<Product> products = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        boolean isFirstLine = true;
        while ((line = br.readLine()) != null) {
            if (isFirstLine) {
                isFirstLine = false;
                continue;
            }
            String[] values = line.split(",");
            int id = Integer.parseInt(values[0]);
            String name = values[1];
            double price = Double.parseDouble(values[2]);
            products.add(new Product(id, name, price));
        }
        return products;
    }
}
