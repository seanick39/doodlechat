package com.doodle.backend.service;

import com.doodle.backend.domain.Message;
import com.doodle.backend.domain.User;
import com.doodle.backend.exception.MessageNotFoundException;
import com.doodle.backend.model.request.MessageRequestDto;
import com.doodle.backend.model.response.MessageResponseDto;
import com.doodle.backend.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.doodle.backend.service.UserService.DEMO_USER;

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
        repository.save(message);
        return convertModelToResponse(message);
    }

    public List<MessageResponseDto> getAllMessagesAfterId(UUID messageId) {
        Message lastMessage = repository.findById(messageId).orElseThrow(() -> new MessageNotFoundException(messageId));
        return repository.findAllByCreatedAtIsAfterOrderByCreatedAtAsc(lastMessage.getCreatedAt()).stream().map(this::convertModelToResponse).collect(Collectors.toList());
    }

    /**
     * Ensure an initial message by DEMO_USER persists when application server boots up
     */
    @PostConstruct
    public void persistDemoUserMessage() {
        User demoUser = userService.findByName(DEMO_USER);
        if (repository.countByUserName(demoUser.getName()) == 0) {
            Message message = new Message(demoUser, "Hey folks! I wanted to get in touch with you regarding " +
                    "the project. Please, let me know how you plan to contribute");
            message.setCreatedAt(Instant.now());
            repository.save(message);
        }
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
