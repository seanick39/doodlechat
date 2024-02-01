package com.doodle.backend;

import com.doodle.backend.domain.Message;
import com.doodle.backend.domain.User;
import com.doodle.backend.repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@SpringBootTest(classes = BackendChallengeApplicationStarter.class)
@AutoConfigureMockMvc
public class MessagesMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageRepository messageRepository;

    @Test
    public void listMessages_matchesExpectedJson() throws Exception {
        // Create dummy user
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setName("John Doe");

        // Create dummy message using above user
        Message message = new Message();
        message.setId(UUID.randomUUID());
        message.setUser(user);
        message.setData("Hello, world!");

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
                .andExpect(jsonPath("$[0].id", equalTo(message.getId())))
                .andExpect(jsonPath("$[0].message", equalTo(message.getData())))
                .andExpect(jsonPath("$[0].created_at", equalTo(expectedCreatedAt)))
                .andExpect(jsonPath("$[0].user.id", equalTo(user.getId().toString())))
                .andExpect(jsonPath("$[0].user.name", equalTo(user.getName())));
    }
}
