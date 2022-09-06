package com.example.demo.entity

import java.util.Date
import javax.persistence.*


@Entity
@Table(name = "CPU")
class Cpu {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    val id: Long? = null

    @Column(name = "brand")
    val brand: String? = null

    @Column(name = "serial_number", length = 15)
    val serialNumber: Int? = null

    @Column(name = "model")
    val model: String? = null

    @Column(name = "purchase_price")
    val purchasePrice: Date? = null

    @Column(name = "purchase_date")
    val purchaseDate: Date? = null

    @Column(name = "sale_value")
    val saleValue: Date? = null

    @Column(name = "arrival_date")
    val arrivalDate: Date? = null

    @Column(name = "cores")
    val cores: Int? = null

    @Column(name = "threads")
    val threads: Int? = null

    @Column(name = "clock")
    val clock: Double? = null

}