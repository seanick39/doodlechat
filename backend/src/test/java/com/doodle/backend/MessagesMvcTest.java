package com.doodle.backend;

import com.doodle.backend.domain.Message;
import com.doodle.backend.domain.User;
import com.doodle.backend.model.request.MessageRequestDto;
import com.doodle.backend.repository.MessageRepository;
import com.doodle.backend.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest(classes = BackendChallengeApplicationStarter.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class MessagesMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageRepository messageRepository;

    @MockBean
    private UserRepository userRepository;

    private User user;
    private Message message;

    @BeforeEach
    public void setUpFixtures() {
        // Create dummy user
        user = new User();
        user.setId(UUID.randomUUID());
        user.setName("John Doe");

        // Create dummy message using above user
        message = new Message();
        message.setId(UUID.randomUUID());
        message.setUser(user);
        message.setData("Hello, world!");
        message.setCreatedAt(Instant.now());
    }

    @Test
    public void listMessages_matchesExpectedJson() throws Exception {
        // Date time string with zone CET as used in service
        String createdAtString = "2023-01-01T12:00:00+01:00";
        String expectedCreatedAt = "01 Jan 2023 12:00";
        message.setCreatedAt(Instant.parse(createdAtString));

        // Mock repository's method to return list of the above dummy message
        Mockito.when(messageRepository.findAllByOrderByCreatedAtAsc()).thenReturn(List.of(message));

        // perform mock GET request to list all messages and assert expectations
        mockMvc.perform(get("/messages/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(message.getId().toString())))
                .andExpect(jsonPath("$[0].message", is(message.getData())))
                .andExpect(jsonPath("$[0].created_at", is(expectedCreatedAt)))
                .andExpect(jsonPath("$[0].user.id", is(user.getId().toString())))
                .andExpect(jsonPath("$[0].user.name", is(user.getName())));
    }

    @Test
    public void postValidMessage_returnsExpectedJson() throws Exception {
        // arrange request object to send
        MessageRequestDto requestDto = new MessageRequestDto();
        requestDto.setUserId(user.getId());
        requestDto.setData(message.getData());

        // mock repository
        Mockito.when(messageRepository.existsById(any(UUID.class))).thenReturn(true);
        Mockito.when(messageRepository.save(any(Message.class))).thenReturn(message);
        Mockito.when(userRepository.findById(any(UUID.class))).thenReturn(Optional.of(user));

        String requestBody = new ObjectMapper().writeValueAsString(requestDto);

        // perform mock POST request to save new message and expect valid json object
        mockMvc.perform(post("/messages/").contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(message.getId().toString())))
                .andExpect(jsonPath("$.message", is(message.getData())))
                .andExpect(jsonPath("$.user.id", is(user.getId().toString())))
                .andExpect(jsonPath("$.user.name", is(user.getName())));
    }
}
