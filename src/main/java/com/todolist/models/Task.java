package com.todolist.models;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;
    @Column(name="Title")
    private String title;
    @Column(name="Description")

    private String description;
    @Column(name="Color")
    private Color color;

//    @CreatedDate
//    private Instant createdAt;
//    @LastModifiedDate
//    private Instant lastUpdatedAt;

    public Task() {
    }

    public Task(String title, String description, Color color) {
        this.title = title;
        this.description = description;
        this.color = color;
    }

    public Task(Long id, Instant createdAt, String title, String description, Color color) {
        this.id = id;
//        this.createdAt = createdAt;
        this.title = title;
        this.description = description;
        this.color = color;
    }

    public Instant getCreatedAt() {
        return null;
    }

    public void setCreatedAt(Instant createdAt) {
 //       this.createdAt = createdAt;
    }
    public Instant getLastUpdatedAt() {
        return null;
    }

    public void setLastUpdatedAt(Instant lastUpdatedAt) {
 //       this.lastUpdatedAt = lastUpdatedAt;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String toString() {
        return " id: " + id + " title: " + title + " description: " + description + " color: " + color;
    }

    public String getColorAsName() {
        if (color != null) {
            return color.name();
        }
        return "UNDEFINED";
    }

    public void setColorAsName(String colorAsName) {
        Color color = Color.valueOf(colorAsName);
        this.color = color;
    }
}
