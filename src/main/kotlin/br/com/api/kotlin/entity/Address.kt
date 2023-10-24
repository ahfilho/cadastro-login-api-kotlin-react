package br.com.api.kotlin.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "ADDRESS")
class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(name = "STREET")
    var street: String? = null

    @Column(name = "NUMBER")
    var number: String? = null

    @Column(name = "DISTRICT")
    var district: String? = null

    @Column(name = "CITY")
    var city: String? = null

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADDRESS_ID")
    val client: Client? = null

}