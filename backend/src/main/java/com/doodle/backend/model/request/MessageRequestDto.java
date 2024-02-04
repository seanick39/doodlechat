package com.doodle.backend.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

/**
 * Request DTO for Message entity.
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MessageRequestDto {

    @NotNull(message = "\"message\" mustn't be null")
    @NotBlank(message = "\"message\" mustn't be blank")
    @JsonProperty("message")
    private String data;

    @NotNull(message = "\"user_id\" mustn't be null")
    @JsonProperty("user_id")
    private UUID userId;

    public String getData() {
        return data;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageRequestDto other = (MessageRequestDto) o;
        return Objects.equals(data, other.getData()) && Objects.equals(userId, other.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, userId);
    }
}
