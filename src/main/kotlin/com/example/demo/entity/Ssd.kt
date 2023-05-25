package com.example.demo.entity

import java.math.BigDecimal
import java.time.LocalDate
import java.util.Date
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "hd_ssd")
class Ssd {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    val id: Long? = null

    @Column(name = "brand")
    var brand: String? = null

    @Column(name = "serial_number", length = 17)
    var serialNumber: String? = null

    @Column(name = "size_storage", length = 3)
    var size: Int? = null

    @Column(name = "purchase_price")
    var purchasePrice: Double? = null

    @Column(name = "purchase_date")
    var purchaseDate: LocalDate? = null

    @Column(name = "sale_value")
    var saleValue: Float? = null

    @Column(name = "arrival_date")
    var arrivalDate: LocalDate? = null

    @Column(name = "actual_date")
    var actualDate: LocalDate? = null

    @Column(name = "sale_date")
    var saleDate: LocalDate? = null
}