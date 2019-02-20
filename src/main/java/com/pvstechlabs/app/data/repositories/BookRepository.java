package com.pvstechlabs.app.data.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pvstechlabs.app.data.entities.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
	public List<Book> findAll();
	
	public Book findByTitle(String title);
	
	public Boolean existsByTitle(String title);
	
}
