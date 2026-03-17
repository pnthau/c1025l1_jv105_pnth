package module_2.src.bai_thi.entity;

import java.io.Serializable;

public abstract class Product implements Serializable {
    //    Mã sản phẩm, Tên sản phẩm, Giá sản phẩm, Số lượng, Quốc
//    gia sản xuất
    private String code;
    private String name;
    private double price;
    private int quantity;
    private String manufactureCountry;

    public Product(String code, String name, double price, int quantity, String manufactureCountry) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.manufactureCountry = manufactureCountry;
    }

    public Product() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getManufactureCountry() {
        return manufactureCountry;
    }

    public void setManufactureCountry(String manufactureCountry) {
        this.manufactureCountry = manufactureCountry;
    }

    @Override
    public String toString() {
        return String.join(",", this.getCode(), this.getName(), this.getPrice() + "", this.getQuantity() + "");
    }

    public abstract String getInfoCSVFile() ;

    public abstract ProductType getProductType();
}
