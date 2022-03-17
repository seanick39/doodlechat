package com.doodle.backend.domain;

import java.util.UUID;

public class Message {

    private UUID id;

    public Message() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
