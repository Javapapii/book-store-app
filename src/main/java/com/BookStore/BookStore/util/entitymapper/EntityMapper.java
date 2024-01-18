package com.BookStore.BookStore.util.entitymapper;

import com.BookStore.BookStore.dtos.request.AddBookRequest;
import com.BookStore.BookStore.dtos.response.BookResponse;
import com.BookStore.BookStore.entities.Book;
import org.springframework.stereotype.Service;

@Service
public class EntityMapper {
    public Book dtoToBook(AddBookRequest addBookRequest){
        Book book = new Book();
        book.setTitle(addBookRequest.getTitle());
        book.setAuthor(addBookRequest.getAuthor());
        book.setTotalCopies(addBookRequest.getTotalCopies());
        return book;
    }

    public BookResponse convertToBookResponse(Book book) {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setId(book.getId());
        bookResponse.setTitle(book.getTitle());
        bookResponse.setAuthor(book.getAuthor());
        bookResponse.setTotalCopies(book.getTotalCopies());
        return bookResponse;
    }
}
