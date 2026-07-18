package com.example.service;

import com.example.entity.RentalSpace;
import com.example.repository.IRentalSpaceRepository;
import com.example.repository.RentalSpaceRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RentalSpaceService {
    private IRentalSpaceRepository repository = new RentalSpaceRepository();

    public List<RentalSpace> findAll(String type, Integer floor) {
        return repository.findAll(type, floor);
    }

    public Map<String, String> save(RentalSpace rentalSpace) {
        Map<String, String> errors = new HashMap<>();

        if (rentalSpace.getCode() == null || !rentalSpace.getCode().matches("^[A-Z0-9]{3}-[A-Z0-9]{2}-[A-Z0-9]{2}$")) {
            errors.put("code", "Mã mặt bằng không đúng định dạng XXX-XX-XX");
        }

        if (rentalSpace.getArea() == null || rentalSpace.getArea() <= 20) {
            errors.put("area", "Diện tích phải lớn hơn 20m2");
        }

        if (rentalSpace.getPrice() == null || rentalSpace.getPrice() <= 1000000) {
            errors.put("price", "Giá tiền phải lớn hơn 1,000,000 VNĐ");
        }

        if (rentalSpace.getFloor() == null || rentalSpace.getFloor() < 1 || rentalSpace.getFloor() > 15) {
            errors.put("floor", "Tầng phải từ 1 đến 15");
        }

        if (rentalSpace.getStatus() == null || (!rentalSpace.getStatus().equals("Trống") && !rentalSpace.getStatus().equals("Hạ tầng") && !rentalSpace.getStatus().equals("Đầy đủ"))) {
            errors.put("status", "Trạng thái không hợp lệ");
        }

        if (rentalSpace.getType() == null || (!rentalSpace.getType().equals("Văn phòng chia sẻ") && !rentalSpace.getType().equals("Văn phòng trọn gói"))) {
            errors.put("type", "Loại mặt bằng không hợp lệ");
        }

        if (errors.isEmpty()) {
            boolean isSaved = repository.save(rentalSpace);
            if (!isSaved) {
                errors.put("system", "Không thể lưu. Mã mặt bằng có thể đã tồn tại.");
            }
        }
        return errors;
    }

    public boolean delete(String code) {
        return repository.delete(code);
    }
}
