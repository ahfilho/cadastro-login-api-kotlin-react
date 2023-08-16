package com.example.demo.repository

import com.example.demo.entity.Client
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface ClientRepository : JpaRepository<Client, Long> {


    @Query("SELECT cl FROM Client cl WHERE cl.cpf = :cpf")
    fun findByCpf(cpf: String?): Optional<Client>

}