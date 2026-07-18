package com.example.service;

import com.example.entity.RentalSpace;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class RentalSpaceServiceTest {

    private RentalSpaceService service;

    @BeforeEach
    public void setUp() {
        service = new RentalSpaceService();
    }

    @Test
    public void testValidRentalSpace() {
        RentalSpace space = new RentalSpace(null, "ABC-12-34", 30.0, "Trống", 5, "Văn phòng chia sẻ", 2000000.0);
        Map<String, String> errors = service.save(space);
        
        // Nó có thể dính lỗi hệ thống (system) nếu DB chưa bật hoặc bị trùng mã. 
        // Nhưng ở đây ta kiểm tra các validation fields phải pass.
        assertNull(errors.get("code"));
        assertNull(errors.get("area"));
        assertNull(errors.get("floor"));
        assertNull(errors.get("price"));
    }

    @Test
    public void testInvalidCode() {
        RentalSpace space = new RentalSpace(null, "abc-12-34", 30.0, "Trống", 5, "Văn phòng chia sẻ", 2000000.0);
        Map<String, String> errors = service.save(space);
        assertEquals("Mã mặt bằng không đúng định dạng XXX-XX-XX", errors.get("code"));
    }

    @Test
    public void testInvalidArea() {
        RentalSpace space = new RentalSpace(null, "ABC-12-34", 19.9, "Trống", 5, "Văn phòng chia sẻ", 2000000.0);
        Map<String, String> errors = service.save(space);
        assertEquals("Diện tích phải lớn hơn 20m2", errors.get("area"));
    }

    @Test
    public void testInvalidPrice() {
        RentalSpace space = new RentalSpace(null, "ABC-12-34", 30.0, "Trống", 5, "Văn phòng chia sẻ", 999999.0);
        Map<String, String> errors = service.save(space);
        assertEquals("Giá tiền phải lớn hơn 1,000,000 VNĐ", errors.get("price"));
    }

    @Test
    public void testInvalidFloor() {
        RentalSpace space = new RentalSpace(null, "ABC-12-34", 30.0, "Trống", 16, "Văn phòng chia sẻ", 2000000.0);
        Map<String, String> errors = service.save(space);
        assertEquals("Tầng phải từ 1 đến 15", errors.get("floor"));
    }
}
