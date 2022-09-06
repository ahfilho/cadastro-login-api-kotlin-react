package com.example.demo.service

import com.example.demo.entity.Cpu
import com.example.demo.repository.CpuRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
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
        return this.cpuRepository.delete(Cpu())
    }


    fun List(): MutableList<Cpu> {
        return this.cpuRepository.findAll()
    }

    fun update(id: Long) {
        val cpu = this.cpuRepository.findById(id)

        val obj = Cpu()
        if (cpu != null) {
            cpu.get()
            println("puxou algo")
        }
        return println("asdasdd teste update")
    }
}