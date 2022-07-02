package com.abdullah796.dynamicchatbotapi.controllers;

import com.abdullah796.dynamicchatbotapi.models.Dialogue;
import com.abdullah796.dynamicchatbotapi.repositories.DialogueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class DialogueController {

    private static final Logger logger = LoggerFactory.getLogger(DialogueController.class);

    private final DialogueRepository dialogueRepository;

    public DialogueController(DialogueRepository dialogueRepository) {
        this.dialogueRepository = dialogueRepository;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/dialogue")
    public ResponseEntity<Dialogue> save(@RequestBody Dialogue dialogue) {
        logger.info("POST(/dialogue) start");
        logger.info("POST(/dialogue) end");
        return ResponseEntity.ok(dialogueRepository.save(dialogue));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/dialogue/{id}")
    public ResponseEntity<Dialogue> findById(@PathVariable long id) {
        logger.info("GET(/dialogue/{id}) start");
        Optional<Dialogue> dialogue = dialogueRepository.findById(id);
        logger.info("GET(/dialogue/{id}) end");
        return ResponseEntity.ok(dialogue.orElse(null));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/dialogues")
    public ResponseEntity<List<Dialogue>> findAll() {
        logger.info("GET(/dialogues) start");
        logger.info("GET(/dialogues) end");
        return ResponseEntity.ok(dialogueRepository.findAll());
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/dialogue/{id}")
    public ResponseEntity<Dialogue> update(@PathVariable long id, @RequestBody Dialogue dialogue) {
        logger.info("PUT(/dialogue/{id}) start");
        Optional<Dialogue> dialogueObject = dialogueRepository.findById(id);

        if (dialogue.getType() != null && dialogueObject.isPresent()) {
            dialogueObject.get().setType(dialogue.getType());
        }
        if (dialogue.getCreatedDate() != null && dialogueObject.isPresent()) {
            dialogueObject.get().setCreatedDate(dialogue.getCreatedDate());
        }
        if (dialogue.getText() != null && dialogueObject.isPresent()) {
            dialogueObject.get().setText(dialogue.getText());
        }
        if (dialogue.getChat() != null && dialogueObject.isPresent()) {
            dialogueObject.get().setChat(dialogue.getChat());
        }
        logger.info("PUT(/dialogue/{id}) end");
        assert dialogueObject.orElse(null) != null;
        return ResponseEntity.ok(dialogueRepository.save(dialogueObject.orElse(null)));

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/dialogue/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable long id) {
        logger.info("DELETE(/dialogue/{id}) start");
        Map<String, Object> status = new HashMap<>();
        status.put("deleted", true);
        try {
            dialogueRepository.deleteById(id);
        } catch (Exception ex) {
            status.put("deleted", false);
        }
        logger.info("DELETE(/dialogue/{id}) end");
        return ResponseEntity.ok(status);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/dialogues")
    public ResponseEntity<Map<String, Object>> deleteAll() {
        logger.info("DELETE(/dialogues) start");
        Map<String, Object> status = new HashMap<>();
        status.put("deleted", true);
        try {
            dialogueRepository.deleteAll();
        } catch (Exception ex) {
            status.put("deleted", false);
        }
        logger.info("DELETE(/dialogues) end");
        return ResponseEntity.ok(status);
    }

}
