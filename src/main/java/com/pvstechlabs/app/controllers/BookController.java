package com.pvstechlabs.app.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pvstechlabs.app.data.entities.Book;
import com.pvstechlabs.app.data.services.BookService;

@CrossOrigin
@RestController
@RequestMapping(value = "/pvs-store/api/books")
public class BookController {

	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addBook(@RequestBody Book book) {
		System.out.println(book);
		return bookService.save(book);
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public @ResponseBody List<Book> getAllBooks() {
		return bookService.findAll();
	}

	@RequestMapping(value = "/get/{bookId}", method = RequestMethod.GET)
	public @ResponseBody Book getBook(@PathVariable Long bookId) {
		Optional<Book> book = bookService.findOne(bookId);
		if (book.isPresent())
			return book.get();
		else {
			return null;
		}
	}
	
	@RequestMapping(value = "/delete/{bookId}", method = RequestMethod.DELETE)
	public void deleteBook(@PathVariable Long bookId) {
		Optional<Book> book = bookService.findOne(bookId);
		if (book.isPresent())
			bookService.delete(bookId);
	}

}
