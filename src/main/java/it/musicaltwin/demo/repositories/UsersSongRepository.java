package it.musicaltwin.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.musicaltwin.demo.entities.UsersSongs;

public interface UsersSongRepository extends JpaRepository<UsersSongs, Long>{

    @Query(value = "SELECT song_name FROM users_songs us WHERE user_id = ?1", nativeQuery = true)
    List<String> getTopSongs(String userId);

    @Query(value = "SELECT song_id FROM users_songs us WHERE us.user_id = ?1", nativeQuery = true)
    List<Long> findAllIdFromUserId(String id);
    
}
