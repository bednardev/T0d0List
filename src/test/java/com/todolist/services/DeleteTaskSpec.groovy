package com.todolist.services

import com.todolist.repositories.TaskRepository
import spock.lang.Specification

class DeleteTaskSpec extends Specification {

    TaskRepository taskRepository = Mock()
    def taskService = new TaskService(taskRepository)

    def "should delete task with given id"() {

        given:
        def id = 5L

        when:
        def result = taskService.deleteTask(id)

        then:
        1* taskRepository.deleteById(id)
        result == 'task with id: ' + id + ' successfully deleted'
    }
}
