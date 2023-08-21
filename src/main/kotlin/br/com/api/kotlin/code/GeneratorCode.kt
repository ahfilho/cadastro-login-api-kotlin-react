package br.com.api.kotlin.code

import org.springframework.stereotype.Controller

@Controller
class GeneratorCode {

    fun generateCode(length: Int): String {

        val alphanumericCode = (('a'..'z') + ('0'..'9') + ('A'..'Z') + ('0'..'9'))
        return (1..length).map { alphanumericCode.random() }.joinToString("")
    }

}