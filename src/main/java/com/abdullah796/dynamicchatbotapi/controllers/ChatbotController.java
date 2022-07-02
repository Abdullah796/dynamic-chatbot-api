package com.abdullah796.dynamicchatbotapi.controllers;

import com.abdullah796.dynamicchatbotapi.models.Chatbot;
import com.abdullah796.dynamicchatbotapi.repositories.ChatbotRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class ChatbotController {

    private static final Logger logger = LoggerFactory.getLogger(ChatbotController.class);

    private final ChatbotRepository chatbotRepository;

    public ChatbotController(ChatbotRepository chatbotRepository) {
        this.chatbotRepository = chatbotRepository;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/chatbot")
    public ResponseEntity<Chatbot> save(@RequestBody Chatbot chatbot) {
        logger.info("POST(/chatbot) start");
        logger.info("POST(/chatbot) end");
        return ResponseEntity.ok(chatbotRepository.save(chatbot));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/chatbot/{id}")
    public ResponseEntity<Chatbot> findById(@PathVariable long id) {
        logger.info("GET(/chatbot/{id}) start");
        Optional<Chatbot> chatbot = chatbotRepository.findById(id);
        logger.info("GET(/chatbot/{id}) end");
        return ResponseEntity.ok(chatbot.orElse(null));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/chatbots")
    public ResponseEntity<List<Chatbot>> findAll() {
        logger.info("GET(/chatbots) start");
        logger.info("GET(/chatbots) end");
        return ResponseEntity.ok(chatbotRepository.findAll());
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/chatbot/{id}")
    public ResponseEntity<Chatbot> update(@PathVariable long id, @RequestBody Chatbot chatbot) {
        logger.info("PUT(/chatbot/{id}) start");
        Optional<Chatbot> chatbotObject = chatbotRepository.findById(id);

        if (chatbot.getName() != null && chatbotObject.isPresent()) {
            chatbotObject.get().setName(chatbot.getName());
        }
        if (chatbot.getDescription() != null && chatbotObject.isPresent()) {
            chatbotObject.get().setDescription(chatbot.getDescription());
        }
        if (chatbot.getCreatedDate() != null && chatbotObject.isPresent()) {
            chatbotObject.get().setCreatedDate(chatbot.getCreatedDate());
        }
        if (chatbot.getUpdatedDate() != null && chatbotObject.isPresent()) {
            chatbotObject.get().setUpdatedDate(chatbot.getUpdatedDate());
        }
        if (chatbot.getCreatedBy() != null && chatbotObject.isPresent()) {
            chatbotObject.get().setCreatedBy(chatbot.getCreatedBy());
        }
        if (chatbot.getChatList() != null && chatbotObject.isPresent()) {
            chatbotObject.get().setChatList(chatbot.getChatList());
        }
        if (chatbot.getReviewList() != null && chatbotObject.isPresent()) {
            chatbotObject.get().setReviewList(chatbot.getReviewList());
        }
        logger.info("PUT(/chatbot/{id}) end");
        assert chatbotObject.orElse(null) != null;
        return ResponseEntity.ok(chatbotRepository.save(chatbotObject.orElse(null)));

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/chatbot/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable long id) {
        logger.info("DELETE(/chatbot/{id}) start");
        Map<String, Object> status = new HashMap<>();
        status.put("deleted", true);
        try {
            chatbotRepository.deleteById(id);
        } catch (Exception ex) {
            status.put("deleted", false);
        }
        logger.info("DELETE(/chatbot/{id}) end");
        return ResponseEntity.ok(status);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/chatbots")
    public ResponseEntity<Map<String, Object>> deleteAll() {
        logger.info("DELETE(/chatbots) start");
        Map<String, Object> status = new HashMap<>();
        status.put("deleted", true);
        try {
            chatbotRepository.deleteAll();
        } catch (Exception ex) {
            status.put("deleted", false);
        }
        logger.info("DELETE(/chatbots) end");
        return ResponseEntity.ok(status);
    }

}
