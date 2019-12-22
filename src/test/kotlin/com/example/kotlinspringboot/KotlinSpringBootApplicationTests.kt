package com.example.kotlinspringboot

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*

@SpringBootTest
class KotlinSpringBootApplicationTests(
        @Autowired private val homeController: HomeController
) {

    private val id = UUID.randomUUID()

    @MockkBean
    lateinit var test1Repository: Test1Repository

    @BeforeEach
    fun setUp() {
        every { test1Repository.findByIdOrNull(id) } returns Test1(id, "test1")
    }

    @Test
    fun homeTest() {

        val actual = homeController.home(id)
        assertThat(actual).isEqualTo("test1")

        assertThrows<RuntimeException>("not found") {
            homeController.home(UUID.randomUUID())
        }
    }

}


@SpringBootTest
@AutoConfigureMockMvc
class IntegrationTests(
        @Autowired private val mockMvc: MockMvc
) {


    @Test
    fun firstTest() {
        val expectedResult = "test1"
        mockMvc.perform(get("/test"))
                .andExpect(status().isOk)
                .andExpect(content().string(expectedResult))
    }

}


