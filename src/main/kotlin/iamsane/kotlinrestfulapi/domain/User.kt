package iamsane.kotlinrestfulapi.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.PrePersist
import jakarta.persistence.Table
import java.time.Instant

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue
    val id: Long? = null,

    val firstName: String,

    val lastName: String,

    val username: String,

    val password: String,

    var createdAt: Instant? = null,
) {
    @PrePersist
    fun prePersist() {
        this.createdAt = Instant.now()
    }
}
