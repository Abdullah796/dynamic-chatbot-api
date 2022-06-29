package com.abdullah796.dynamicchatbotapi.repositories;

import com.abdullah796.dynamicchatbotapi.models.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
