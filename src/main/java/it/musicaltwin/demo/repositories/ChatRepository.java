package it.musicaltwin.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.musicaltwin.demo.entities.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    @Query(value = "SELECT * FROM chat c WHERE c.user1_id = ?1 OR c.user2_id = ?1", nativeQuery = true)
    List<Chat> getChatsOfUser(String userId);
    
}
