package com.todolist.controllers;


import com.todolist.models.UserDto;
import com.todolist.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ControllerExceptionHandler controllerExceptionHandler;

    public UserController(UserService userService, ControllerExceptionHandler controllerExceptionHandler) {
        this.userService = userService;
        this.controllerExceptionHandler = controllerExceptionHandler;
    }

    @PostMapping
    public UserDto saveUser(@RequestBody UserDto userDto) {
        return userService.saveUser(userDto);
    }

    @GetMapping
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.findById(id).
                orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        userService.deleteUser(id);
    }

    @PutMapping("{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return userService.updateUser(userDto, id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }
}
