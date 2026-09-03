package com.example.manager_library.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReturnBorrowRequest {
    private int borrowId;
    private String borrowCode;
}
