package com.abdullah796.dynamicchatbotapi.repositories;

import com.abdullah796.dynamicchatbotapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
