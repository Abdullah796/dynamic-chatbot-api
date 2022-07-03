package com.abdullah796.dynamicchatbotapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Date createdDate = new Date();
    private Date updatedDate = new Date();
    private String text;

    @JsonIgnoreProperties(value = {"reviewList", "chatbotList", "chatList"}, allowSetters = true)
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnoreProperties(value = {"reviewList", "chatList", "createdBy"}, allowSetters = true)
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "chatbot_id")
    private Chatbot chatbot;
}
