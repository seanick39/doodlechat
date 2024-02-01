package com.doodle.backend.model.response;

import java.util.Objects;
import java.util.UUID;

public class UserResponseDto {
    private UUID id;
    private String name;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserResponseDto other = (UserResponseDto) o;
        return Objects.equals(id, other.getId()) && Objects.equals(name, other.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
