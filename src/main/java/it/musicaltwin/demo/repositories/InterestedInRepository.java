package it.musicaltwin.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.musicaltwin.demo.entities.InterestedIn;

public interface InterestedInRepository extends JpaRepository<InterestedIn, Long> {

    @Query(value = "SELECT * FROM interested_in in WHERE in.user_id = ?1", nativeQuery = true)
    List<InterestedIn> findByUserId(String userId);

}