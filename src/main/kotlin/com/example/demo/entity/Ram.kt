package com.example.demo.entity

import org.springframework.web.bind.annotation.RestController
import java.util.Date
import javax.persistence.*


@Entity
@Table(name = "RAM")
class Ram {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "encryptId")
    val id: Long? = null

    @Column(name = "brand")
    val brand: String? = null

    @Column(name = "serial_number")
    val serial_number: Int? = null

    @Column(name = "size")
    val size: Int? = null

    @Column(name = "purchase_price")
    val purchase_price: Date? = null

    @Column(name = "purchase_date")
    val purchase_date: Date? = null

    @Column(name = "sale_value")
    val sale_value: Date? = null

    @Column(name = "arrival_date")
    val arrival_date: Date? = null

    @Column(name = "frequency")
    val frequency: Double? = null
}