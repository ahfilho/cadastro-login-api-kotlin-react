package br.com.api.kotlin.service

import br.com.api.kotlin.entity.Cpu
import br.com.api.kotlin.repository.CpuRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
@Transactional
class CpuService(private val cpuRepository: CpuRepository) {

//    @Autowired
//    lateinit var cpuRepository: CpuRepository

    fun saveCpu(cpu: Cpu): Cpu {
        return this.cpuRepository.save(cpu)
    }

    fun deleteCpu(id: Long) {
        val teste: Optional<Cpu> = cpuRepository.findById(id)
        if (cpuRepository.existsById(id))
            cpuRepository.delete(teste.get())
    }

    fun List(): MutableList<Cpu> {
        return this.cpuRepository.findAll()
    }

    //TODO
    fun updateCpu(id: Long, cpu: Cpu): Cpu {
        val a = cpuRepository.findById(id)
        if (a.isPresent) {
            val c: Cpu = a.get()
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

