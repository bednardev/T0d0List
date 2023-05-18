package com.todolist.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.Instant;

@Entity
public class Task implements Auditable {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    private Color color;
    private Instant createdAt;
    private Instant lastUpdatedAt;

    public Task() {
    }

    public Task(String title, String description, Color color) {
        this.title = title;
        this.description = description;
        this.color = color;
    }
    public Task(Long id,String title, String description, Color color) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.color = color;
    }
    @Override
    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
    @Override
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
}
