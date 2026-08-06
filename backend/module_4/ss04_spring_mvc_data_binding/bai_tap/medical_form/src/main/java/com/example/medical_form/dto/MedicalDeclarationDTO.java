package com.example.medical_form.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalDeclarationDTO {
    // Thông tin cá nhân
    @NotBlank(message = "Họ tên không được để trống")
    private String fullName;

    @NotNull(message = "Năm sinh không được để trống")
    private Integer birthYear;

    @NotBlank(message = "Giới tính không được để trống")
    private String gender;

    @NotBlank(message = "Quốc tịch không được để trống")
    private String nationality;

    @NotBlank(message = "Số hộ chiếu/CMND không được để trống")
    private String idCard;

    // Thông tin đi lại
    @NotBlank(message = "Thông tin đi lại không được để trống")
    private String travelInfo;
    private String vehicleNumber; // Không bắt buộc
    private String seatNumber;    // Không bắt buộc

    // Ngày khởi hành
    @NotBlank(message = "Ngày khởi hành không được để trống")
    private String startDate;
    @NotBlank(message = "Tháng khởi hành không được để trống")
    private String startMonth;
    @NotBlank(message = "Năm khởi hành không được để trống")
    private String startYear;
    
    // Ngày kết thúc
    @NotBlank(message = "Ngày kết thúc không được để trống")
    private String endDate;
    @NotBlank(message = "Tháng kết thúc không được để trống")
    private String endMonth;
    @NotBlank(message = "Năm kết thúc không được để trống")
    private String endYear;

    // Tỉnh thành đã đến trong 14 ngày
    @NotBlank(message = "Thông tin này không được để trống")
    private String visitedProvinces;

    // Địa chỉ liên lạc
    @NotBlank(message = "Tỉnh/thành không được để trống")
    private String province;
    @NotBlank(message = "Quận/huyện không được để trống")
    private String district;
    @NotBlank(message = "Phường/xã không được để trống")
    private String ward;
    @NotBlank(message = "Địa chỉ không được để trống")
    private String address;
    
    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$", message = "Số điện thoại không đúng định dạng VN")
    private String phone;
    
    @Email(message = "Email không đúng định dạng")
    private String email;

    // Triệu chứng trong 14 ngày
    @NotNull(message = "Vui lòng chọn trạng thái sốt")
    private Boolean fever;
    @NotNull(message = "Vui lòng chọn trạng thái nôn/buồn nôn")
    private Boolean nausea;
    @NotNull(message = "Vui lòng chọn trạng thái ho")
    private Boolean cough;
    @NotNull(message = "Vui lòng chọn trạng thái tiêu chảy")
    private Boolean diarrhea;
    @NotNull(message = "Vui lòng chọn trạng thái khó thở")
    private Boolean shortnessOfBreath;
    @NotNull(message = "Vui lòng chọn trạng thái xuất huyết ngoài da")
    private Boolean hemorrhage;
    @NotNull(message = "Vui lòng chọn trạng thái đau họng")
    private Boolean soreThroat;
    @NotNull(message = "Vui lòng chọn trạng thái nổi ban")
    private Boolean skinRash;

    // Lịch sử phơi nhiễm trong 14 ngày
    @NotNull(message = "Vui lòng chọn lịch sử phơi nhiễm với động vật")
    private Boolean animalExposure;
    @NotNull(message = "Vui lòng chọn lịch sử tiếp xúc người bệnh")
    private Boolean contactNCoV;
}
