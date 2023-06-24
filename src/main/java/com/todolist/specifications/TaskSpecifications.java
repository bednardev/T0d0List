package com.todolist.specifications;

import com.todolist.models.Task;
import org.springframework.data.jpa.domain.Specification;

public class TaskSpecifications {

    private TaskSpecifications(){
    }

    public static Specification<Task> titleLike(String title){
        return new TaskTitleSpecification(title);
    }

    public static Specification<Task> colorLike(String color){
        return new TaskColorSpecification(color);
    }
}
