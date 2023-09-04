package br.com.api.kotlin.repository

import br.com.api.kotlin.entity.Client
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

<<<<<<< HEAD
interface ClientRepository : JpaRepository<br.com.api.kotlin.entity.Client, Long> {


    @Query("SELECT cl FROM Client cl WHERE cl.cpf = :cpf")
    fun findByCpf(cpf: String?): Optional<br.com.api.kotlin.entity.Client>
=======
interface ClientRepository : JpaRepository<Client, Long> {


    @Query("SELECT cl FROM Client cl WHERE cl.cpf = :cpf")
    fun findByCpf(cpf: String?): Optional<Client>
>>>>>>> master

}