package com.example.repository;

import com.example.entity.RentalSpace;
import java.util.List;

public interface IRentalSpaceRepository {
    List<RentalSpace> findAll(String type, Integer floor);
    boolean save(RentalSpace rentalSpace);
    boolean delete(String code);
}
