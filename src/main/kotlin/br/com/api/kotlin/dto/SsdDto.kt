package br.com.api.kotlin.dto

import br.com.api.kotlin.entity.Ssd
import java.math.BigDecimal
import java.time.LocalDate

data class SsdDto(
    val id: Long?,
    val brand: String?,
    val serialNumber: String?,
    val size: Int?,
    val purchasePrice: Double?,
    val purchaseDate: LocalDate?,
    val saleValue: Float?,
    val arrivalDate: LocalDate?,
    val currentDate: LocalDate?,
    val saleDate: LocalDate?,
    val condition: String?
)
