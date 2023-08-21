package br.com.api.kotlin.repository

import br.com.api.kotlin.entity.Sale
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface SaleRepository : JpaRepository<br.com.api.kotlin.entity.Sale, Long> {



    @Query("SELECT sa FROM Sale sa WHERE sa.saleCode = :saleCode")
    fun findByName(saleCode: String?): Optional<br.com.api.kotlin.entity.Sale>
}