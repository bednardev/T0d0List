package com.todolist.services;

import com.todolist.controllers.StatusDoneException;
import com.todolist.models.Color;
import com.todolist.models.Task;
import com.todolist.models.TaskDto;
import com.todolist.models.TaskStatus;
import com.todolist.repositories.TaskRepository;
import com.todolist.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.todolist.repositories.specifications.TaskColorSpecification.colorLike;
import static com.todolist.repositories.specifications.TaskTitleSpecification.titleLike;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskDto saveTask(TaskDto taskDto) {
        Task taskToSave = new Task(taskDto.title(), taskDto.description(), Color.valueOf(taskDto.color().toUpperCase()), taskDto.userId(), TaskStatus.valueOf(taskDto.status()));
        Task task = taskRepository.save(taskToSave);
        return new TaskDto(task.getId(), task.getTitle(), task.getDescription(), task.getColorAsName(), task.getUserId(), task.getStatusAsName());
    }

    public List<TaskDto> getTasks(String color, String title) {
        Specification<Task> spec = where(null);
        if (color != null) {
            spec = spec.and(colorLike(Color.valueOf(color)));
        }
        if (title != null) {
            spec = spec.and(titleLike(title));
        }

        List<Task> tasks = taskRepository.findAll(spec);

        return tasks.stream()
                .map(task -> new TaskDto(task.getId(), task.getTitle(), task.getDescription(), task.getColorAsName(), task.getUserId(), task.getStatus()))
                .collect(Collectors.toList());
    }

    public TaskDto changeStatus(Long id) throws StatusDoneException {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        task.setStatus(task.getStatus().moveForward());
        taskRepository.save(task);
        return new TaskDto(task.getId(), task.getTitle(), task.getDescription(), task.getColorAsName(), task.getUserId(), task.getStatus());
    }

    public Page<TaskDto> getTasksAsPage(Integer pageNumber, Integer pageSize, String sort, Sort.Direction direction) {
        Pageable taskPage = PageRequest.of(pageNumber, pageSize, Sort.by(direction, sort));
        return taskRepository.findAll(taskPage)
                .map(task -> new TaskDto(task.getId(), task.getTitle(), task.getDescription(), task.getColorAsName(), task.getUserId(), task.getStatus()));
    }


    public Optional<TaskDto> updateTask(TaskDto taskDtoToUpdate, Long id) {
        Task taskToUpdate = new Task(id, taskDtoToUpdate.getTitle(), taskDtoToUpdate.getDescription(), Color.valueOf(taskDtoToUpdate.getColor().toUpperCase()), taskDtoToUpdate.getUserId());
        return taskRepository.findById(id)
                .map(t -> taskRepository.save(taskToUpdate))
                .map(task -> new TaskDto(task.getId(), task.getTitle(), task.getDescription(), task.getColorAsName(), task.getUserId(), task.getStatus()));
    }

    private Task setTaskToPatch(Map<String, String> updates, Task taskToPatch) {
        if (updates.containsKey("title")) {
            taskToPatch.setTitle(updates.get("title"));
        }
        if (updates.containsKey("description")) {
            taskToPatch.setDescription(updates.get("description"));
        }
        if (updates.containsKey("color")) {
            taskToPatch.setColorAsName(updates.get("color").toUpperCase());
        }
        taskRepository.save(taskToPatch);
        return taskToPatch;
    }

    public Optional<TaskDto> patchTask(Map<String, String> updates, Long id) {
        return taskRepository.findById(id)
                .map(t -> setTaskToPatch(updates, t))
                .map(task -> new TaskDto(task.getId(), task.getTitle(), task.getDescription(), task.getColorAsName(), task.getUserId(), task.getStatus()));
    }

    public Optional<TaskDto> findById(Long id) {
        return taskRepository.findById(id)
                .map(task -> new TaskDto(task.getId(), task.getTitle(), task.getDescription(), task.getColorAsName(), task.getUserId(), task.getStatus()));
    }

    public String deleteTask(Long id) {
        taskRepository.deleteById(id);
        return "task with id: " + id + " successfully deleted";
    }

    public String getUsernameForTask(Long id) {
        Long userId = taskRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND))
                .getUserId();
        return userRepository.findById(userId)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND))
                .getName();
    }


}