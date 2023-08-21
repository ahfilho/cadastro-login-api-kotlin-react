package br.com.api.kotlin.entity

import javax.persistence.*

@Entity
@Table(name = "IMAGE")
class Image {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column
    val name: String? = null
}