package yte.intern.springdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import yte.intern.springdata.entity.Book;
import yte.intern.springdata.repository.BookRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringDataApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringDataApplication.class, args);
		BookRepository bookRepository = context.getBean(BookRepository.class);

		List<Book> books = new ArrayList<>();
		books.add(new Book(1L, "Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin", 15L, LocalDate.of(2008, 8, 11)));
		books.add(new Book(2L, "Introduction to Algorithms", "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, Clifford Stein", 14L, LocalDate.of(2009, 7, 31)));
		books.add(new Book(3L, "Effective Java", "Joshua Bloch", 5L, LocalDate.of(2018, 12, 27)));

		bookRepository.saveAll(books);

		List<Book> javaBooks = bookRepository.findByTitleContainingIgnoreCase("Java");
		List<Book> oldBooks = bookRepository.findByAgeGreaterThanEqual(15L, Sort.by("age").ascending());
		List<Book> newBooks = bookRepository.findByPublishDateAfter(
				LocalDate.of(2018, 1, 1),
				PageRequest.of(0, 5)
		);
		Long count = bookRepository.countAllByAuthor("Robert C. Martin");
		Boolean algorithmBookExists = bookRepository.existsByTitle("Introduction to Algorithms");

		System.out.println("Robert C. Martin Count: " + count);
		System.out.println("Algorithm Book Exists: " + algorithmBookExists);

		System.out.println("Java Books: " + javaBooks);
		System.out.println("Old Books: " + oldBooks);
		System.out.println("New Books: " + newBooks);
	}


}
