package it.musicaltwin.demo.controllers;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.musicaltwin.demo.entities.Chat;
import it.musicaltwin.demo.services.ChatService;

@RestController
@RequestMapping(path = "api/v1/chat")
public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }
    
    @PostMapping(path = "{userId}")
    public List<Chat> getUserChats(@PathVariable String userId) {
        // To show only the id of who you are chatting with
        
        // for (Chat chat : chatService.getChatsOfUser(userId)) {
        //     if (chat.getUser1Id().equals(userId)) {
        //         System.out.println(chat.getUser2Id());
        //     }
        //     else if (chat.getUser2Id().equals(userId)) {
        //         System.out.println(chat.getUser1Id());
        //     }
        // }
        
        return chatService.getChatsOfUser(userId);
    }

    @PostMapping(path = "add-chat")
    public void addChatToDatabase(@RequestParam String user1Id, @RequestParam String user2Id) {
        chatService.addChatToDatabase(user1Id, user2Id);
    }

    @PostMapping(path = "update-last-message")
    public void updateLastMessage(@RequestParam Long chatId, @RequestParam String lastMessage, @RequestParam Timestamp lastTimeSent) {
        chatService.updateLastMessageOfChat(chatId, lastMessage, lastTimeSent);
    }
}