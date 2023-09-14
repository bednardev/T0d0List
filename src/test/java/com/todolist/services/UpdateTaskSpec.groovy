package com.todolist.services

import com.todolist.models.Color
import com.todolist.models.Task
import com.todolist.models.TaskDto
import com.todolist.models.TaskStatus
import com.todolist.repositories.TaskRepository
import spock.lang.Specification

class UpdateTaskSpec extends Specification {

    TaskRepository taskRepository = Mock()
    def taskService = new TaskService(taskRepository)

    def "should update task"() {
        given:
        def id = 1L
        def taskDto = new TaskDto(null, "Task", "Do the task", "BLUE", TaskStatus.IN_PROGRESS)
        def taskToUpdate = Optional.of(new Task(id, "Z", "B", Color.GREY, TaskStatus.TO_DO))
        def expectedTask = new Task(id, "Task", "Do the task", Color.BLUE, TaskStatus.IN_PROGRESS)

        when:
        def result = taskService.updateTask(taskDto, id)

        then:
        1 * taskRepository.findById(id) >> taskToUpdate
        1 * taskRepository.save(taskToUpdate.get()) >> expectedTask
        result.id() == expectedTask.getId()
        result.title() == expectedTask.getTitle()
        result.description() == expectedTask.getDescription()
        result.color() == expectedTask.getColor().name()
        result.status() == expectedTask.getStatus()
    }
}