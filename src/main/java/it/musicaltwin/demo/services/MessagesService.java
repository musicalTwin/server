package it.musicaltwin.demo.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.musicaltwin.demo.entities.Messages;
import it.musicaltwin.demo.repositories.MessagesRepository;

@Service
public class MessagesService {
    
    private final MessagesRepository messagesRepository;

    @Autowired
    public MessagesService(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    public List<Messages> getMessagesOfChat(Long chatId) {
        return messagesRepository.getMessagesOfChat(chatId);
    }

    public void addMessageToDatabase(Long chatId, String senderId, String text, Timestamp dateTime) {
        messagesRepository.addMessageToDatabase(chatId, senderId, text, dateTime);
    }
}
