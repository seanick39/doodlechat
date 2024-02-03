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
import java.util.UUID;

import static com.doodle.backend.LogCodes.LIST_MESSAGES;
import static com.doodle.backend.LogCodes.SAVE_NEW_MESSAGE;

@RestController
@CrossOrigin
@RequestMapping("/messages")
public class MessagesController {

    @Autowired
    private MessageService messageService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * List all messages if no `afterMessageId` UUID as request param is provided.
     * With this request param, the frontend can poll for new messages that
     * were saved after the message to which this id pertains.
     * @param afterMessageId    UUID    The id of message after which all messages to be sent.
     */
    @RequestMapping("")
    public List<MessageResponseDto> getAllMessages(@RequestParam(name = "afterMessageId", required = false) @Valid UUID afterMessageId) {
        logger.info(LIST_MESSAGES);
        if (afterMessageId == null) {
            return messageService.getAllMessages();
        }
        return messageService.getAllMessagesAfterId(afterMessageId);

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public MessageResponseDto saveMessage(@RequestBody @Valid MessageRequestDto requestMessage) {
        logger.info(SAVE_NEW_MESSAGE);
        return messageService.save(requestMessage);
    }
}
