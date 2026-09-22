package com.example.validation_form.entity;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.*;
import java.time.Year;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "Tên không được để trống")
    @Size(min = 5, max = 45, message = "Tên phải có độ dài từ 5 đến 45 ký tự")
    private String firstName;

    @NotBlank(message = "Họ không được để trống")
    @Size(min = 5, max = 45, message = "Tên phải có độ dài từ 5 đến 45 ký tự")
    private  String lastName;

    private int year;

    @Min(value = 18, message ="Tuổi phải từ 18 trở lên")
    @Transient
    public int getAge(){
        if(this.year <= 0)
        {
            return 0;
        }
        int currentYear = Year.now().getValue();
        return currentYear - this.year;
    }

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    private String email;
}
