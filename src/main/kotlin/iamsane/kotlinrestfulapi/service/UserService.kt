package iamsane.kotlinrestfulapi.service

import iamsane.kotlinrestfulapi.dto.common.IdResponse
import iamsane.kotlinrestfulapi.dto.user.CreateUserRequest

interface UserService {
    fun createUser(dto: CreateUserRequest): IdResponse
}