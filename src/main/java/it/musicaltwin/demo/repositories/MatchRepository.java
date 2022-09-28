package it.musicaltwin.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.musicaltwin.demo.entities.Matches;

public interface MatchRepository extends JpaRepository<Matches, String> {

    @Query(value = "SELECT card_id FROM matches mat WHERE mat.user_id = ?1 ", nativeQuery = true)
    List<Long> findCardId(String userId);

    @Query(value = "SELECT * FROM matches mat WHERE mat.user_id = ?1 AND mat.card_id = ?2 ", nativeQuery = true)
    List<Matches> aaa(String userId, Long cardId);
    
}