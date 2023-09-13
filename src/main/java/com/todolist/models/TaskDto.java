package com.todolist.models;

import jakarta.validation.constraints.NotEmpty;


public record TaskDto(
        Long id,
        @NotEmpty()
        String title,
        String description,
        @NotEmpty()
        String color,
        TaskStatus status) {
}