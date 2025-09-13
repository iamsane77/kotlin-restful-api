package iamsane.kotlinrestfulapi.dto.user

data class CreateUserRequest(
    val firstName: String,
    val lastName: String,
    val username: String,
    val password: String,
)
