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
    val serial_number: Int? = null

    @Column(name = "model")
    val model: String? = null

    @Column(name = "purchase_price")
    val purchase_price: Date? = null

    @Column(name = "purchase_date")
    val purchase_date: Date? = null

    @Column(name = "sale_value")
    val sale_value: Date? = null

    @Column(name = "arrival_date")
    val arrival_date: Date? = null

    @Column(name = "core")
    val cores: Int? = null

    @Column(name = "thread")
    val threads: Int? = null

    @Column(name = "clock")
    val clock: Double? = null

    val nome: String = "arlindo"

    val teste: String? = null

}