package com.jesusgsdev.services;

import com.jesusgsdev.entities.Book;
import com.jesusgsdev.repositories.BookRepository;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BookServiceTests {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void setUp(){
        bookRepository.deleteAll();
    }

    @Test
    @DisplayName("Add a new book in the Bookshop")
    public void saveTest(){
        Book book = new Book("ISBN00001", "Book Name", 9.99, "Author Name Test", 200, "provider");
        Book savedBook = bookService.save(book);

        assertNotNull(savedBook);
    }


    @Test
    @DisplayName("Find all books for a given author name")
    public void findBooksByAuthor(){
        //Given
        final String author = UUID.randomUUID().toString().substring(0,10);
        Book book1 = new Book("ISBN00001", "Book Name", 9.99, author, 200, "provider");
        Book book2 = new Book("ISBN00002", "Book Name 2", 9.99, "Author Name 2", 200, "provider");
        Book book3 = new Book("ISBN00003", "Book Name 3", 9.99, author, 200, "provider");
        bookService.save(book1);
        bookService.save(book2);
        bookService.save(book3);

        //When
        List<Book> booksByAuthor = bookService.findBooksByAuthor(author);

        //Then
        assertAll( "Books found",
                () -> assertThat(booksByAuthor, not(IsEmptyCollection.empty())),
                () -> assertThat(booksByAuthor, hasSize(2)),
                () -> assertTrue(booksByAuthor.stream().allMatch(book -> author.equals(book.getAuthor())))
            );
    }

    @Test
    @DisplayName("When there is no book for an author will return empty list")
    public void findBooksByAuthorWhenThereAreNoBooks(){
        //Given
        final String author = UUID.randomUUID().toString().substring(0,10);
        Book book1 = new Book("ISBN00001", "Book Name", 9.99, "Author Name", 200, "provider");
        bookService.save(book1);

        //When
        List<Book> booksByAuthor = bookService.findBooksByAuthor(author);

        //Then
        assertAll( "Books found",
                () -> assertThat(booksByAuthor, IsEmptyCollection.empty())
        );
    }

}
