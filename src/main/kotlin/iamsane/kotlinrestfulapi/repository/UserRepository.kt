package iamsane.kotlinrestfulapi.repository

import iamsane.kotlinrestfulapi.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
}