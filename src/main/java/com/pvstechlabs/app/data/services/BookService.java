package com.pvstechlabs.app.data.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pvstechlabs.app.data.entities.Book;
import com.pvstechlabs.app.data.repositories.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository repo;

	public String save(Book book) {
		Book current = repo.findByTitle(book.getTitle());
		if (current == null || (!current.getAuthor().equalsIgnoreCase(book.getAuthor()))) {
			repo.save(book);
			return "success";
		} else if (current.getAuthor().equals(book.getAuthor())) {
			System.out.println("update...");
			book.setBookId(current.getBookId());
			repo.save(book);
			return "success";
		} else {
			return "fail";
		}
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
