package iamsane.kotlinrestfulapi.controller

import com.fasterxml.jackson.databind.ObjectMapper
import iamsane.kotlinrestfulapi.TestContainersConfig
import iamsane.kotlinrestfulapi.domain.User
import iamsane.kotlinrestfulapi.dto.user.CreateUserRequest
import iamsane.kotlinrestfulapi.repository.UserRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Import(TestContainersConfig::class)
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var userRepository: UserRepository
    private val objectMapper = ObjectMapper()

    @Test
    @Transactional
    fun `should find a user by id`() {
        // given
        var user = User(
            firstName = "John",
            lastName = "Doe",
            username = "john_doe",
            password = "password",
        )
        user = userRepository.save(user)

        // when // then
        mockMvc.get("/v1/users/{id}", user.id) {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status().isOk()
            jsonPath("$.id").isNumber()
            jsonPath("$.firstName").value("John")
            jsonPath("$.lastName").value("Doe")
            jsonPath("$.username").value("john_doe")
            jsonPath("$.password").doesNotExist()
        }
    }

    @Test
    @Transactional
    fun `should not find a user by id and respond with 404`() {
        // given
        val id = 1L

        // when // then
        mockMvc.get("/v1/users/{id}", id) {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status().isNotFound()
        }
    }

    @Test
    @Transactional
    fun `should create a user and return id`() {
        // given
        val dto = CreateUserRequest(
            firstName = "John",
            lastName = "Doe",
            username = "john_doe",
            password = "password",
        )

        // when // then
        mockMvc.post("/v1/users") {
            accept = MediaType.APPLICATION_JSON
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(dto)
        }.andExpect {
            status().isCreated()
            jsonPath("$.id").isNumber()
        }
    }
}
