package br.com.api.kotlin.service

import br.com.api.kotlin.dto.SsdDto
import br.com.api.kotlin.entity.Ssd
import br.com.api.kotlin.repository.SsdRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.Optional
import javax.transaction.Transactional

@Service
@Transactional
class SsdService(private val ssdRepository: SsdRepository) {

//    @Autowired
//    lateinit var ssdRepository: SsdRepository


    fun save(ssd: Ssd): Ssd {
        ssdRepository.save(ssd)
        return ssd
    }

    fun list(): MutableList<Ssd> {
        return this.ssdRepository.findAll()
    }

    fun delete(id: Long) {
        val del: Optional<Ssd> = ssdRepository.findById(id)
        if (ssdRepository.existsById(id))
            ssdRepository.delete(del.get())

    }

    fun teste(): List<Ssd> {
        return this.ssdRepository.checaTeste()
    }

    //TODO TERMINAR
    fun updateSsd(id: Long, ssd: Ssd): Ssd {
        val oldObject = ssdRepository.getById(id)

        if (ssdRepository.existsById(id)) {
            oldObject.brand = ssd.brand
            oldObject.arrivalDate = ssd.arrivalDate
            oldObject.purchaseDate = ssd.purchaseDate
            oldObject.size = ssd.size
            oldObject.saleValue = ssd.saleValue
            oldObject.purchasePrice = ssd.purchasePrice
            oldObject.serialNumber = ssd.serialNumber
            ssdRepository.save(oldObject)
        }
        return oldObject
    }
}




