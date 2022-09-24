package it.musicaltwin.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.musicaltwin.demo.entities.Matches;

public interface MatchRepository extends JpaRepository<Matches, String> {
    
}