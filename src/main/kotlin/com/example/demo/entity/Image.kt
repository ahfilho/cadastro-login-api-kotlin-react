package com.example.demo.entity

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