package com.todolist.models;

public class Task {
    private Long id;
    private String title;
    private String description;
    private Enum color;

    public Task(String title,String description,Enum color) {
        this.title = title;
        this.description = description;
        this.color = color;
    }
    public Enum getColor() { return color; }
    public String getTitle(){
        return title;
    }
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
