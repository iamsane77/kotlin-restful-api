package iamsane.kotlinrestfulapi.service.impl

import iamsane.kotlinrestfulapi.dto.common.IdResponse
import iamsane.kotlinrestfulapi.dto.user.CreateUserRequest
import iamsane.kotlinrestfulapi.mapper.UserMapper
import iamsane.kotlinrestfulapi.repository.UserRepository
import iamsane.kotlinrestfulapi.service.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper,
) : UserService {
    override fun createUser(dto: CreateUserRequest): IdResponse {
        var entity = userMapper.toEntity(dto)

        entity = userRepository.save(entity)

        return IdResponse(id = entity.id)
    }
}