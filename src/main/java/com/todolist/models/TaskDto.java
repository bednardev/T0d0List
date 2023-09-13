package com.todolist.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public record TaskDto(
        Long id,
        @NotEmpty()
        String title,
        String description,
        @NotEmpty()
        String color,
        TaskStatus status,
        @NotNull()
        Long userId) {
}