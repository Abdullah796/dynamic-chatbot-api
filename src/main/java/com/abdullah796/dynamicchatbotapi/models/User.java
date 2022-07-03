package com.abdullah796.dynamicchatbotapi.models;

import com.abdullah796.dynamicchatbotapi.enums.Role;
import com.abdullah796.dynamicchatbotapi.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String phone;
    private String email;
    private String status = UserStatus.ACTIVE.toString();
    private String password;
    private Date createdDate = new Date();
    private Date updatedDate = new Date();
    private String role = Role.USER.toString();

    @JsonIgnoreProperties(value = {"user"}, allowSetters = true)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Chat> chatList;

    @JsonIgnoreProperties(value = {"createdBy", "reviewList", "chatList"}, allowSetters = true)
    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private List<Chatbot> chatbotList = new ArrayList<>();

    @JsonIgnoreProperties(value = {"user"}, allowSetters = true)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();
}
