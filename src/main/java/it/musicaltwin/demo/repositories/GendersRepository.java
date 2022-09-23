package it.musicaltwin.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.musicaltwin.demo.entities.Genders;

public interface GendersRepository extends JpaRepository<Genders, Long> {

    @Query(value = "SELECT * FROM genders gen WHERE gen.gender_name = ?1", nativeQuery = true)
    Optional<Genders> getByName(String genreName);

}