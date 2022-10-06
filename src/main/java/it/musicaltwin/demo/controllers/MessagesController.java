package it.musicaltwin.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.musicaltwin.demo.entities.Messages;
import it.musicaltwin.demo.services.MessagesService;

@RestController
@RequestMapping(path = "api/v1/messages")
public class MessagesController {
    private final MessagesService messagesService;
    private final ChatController chatController;

    @Autowired
    public MessagesController(MessagesService messagesService, ChatController chatController) {
        this.messagesService = messagesService;
        this.chatController = chatController;
    }

    @GetMapping(path = "{chatId}")
    public List<Messages> getMessagesOfChat(@PathVariable Long chatId) {
        return messagesService.getMessagesOfChat(chatId);
    }

    @PostMapping(path = "add-message")
    public void addNewMessage(@RequestBody Messages body) {

        messagesService.addMessageToDatabase(body);
        chatController.updateLastMessage(body);
    }
}
