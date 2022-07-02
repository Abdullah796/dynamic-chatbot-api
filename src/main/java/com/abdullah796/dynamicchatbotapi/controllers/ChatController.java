package com.abdullah796.dynamicchatbotapi.controllers;

import com.abdullah796.dynamicchatbotapi.models.Chat;
import com.abdullah796.dynamicchatbotapi.repositories.ChatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    private final ChatRepository chatRepository;

    public ChatController(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/chat")
    public ResponseEntity<Chat> save(@RequestBody Chat chat) {
        logger.info("POST(/chat) start");
        logger.info("POST(/chat) end");
        return ResponseEntity.ok(chatRepository.save(chat));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/chat/{id}")
    public ResponseEntity<Chat> findById(@PathVariable long id) {
        logger.info("GET(/chat/{id}) start");
        Optional<Chat> chat = chatRepository.findById(id);
        logger.info("GET(/chat/{id}) end");
        return ResponseEntity.ok(chat.orElse(null));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/chats")
    public ResponseEntity<List<Chat>> findAll() {
        logger.info("GET(/chats) start");
        logger.info("GET(/chats) end");
        return ResponseEntity.ok(chatRepository.findAll());
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/chat/{id}")
    public ResponseEntity<Chat> update(@PathVariable long id, @RequestBody Chat chat) {
        logger.info("PUT(/chat/{id}) start");
        Optional<Chat> chatObject = chatRepository.findById(id);

        if (chat.getCreatedDate() != null && chatObject.isPresent()) {
            chatObject.get().setCreatedDate(chat.getCreatedDate());
        }
        if (chat.getUpdatedDate() != null && chatObject.isPresent()) {
            chatObject.get().setUpdatedDate(chat.getUpdatedDate());
        }
        if (chat.getUser() != null && chatObject.isPresent()) {
            chatObject.get().setUser(chat.getUser());
        }
        if (chat.getChatbot() != null && chatObject.isPresent()) {
            chatObject.get().setChatbot(chat.getChatbot());
        }
        if (chat.getDialogueList() != null && chatObject.isPresent()) {
            chatObject.get().setDialogueList(chat.getDialogueList());
        }
        logger.info("PUT(/chat/{id}) end");
        assert chatObject.orElse(null) != null;
        return ResponseEntity.ok(chatRepository.save(chatObject.orElse(null)));

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/chat/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable long id) {
        logger.info("DELETE(/chat/{id}) start");
        Map<String, Object> status = new HashMap<>();
        status.put("deleted", true);
        try {
            chatRepository.deleteById(id);
        } catch (Exception ex) {
            status.put("deleted", false);
        }
        logger.info("DELETE(/chat/{id}) end");
        return ResponseEntity.ok(status);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/chats")
    public ResponseEntity<Map<String, Object>> deleteAll() {
        logger.info("DELETE(/chats) start");
        Map<String, Object> status = new HashMap<>();
        status.put("deleted", true);
        try {
            chatRepository.deleteAll();
        } catch (Exception ex) {
            status.put("deleted", false);
        }
        logger.info("DELETE(/chats) end");
        return ResponseEntity.ok(status);
    }

}
