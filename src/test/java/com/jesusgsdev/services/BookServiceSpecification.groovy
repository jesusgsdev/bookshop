package com.jesusgsdev.services

import com.jesusgsdev.entities.Book
import com.jesusgsdev.repositories.BookRepository
import groovy.util.logging.Slf4j
import spock.lang.Specification

@Slf4j
class BookServiceSpecification extends Specification {

    BookService bookService
    BookRepository bookRepository = Mock()

    def setupSpec(){
        log.debug("setupSpec() - Runs once per Specification")
    }

    def setup(){
        log.debug ("setup() - Runs before every feature method")

        bookRepository = Mock()
        bookService = new BookService(bookRepository)
    }

    def "Book is correctly saved"(){
        given: "A Book with ISBN, Title, Price, Author, Pages and Provider"
            Book book = Book.builder().isbn("ISBN00001").title("Book Name").price(9.99).author("Author Name Test").price(200).provider("provider").build();
        and: "Mocking the repository to return the book after performing a save"
            bookRepository.save(book) >> book

        when: "Service layer saves the book"
            bookService.save(book)

        then: "The method save(Book b) of BookRepository will be called once and the return object will be the book object"
            1 * bookRepository.save(book) >> book
    }

    def cleanup(){
        log.debug ("Cleanup method - Runs after every feature method.")
    }
    def cleanupSpec(){
        log.debug ("cleanupSpec() - Runs after the last feature method")
    }

}