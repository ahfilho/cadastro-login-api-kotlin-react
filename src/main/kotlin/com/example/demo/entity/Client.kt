package com.example.demo.entity


import java.util.Date
import javax.persistence.*


@Entity
@Table(name = "client")
class Client {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    var id: Long? = null

    @Column(name = "name")
    var name: String? = null

    @Column(name = "telephone")
    var telephone: Int? = null

    @Column(name = "cpf")
    var cpf: String? = null

    @Column(name = "contact_number")
    var contactNumber : Date? = null


}