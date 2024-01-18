package com.BookStore.BookStore.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BorrowBookResponse {
    private String title;
    private String author;
    private int copiesBorrowed;
}
