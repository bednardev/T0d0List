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
        def id = 4L
        def taskDto = new TaskDto(1L, "Task", "Do the task", "BLUE", 4L, "IN_PROGRESS")
        def taskToUpdate = Optional.of(new Task(id, "Z", "B", Color.GREY, 3L, TaskStatus.TO_DO))
        def expectedTask = new Task(id, "Task", "Do the task", Color.BLUE, 4L, TaskStatus.IN_PROGRESS)
        taskRepository.findById(id) >> taskToUpdate

        when:
        def result = taskService.updateTask(taskDto, id).get()

        then:
        1 * taskRepository.findById(id)
        1 * taskRepository.save(_) >> expectedTask
        result.id() == expectedTask.getId()
        result.title() == expectedTask.getTitle()
        result.description() == expectedTask.getDescription()
        result.color() == expectedTask.getColor().name()
        result.status() == expectedTask.getStatus().name()
    }
}