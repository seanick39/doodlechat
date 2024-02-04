package com.doodle.backend.web.controller;

import com.doodle.backend.model.response.UserResponseDto;
import com.doodle.backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.doodle.backend.LogCodes.GET_DEMO_USER;
import static com.doodle.backend.LogCodes.LIST_USERS;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService service;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("")
    public HttpEntity<List<UserResponseDto>> getAllUsers() {
        logger.info(LIST_USERS);
        return ResponseEntity.ok(service.getAllUsers());
    }

    /** The DEMO_USER (DoodleUser) will have been saved in database using sql script under /resources */
    @GetMapping("/demo-user")
    public HttpEntity<UserResponseDto> getDemoUser() {
        logger.info(GET_DEMO_USER);
        return ResponseEntity.ok(service.getDemoUserDto());
    }
}
