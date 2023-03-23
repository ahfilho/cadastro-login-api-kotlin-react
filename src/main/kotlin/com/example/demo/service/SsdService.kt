package com.example.demo.service

import com.example.demo.entity.Ssd
import com.example.demo.repository.SsdRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Optional
import javax.transaction.Transactional

@Service
@Transactional
class HdSsdService {

    @Autowired
    lateinit var ssdRepository: SsdRepository

    fun save(hdssd: Ssd): Ssd {

        hdssd.arrivalDate = LocalDate.now()
        ssdRepository.save(hdssd)
        return hdssd
    }

    fun List(): MutableList<Ssd> {

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
        val oldObject: Ssd = ssdRepository.getById(id)

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




