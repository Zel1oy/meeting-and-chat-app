package com.app.lab7.repository;

import com.app.lab7.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("FROM Message m LEFT JOIN FETCH m.chat LEFT JOIN FETCH m.sender c WHERE c.id = :chatId")
    List<Optional<Message>> findAllByChatId(Long chatId);

    @Query("DELETE Message m WHERE m.chat.id = :chatId")
    void deleteAllByChatId(Long chatId);
}
