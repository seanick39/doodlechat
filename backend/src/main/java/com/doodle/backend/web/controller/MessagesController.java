package com.doodle.backend.web.controller;

import com.doodle.backend.model.response.MessageResponseDto;
import com.doodle.backend.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.doodle.backend.LogCodes.LIST_MESSAGES;

@RestController
@CrossOrigin
@RequestMapping("/messages")
public class MessagesController {

    @Autowired
    private MessageService messageService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("")
    public List<MessageResponseDto> getAllMessages() {
        logger.info(LIST_MESSAGES);
        return messageService.getAllMessages();
    }
}
