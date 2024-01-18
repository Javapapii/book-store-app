package com.BookStore.BookStore.service;

import com.BookStore.BookStore.dtos.request.AddBookRequest;
import com.BookStore.BookStore.dtos.request.BorrowBookRequest;
import com.BookStore.BookStore.dtos.response.BookRequestResponse;
import com.BookStore.BookStore.dtos.response.BookResponse;
import com.BookStore.BookStore.dtos.response.BorrowBookResponse;
import com.BookStore.BookStore.entities.Book;
import com.BookStore.BookStore.util.validations.Validations;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.BookStore.BookStore.repository.BookRepository;
import com.BookStore.BookStore.util.ApiResponse;
import com.BookStore.BookStore.util.entitymapper.EntityMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;
    private final EntityMapper entityMapper;
    private final Validations validations;


    @Override
    public ApiResponse<BookRequestResponse> addBook(AddBookRequest addBookRequest) {
        Book book = entityMapper.dtoToBook(addBookRequest);
        Book savedBook = bookRepository.save(book);
        BookRequestResponse bookRequestResponse = new BookRequestResponse("Successfully created ", savedBook.getTitle());
        log.info("Book-Service: Book Successfully added :: [{}] ::", savedBook.getTitle());
        ApiResponse genericResponse = new ApiResponse<>();
        genericResponse.setMessage("Registration Successful");
        genericResponse.setStatus("Success");
        genericResponse.setCode("00");
        genericResponse.setData(savedBook);
        genericResponse.setHttpStatus(HttpStatus.OK);
        return genericResponse;

    }

    public ApiResponse<List<BookResponse>> getAllBooks() {
        log.info("fetching all the Books ");
        List<Book> allBooks = bookRepository.findAll();
        List<BookResponse> bookResponses = allBooks.stream()
                .map(entityMapper::convertToBookResponse)
                .collect(Collectors.toList());
        return new ApiResponse<>("00","All books successfully fetched",bookResponses);
    }

    @Override
    public ApiResponse<BookResponse> updateBook(AddBookRequest addBookRequest, Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (optionalBook.isPresent()) {
            log.info("updating " + addBookRequest.getTitle());
            Book existingBook = optionalBook.get();

            existingBook.setTitle(addBookRequest.getTitle());
            existingBook.setAuthor(addBookRequest.getAuthor());
            existingBook.setTotalCopies(addBookRequest.getTotalCopies());

            Book updatedBook = bookRepository.save(existingBook);
            BookResponse bookResponse = entityMapper.convertToBookResponse(updatedBook);

            ApiResponse<BookResponse> apiResponse = new ApiResponse<>("00","success","Book successfully updated",bookResponse,HttpStatus.OK);
            apiResponse.setMessage("Book successfully updated");
            return apiResponse;
        } else {
            log.error("Book not found, kindly provide the correct info");
            return new ApiResponse<BookResponse>("01","Book not found " ,HttpStatus.BAD_REQUEST);
        }
    }


    @Override
    public ApiResponse<String> deleteOrder(Long Id) {
        log.info("Deleting the Book with id of " + Id);
        Optional<Book> optionalBook = bookRepository.findById(Id);
        if (optionalBook.isPresent()) {
            Book existingBook = optionalBook.get();
            bookRepository.delete(existingBook);
        }
        else {
            log.error("Book not found, kindly provide the correct info");
            return new ApiResponse<>("01","error","error","Book not found",HttpStatus.BAD_REQUEST);
        }
        return new ApiResponse<>("00", "success", optionalBook.get().getTitle() + " successfully deleted", "Book successfully deleted",HttpStatus.OK);

    }
//Users should be able to borrow books
    @Override
    public ApiResponse<BorrowBookResponse> borrowBook(BorrowBookRequest bookRequest) {
        log.info("Borrowing" + bookRequest.getTitle());
        ApiResponse<?> validation = validations.ValidateIfBookCanBeBorrowed(bookRequest);
        if (validation.getCode().equals("00")){
            log.info("Checking validity");
            Book requiredBook = (Book) validation.getData();
            BorrowBookResponse borrowBookResponse = new BorrowBookResponse(
                    requiredBook.getTitle(),
                    requiredBook.getAuthor(),
                    bookRequest.getCopiesToBorrow()
            );
            return new ApiResponse<>("00", "success", "Book has been added to your cart", borrowBookResponse,HttpStatus.OK);
        }else {
            return new ApiResponse<>("01", "error", validation.getMessage(), null,HttpStatus.BAD_REQUEST);
        }

    }


}
