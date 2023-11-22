package br.com.api.kotlin.dto

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

data class AddressDto(
    val street: String?,
    val number: String?,
    val district: String?,
    val city: String?,

)
