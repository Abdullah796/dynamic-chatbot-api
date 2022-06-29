package com.abdullah796.dynamicchatbotapi.repositories;

import com.abdullah796.dynamicchatbotapi.models.Chatbot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatbotRepository extends JpaRepository<Chatbot, Long> {
}
