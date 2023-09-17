package com.todolist.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Surname")
    private String surname;
    @Column(name = "mail")
    private String mail;
    @CreatedDate
    @Column(name = "CreatedAt", updatable = false)
    private Instant createdAt;
    @Column(name = "LastUpdatedAt")
    @LastModifiedDate
    private Instant lastUpdatedAt;

    @OneToMany(mappedBy = "userId")
    private List<Task> tasks;

    public User() {
    }

    public User(String name, String surname, String mail) {
        this.name = name;
        this.surname = surname;
        this.mail = mail;
    }

    public User(Long id, String name, String surname, String mail) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
    }
}
