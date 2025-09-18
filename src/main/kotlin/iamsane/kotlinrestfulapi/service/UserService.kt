package iamsane.kotlinrestfulapi.service

import iamsane.kotlinrestfulapi.dto.common.IdResponse
import iamsane.kotlinrestfulapi.dto.user.CreateUserRequest
import iamsane.kotlinrestfulapi.dto.user.UserResponse

interface UserService {
    fun createUser(dto: CreateUserRequest): IdResponse

    fun getUser(id: Long): UserResponse
}
