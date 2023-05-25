package com.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@Configuration
@EnableJpaAuditing
public class ToDoListApplication {


    @Autowired
    public static void main(String[] args) {
        SpringApplication.run(ToDoListApplication.class, args);
    }
}
