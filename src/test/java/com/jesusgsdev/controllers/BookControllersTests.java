package com.jesusgsdev.controllers;

import com.jesusgsdev.dtos.BookDTO;
import com.jesusgsdev.entities.Book;
import com.jesusgsdev.facades.BookFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

public class BookControllersTests {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookFacade bookFacade;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  @DisplayName("xxxx")
  public void addBookTest() {
    //Given
    Book book = Book
            .builder()
              .isbn("ISBN00003")
              .title("Book Name 3")
              .price(9.99)
              .author("Author Name")
              .pages(200)
              .provider("provider")
              .id(1L)
            .build();

    BookDTO bookDTOInput = BookDTO.fromBook(book);
    BookDTO bookDTOExpected = BookDTO.fromBook(book);

    given(bookFacade.addBook(bookDTOInput)).willReturn(bookDTOExpected);

    //When
    BookDTO bookDTO = bookController.addBook(bookDTOInput);

    //Then
    assertEquals(bookDTOExpected, bookDTO);
  }
  /*  @Test
    public void addBookTest()
    {
  String author = "Manuel";
        Book book1 = new Book("ISBN00001", "Book Name", 9.99, author, 200, "provider");
        Book book2 = new Book("ISBN00003", "Book Name 3", 9.99, author, 200, "provider");
        BookDTO bookDTO= BookDTO.newBookDTO().build();
        book1 = bookService.save(book1);
        book2 = bookService.save(book2);
        bookDTO.setId(book1.getId());
        bookDTO.setId(book2.getId());
    }
*/
    @Test
    public void getAllTest()
    {

    }
/*
    @Test
    public void getBookByISBNTest() {


    }

     @Test
    public void getBooksByAuthorTest() {


    }
    */
}
