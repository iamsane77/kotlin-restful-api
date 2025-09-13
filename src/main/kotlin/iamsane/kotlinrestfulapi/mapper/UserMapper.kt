package iamsane.kotlinrestfulapi.mapper

import iamsane.kotlinrestfulapi.domain.User
import iamsane.kotlinrestfulapi.dto.user.CreateUserRequest
import org.springframework.stereotype.Component

@Component
class UserMapper {
    fun toEntity(createUserRequest: CreateUserRequest): User {
        return User(
            firstName = createUserRequest.firstName,
            lastName = createUserRequest.lastName,
            username = createUserRequest.username,
            password = createUserRequest.password,
        )
    }
}