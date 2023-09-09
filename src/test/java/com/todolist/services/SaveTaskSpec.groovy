package com.todolist.services

import com.todolist.models.TaskDto
import com.todolist.models.TaskStatus
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.client.HttpClientErrorException
import spock.lang.Specification

class SaveTaskSpec extends Specification {

    TaskService taskService = Mock(TaskService.class)

    def "should save new task"()

    {
        given:
        def taskDto = new TaskDto()
        taskDto.setTitle("Task")
        taskDto.setDescription("Task test")
        taskDto.setColor("BLUE")

        when:
        def taskDtoToSave = taskService.saveTask(taskDto)
        def savedTaskDto = taskService.findById(taskDtoToSave.getId())
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND))

        then:
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
