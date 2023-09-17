package com.todolist.models;
public record TaskDto(
        Long id,
        String title,
        String description,
        String color,
        Long userId,
        String status) {
}