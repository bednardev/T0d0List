package com.todolist.services

import com.todolist.models.Color
import com.todolist.models.Task
import com.todolist.models.TaskDto
import com.todolist.models.TaskStatus
import com.todolist.repositories.TaskRepository
import spock.lang.Specification

class SaveTaskSpec extends Specification {

    TaskRepository taskRepository = Mock()
    TaskService taskService = new TaskService(taskRepository)

    def "should save new task"()

    {
        given:
        def taskDto = new TaskDto ('Task', 'Task test', 'BLUE')
        def expectedTask = new Task(0L, 'Task', 'Task test', Color.BLUE, TaskStatus.BACKLOG)

        when:
        def result = taskService.saveTask(taskDto)

        then:
        1 * taskRepository.save(_) >> expectedTask
        result.id() == expectedTask.getId()
        result.title() == expectedTask.getTitle()
        result.description() == expectedTask.getDescription()
        result.color() == expectedTask.getColor().name()
        result.status() == expectedTask.getStatus()
        }
    }
