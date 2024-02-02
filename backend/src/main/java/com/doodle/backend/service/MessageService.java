package com.doodle.backend.service;

import com.doodle.backend.domain.Message;
import com.doodle.backend.domain.User;
import com.doodle.backend.model.request.MessageRequestDto;
import com.doodle.backend.model.response.MessageResponseDto;
import com.doodle.backend.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    @Autowired
    private MessageRepository repository;

    @Autowired
    private UserService userService;

    public MessageService() {
    }

    private static final String PATTERN_FORMAT = "dd MMM yyyy hh:mm";

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter
            .ofPattern(PATTERN_FORMAT)
            .withZone(ZoneId.of("Europe/Paris"));

    public List<MessageResponseDto> getAllMessages() {
        return repository.findAllByOrderByCreatedAtAsc().stream().map(this::convertModelToResponse).collect(Collectors.toList());
    }

    public MessageResponseDto save(MessageRequestDto requestMessage) {
        User user = userService.findById(requestMessage.getUserId());
        Message message = new Message(user, requestMessage.getData());
        message.setCreatedAt(Instant.now());
        message = repository.save(message);
        return convertModelToResponse(message);
    }

    protected MessageResponseDto convertModelToResponse(Message message) {
        MessageResponseDto dto = new MessageResponseDto();
        dto.setId(message.getId());
        dto.setData(message.getData());
        dto.setCreatedAt(dateTimeFormatter.format(message.getCreatedAt()));
        dto.setUser(userService.convertModelToResponse(message.getUser()));
        return dto;
    }

}
