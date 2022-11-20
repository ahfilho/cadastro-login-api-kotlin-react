package com.example.demo.service

import com.example.demo.entity.Ssd
import com.example.demo.repository.SsdRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Calendar
import javax.transaction.Transactional

@Service
@Transactional
class HdSsdService {

    @Autowired
    lateinit var ssdRepository: SsdRepository

    fun save(hdssd: Ssd): Ssd {
        val tempo = Calendar.getInstance()
        println(tempo.time)
//
//        try {
//            if(hdssd.serialNumber == "" || hdssd.serialNumber == null){
//                ResponseEntity.badRequest()
//            }
//        } catch (e: Exception) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Status(1,"ERRO"))
//        }

        return this.ssdRepository.save(hdssd)
    }

    fun List(): MutableList<Ssd> {

        return this.ssdRepository.findAll()
    }

    fun delete(id: Long) {
        return this.ssdRepository.delete(Ssd())
    }

    fun teste(): List<Ssd> {
        return this.ssdRepository.checaTeste()
    }


    //TODO TERMINAR
    fun updateSsd(id: Long, ssd: Ssd): Ssd {
        val buscaSsd = ssdRepository.findById(id)
        if (buscaSsd.isPresent) {
            val objetoNovo: Ssd = buscaSsd.get()
            objetoNovo.brand = ssd.brand
            objetoNovo.arrivalDate = ssd.arrivalDate
            objetoNovo.purchaseDate
            objetoNovo.size
            objetoNovo.saleValue
            objetoNovo.purchasePrice
            objetoNovo.serialNumber
        }

        return ssd
    }
}




