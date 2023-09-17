package com.todolist.models;

public record UserDto

        (Long id,
         String name,
         String surname,
         String mail) {

}