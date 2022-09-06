package com.example.demo.entity


import java.util.Date
import javax.persistence.*


@Entity
@Table(name = "cliente")
class Cliente {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    val id: Long? = null

    @Column(name = "name")
    val name: String? = null

    @Column(name = "telephone")
    val telephone: Int? = null

    @Column(name = "cpf")
    val cpf: String? = null

    @Column(name = "home_number")
    val home_number : Date? = null

    @Column(name = "purchase_date")
    val purchase_date: Date? = null

    @Column(name = "street")
    val street: Date? = null

    @Column(name = "arrival_date")
    val arrival_date: Date? = null

    @Column(name = "core")
    val cores: Int? = null

    @Column(name = "clock")
    val clock: Double? = null


}