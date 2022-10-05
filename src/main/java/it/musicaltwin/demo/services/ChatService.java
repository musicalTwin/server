package it.musicaltwin.demo.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.musicaltwin.demo.entities.Chat;
import it.musicaltwin.demo.repositories.ChatRepository;

@Service
public class ChatService {
    private final ChatRepository chatRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public List<Chat> getChatsOfUser(String userId) {
        return chatRepository.getChatsOfUser(userId);
    }

    public void updateLastMessageOfChat(Long chatId, String lastMessage, Timestamp lastTimeSent) {
        chatRepository.updateLastMessageSentOfChat(chatId, lastMessage);
        chatRepository.updateLastMessageTimeOfChat(chatId, lastTimeSent);
    }

    public void addChatToDatabase(String user1Id, String user2Id) {
        chatRepository.addChatToDatabase(user1Id, user2Id);
    }
}
