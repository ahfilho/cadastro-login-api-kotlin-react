package br.com.api.kotlin.dto

import java.util.*
import javax.persistence.Column

data class CpuDto(
    val id: Long?,
    val brand: String?,
    var serialNumber: String?,

    var model: String?,

    var purchasePrice: Date?,

    var purchaseDate: Date?,

    var saleValue: Date?,

    var arrivalDate: Date?,

    var cores: Int?,

    var threads: Int?,

    var clock: Double?,
)