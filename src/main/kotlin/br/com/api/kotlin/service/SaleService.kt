package br.com.api.kotlin.service

import br.com.api.kotlin.entity.Sale
import br.com.api.kotlin.enumerator.PaymentType
import br.com.api.kotlin.repository.SaleRepository
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional


@Transactional
@Service
class SaleService(private val saleRepository: SaleRepository) {


    fun List(): MutableList<Sale> {
        return saleRepository.findAll()
    }

    fun save(sale: Sale) {
        sale.saleDate = Date()
        if (sale.paymentType == ("APRAZO")) {

            sale.saleValue
            val porcentage: Double = 0.10 / 100.00
            val total = sale.saleValue!! + (porcentage * sale.saleValue!!)
            sale.saleValue = total
        }
        saleRepository.save(sale)

    }

    fun findByClientName(clientName: String?): Any {
        return saleRepository.findByName(clientName)
    }

    fun deleteSale(id: Long): Any {
        val del: Optional<Sale> = saleRepository.findById(id)
        if (saleRepository.existsById(id)) {
            saleRepository.delete(del.get())
            return "Encontrado."
        }
        return "ID não encontrado"
    }


}