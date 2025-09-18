package iamsane.kotlinrestfulapi.controller

import iamsane.kotlinrestfulapi.dto.common.IdResponse
import iamsane.kotlinrestfulapi.dto.user.CreateUserRequest
import iamsane.kotlinrestfulapi.dto.user.UserResponse
import iamsane.kotlinrestfulapi.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService,
) {
    @GetMapping(path = ["/v1/users/{id}"])
    fun getUserById(@PathVariable id: Long): UserResponse {
        return userService.getUser(id)
    }

    @PostMapping(path = ["/v1/users"])
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody dto: CreateUserRequest): IdResponse {
        return userService.createUser(dto)
    }
}
