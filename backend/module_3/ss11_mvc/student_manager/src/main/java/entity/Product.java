package entity;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private int id;
    private int price;
    private String name;
}
