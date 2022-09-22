package it.musicaltwin.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.musicaltwin.demo.entities.Genres;

public interface GenresRepository extends JpaRepository<Genres, Long> {

    @Query(value = "SELECT * FROM genres gen WHERE gen.genre_name = ?1", nativeQuery = true)
    Genres findByGenreName(String genreName);

}