package yte.intern.springdata.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import yte.intern.springdata.entity.Book;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByTitle(String title);

    List<Book> findByAuthor(String author);

    List<Book> findByTitleContainingIgnoreCase(String substring);

    List<Book> findByAgeGreaterThanEqual(Long age, Sort sort);

    List<Book> findByPublishDateAfter(LocalDate localDate, PageRequest pageRequest);

    List<Book> findByAuthorAndAgeGreaterThan(String author, Long age);

    Long countAllByAuthor(String author);

    Boolean existsByTitle(String name);

}
