package com.example.demo.entity

import javax.persistence.*

@Entity
@Table(name = "ADDRESS")
class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(name = "street")
    val street: String? = null

    @Column(name = "number")
    val number: String? = null

    @Column(name = "district")
    val district: String? = null

    @Column(name = "city")
    val city: String? = null


}