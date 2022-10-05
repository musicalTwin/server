package it.musicaltwin.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.musicaltwin.demo.entities.Messages;

public interface MessagesRepository extends JpaRepository <Messages, Long> {

    @Query(value = "SELECT * FROM messages m WHERE m.chat_id = ?1", nativeQuery = true)
    List<Messages> getMessagesOfChat(Long chatId);
    
}
