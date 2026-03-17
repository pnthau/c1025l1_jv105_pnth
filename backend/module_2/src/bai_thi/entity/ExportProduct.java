package module_2.src.bai_thi.entity;

public class ExportProduct extends Product {
    private String importingCountry;

    public ExportProduct(String code,
                         String name,
                         double price,
                         int quantity,
                         String manufactureCountry,
                         String importingCountry) {
        super(code, name, price, quantity, manufactureCountry);
        this.importingCountry = importingCountry;
    }

    public ExportProduct() {
    }

    public String getImportingCountry() {
        return importingCountry;
    }

    public void setImportingCountry(String importingCountry) {
        this.importingCountry = importingCountry;
    }

    @Override
    public String toString() {
        return String.join(",", super.toString(), this.getManufactureCountry());
    }

    @Override
    public String getInfoCSVFile() {
        return String.join(",",
                this.getCode(),
                this.getName(),
                this.getPrice() + "",
                this.getQuantity() + "",
                this.getManufactureCountry(),
                this.getImportingCountry());
    }

    @Override
    public ProductType getProductType() {
        return ProductType.EXPORT;
    }
}
