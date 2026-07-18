package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalSpace {
    private Integer id;
    private String code;
    private Double area;
    private String status;
    private Integer floor;
    private String type;
    private Double price;
}
