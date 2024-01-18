package com.BookStore.BookStore.service;

import com.BookStore.BookStore.dtos.request.AddBookRequest;
import com.BookStore.BookStore.dtos.request.BorrowBookRequest;
import com.BookStore.BookStore.dtos.response.BookRequestResponse;
import com.BookStore.BookStore.dtos.response.BookResponse;
import com.BookStore.BookStore.dtos.response.BorrowBookResponse;
import com.BookStore.BookStore.util.ApiResponse;

import java.util.List;

public interface BookService {

    ApiResponse<BookRequestResponse> addBook(AddBookRequest addBookRequest);
    ApiResponse<List<BookResponse>> getAllBooks();

    ApiResponse<BookResponse> updateBook(AddBookRequest addBookRequest, Long id);
    ApiResponse<String> deleteOrder(Long Id);
    ApiResponse<BorrowBookResponse> borrowBook(BorrowBookRequest bookRequest);
}
