package com.todolist.models;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class TaskDto {

    private Long id;
    @NotEmpty()
    private String title;
    private String description;
    @NotEmpty()
    private String color;
    private TaskStatus status;
}