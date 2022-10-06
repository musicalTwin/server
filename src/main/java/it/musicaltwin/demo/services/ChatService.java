package it.musicaltwin.demo.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.musicaltwin.demo.entities.Chat;
import it.musicaltwin.demo.entities.Messages;
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

    @Transactional
    public void updateLastMessageOfChat(Messages msg) {
        Chat chat = chatRepository.findById(msg.getChatId()).orElse(new Chat());
        chat.setLastMessage(msg.getText());
        chat.setLastTimeSent(msg.getDateTime());
    }

    public void addChatToDatabase(Chat chat) {
        chatRepository.save(chat);
    }
}
