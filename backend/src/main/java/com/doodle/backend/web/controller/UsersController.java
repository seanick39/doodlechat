package com.doodle.backend.web.controller;

import com.doodle.backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService service;

    private final Logger logger = LoggerFactory.getLogger(getClass());
}
