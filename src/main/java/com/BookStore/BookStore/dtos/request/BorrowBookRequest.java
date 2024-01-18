package com.BookStore.BookStore.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BorrowBookRequest {
    private String title;
    private int copiesToBorrow;
}
