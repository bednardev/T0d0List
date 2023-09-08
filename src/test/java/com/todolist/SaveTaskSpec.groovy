package com.todolist

import com.todolist.models.TaskDto
import com.todolist.models.TaskStatus
import com.todolist.services.TaskService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.client.HttpClientErrorException
import spock.lang.Specification

class SaveTaskSpec extends Specification {

    TaskService taskService

    def "should save new task"()

    {
        given:
        TaskDto taskDto = new TaskDto()
        taskDto.setTitle("Task")
        taskDto.setDescription("Task test")
        taskDto.setColor("BLUE")

        when:
        TaskDto taskDtoToSave = taskService.saveTask(taskDto)

        then:
        TaskDto savedTaskDto = taskService.findById(taskDtoToSave.getId())
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND))
        savedTaskDto.getTitle() == "Task"
        savedTaskDto.getDescription() == "Task test"
        savedTaskDto.getColor() == "BLUE"
        savedTaskDto.getStatus() == TaskStatus.BACKLOG
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
