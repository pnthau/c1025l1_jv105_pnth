package module_2.src.bai_thi.entity;

public class ImportProduct extends Product {
    private double importDuty;

    public ImportProduct(String code, String name, double price, int quantity, String manufactureCountry, double importDuty) {
        super(code, name, price, quantity, manufactureCountry);
        this.importDuty = importDuty;
    }

    public ImportProduct() {
    }

    public double getImportDuty() {
        return importDuty;
    }

    public void setImportDuty(double importDuty) {
        this.importDuty = importDuty;
    }

    @Override
    public String toString() {
        return String.join(",", super.toString(), this.getImportDuty() + "");
    }

    @Override
    public String getInfoCSVFile() {
        return String.join(",",
                this.getCode(),
                this.getName(),
                this.getPrice() + "",
                this.getQuantity() + "",
                this.getManufactureCountry(),
                this.getImportDuty() + "");
    }

    @Override
    public ProductType getProductType() {
        return ProductType.IMPORT;
    }

}
