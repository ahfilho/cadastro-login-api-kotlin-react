package br.com.api.kotlin.service

import br.com.api.kotlin.entity.Cpu
import br.com.api.kotlin.repository.CpuRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
@Transactional
class CpuService(private val cpuRepository: br.com.api.kotlin.repository.CpuRepository) {

//    @Autowired
//    lateinit var cpuRepository: CpuRepository

    fun saveCpu(cpu: br.com.api.kotlin.entity.Cpu): br.com.api.kotlin.entity.Cpu {
        return this.cpuRepository.save(cpu)
    }

    fun deleteCpu(id: Long) {
        val teste: Optional<br.com.api.kotlin.entity.Cpu> = cpuRepository.findById(id)
        if (cpuRepository.existsById(id))
            cpuRepository.delete(teste.get())
    }

    fun List(): MutableList<br.com.api.kotlin.entity.Cpu> {
        return this.cpuRepository.findAll()
    }

    //TODO
    fun updateCpu(id: Long, cpu: br.com.api.kotlin.entity.Cpu): br.com.api.kotlin.entity.Cpu {
        val a = cpuRepository.findById(id)
        if (a.isPresent) {
            val c: br.com.api.kotlin.entity.Cpu = a.get()
            c.brand = cpu.brand
            c.model = cpu.model
            c.serialNumber = cpu.serialNumber
            c.purchasePrice = cpu.purchasePrice
            c.purchaseDate = cpu.purchaseDate
            c.saleValue = cpu.saleValue
            c.arrivalDate = cpu.arrivalDate
            c.cores = cpu.cores
            c.threads = cpu.threads
            c.clock = cpu.clock
        }
        return cpu
    }
}

