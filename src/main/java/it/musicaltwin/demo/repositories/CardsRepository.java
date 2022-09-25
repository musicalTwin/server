package it.musicaltwin.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.musicaltwin.demo.entities.Cards;

public interface CardsRepository extends JpaRepository<Cards, Long> {

    @Query(value = "SELECT * FROM cards c WHERE c.user_id = ?1", nativeQuery = true)
    Optional<Cards> findByUserId(String id);

}
