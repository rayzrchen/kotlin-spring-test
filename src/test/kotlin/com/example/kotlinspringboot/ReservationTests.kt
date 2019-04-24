package com.example.kotlinspringboot

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

class ReservationTests {

    @Test
    fun createTest() {
        val reservation = Reservation(1L, "ray")
        Assertions.assertThat(reservation)
                .`as`("not null")
                .isNotNull
        Assertions.assertThat(reservation.name)
                .`as`("return right name")
                .isEqualTo("ray")
    }


}


@RunWith(SpringRunner::class)
@DataJpaTest
class ReservationRepositoryTests {

    @Autowired
    private lateinit var entityManager: TestEntityManager


    @Autowired
    private lateinit var reservationRepository: ReservationRepository

    @Test
    fun createTest() {
        entityManager.persist(Reservation(1L, "ray"))
        val actual = reservationRepository.findById(1L)
        assertThat(actual).isNotEmpty
        assertThat(actual.get().name).isEqualTo("ray")
    }
}



