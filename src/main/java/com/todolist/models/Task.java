package com.todolist.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Getter @Setter
@EntityListeners(AuditingEntityListener.class)
@Table(name="tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;
    @Column(name="Title")
    private String title;
    @Column(name="Description")

    private String description;
    @Column(name="Color")
    @Enumerated(EnumType.STRING)
    private Color color;

@CreatedDate
@Column(name="CreatedAt")
private Instant createdAt;
@LastModifiedDate
@Column(name="LastUpdatedAt")
private Instant lastUpdatedAt;

@Column(name="Status")
private TaskStatus status;

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
