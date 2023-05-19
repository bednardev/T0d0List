package com.todolist.repositories;

import com.todolist.models.Color;
import com.todolist.models.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {

    @Query("SELECT task FROM Task task WHERE task.color = :color")
    List<Task> findTasksByColor(@Param("color") Color color);

    @Query("SELECT task FROM Task task WHERE task.title like %:title%")
    List<Task> findTasksByTitle(@Param("title") String title);

    @Query("SELECT task FROM Task task WHERE task.title like %:title% AND task.color = :color")
    List<Task> findTasksByTitleAndColor(@Param("title") String title, @Param("color") Color color);


}

