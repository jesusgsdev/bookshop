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

        given :
            Book book = new Book("ISBN00001", "Book Name", 9.99, "Author Name Test", 200, "provider")
            bookRepository.save(book) >> book
        when:
            bookService.save(book)

        then:
            1 * bookRepository.save(book) >> book
    }

    def cleanup(){
        log.debug ("Cleanup method - Runs after every feature method.")
    }
    def cleanupSpec(){
        log.debug ("cleanupSpec() - Runs only once per specification")
    }

}