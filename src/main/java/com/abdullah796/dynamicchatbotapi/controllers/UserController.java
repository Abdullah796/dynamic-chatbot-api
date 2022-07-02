package com.abdullah796.dynamicchatbotapi.controllers;

import com.abdullah796.dynamicchatbotapi.models.User;
import com.abdullah796.dynamicchatbotapi.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/user")
    public ResponseEntity<User> save(@RequestBody User user) {
        logger.info("POST(/user) start");
        logger.info("POST(/user) end");
        return ResponseEntity.ok(userRepository.save(user));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/{id}")
    public ResponseEntity<User> findById(@PathVariable long id) {
        logger.info("GET(/user/{id}) start");
        Optional<User> user = userRepository.findById(id);
        logger.info("GET(/user/{id}) end");
        return ResponseEntity.ok(user.orElse(null));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public ResponseEntity<List<User>> findAll() {
        logger.info("GET(/users) start");
        logger.info("GET(/users) end");
        return ResponseEntity.ok(userRepository.findAll());
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/user/{id}")
    public ResponseEntity<User> update(@PathVariable long id, @RequestBody User user) {
        logger.info("PUT(/user/{id}) start");
        Optional<User> userObject = userRepository.findById(id);

        if (user.getName() != null && userObject.isPresent()) {
            userObject.get().setName(user.getName());
        }
        if (user.getPhone() != null && userObject.isPresent()) {
            userObject.get().setPhone(user.getPhone());
        }
        if (user.getEmail() != null && userObject.isPresent()) {
            userObject.get().setEmail(user.getEmail());
        }
        if (user.getStatus() != null && userObject.isPresent()) {
            userObject.get().setStatus(user.getStatus());
        }
        if (user.getPassword() != null && userObject.isPresent()) {
            userObject.get().setPassword(user.getPassword());
        }
        if (user.getCreatedDate() != null && userObject.isPresent()) {
            userObject.get().setCreatedDate(user.getCreatedDate());
        }
        if (user.getUpdatedDate() != null && userObject.isPresent()) {
            userObject.get().setUpdatedDate(user.getUpdatedDate());
        }
        if (user.getRole() != null && userObject.isPresent()) {
            userObject.get().setRole(user.getRole());
        }
        if (user.getChatList() != null && userObject.isPresent()) {
            userObject.get().setChatList(user.getChatList());
        }
        if (user.getChatbotList() != null && userObject.isPresent()) {
            userObject.get().setChatbotList(user.getChatbotList());
        }
        if (user.getReviewList() != null && userObject.isPresent()) {
            userObject.get().setReviewList(user.getReviewList());
        }
        logger.info("PUT(/user/{id}) end");
        assert userObject.orElse(null) != null;
        return ResponseEntity.ok(userRepository.save(userObject.orElse(null)));

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/user/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable long id) {
        logger.info("DELETE(/user/{id}) start");
        Map<String, Object> status = new HashMap<>();
        status.put("deleted", true);
        try {
            userRepository.deleteById(id);
        } catch (Exception ex) {
            status.put("deleted", false);
        }
        logger.info("DELETE(/user/{id}) end");
        return ResponseEntity.ok(status);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users")
    public ResponseEntity<Map<String, Object>> deleteAll() {
        logger.info("DELETE(/users) start");
        Map<String, Object> status = new HashMap<>();
        status.put("deleted", true);
        try {
            userRepository.deleteAll();
        } catch (Exception ex) {
            status.put("deleted", false);
        }
        logger.info("DELETE(/users) end");
        return ResponseEntity.ok(status);
    }

}
