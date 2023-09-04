package com.example.demo.entity


import java.util.*
import javax.persistence.*


@Entity
@Table(name = "CLIENT")
class Client {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    var id: Long? = null

    @Column(name = "NAME")
    var name: String? = null

    @Column(name = "PHONE")
    var phone: Int? = null

    @Column(name = "CPF")
    var cpf: String? = null

    @Column(name = "DATE_REGISTER")
    var dateRegister: Date? = null

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "CLIENT_ID")
    val address: Address? = null

}