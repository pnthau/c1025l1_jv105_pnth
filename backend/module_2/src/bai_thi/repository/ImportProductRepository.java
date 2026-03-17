package module_2.src.bai_thi.repository;

import module_2.src.bai_thi.entity.ExportProduct;
import module_2.src.bai_thi.entity.ImportProduct;
import module_2.src.bai_thi.entity.Product;
import module_2.src.bai_thi.util.ConstantsVariables;
import module_2.src.bai_thi.util.WriterAndReaderFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImportProductRepository extends ProductRepository {
    private final String filePath = ConstantsVariables.IMPORT_FILE;
    List<Product> importProducts = new ArrayList<>();

    @Override
    public List<Product> getAll() throws IOException {
        List<String> productsString = WriterAndReaderFile.readCSVFile(filePath);
        for (String productString : productsString) {
            String[] infoProduct = productString.split(",");
            //SP-0004,Ao dai,1000000,50,Viet Nam ,Singapore
            importProducts.add(new ImportProduct(
                    infoProduct[ConstantsVariables.ID],
                    infoProduct[ConstantsVariables.NAME],
                    Double.parseDouble(infoProduct[ConstantsVariables.PRICE]),
                    Integer.parseInt(infoProduct[ConstantsVariables.QUANTITY]),
                    infoProduct[ConstantsVariables.COUNTRY],
                    Double.parseDouble(infoProduct[ConstantsVariables.IMPORT_DUTY])) {
            });
        }
        return importProducts;
    }

    @Override
    public boolean add(Product product) {
        try {
            WriterAndReaderFile.writeCSVFile(product.getInfoCSVFile(), filePath);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
