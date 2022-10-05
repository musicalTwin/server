package it.musicaltwin.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.musicaltwin.demo.entities.Messages;
import it.musicaltwin.demo.services.MessagesService;

@RestController
@RequestMapping(path = "api/v1/messages")
public class MessagesController {
    private final MessagesService messagesService;

    @Autowired
    public MessagesController(MessagesService messagesService) {
        this.messagesService = messagesService;
    }

    @PostMapping(path = "{chatId}")
    public List<Messages> getMessagesOfChat(@PathVariable Long chatId) {
        return messagesService.getMessagesOfChat(chatId);
    }
}
