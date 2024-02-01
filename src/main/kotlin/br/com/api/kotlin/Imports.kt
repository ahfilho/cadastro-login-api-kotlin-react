package br.com.api.kotlin
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EstudarApp

fun main(args: Array<String>) {
    runApplication<EstudarApp>(*args)
}
