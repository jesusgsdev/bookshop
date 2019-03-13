package com.jesusgsdev.services;

import com.jesusgsdev.entities.Book;
import com.jesusgsdev.repositories.BookRepository;
import org.assertj.core.util.Lists;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
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

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Add a new book in the Bookshop")
    public void saveTest(){
        //Given
        Book book = Book.builder().isbn("ISBN00001").title("Book Name").price(9.99).author("Author Name").pages(200).provider("provider").build();
        book.setId(1L);
        given(bookRepository.save(any(Book.class))).willReturn(book);

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
        Book book1 = Book.builder().isbn("ISBN00001").title("Book Name").price(9.99).author(author).pages(200).provider("provider").build();
        Book book2 = Book.builder().isbn("ISBN00003").title("Book Name 3").price(9.99).author(author).pages(200).provider("provider").build();
        given(bookRepository.findBookByAuthor(author)).willReturn(Lists.newArrayList(book1, book2));

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
        given(bookRepository.findBookByAuthor(author)).willReturn(Lists.emptyList());

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
        Book book1 = Book.builder().isbn(isbn).title("Book Name").price(9.99).author("Author Name").pages(200).provider("provider").build();
        given(bookRepository.findBookByIsbn(isbn)).willReturn(Optional.of(book1));

        //When
        Optional<Book> bookFound = bookService.findBookByISBN(isbn);

        //Then
        assertAll( "Book found",
                () -> assertTrue(bookFound.isPresent()),
                () -> assertEquals(bookFound.get().getIsbn(), isbn)
        );
    }

    @Test
    @DisplayName("Find all books")
    public void findAllBooks(){
        //Given
        Book book1 = Book.builder().isbn("ISBN00001").title("Book Name").price(9.99).author("Author Name").pages(200).provider("provider").build();
        Book book2 = Book.builder().isbn("ISBN00003").title("Book Name 3").price(9.99).author("Author Name").pages(200).provider("provider").build();
        given(bookRepository.findAll()).willReturn(Lists.newArrayList(book1, book2));

        //When
        List<Book> books = bookService.findAll();

        //Then
        assertAll( "It returns all books",
                () -> assertThat(books, not(IsEmptyCollection.empty())),
                () -> assertThat(books, hasSize(2))
        );
    }

}
