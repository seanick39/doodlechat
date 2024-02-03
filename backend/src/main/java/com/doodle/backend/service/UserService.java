package com.doodle.backend.service;

import com.doodle.backend.domain.User;
import com.doodle.backend.exception.ClientNotFoundException;
import com.doodle.backend.model.response.UserResponseDto;
import com.doodle.backend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    private final Logger logger = LoggerFactory.getLogger(getClass());
    protected static final String DEMO_USER = "DoodleUser";

    public List<UserResponseDto> getAllUsers() {
        return repository.findAll().stream().map(this::convertModelToResponse).collect(Collectors.toList());
    }

    public UserResponseDto getDemoUser() {
        return convertModelToResponse(repository.findByName(DEMO_USER).orElseThrow(() -> new ClientNotFoundException(DEMO_USER)));
    }

    protected User findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
    }

    protected User findByName(String name) {
        return repository.findByName(name).orElseThrow(() -> new ClientNotFoundException(name));
    }

    /**
     * Ensure the DEMO_USER exists when application server boots up
     */
    @PostConstruct
    public void persistDemoUser() {
        if (!repository.existsByName(DEMO_USER)) {
            repository.save(new User(DEMO_USER));
        }
    }

    protected UserResponseDto convertModelToResponse(User user) {
        UserResponseDto response = new UserResponseDto();
        response.setId(user.getId());
        response.setName(user.getName());
        return response;
    }

}
