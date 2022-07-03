package com.abdullah796.dynamicchatbotapi.models;

import com.abdullah796.dynamicchatbotapi.enums.DialogueType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "dialogues")
public class Dialogue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String type = DialogueType.CHATBOT.toString();
    private Date createdDate = new Date();
    private String text;

    @JsonIgnoreProperties(value = {"dialogueList"}, allowSetters = true)
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "chat_id")
    private Chat chat;
}
