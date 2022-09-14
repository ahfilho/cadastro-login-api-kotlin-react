package com.example.demo.controller

import com.example.demo.entity.Cpu
import com.example.demo.service.CpuService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/cpu")
class CpuController {

    @Autowired
    lateinit var cpuService: CpuService

    @PostMapping
    fun save(@RequestBody cpu: Cpu): Cpu {
        return cpuService.saveCpu(cpu)
    }

    @GetMapping
    fun list(): List<Cpu> {
        return cpuService.List()
    }

    //TODO
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long?, @RequestBody cpu: Cpu): ResponseEntity<Cpu?>? {
        return ResponseEntity.ok().body(cpuService.updateCpu(id!!, cpu))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long?): HttpStatus? {
        cpuService.deleteCpu(id!!)
        return HttpStatus.OK
    }

}



