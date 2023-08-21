package br.com.api.kotlin.entity

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
    val serialNumber: String? = null

    @Column(name = "size")
    val size: Int? = null

    @Column(name = "purchase_price")
    val purchasePrice: Date? = null

    @Column(name = "purchase_date")
    val purchaseDate: Date? = null

    @Column(name = "sale_value")
    val saleValue: Date? = null

    @Column(name = "arrival_date")
    val arrivalDate: Date? = null

    @Column(name = "frequency")
    val frequency: Double? = null
}