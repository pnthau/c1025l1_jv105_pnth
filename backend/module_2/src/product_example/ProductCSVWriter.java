package module_2.src.product_example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ProductCSVWriter {
    public static void writeProductsToCSV(List<Product> products, String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            // Ghi tiêu đề cột
            bw.write("ID,Name,Price");
            bw.newLine();
            // Ghi từng sản phẩm
            for (Product p : products) {
                bw.write(p.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
