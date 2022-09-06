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
    lateinit var repositorySsd: SsdRepository

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

        return this.repositorySsd.save(hdssd)
    }

    fun List(): MutableList<Ssd> {

        return this.repositorySsd.findAll()
    }

    fun delete(id: Long) {
        return this.repositorySsd.delete(Ssd())
    }
    fun teste(): List<Ssd> {
        return this.repositorySsd.checaTeste()
    }

}

fun update(id: Long) {
    //Optional<Long >() teste = (Optional<HdSsd>) EMPTY;

    // if(id!= null){
    //  val a = object : HdSsd() {

}




