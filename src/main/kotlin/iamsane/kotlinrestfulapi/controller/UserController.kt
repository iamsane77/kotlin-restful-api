package iamsane.kotlinrestfulapi.controller

import iamsane.kotlinrestfulapi.dto.common.IdResponse
import iamsane.kotlinrestfulapi.dto.user.CreateUserRequest
import iamsane.kotlinrestfulapi.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService,
) {
    @PostMapping(path = ["/v1/users"])
    fun createUser(@RequestBody dto: CreateUserRequest): IdResponse {
        return userService.createUser(dto)
    }
}