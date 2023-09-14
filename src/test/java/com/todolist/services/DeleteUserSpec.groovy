package com.todolist.services


import com.todolist.repositories.UserRepository
import spock.lang.Specification

class DeleteUserSpec extends Specification {
    UserRepository userRepository = Mock()
    def userService = new UserService(userRepository)

    def "should delete user with given id"() {
        given:
        Long id = 5L

        when:
        userService.deleteUser(id)

        then:
        1 * userRepository.deleteById(id)
    }
}
