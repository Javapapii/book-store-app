package com.BookStore.BookStore.service;

import com.BookStore.BookStore.dtos.request.AddBookRequest;
import com.BookStore.BookStore.dtos.response.BookRequestResponse;
import com.BookStore.BookStore.entities.Book;
import com.BookStore.BookStore.repository.BookRepository;
import com.BookStore.BookStore.util.ApiResponse;
import com.BookStore.BookStore.util.entitymapper.EntityMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;
    @Mock
    private EntityMapper entityMapper;
    //Service Under Test
    @InjectMocks
    private BookServiceImpl bookService;

//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);  // Initialize mocks
//        bookService = new BookServiceImpl(bookRepository, entityMapper);
//    }

    @Test
    void addBook() {
        //Given
        AddBookRequest bookData  = new AddBookRequest("Chemistry","David",15);
        Book book = new Book(1L,bookData.getTitle(),bookData.getAuthor(), bookData.getTotalCopies());

        //When
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        ApiResponse<BookRequestResponse> Response = bookService.addBook(bookData);
        assertEquals(bookData.getTitle(),Response.getData().getBookName());


    }

    @Test
    void getAllBooks() {
    }

    @Test
    void updateBook() {
    }

    @Test
    void deleteOrder() {
    }
}