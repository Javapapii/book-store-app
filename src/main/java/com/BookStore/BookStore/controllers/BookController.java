package com.BookStore.BookStore.controllers;

import com.BookStore.BookStore.dtos.request.AddBookRequest;
import com.BookStore.BookStore.dtos.request.BorrowBookRequest;
import com.BookStore.BookStore.dtos.response.BookRequestResponse;
import com.BookStore.BookStore.dtos.response.BookResponse;
import com.BookStore.BookStore.dtos.response.BorrowBookResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.BookStore.BookStore.service.BookService;
import com.BookStore.BookStore.util.ApiResponse;

import java.util.List;

/**
 * The BookStore API provides endpoints for managing books, including operations for
 * creating, updating, and deleting books by the admin. Additionally, users can view
 * all books available in the system and borrow books.
 *
 * @apiNote This API is designed for a book management system where administrators
 * can perform CRUD operations on books, and users can borrow books from the system.
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookStore")
@Slf4j
public class BookController {

    private final BookService bookService;
    //This endpoint should only be available to Admin, They should be the only ones able to add books to the system
    @PostMapping("/add-book")
    public ResponseEntity<ApiResponse<BookRequestResponse>> RegisterBook(@RequestBody AddBookRequest addBookRequest){
        log.info("Book-Controller register: Add Book :: [{}] ::", addBookRequest.getTitle());
        ApiResponse<BookRequestResponse> book = bookService.addBook(addBookRequest);
        return new ResponseEntity<>(book, book.getHttpStatus());
    }
    @GetMapping("/get-book")
    public ResponseEntity<ApiResponse<List<BookResponse>>> fetchAllBook(){
        log.info("Book-Controller : Fetch all books ");
        ApiResponse<List<BookResponse>> allBooks = bookService.getAllBooks();
        return new ResponseEntity<>(allBooks,HttpStatus.OK);
    }
    //This endpoint should only be available  to Admin, They should be the only ones able to edit details of books in the system
    @PutMapping("/update-book/{id}")
    public ResponseEntity<ApiResponse<?>> updateBook(@RequestBody AddBookRequest addBookRequest, @PathVariable Long id){
        log.info("Book-Controller : updating " + addBookRequest.getTitle());
        ApiResponse<BookResponse> updatedBook = bookService.updateBook(addBookRequest, id);
        return new ResponseEntity<>(updatedBook, updatedBook.getHttpStatus());
    }
    //This endpoint should only be available  to Admin, They should be the only ones able to delete books from the system
    @DeleteMapping("/delete-book/{id}")
    public ResponseEntity<ApiResponse<String>> deleteOrder(@PathVariable Long id){
        log.info("Book-Controller : deleting the book with " + id);
        ApiResponse<String> deleteBook = bookService.deleteOrder(id);
        return new ResponseEntity<>(deleteBook,deleteBook.getHttpStatus());

    }
    //This endpoint should only be available  to the public Users, They should be able to borrow books from the system
    @PostMapping("/borrow-book")
    public ResponseEntity<ApiResponse<BorrowBookResponse>> BorrowBook(@RequestBody BorrowBookRequest bookRequest){
        log.info("Book-Controller: Borrow Book :: [{}] ::", bookRequest.getTitle());
        ApiResponse<BorrowBookResponse> borrowBookResponse = bookService.borrowBook(bookRequest);
        return new ResponseEntity<>(borrowBookResponse, borrowBookResponse.getHttpStatus());
    }
}
