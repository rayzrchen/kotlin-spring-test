package com.example.kotlinspringboot.reservation

import com.example.kotlinspringboot.reservation.Reservation
import org.springframework.data.jpa.repository.JpaRepository

interface ReservationRepository : JpaRepository<Reservation, Long>{

}
