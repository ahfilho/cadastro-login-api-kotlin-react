package com.example.demo.entity

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
    @Column(name = "encryptId")
    val id: Long? = null

    @Column(name = "brand")
    val brand: String? = null

    @Column(name = "serial_number", length = 17)
    val serialNumber: String? = null

    @Column(name = "size_storage", length = 3)
    val size: Int? = null

    @Column(name = "purchase_price")
    val purchasPrice: Float? = null

    @Column(name = "purchase_date")
    val purchaseDate: Date? = null

    @Column(name = "sale_value")
    val saleValue: Date? = null

    @Column(name = "arrival_date")
    val arrivalDate: Date? = null


}