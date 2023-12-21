package br.com.api.kotlin.dto

import java.time.LocalDate
import java.util.*
import javax.persistence.*


data class ClientDto(
    val name: String?,
    val phone: String?,
    val dateRegister: LocalDate?,
    val cpf: String?,
    val address: AddressDto? = null
)



