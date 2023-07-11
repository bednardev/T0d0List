package com.todolist.models;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "Title")
    private String title;
    @Column(name = "Description")
    private String description;
    @Column(name = "Color")
    @Enumerated(EnumType.STRING)
    private Color color;

    @Column(name = "UserId")
    private Long userId;

    @CreatedDate
    @Column(name = "CreatedAt")
    private Instant createdAt;
    @LastModifiedDate
    @Column(name = "LastUpdatedAt")
    private Instant lastUpdatedAt;

    public Task() {
    }

    public Task(String title, String description, Color color) {
        this.title = title;
        this.description = description;
        this.color = color;
    }

    public Task(Long id, String title, String description, Color color) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.color = color;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(Instant lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
