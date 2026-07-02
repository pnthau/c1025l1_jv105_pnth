package entity;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private int id;
    private double price;
    private String name;
    private int categoryId;
}
