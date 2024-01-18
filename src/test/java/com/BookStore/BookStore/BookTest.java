//package com.BookStore.BookStore;
//
//import com.BookStore.BookStore.entities.Book;
//import com.BookStore.BookStore.repository.BookRepository;
//import org.junit.Test;
//import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Replace;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
//
//@DataJpaTest
////@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//public class BookTest {
//    @Autowired
//    private BookRepository bookRepository;
//
//    @Test
//    public void testAddBook(){
//        Book book  = new Book(2L,"Chemsitry","David",15);
//        Book savedBook = bookRepository.save(book);
//        assertNotNull(savedBook);
//
//    }
//}
