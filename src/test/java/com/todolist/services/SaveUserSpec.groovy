package com.todolist.services

import com.todolist.models.User
import com.todolist.models.UserDto
import com.todolist.repositories.UserRepository
import spock.lang.Specification

class SaveUserSpec extends Specification {
    UserRepository userRepository = Mock()
    def userService = new UserService(userRepository)

    def "should create new user" () {
        given:
        def userDto = new UserDto(null,'Max', 'Jones', 'max@jones.pl')
        def expectedUser = new User(0L, 'Max', 'Jones', 'max@jones.pl')

        when:
        def result = userService.saveUser(userDto)

        then:
        1* userRepository.save(_) >> expectedUser
        result.id() == expectedUser.getId()
        result.name() == expectedUser.getName()
        result.surname() == expectedUser.getSurname()
        result.mail() == expectedUser.getMail()
    }
}
