package it.musicaltwin.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.musicaltwin.demo.entities.Chat;
import it.musicaltwin.demo.entities.Messages;
import it.musicaltwin.demo.services.ChatService;

@RestController
@RequestMapping(path = "api/v1/chat")
public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping(path = "{userId}")
    public List<Chat> getUserChats(@PathVariable String userId) {
        // To show only the id of who you are chatting with

        // for (Chat chat : chatService.getChatsOfUser(userId)) {
        // if (chat.getUser1Id().equals(userId)) {
        // System.out.println(chat.getUser2Id());
        // }
        // else if (chat.getUser2Id().equals(userId)) {
        // System.out.println(chat.getUser1Id());
        // }
        // }

        return chatService.getChatsOfUser(userId);
    }

    @PostMapping(path = "add-chat")
    public void addChatToDatabase(@RequestBody Chat chat) {
        System.out.println(chat);
        chatService.addChatToDatabase(chat);
    }

    @PostMapping(path = "update-last-message")
    public void updateLastMessage(Messages msg) {
        chatService.updateLastMessageOfChat(msg);
    }
}