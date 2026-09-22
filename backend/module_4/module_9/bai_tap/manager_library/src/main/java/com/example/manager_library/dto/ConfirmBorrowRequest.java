package com.example.manager_library.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConfirmBorrowRequest {
    private int bookId;
    private int userId;
    private String borrowCode;
}
