package iamsane.kotlinrestfulapi.dto.user

data class UserResponse(
    val id: Long?,
    val firstName: String,
    val lastName: String,
    val username: String,
)
