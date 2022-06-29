package com.abdullah796.dynamicchatbotapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "chats")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Date createdDate = new Date();
    private Date updatedDate = new Date();

    @JsonIgnoreProperties({"chatList"})
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnoreProperties({"chatList"})
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "chatbot_id")
    private Chatbot chatbot;

    @JsonIgnoreProperties({"chat"})
    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
    private List<Dialogue> dialogueList = new ArrayList<>();
}
