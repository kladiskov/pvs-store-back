package com.pvstechlabs.app.data.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pvstechlabs.app.data.entities.Book;
import com.pvstechlabs.app.data.repositories.BookRepository;
import com.pvstechlabs.app.exceptions.BadRequestException;
import com.pvstechlabs.app.exceptions.ResourceNotFoundException;

@Service
public class BookService {

	@Autowired
	private BookRepository repo;

	public Book save(Book book) {
		if (repo.existsByTitle(book.getTitle())) {
			throw new BadRequestException("Book already exists in the database.");
		}
		return repo.save(book);
	}

	public Book update(Book book) {
		if (!repo.existsById(book.getBookId())) {
			throw new ResourceNotFoundException("Selected book could not be found.");
		}
		return repo.save(book);
	}

	public Boolean existsByTitle(String title) {
		return repo.existsByTitle(title);
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}

	public Optional<Book> findOne(Long id) {
		return repo.findById(id);
	}

	public List<Book> findAll() {
		return repo.findAll();
	}

}
