package com.example.kotlinspringboot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.lang.Exception
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@SpringBootApplication
class KotlinSpringBootApplication

fun main(args: Array<String>) {
    runApplication<KotlinSpringBootApplication>(*args)
}


@Entity
data class Test1(
        @Id
        var id: UUID = UUID(0, 0),
        var name: String = ""
)

interface Test1Repository : JpaRepository<Test1, UUID>

@RestController
class Test1Controller() {

    @GetMapping("/test")
    fun getAll(): String {
        return "test1"
    }
}


@RestController
class HomeController(
        private val test1Repository: Test1Repository
) {

    @GetMapping("{id}")
    fun home(@PathVariable id: UUID): String {
        return test1Repository.findById(id)
                .map { it.name }
                .orElseThrow { Exception("not found") }
    }
}