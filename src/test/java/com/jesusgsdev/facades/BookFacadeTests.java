package com.jesusgsdev.facades;

import com.jesusgsdev.dtos.BookDTO;
import com.jesusgsdev.entities.Book;
import com.jesusgsdev.services.BookService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;


public class BookFacadeTests {

    @InjectMocks private BookFacade bookFacade;

    @Mock private BookService bookService;

    @BeforeEach public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAllTest() {
        String author = "Manuel";
        //Given
        Book book1 = Book
                .builder()
                    .isbn("ISBN00001")
                    .title("Book Name")
                    .price(9.99)
                    .author(author)
                    .pages(200)
                    .provider("provider")
                    .id(1L)
                .build();
        Book book2 = Book
                .builder()
                    .isbn("ISBN00003")
                    .title("Book Name 3")
                    .price(9.99)
                    .author(author)
                    .pages(200)
                    .provider("provider")
                    .id(2L)
                .build();

        List<BookDTO> expectedResult = Lists.list(BookDTO.fromBook(book1), BookDTO.fromBook(book2));
        given(bookService.findAll()).willReturn(Lists.list(book1, book2));

        //When
        List<BookDTO> results = bookFacade.findAll();

        //Then
        assertEquals(expectedResult, results);
    }


 /*   public BookDTO addBookTest( ) {

    }

    public BookDTO findBookByISBNTest( ){

    }

    public List<BookDTO> findBooksByAuthorTest( ){

    }*/




}
