package br.com.api.kotlin.repository

import br.com.api.kotlin.entity.Ssd
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface SsdRepository: JpaRepository<br.com.api.kotlin.entity.Ssd, Long> {

    @Query("SELECT p.id FROM Ssd p WHERE purchase_date = arrival_date ")
    fun checaTeste(): List<br.com.api.kotlin.entity.Ssd>


}