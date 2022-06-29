package com.abdullah796.dynamicchatbotapi.controllers;

import com.abdullah796.dynamicchatbotapi.models.Chat;
import com.abdullah796.dynamicchatbotapi.repositories.ChatRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class ChatController {

    private final ChatRepository chatRepository;

    public ChatController(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/chat")
    public ResponseEntity<Chat> save(@RequestBody Chat attendance) {
        return ResponseEntity.ok(chatRepository.save(attendance));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/chat/{id}")
    public ResponseEntity<Chat> findById(@PathVariable long id) {
        Optional<Chat> chat = chatRepository.findById(id);
        return ResponseEntity.ok(chat.orElse(null));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/chats")
    public ResponseEntity<List<Chat>> findAll() {
        return ResponseEntity.ok(chatRepository.findAll());
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/chat/{id}")
    public ResponseEntity<Chat> update(@PathVariable long id, @RequestBody Chat chat) {
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
        assert chatObject.orElse(null) != null;
        return ResponseEntity.ok(chatRepository.save(chatObject.orElse(null)));

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/chat/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable long id) {
        Map<String, Object> status = new HashMap<String, Object>();
        status.put("deleted", true);
        try {
            chatRepository.deleteById(id);
        } catch (Exception ex) {
            status.put("deleted", false);
        }
        return ResponseEntity.ok(status);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/chats")
    public ResponseEntity<Map<String, Object>> deleteAll() {
        Map<String, Object> status = new HashMap<String, Object>();
        status.put("deleted", true);
        try {
            chatRepository.deleteAll();
        } catch (Exception ex) {
            status.put("deleted", false);
        }
        return ResponseEntity.ok(status);
    }

}
