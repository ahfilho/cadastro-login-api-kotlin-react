package com.example.demo.controller

import com.example.demo.entity.Cpu
import com.example.demo.service.CpuService
import org.apache.coyote.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/cpu")
class CpuController {

    @Autowired
    lateinit var cpuService: CpuService

    @GetMapping
    fun list(): List<Cpu>{
        return cpuService.List()
    }
    @PostMapping
    fun save(@RequestBody cpu: Cpu): Cpu {
        return cpuService.saveCpu(cpu)
    }


}