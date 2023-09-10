package com.todolist.services

import com.todolist.models.Color
import com.todolist.models.Task
import com.todolist.models.TaskDto
import com.todolist.models.TaskStatus
import com.todolist.repositories.TaskRepository
import org.springframework.web.bind.MethodArgumentNotValidException
import spock.lang.Specification

class SaveTaskSpec extends Specification {

    TaskRepository taskRepository = Mock(TaskRepository.class)
    TaskService taskService = new TaskService(taskRepository)

    def "should save new task"()

    {
        given:
        def taskDto = new TaskDto ('Task', 'Task test', 'BLUE')
        def expectedTask = new Task(5, 'Task', 'Task test', Color.BLUE, TaskStatus.BACKLOG)
        when:
        taskService.saveTask(taskDto)

        then:
        1 * taskRepository.save(_) >> { Task actualTask ->
            assert actualTask.getTitle() == expectedTask.getTitle()
            assert actualTask.getDescription() == expectedTask.getDescription()
            assert actualTask.getColor() == expectedTask.getColor()
            assert actualTask.getStatus() == expectedTask.getStatus()
        }
    }

    def "should throw MethodArgumentNotValidException empty title"() {
        given:
        TaskDto taskDto = new TaskDto()
        taskDto.setTitle("")
        taskDto.setDescription("Task test")
        taskDto.setColor("BLUE")

        when:
        taskService.saveTask(taskDto)

        then:
        thrown(MethodArgumentNotValidException)
    }

    def "should throw MethodArgumentNotValidException wrong color value"() {
        given:
        TaskDto taskDto = new TaskDto()
        taskDto.setTitle("Task")
        taskDto.setDescription("Task test")
        taskDto.setColor("Yellow")


        when:
        taskService.saveTask(taskDto)

        then:
        thrown(MethodArgumentNotValidException)
    }
}
