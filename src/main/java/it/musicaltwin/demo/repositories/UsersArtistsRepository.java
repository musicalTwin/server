package it.musicaltwin.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.musicaltwin.demo.entities.UsersArtists;

public interface UsersArtistsRepository extends JpaRepository<UsersArtists, Long>{

    @Query(value = "SELECT artist_name FROM users_artists ua WHERE ua.user_id = ?1", nativeQuery = true)
    List<String> getTopArtistsOfUser(String userId);

    @Query(value = "SELECT artist_id FROM users_artists ua WHERE ua.user_id = ?1", nativeQuery = true)
    List<Long> findAllIdFromUserId(String userId);
    
}
