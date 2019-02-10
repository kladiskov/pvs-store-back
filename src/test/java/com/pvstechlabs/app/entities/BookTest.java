package com.pvstechlabs.app.entities;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pvstechlabs.app.data.entities.Book;
import com.pvstechlabs.app.data.entities.Genre;
import com.pvstechlabs.app.data.repositories.BookRepository;
import com.pvstechlabs.app.data.repositories.GenreRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookTest {

	@Autowired
	private BookRepository bookRepo;

	@Autowired
	private GenreRepository genRepo;

	private Genre genre;

	@Before
	public void setUp() {
		genre = new Genre();
		genre.setGenreId(1L);
		genre.setGenreName("Fiction");
		genre.setGenreType("Fantasy");
		genRepo.save(genre);
	}

	@Test
	public void add() {
		Book book = new Book();
		book.setBookId(1L);
		book.setActive(true);
		book.setGenre(genre);
		book.setTitle("The Alchemist");
		book.setAuthor("Paulo Coelho");
		book.setPublishDate(new Date());
		book.setStock(100);
		book.setRating(3.90);
		book.setPrice(250.00);
		bookRepo.save(book);
		assertTrue(bookRepo.findById(1L).isPresent());
	}

	@Test
	public void update() {
		Book book = new Book();
		book.setBookId(1L);
		book.setActive(true);
		book.setGenre(genre);
		book.setTitle("1984");
		book.setAuthor("George Orwell");
		book.setPublishDate(new Date());
		book.setStock(100);
		book.setRating(3.91);
		book.setPrice(250.00);
		bookRepo.save(book);
		System.out.println(bookRepo.findById(1L).get().getRating());
		assertTrue(bookRepo.findById(1L).get().getRating() == 3.91);
	}

	@After
	public void tearDown() {
		if (bookRepo.findById(1L).isPresent()) {
			bookRepo.deleteById(1L);
		}
	}

}
