package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    private int id;
    private String cardCode;
    private int bookId;
    private int studentId;
    private LocalDate startDate;
    private LocalDate endDate;
}
