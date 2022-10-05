package it.musicaltwin.demo.repositories;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import it.musicaltwin.demo.entities.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    @Query(value = "SELECT * FROM chat c WHERE c.user1_id = ?1 OR c.user2_id = ?1", nativeQuery = true)
    List<Chat> getChatsOfUser(String userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE chat c SET c.last_message = ?2 WHERE chat_id = ?1", nativeQuery = true)
    void updateLastMessageSentOfChat(Long chatId, String lastMessage);

    @Modifying
    @Transactional
    @Query(value = "UPDATE chat c SET c.last_time_sent = ?2 WHERE chat_id = ?1", nativeQuery = true)
    void updateLastMessageTimeOfChat(Long chatId, Timestamp lastTimeSent);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO chat c(user1_id, user2_id) VALUES(?1, ?2)", nativeQuery = true)
    void addChatToDatabase(String user1Id, String user2Id);
    
}
