package com.jesusgsdev.services;

import com.jesusgsdev.entities.Book;
import com.jesusgsdev.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book save(Book book){
        return bookRepository.save(book);
    }

    public Optional<Book> findBookByISBN(String isbn){
        return bookRepository.findBookByIsbn(isbn);
    }

    @Transactional
    public List<Book> findBooksByAuthor(String author){
        return bookRepository.findBookByAuthor(author);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}
