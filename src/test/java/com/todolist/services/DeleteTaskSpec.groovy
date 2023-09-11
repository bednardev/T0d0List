package com.todolist.services

import com.todolist.models.Task
import com.todolist.repositories.TaskRepository
import org.springframework.web.client.HttpClientErrorException
import spock.lang.Specification

class DeleteTaskSpec extends Specification{

    TaskRepository taskRepository = Mock(TaskRepository.class)
    TaskService taskService = new TaskService(taskRepository)

    def "should delete task with given id"(){

        given:
        Long id
        taskRepository.findById(id) >> Optional<Task>

        when:
        taskRepository.findById(id).isPresent()
        taskService.deleteTask(id)

        then:
        taskService.findById(id).isEmpty()
    }

    def "should throw handleHttpClientErrorException task with given id not present"(){

        given:
        Long id

        when:
        taskService.findById(id).isEmpty()
        taskService.deleteTask(id)

        then:
        thrown(HttpClientErrorException)
    }
}
