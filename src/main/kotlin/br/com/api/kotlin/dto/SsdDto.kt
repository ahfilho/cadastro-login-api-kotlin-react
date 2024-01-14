package br.com.api.kotlin.dto

import br.com.api.kotlin.entity.Ssd
import java.math.BigDecimal
import java.time.LocalDate

data class SsdDto(
    var brand: String? = null,
    var serialNumber: String? = null,
    var size: Int? = null,
    var purchasePrice: Double? = null,
    var purchaseDate: LocalDate? = null,
    var saleValue: Float? = null,
    var arrivalDate: LocalDate? = null,
    var currentDate: LocalDate? = null,
    var saleDate: LocalDate? = null,
    var condition: String? = null,
)



