package com.jesusgsdev.repositories;

import com.jesusgsdev.entities.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findAll();

    @Query(value = "SELECT u FROM Book u where title like CONCAT('%', ?1 ,'%') ")
    List<Book> findAllByNameSearch(String str);

    List<Book> findBookByAuthor(String author);

    Optional<Book> findBookByIsbn(String isbn);


}
