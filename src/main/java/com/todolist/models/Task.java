package com.todolist.models;

import java.time.Instant;

public class Task implements Auditable{
    private Long id;
    private String title;
    private String description;
    private Color color;
    private Instant createdAt;
    private Instant lastUpdatedAt;
    public Task(String title, String description,Color color) {
        this.title = title;
        this.description = description;
        this.color = color;
    }
    @Override
    public Instant getCreatedAt(){
        return Instant.now();
    }
    public void setCreatedAt(Instant createdAt){ this.createdAt = createdAt; }
    @Override
    public Instant getLastUpdatedAt(){
        return Instant.now();
    }
    public void setLastUpdatedAt(Instant lastUpdatedAt) { this.lastUpdatedAt = lastUpdatedAt; }
    public Color getColor() { return color; }
    public String getTitle(){ return title; }
    public String getDescription() {
        return description;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId(){
        return id;
    }
    public String toString() {
        return  " id: " + id +
                " title: " + title +
                " description: " + description +
                " color: " + color;
    }

}
