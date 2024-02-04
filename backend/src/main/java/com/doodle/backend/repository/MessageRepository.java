package com.doodle.backend.repository;

import com.doodle.backend.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {

    /** findAll with chronological sorting */
    List<Message> findAllByOrderByCreatedAtAsc();
    Long countByUserName(String userName);

    /** findAll with chronological sorting where attribute createdAt is after parameter createdAt */
    List<Message> findAllByCreatedAtIsAfterOrderByCreatedAtAsc(Instant createdAt);
}
