package com.example.validation_form.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.Year;
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public final class UserRequestDTO {

    @NotBlank(message = "{error.firstName.blank}")
    @Pattern(regexp = "^([\\p{L}\\s]{5,45})?$", message = "Tên chỉ được chứa chủ cái, khong được có số")
    private String firstName;

    @NotBlank(message = "Họ không được để trống")
    @Pattern(regexp = "^([\\p{L}\\s]{5,45})?$", message = "Họ phải từ 5-45 ký tự và chỉ chứa chữ cái")
    private  String lastName;

    @Min(value = 18, message ="Tuổi phải từ 18 trở lên")
    @Max(value = 200, message = "Tuổi nhập vào không hợp lệ")
    private int age;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    private String email;

}
