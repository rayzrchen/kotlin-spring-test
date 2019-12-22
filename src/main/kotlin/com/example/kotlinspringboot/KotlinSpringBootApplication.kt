package com.example.kotlinspringboot

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@SpringBootApplication
class KotlinSpringBootApplication

fun main(args: Array<String>) {
    runApplication<KotlinSpringBootApplication>(*args)
}


@Entity
data class Test1(
        @Id
        @GeneratedValue
        var id: UUID,
        var name: String = ""
)

interface Test1Repository : JpaRepository<Test1, UUID>

@RestController
class Test1Controller {

    @GetMapping("/test")
    fun getAll(): String {
        return "test1"
    }
}


@RestController
class HomeController(
        private val test1Repository: Test1Repository
) {

    val logger: Logger = LoggerFactory.getLogger(HomeController::class.java)

    @GetMapping("{id}")
    fun home(@PathVariable id: UUID): String {
        logger.info(id.toString())
        return test1Repository.findByIdOrNull(id)?.name
                ?: throw RuntimeException("not found")
    }
}
