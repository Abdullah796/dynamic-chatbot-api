package com.abdullah796.dynamicchatbotapi.repositories;

import com.abdullah796.dynamicchatbotapi.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
