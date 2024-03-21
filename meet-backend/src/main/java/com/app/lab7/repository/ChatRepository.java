package com.app.lab7.repository;

import com.app.lab7.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    @Query("SELECT c FROM Chat c " +
            "JOIN FETCH c.users " +
            "WHERE c.id IN (SELECT cc.id FROM Chat cc JOIN cc.users uu WHERE uu.email = :email)")
    List<Chat> findAllByEmail(String email);
}
