package com.pvstechlabs.app.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pvstechlabs.app.data.entities.Genre;
import com.pvstechlabs.app.data.services.GenreService;

@CrossOrigin
@RestController
@RequestMapping(value = "/pvs-store/api/genres")
public class GenreController {

	@Autowired
	private GenreService genreService;

	@RequestMapping(value = "/getAll")
	public @ResponseBody List<Genre> getAllGenres() {
		return genreService.findAll();
	}

	@RequestMapping(value = "/get/{id}")
	public @ResponseBody Genre getGenre(@PathVariable Long id) {
		Optional<Genre> genre = genreService.findOne(id);
		return genre.isPresent() ? genre.get() : null;
	}

}
