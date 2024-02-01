package com.doodle.backend.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.UUID;

/**
 * Entity for the message object in the chat application.
 * Members:
 *      - id        UUID        (postgres native)
 *      - data      String      Actual message string
 *      - user      User        foreign key to User
 *      - createdAt Instant     Time the message was received
 */
@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private UUID id;

    @NotBlank
    @NotNull
    @Column
    private String data;

    @NotNull
    @ManyToOne(cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    public Message(User user, String data) {
        this.user = user;
        this.data = data;
    }

    public Message() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setData(String message) {
        this.data = message;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
