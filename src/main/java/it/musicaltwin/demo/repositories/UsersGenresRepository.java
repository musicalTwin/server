package it.musicaltwin.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.musicaltwin.demo.entities.UsersGenres;

public interface UsersGenresRepository extends JpaRepository<UsersGenres, Long> {
    @Query(value = "SELECT genre_id FROM users_genres u_g WHERE u_g.user_id = ?1", nativeQuery = true)
    List<String> findListenedGenres(String userId);

    @Query(value = "SELECT users_genres_id FROM users_genres u_g WHERE u_g.user_id = ?1", nativeQuery = true)
    List<Long> findAllIdFromUserId(String userId);

}