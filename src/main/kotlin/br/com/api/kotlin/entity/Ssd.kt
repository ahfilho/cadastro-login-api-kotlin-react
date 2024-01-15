package br.com.api.kotlin.entity

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "hd_ssd")
class Ssd {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    val id: Long? = null

    @Column(name = "BRAND")
    var brand: String? = null

    @Column(name = "SERIAL_NUMBER", length = 17)
    var serialNumber: String? = null

    @Column(name = "SIZE_STORAGE", length = 3)
    var size: Int? = null

    @Column(name = "PURCHASE_PRICE")
    var purchasePrice: Double? = null

    @Column(name = "PURCHASE_DATE")
    var purchaseDate: LocalDate? = null

    @Column(name = "SALE_VALUE")
    var saleValue: Float? = null

    @Column(name = "ARRIVAL_DATE")
    var arrivalDate: LocalDate? = null

    @Column(name = "CURRENT_DATE")
    var currentDate: LocalDate? = null

    @Column(name = "SALE_DATE")
    var saleDate: LocalDate? = null

    @Column(name = "CONDITION")
    var condition: String? = null

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "IMAGE_ID")
    var image: Image? = null

}