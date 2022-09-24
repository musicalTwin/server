package it.musicaltwin.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.musicaltwin.demo.entities.Cards;

public interface CardsRepository extends JpaRepository<Cards, Long> {
    
}
