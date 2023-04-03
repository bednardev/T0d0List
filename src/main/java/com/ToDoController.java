package com;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.TaskService;
import java.util.List;


@RestController
public class ToDoController {
    private TaskService taskService;
    public ToDoController(TaskService taskService) {
        this.taskService=taskService;
    }


@GetMapping (value = "/tasks"){
    public List<Task> getTask() {
        return ();
    }
}
@PostMapping (value = "/tasks"){

}
}
