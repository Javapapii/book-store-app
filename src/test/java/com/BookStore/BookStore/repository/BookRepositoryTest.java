package com.BookStore.BookStore.repository;

import com.BookStore.BookStore.entities.Book;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName(value = "testSaveRepository")
    public void GivenBook_WhenSave_ThenReturnObject(){
        Book book  = new Book(2L,"Chemistry","David",15);
        Book actual = bookRepository.save(book);
        assertNotNull(actual);
    }

    @Test
    public void GivenId1_WhenFindById_ThenReturn(){
        //Given
        Long id = 1L;
        Book bookData  = new Book(1L,"Chemsitry","David",15);

        //When
        bookRepository.save(bookData);
        Optional<Book> book = bookRepository.findById(id);
        String expected = book.get().getTitle();
        String actual = "David";

        //Then
        assertNotNull(book.get());
        assertEquals(actual,expected);
    }

    @Test
    public void GivenDeleteId_whenDelete_shouldDeleteEntity(){
        //Given
        Long id = 1L;
        Book bookData  = new Book(1L,"Chemistry","David",15);

        //When
        Book savedBook = bookRepository.save(bookData);
        bookRepository.deleteById(id);
        Book response = bookRepository.findById(id).orElse(null);
        assertNull(response);

    }

    @BeforeEach
    void setUp() {
    }
}