package com.example.medical_form.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalDeclaration {
    
    private Integer id; // Khóa chính để phân biệt các tờ khai (rất quan trọng)

    // Thông tin cá nhân
    private String fullName;
    private Integer birthYear;
    private String gender;
    private String nationality;
    private String idCard;

    // Thông tin đi lại
    private String travelInfo;
    private String vehicleNumber;
    private String seatNumber;

    // Ngày khởi hành
    private String startDate;
    private String startMonth;
    private String startYear;
    
    // Ngày kết thúc
    private String endDate;
    private String endMonth;
    private String endYear;

    // Tỉnh thành đã đến trong 14 ngày
    private String visitedProvinces;

    // Địa chỉ liên lạc
    private String province;
    private String district;
    private String ward;
    private String address;
    private String phone;
    private String email;

    // Triệu chứng trong 14 ngày
    private Boolean fever;
    private Boolean nausea;
    private Boolean cough;
    private Boolean diarrhea;
    private Boolean shortnessOfBreath;
    private Boolean hemorrhage;
    private Boolean soreThroat;
    private Boolean skinRash;

    // Lịch sử phơi nhiễm trong 14 ngày
    private Boolean animalExposure;
    private Boolean contactNCoV;
}
