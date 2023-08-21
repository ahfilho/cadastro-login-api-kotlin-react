package br.com.api.kotlin.entity

import java.util.Date
import javax.persistence.*


@Entity
@Table(name = "CPU")
class Cpu() {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    val id: Long? = null

    @Column(name = "brand")
    var brand: String? = null

    @Column(name = "serial_number", length = 15)
    var serialNumber: String? = null

    @Column(name = "model")
    var model: String? = null

    @Column(name = "purchase_price")
    var purchasePrice: Date? = null

    @Column(name = "purchase_date")
    var purchaseDate: Date? = null

    @Column(name = "sale_value")
    var saleValue: Date? = null

    @Column(name = "arrival_date")
    var arrivalDate: Date? = null

    @Column(name = "cores")
    var cores: Int? = null

    @Column(name = "threads")
    var threads: Int? = null

    @Column(name = "clock")
    var clock: Double? = null


    constructor(
        brand: String?,
        model: String?,
        serialNumber: String,
        purchasePrice: Date,
        purchaseDate: Date,
        saleValue: Float?,
        arrivalDate: Date?,
        core: Int?,
        threads: Int?,
        clock: Float?
    ) : this() {
        this.brand = brand
        this.model = model
        this.serialNumber = serialNumber
    }


}