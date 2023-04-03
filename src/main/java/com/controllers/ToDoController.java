package com.controllers;

import com.repositories.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.services.TaskService.getTasks;


@RestController
public class ToDoController {

 /*  private TaskService taskService;
    public ToDoController(TaskService taskService) {
        this.taskService=taskService;
    }
*/

@GetMapping (value = "/tasks"){
    public List<Task> getTask() {
        return getTasks();
    }
}
@PostMapping (value = "/tasks"){

}
}
