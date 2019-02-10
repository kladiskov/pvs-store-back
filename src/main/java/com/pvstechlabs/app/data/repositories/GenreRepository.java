package com.pvstechlabs.app.data.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pvstechlabs.app.data.entities.Genre;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Long> {

	List<Genre> findAll();

}
