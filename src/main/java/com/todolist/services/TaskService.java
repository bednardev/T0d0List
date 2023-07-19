package com.todolist.services;

import com.todolist.models.Color;
import com.todolist.models.Task;
import com.todolist.models.TaskDto;
import com.todolist.models.User;
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
import static com.todolist.repositories.specifications.TaskUserIdSpecification.userIdLike;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public TaskDto saveTask(TaskDto taskDto) {
        Task taskToSave = new Task(taskDto.getTitle(), taskDto.getDescription(), Color.valueOf(taskDto.getColor().toUpperCase()));
        User user = userRepository.findById(taskDto.getUserId())
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        taskToSave.setUserId(user.getId());
        Task task = taskRepository.save(taskToSave);
        return new TaskDto(task.getId(), task.getTitle(), task.getDescription(), task.getColorAsName(), task.getUserId());
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
                .map(task -> new TaskDto(task.getId(), task.getTitle(), task.getDescription(), task.getColorAsName(), task.getUserId()))
                .collect(Collectors.toList());
    }


    public Page<TaskDto> getTasksAsPage(Integer pageNumber, Integer pageSize, String sort, Sort.Direction direction) {
        Pageable taskPage = PageRequest.of(pageNumber, pageSize, Sort.by(direction,sort));
        return taskRepository.findAll(taskPage)
                .map(task -> new TaskDto(task.getId(), task.getTitle(), task.getDescription(), task.getColorAsName()));
    }
    public List<TaskDto> getTasksForUserId(Long userId){
        Specification<Task> spec = where(null);
        List<Task> tasks = taskRepository.findAll(spec.and(userIdLike(userId)));
        return tasks.stream()
                .map(task -> new TaskDto(task.getId(), task.getTitle(), task.getDescription(), task.getColorAsName(), task.getUserId()))
                .collect(Collectors.toList());
    }



    public Optional<TaskDto> updateTask(TaskDto taskDtoToUpdate, Long id) {
        Task taskToUpdate = new Task(id, taskDtoToUpdate.getTitle(), taskDtoToUpdate.getDescription(), Color.valueOf(taskDtoToUpdate.getColor().toUpperCase()));
        User user = userRepository.findById(taskDtoToUpdate.getUserId())
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        taskToUpdate.setUserId(user.getId());
        return taskRepository.findById(id)
                .map(t -> taskRepository.save(taskToUpdate))
                .map(task -> new TaskDto(task.getId(), task.getTitle(), task.getDescription(), task.getColorAsName(), task.getUserId()));
    }

    private Task setTaskToPatch(Map<String, String> updates, Task taskToPatch){
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
                .map(task -> new TaskDto(task.getId(), task.getTitle(), task.getDescription(), task.getColorAsName()));
    }

    public Optional<TaskDto> findById(Long id){
        return taskRepository.findById(id)
                .map(task -> new TaskDto(task.getId(), task.getTitle(), task.getDescription(), task.getColorAsName()));
    }

    public String deleteTask(Long id) {
        taskRepository.deleteById(id);
        return "task with id: " + id + " successfully deleted";
    }
}