package com.doodle.backend.web.controller;

import com.doodle.backend.model.request.MessageRequestDto;
import com.doodle.backend.model.response.MessageResponseDto;
import com.doodle.backend.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.doodle.backend.LogCodes.LIST_MESSAGES;
import static com.doodle.backend.LogCodes.SAVE_NEW_MESSAGE;

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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public MessageResponseDto saveMessage(@RequestBody @Valid MessageRequestDto requestMessage) {
        logger.info(SAVE_NEW_MESSAGE);
        return messageService.save(requestMessage);
    }
}
