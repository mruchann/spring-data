package yte.intern.springdata.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import yte.intern.springdata.entity.Book;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.title = :title")
    Book findByTitle(String title);

    List<Book> findByAuthor(String author);

    List<Book> findByTitleContainingIgnoreCase(String substring);

    @Query("SELECT b FROM Book b WHERE b.age >= :age")
    List<Book> findByAgeGreaterThanEqual(Long age, Sort sort);

    @Query("SELECT b FROM Book b WHERE b.publishDate >= :publishDate")
    List<Book> findByPublishDateAfter(LocalDate publishDate, PageRequest pageRequest);

    List<Book> findByAuthorAndAgeGreaterThan(String author, Long age);

    @Query("SELECT COUNT(b) from Book b WHERE b.author = :author")
    Long countByAuthor(String author);

    Boolean existsByTitle(String name);

    @Query("SELECT b FROM Book b WHERE b.title ILIKE %:substring%")
    List<Book> findByTitleLike(String substring);

    @Query("SELECT b FROM Book b WHERE b.author = :author AND b.age >= :age")
    List<Book> findByAuthorAndAge(String author, Long age);

}
