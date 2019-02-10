package com.pvstechlabs.app.data.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pvstechlabs.app.data.entities.Genre;
import com.pvstechlabs.app.data.repositories.GenreRepository;

@Service
public class GenreService {

	@Autowired
	private GenreRepository repo;
	
	public void save(Genre genre) {
		repo.save(genre);
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}

	public Optional<Genre> findOne(Long id) {
		return repo.findById(id);
	}

	public List<Genre> findAll() {
		return repo.findAll();
	}

}
