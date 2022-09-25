package it.musicaltwin.demo.services;

import it.musicaltwin.demo.entities.Genres;
import it.musicaltwin.demo.repositories.GenresRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenresService {

    private final GenresRepository genresRepository;

    @Autowired
    public GenresService(GenresRepository genresRepository) {
        this.genresRepository = genresRepository;
    }

    public Genres findByName(String name) {
        return genresRepository.findByGenreName(name);
    }

    public Genres findById(Long genreId) {
        return genresRepository.findById(genreId).orElse(new Genres());
    }

}
