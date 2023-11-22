package br.com.api.kotlin.dto

import java.util.*
import javax.persistence.*


data class ClientDto(
    val name: String?,
    val phone: String?,
    val dateRegister: Date?,
    val cpf: String?,
    val address: AddressDto? = null
)



