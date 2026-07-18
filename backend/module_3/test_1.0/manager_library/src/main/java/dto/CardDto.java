package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {
    private int id;
    private String cardCode;
    private int bookId;
    private String bookName;
    private String author;
    private String studentName;
    private String className;
    private String status;
    private Date startDate;
    private Date endDate;
}
