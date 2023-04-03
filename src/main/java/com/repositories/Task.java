package com.repositories;

public class Task {
    Long id;
    private String title;
    private String description;

    public Task(String title,String description) {
        this.title = title;
        this.description = description;
    }

    public static void setId(Long id) {
        this.id = id;
    }

    public String toString() {
        return  " title: " + title +
                " description: " + description;
    }

}
