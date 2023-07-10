package com.todolist.repositories;

import com.todolist.models.Task;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TaskRepository extends CrudRepository<Task, Long>, JpaSpecificationExecutor, PagingAndSortingRepository<Task, Long>
{

}

