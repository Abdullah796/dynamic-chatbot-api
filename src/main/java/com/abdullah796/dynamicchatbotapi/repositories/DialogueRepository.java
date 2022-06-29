package com.abdullah796.dynamicchatbotapi.repositories;

import com.abdullah796.dynamicchatbotapi.models.Dialogue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DialogueRepository extends JpaRepository<Dialogue, Long> {
}
