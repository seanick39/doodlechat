package com.doodle.backend;

import com.doodle.backend.domain.User;
import com.doodle.backend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.UUID;

@SpringBootTest(classes = BackendChallengeApplicationStarter.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UsersMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void listUsers_matchesExpectedJson() throws Exception {
        // Create dummy user
        User user = new User();
        user.setName("John Doe");
        user.setId(UUID.randomUUID());

        // Mock repository's method to return list of above user
        Mockito.when(userRepository.findAll()).thenReturn(List.of(user));

        // perform mock GET request to list all users and assert expectations
        mockMvc.perform(get("/users/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", equalTo(user.getName())))
                .andExpect(jsonPath("$[0].id", equalTo(user.getId().toString())));
    }
}
