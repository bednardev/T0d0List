package com.todolist.models;

public final class UserDto {

    private final Long id;
    private final String name;
    private final String surname;
    private final String mail;

    public UserDto(Long id, String name, String surname, String mail) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
    }

    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getMail() {
        return mail;
    }
    public Long getId() {
        return id;
    }
}
