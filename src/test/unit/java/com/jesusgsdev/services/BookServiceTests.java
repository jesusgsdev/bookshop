package com.jesusgsdev.services;

import com.jesusgsdev.entities.Book;
import org.assertj.core.util.Lists;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class BookServiceTests {

    @Mock
    private BookService bookService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Add a new book in the Bookshop")
    public void saveTest(){
        //Given
        Book book = new Book("ISBN00001", "Book Name", 9.99, "Author Name Test", 200, "provider");
        book.setId(1L);
        given(bookService.save(any(Book.class))).willReturn(book);

        //When
        Book savedBook = bookService.save(book);

        //Then
        assertNotNull(savedBook);
        assertEquals(new Long(1), book.getId());
    }


    @Test
    @DisplayName("Find all books for a given author name")
    public void findBooksByAuthor(){
        //Given
        final String author = UUID.randomUUID().toString().substring(0,10);
        Book book1 = new Book("ISBN00001", "Book Name", 9.99, author, 200, "provider");
        Book book2 = new Book("ISBN00003", "Book Name 3", 9.99, author, 200, "provider");
        given(bookService.findBooksByAuthor(author)).willReturn(Lists.newArrayList(book1, book2));

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
        given(bookService.findBooksByAuthor(author)).willReturn(Lists.emptyList());

        //When
        List<Book> booksByAuthor = bookService.findBooksByAuthor(author);

        //Then
        assertAll( "Books not found",
                () -> assertThat(booksByAuthor, IsEmptyCollection.empty())
        );
    }

    @Test
    @DisplayName("Find a book by ISBN")
    public void findBooksByISBN(){
        //Given
        final String isbn = "ISBN00001";
        Book book1 = new Book(isbn, "Book Name", 9.99, "Author Name", 200, "provider");
        given(bookService.findBookByISBN(isbn)).willReturn(Optional.of(book1));

        //When
        Optional<Book> bookFound = bookService.findBookByISBN(isbn);

        //Then
        assertAll( "Book found",
                () -> assertTrue(bookFound.isPresent()),
                () -> assertEquals(bookFound.get().getIsbn(), isbn)
        );
    }


}
