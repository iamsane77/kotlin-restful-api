package iamsane.kotlinrestfulapi.service.impl

import iamsane.kotlinrestfulapi.dto.common.IdResponse
import iamsane.kotlinrestfulapi.dto.user.CreateUserRequest
import iamsane.kotlinrestfulapi.dto.user.UserResponse
import iamsane.kotlinrestfulapi.exception.ResourceNotFoundException
import iamsane.kotlinrestfulapi.mapper.UserMapper
import iamsane.kotlinrestfulapi.repository.UserRepository
import iamsane.kotlinrestfulapi.service.UserService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper,
) : UserService {
    @Transactional
    override fun createUser(dto: CreateUserRequest): IdResponse {
        var entity = userMapper.toEntity(dto)

        entity = userRepository.save(entity)

        return IdResponse(id = entity.id)
    }

    @Transactional
    override fun getUser(id: Long): UserResponse {
        return userRepository.findById(id)
            .map(userMapper::toDto)
            .orElseThrow { ResourceNotFoundException("User with id=$id not found") }
    }
}
