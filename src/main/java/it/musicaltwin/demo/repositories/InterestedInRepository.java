package it.musicaltwin.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.musicaltwin.demo.entities.InterestedIn;

public interface InterestedInRepository extends JpaRepository<InterestedIn, Long> {

    @Query(value = "SELECT * FROM interested_in itr WHERE itr.user_id = ?1", nativeQuery = true)
    Optional<List<InterestedIn>> findByUserId(String userId);

}