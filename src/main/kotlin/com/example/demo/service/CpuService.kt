package com.example.demo.service

import com.example.demo.entity.Cpu
import com.example.demo.repository.CpuRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
@Transactional
class CpuService {

    @Autowired
    lateinit var cpuRepository: CpuRepository

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
        val a = cpuRepository.findById(id).map {
            it.model
            it.cores
            it.brand
        }
        cpuRepository.save(cpu)
        return cpu
    }


}
