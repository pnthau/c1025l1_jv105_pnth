package module_2.src.bai_thi.repository;

import module_2.src.bai_thi.entity.ExportProduct;
import module_2.src.bai_thi.entity.Product;
import module_2.src.bai_thi.entity.ProductType;
import module_2.src.bai_thi.util.ConstantsVariables;
import module_2.src.bai_thi.util.WriterAndReaderFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class ProductRepository implements IProductRepository {
//    private List<Product> productArrayList = new ArrayList<>();
//
//    @Override
//    public boolean add(Product vehicle) {
//        return this.productArrayList.add(vehicle);
//    }
//
//    @Override
//    public List<Product> getType(ProductType type) throws IOException {
//        String filePath = "";
//
//        if (ProductType.EXPORT == type) {
//            filePath = ConstantsVariables.EXPORT_FILE;
//
//
//            productArrayList.addAll(exportProducts);
//        } else {
//            filePath = ConstantsVariables.IMPORT_FILE;
//
//            List<Product> importProducts = new ArrayList<>();
//
//            List<String> productsString = WriterAndReaderFile.readCSVFile(filePath);
//            for (String productString : productsString) {
//                String[] infoProduct = productString.split(",");
//                //SP-0004,Ao dai,1000000,50,Viet Nam ,Singapore
//                importProducts.add(new ExportProduct(
//                        infoProduct[ConstantsVariables.ID],
//                        infoProduct[ConstantsVariables.NAME],
//                        Double.parseDouble(infoProduct[ConstantsVariables.PRICE]),
//                        Integer.parseInt(infoProduct[ConstantsVariables.QUANTITY]),
//                        infoProduct[ConstantsVariables.COUNTRY],
//                        infoProduct[ConstantsVariables.IMPORT_COUNTRY]) {
//                });
//            }
//
//            productArrayList.addAll(importProducts);
//        }
//
//
//        return products;
//    }

}
