package module_2.src.bai_thi.repository;

import module_2.src.bai_thi.entity.ExportProduct;
import module_2.src.bai_thi.entity.Product;
import module_2.src.bai_thi.util.ConstantsVariables;
import module_2.src.bai_thi.util.WriterAndReaderFile;
import module_2.src.ss8_clean_code.codegym_manager_mvc.entities.StudentEntity;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExportProductRepository extends ProductRepository {
    private final String filePath = ConstantsVariables.EXPORT_FILE;
    List<Product> exportProducts = new ArrayList<>();

    @Override
    public List<Product> getAll() throws IOException {
        List<String> productsString = WriterAndReaderFile.readCSVFile(filePath);
        for (String productString : productsString) {
            String[] infoProduct = productString.split(",");
            //SP-0004,Ao dai,1000000,50,Viet Nam ,Singapore
            exportProducts.add(new ExportProduct(
                    infoProduct[ConstantsVariables.ID],
                    infoProduct[ConstantsVariables.NAME],
                    Double.parseDouble(infoProduct[ConstantsVariables.PRICE]),
                    Integer.parseInt(infoProduct[ConstantsVariables.QUANTITY]),
                    infoProduct[ConstantsVariables.COUNTRY],
                    infoProduct[ConstantsVariables.IMPORT_COUNTRY]) {
            });
        }
        return exportProducts;
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

//    public void saveAll(List<StudentEntity> students, boolean isAppend) {
//        List<String> studentsString = new ArrayList<>();
//        for (StudentEntity student : students) {
//            studentsString.add(student.getInfoCSVFile());
//        }
//
//        try {
//            module_2.src.ss8_clean_code.codegym_manager_mvc.utils.WriterAndReaderFile.writeCSVFile(studentsString, filePath, isAppend);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
