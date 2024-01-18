package com.BookStore.BookStore.util.validations;

import com.BookStore.BookStore.dtos.request.BorrowBookRequest;
import com.BookStore.BookStore.entities.Book;
import com.BookStore.BookStore.repository.BookRepository;
import com.BookStore.BookStore.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class Validations {
    private final BookRepository bookRepository;
    public ApiResponse<?> ValidateIfBookCanBeBorrowed(BorrowBookRequest bookRequest) {
        log.info("validating if the book can be borrowed and managing status");

        Optional<Book> optionalBook = bookRepository.findByTitle(bookRequest.getTitle());

        if (optionalBook.isPresent()) {
            Book requiredBook = optionalBook.get();

            int requestedCopies = bookRequest.getCopiesToBorrow();
            int availableCopies = requiredBook.getTotalCopies();
            log.info("I want to borrow " + requestedCopies);
            log.info("Only " + availableCopies + " is available");

            if (requestedCopies <= availableCopies) {
                requiredBook.setTotalCopies(availableCopies - requestedCopies);
                bookRepository.save(requiredBook);

                return new ApiResponse<>("00","success","Book has been added to you cart",requiredBook);
            } else {
                return new ApiResponse<>("01","error","Book is not available in sufficient copies",null, HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ApiResponse<>("01","error","Book not  found",null,HttpStatus.BAD_REQUEST);
        }
    }
}
