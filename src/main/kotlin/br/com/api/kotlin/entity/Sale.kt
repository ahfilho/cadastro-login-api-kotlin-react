package br.com.api.kotlin.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.jetbrains.annotations.NotNull
import java.util.*
import javax.persistence.*


@Entity
@Table(name = "SALE")
class Sale {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    var id: Long? = null

    @Column(name = "CLIENT_NAME")
    var clientName: String? = null

    @NotNull
    @Column(name = "PAYMENT_TYPE")
    var paymentType: String? = null

    @NotNull
    @Column(name = "TYPE_CARD")
    var typeCard: String? = null

    @NotNull
    @Column(name = "WEIGHT")
    var weigth: String? = null

    @NotNull
    @Column(name = "SALE_DATE")
    var saleDate: Date? = null

    @NotNull
    @Column(name = "SALE_VALUE")
    var saleValue: Double? = null

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID")
    val client: Client? = null

    @Column(name="SALE_CODE")
    var saleCode: String? = null


}