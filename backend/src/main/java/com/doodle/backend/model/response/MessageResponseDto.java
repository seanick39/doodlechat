package com.doodle.backend.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.Objects;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MessageResponseDto {

    private UUID id;
    @JsonProperty("message")
    private String data;

    private UserResponseDto user;

    private String createdAt;

    public UUID getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public UserResponseDto getUser() {
        return user;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setUser(UserResponseDto user) {
        this.user = user;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageResponseDto other = (MessageResponseDto) o;
        return Objects.equals(id, other.getId()) && Objects.equals(data, other.getData())
                && Objects.equals(user, other.getUser()) && Objects.equals(createdAt, other.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, data, user, createdAt);
    }
}
