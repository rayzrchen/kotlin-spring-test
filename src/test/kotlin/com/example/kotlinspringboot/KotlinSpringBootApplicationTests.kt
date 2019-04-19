package com.example.kotlinspringboot

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content

class KotlinSpringBootApplicationTests {

    @InjectMocks
    private lateinit var homeController: HomeController


    @Mock
    private lateinit var test1Repository: Test1Repository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

//    @Test
//    fun homeTest() {
//
//
//        val homeController = HomeController()
//        val actual = homeController.home()
//        assertThat(actual).isEqualTo("hello")
//    }

    @Test
    internal fun homeWithRepositoryTest() {
        val expected = "Ray"

        val uuid = UUID.randomUUID()
        val test1 = Test1(uuid, expected)
        `when`(test1Repository.findById(uuid)).thenReturn(Optional.of(test1))

        val actual = homeController.home(uuid)

        assertThat(actual).isEqualTo(expected)
    }


}


@RunWith(SpringRunner::class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Integration1Tests{

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Test
    fun mockMvcTest() {
        val insertedRecordId = "648a7a92-2cc0-4fbc-bd6a-aeca55435ca3"
        val expectedResult = "test1"

        val actual = this.restTemplate.getForObject("/$insertedRecordId", String::class.java)
        assertThat(actual).isEqualTo(expectedResult)
    }
}


@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class IntegrationTests{

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun firstTest() {

        val expectedResult = "test1"

        mockMvc.perform(get("/test"))
                .andExpect(status().isOk)
                .andExpect(content().string(expectedResult))


    }

}


