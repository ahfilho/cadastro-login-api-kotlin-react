package br.com.api.kotlin.service

import br.com.api.kotlin.entity.Ram
import br.com.api.kotlin.repository.RamRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class RamService(private val ramRepository: RamRepository) {

//    @Autowired
//    lateinit var ramRepository: RamRepository

    fun saveRam(ram: Ram): Ram? {
        return ramRepository.save(ram)
    }

    fun listALl(): MutableList<Ram> {
        return ramRepository.findAll();
    }

    fun update(id: Long, ram: Ram): Ram {
        val u = ramRepository.findById(id).map {
            it.brand
            it.frequency
            it.size
        }
        ramRepository.save(ram)
        return ram
    }

    fun delete(id: Long) {
        val d = ramRepository.findById(id)
        if (d.isPresent) {
            ramRepository.delete(d.get())
        }
    }
}