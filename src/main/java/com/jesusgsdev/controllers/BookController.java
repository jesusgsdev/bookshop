package com.jesusgsdev.controllers;

import com.jesusgsdev.dtos.BookDTO;
import com.jesusgsdev.entities.Book;
import com.jesusgsdev.facades.BookFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookFacade bookFacade;

    @PostMapping
    public BookDTO addBook(@RequestBody @Valid BookDTO bookDTO) {
        return bookFacade.addBook(bookDTO);
    }

    @GetMapping
    public List<BookDTO> getAll(){

        return bookFacade.findAll();
    }

    @GetMapping(params = "isbn")
    public BookDTO getBookByISBN(@RequestParam("isbn") String isbn) {
        return bookFacade.findBookByISBN(isbn);
    }

    @GetMapping(params = "author")
    public List<BookDTO> getBooksByAuthor(@RequestParam("author") String author) {
        return bookFacade.findBooksByAuthor(author);
    }

    @GetMapping(params = "title")
    public List<BookDTO> getBooksByTitle(@RequestParam("title") String title) {
        return bookFacade.findBooksByNameSearch(title);
    }
    /*  Book book = Book.builder().isbn("ISBN00001").title("El niño del pijama").price(9.99).author("Author Name").pages(200).provider("provider").build();
        bookFacade.addBook(BookDTO.fromBook(book));*/
}
