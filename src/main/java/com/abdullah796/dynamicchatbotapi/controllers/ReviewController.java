package com.abdullah796.dynamicchatbotapi.controllers;

import com.abdullah796.dynamicchatbotapi.models.Review;
import com.abdullah796.dynamicchatbotapi.repositories.ReviewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class ReviewController {

    private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);

    private final ReviewRepository reviewRepository;

    public ReviewController(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/review")
    public ResponseEntity<Review> save(@RequestBody Review review) {
        logger.info("POST(/review) start");
        logger.info("POST(/review) end");
        return ResponseEntity.ok(reviewRepository.save(review));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/review/{id}")
    public ResponseEntity<Review> findById(@PathVariable long id) {
        logger.info("GET(/review/{id}) start");
        Optional<Review> review = reviewRepository.findById(id);
        logger.info("GET(/review/{id}) end");
        return ResponseEntity.ok(review.orElse(null));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/reviews")
    public ResponseEntity<List<Review>> findAll() {
        logger.info("GET(/reviews) start");
        logger.info("GET(/reviews) end");
        return ResponseEntity.ok(reviewRepository.findAll());
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/review/{id}")
    public ResponseEntity<Review> update(@PathVariable long id, @RequestBody Review review) {
        logger.info("PUT(/review/{id}) start");
        Optional<Review> reviewObject = reviewRepository.findById(id);

        if (review.getCreatedDate() != null && reviewObject.isPresent()) {
            reviewObject.get().setCreatedDate(review.getCreatedDate());
        }
        if (review.getUpdatedDate() != null && reviewObject.isPresent()) {
            reviewObject.get().setUpdatedDate(review.getUpdatedDate());
        }
        if (review.getText() != null && reviewObject.isPresent()) {
            reviewObject.get().setText(review.getText());
        }
        if (review.getUser() != null && reviewObject.isPresent()) {
            reviewObject.get().setUser(review.getUser());
        }
        if (review.getChatbot() != null && reviewObject.isPresent()) {
            reviewObject.get().setChatbot(review.getChatbot());
        }
        logger.info("PUT(/review/{id}) end");
        assert reviewObject.orElse(null) != null;
        return ResponseEntity.ok(reviewRepository.save(reviewObject.orElse(null)));

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/review/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable long id) {
        logger.info("DELETE(/review/{id}) start");
        Map<String, Object> status = new HashMap<>();
        status.put("deleted", true);
        try {
            reviewRepository.deleteById(id);
        } catch (Exception ex) {
            status.put("deleted", false);
        }
        logger.info("DELETE(/review/{id}) end");
        return ResponseEntity.ok(status);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/reviews")
    public ResponseEntity<Map<String, Object>> deleteAll() {
        logger.info("DELETE(/reviews) start");
        Map<String, Object> status = new HashMap<>();
        status.put("deleted", true);
        try {
            reviewRepository.deleteAll();
        } catch (Exception ex) {
            status.put("deleted", false);
        }
        logger.info("DELETE(/reviews) end");
        return ResponseEntity.ok(status);
    }

}
