package com.example.kotlinspringboot

import org.springframework.data.jpa.repository.JpaRepository

interface ReservationRepository : JpaRepository<Reservation, Long>{

}
