package com.todolist.services;

import com.todolist.models.TaskDto;
import com.todolist.models.User;
import com.todolist.models.UserDto;
import com.todolist.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto saveUser(UserDto userDto) {
        User userToSave = new User(userDto.name(), userDto.surname(), userDto.mail());
        User user = userRepository.save(userToSave);
        return new UserDto(user.getId(), user.getName(), user.getSurname(), user.getMail());
    }

    public List<UserDto> getUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(u -> users.add(u));
        return users
                .stream()
                .map(user -> new UserDto(user.getId(), user.getName(), user.getSurname(), user.getMail()))
                .collect(Collectors.toList());
    }

    public Optional<UserDto> findById(Long id) {
        return userRepository.findById(id)
                .map(user -> new UserDto(user.getId(), user.getName(), user.getSurname(), user.getMail()));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<UserDto> updateUser(UserDto userDto, Long id) {
        User userToUpdate = new User(id, userDto.name(), userDto.surname(), userDto.mail());
        return userRepository.findById(id)
                .map(u -> userRepository.save(userToUpdate))
                .map(user -> new UserDto(user.getId(), user.getName(), user.getSurname(), user.getMail()));
    }

    public List<TaskDto> getTasksForUserId(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND))
                .getTasks()
                .stream()
                .map(task -> new TaskDto(task.getId(), task.getTitle(), task.getDescription(), task.getColorAsName(), task.getUserId(), task.getStatusAsName()))
                .collect(Collectors.toList());
    }
}
