package com.example.manager_qa.dto;

import com.example.manager_qa.entity.QuestionStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionRequestDTO {

    private Integer id;

    @NotBlank(message = "Tiêu đề không được để trống")
    @Size(min = 5, max = 100, message = "Tiêu đề phải từ 5 đến 100 ký tự")
    private String title;

    @NotBlank(message = "Nội dung câu hỏi không được để trống")
    @Size(min = 10,max = 500, message = "Nội dung câu hỏi phải từ 10 ký tự trở lên")
    private String content;

    private String answer;

    @NotNull(message = "Vui lòng chọn loại câu hỏi")
    private Integer questionTypeId;

    @NotNull(message = "Ngày tạo không được để trống")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Builder.Default
    private LocalDate date = LocalDate.now();

    private QuestionStatus status;
}
