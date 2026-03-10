package module_2.src.ss12_java_collection.bai_tap.entities;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class Product implements Comparable, Serializable {
    private String name;
    private double price;
    private int id;
    private String description;
    private String manufacturer;

    public Product() {
    }

    public Product(int id,
                   String name,
                   double price,
                   String description,
                   String manufacturer) {
        this.name = name;
        this.price = price;
        this.id = id;
        this.description = description;
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.join(", ",
                this.id + "",
                this.getName(),
                this.getPrice() + "",
                this.manufacturer,
                this.description);
    }

    @Override
    public int compareTo(@NotNull Object o) {
        Product product = (Product) o;
        return this.getPrice().compareTo(product.getPrice());
    }
}
