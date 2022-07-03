package com.abdullah796.dynamicchatbotapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "chatbots")
public class Chatbot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String description;
    private Date createdDate = new Date();
    private Date updatedDate = new Date();

    @JsonIgnoreProperties(value = {"chatbotList", "reviewList", "chatList"}, allowSetters = true)
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "user_id")
    private User createdBy;

    @JsonIgnoreProperties(value = {"chatbot", "user", "chatbot"}, allowSetters = true)
    @OneToMany(mappedBy = "chatbot", cascade = CascadeType.ALL)
    private List<Chat> chatList = new ArrayList<>();

    @JsonIgnoreProperties(value = {"chatbot", "user", "chatbot"}, allowSetters = true)
    @OneToMany(mappedBy = "chatbot", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();
}
