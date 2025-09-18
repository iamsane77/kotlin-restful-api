package iamsane.kotlinrestfulapi.mapper

import iamsane.kotlinrestfulapi.domain.User
import iamsane.kotlinrestfulapi.dto.user.CreateUserRequest
import iamsane.kotlinrestfulapi.dto.user.UserResponse
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

    fun toDto(entity: User): UserResponse {
        return UserResponse(
            id = entity.id,
            firstName = entity.firstName,
            lastName = entity.lastName,
            username = entity.username,
        )
    }
}
