package com.example.kotlinspringboot.reservation

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Reservation(
        @Id
        var id: Long = 0,

        var name: String = ""
)
