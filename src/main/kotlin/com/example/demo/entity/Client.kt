package com.example.demo.entity


import java.util.Date
import javax.persistence.*


@Entity
@Table(name = "cliente")
class Client {

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

    @Column(name = "contact_number")
    val contactNumber : Date? = null


}