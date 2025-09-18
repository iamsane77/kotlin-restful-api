package iamsane.kotlinrestfulapi.controller

import com.fasterxml.jackson.databind.ObjectMapper
import iamsane.kotlinrestfulapi.TestContainersConfig
import iamsane.kotlinrestfulapi.domain.User
import iamsane.kotlinrestfulapi.dto.user.CreateUserRequest
import iamsane.kotlinrestfulapi.repository.UserRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@Import(TestContainersConfig::class)
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc
    @Autowired
    private lateinit var userRepository: UserRepository
    private val objectMapper = ObjectMapper()

    @AfterEach
    fun tearDown() {
        userRepository.deleteAll()
    }

    @Test
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
        mockMvc.perform(
                get("/v1/users/{id}", user.id)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").isNumber())
            .andExpect(jsonPath("$.firstName").value("John"))
            .andExpect(jsonPath("$.lastName").value("Doe"))
            .andExpect(jsonPath("$.username").value("john_doe"))
            .andExpect(jsonPath("$.password").doesNotExist())
    }

    @Test
    fun `should not find a user by id and respond with 404`() {
        // given
        val id = 1L;

        // when // then
        mockMvc.perform(
                get("/v1/users/{id}", id)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNotFound())
    }

    @Test
    fun `should create a user and return id`() {
        // given
        val dto = CreateUserRequest(
            firstName = "John",
            lastName = "Doe",
            username = "john_doe",
            password = "password",
        )

        // when // then
        mockMvc.perform(
                post("/v1/users")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(dto))
            )
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").isNumber())
    }
}
