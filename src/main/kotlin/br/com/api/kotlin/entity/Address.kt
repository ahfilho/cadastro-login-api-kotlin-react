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
    val street: String? = null

    @Column(name = "NUMBER")
    val number: String? = null

    @Column(name = "DISTRICT")
    val district: String? = null

    @Column(name = "CITY")
    val city: String? = null

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADDRESS_ID")
    val client: Client? = null

}