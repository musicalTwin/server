package it.musicaltwin.demo.repositories;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import it.musicaltwin.demo.entities.Messages;

public interface MessagesRepository extends JpaRepository <Messages, Long> {

    @Query(value = "SELECT * FROM messages m WHERE m.chat_id = ?1", nativeQuery = true)
    List<Messages> getMessagesOfChat(Long chatId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO messages (chat_id, sender_id, text, date_time) VALUES(?1, ?2, ?3, ?4)", nativeQuery = true)
    void addMessageToDatabase(Long chatId, String senderId, String text, Timestamp dateTime);
    
}
