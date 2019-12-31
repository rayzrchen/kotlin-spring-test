package com.example.kotlinspringboot

import com.example.kotlinspringboot.reservation.Reservation
import com.example.kotlinspringboot.reservation.ReservationRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

class ReservationTests {

    @Test
    fun createTest() {
        val reservation = Reservation(1L, "ray")
        assertThat(reservation)
                .isNotNull
        assertThat(reservation.name)
                .isEqualTo("ray")
    }


}


@DataJpaTest
class ReservationRepositoryTests(
        @Autowired private val entityManager: TestEntityManager,
        @Autowired private val reservationRepository: ReservationRepository
) {

    @Test
    fun createTest() {
        entityManager.persist(Reservation(1L, "ray"))
        val actual = reservationRepository.findByIdOrNull(1L)
        assertThat(actual?.name).isEqualTo("ray")
    }
}



