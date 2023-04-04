package com.models;

public class Task {
    private Long id;
    private String title;
    private String description;

    public Task(String title,String description) {
        this.title = title;
        this.description = description;
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
                " description: " + description;
    }

}
