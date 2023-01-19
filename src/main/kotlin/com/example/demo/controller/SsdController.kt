package com.example.demo.controller

import com.example.demo.service.HdSsdService
import com.example.demo.entity.Ssd
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/ssd")
class SsdController {

    @Autowired
    lateinit var ssdService: HdSsdService

    @GetMapping
    fun list(): List<Ssd> {
        return ssdService.List()
    }
    @PostMapping
    fun save(@RequestBody ssd: Ssd): Ssd {
        return this.ssdService.save(ssd)
    }
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long) {
        return ssdService.delete(id)
    }
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long?,@RequestBody ssd:Ssd): ResponseEntity<Ssd?>?{
        ssd.id
        return ResponseEntity.ok().body(ssdService.updateSsd(id!!, ssd))
    }
    @GetMapping("/teste")
    fun teste(): List<Ssd> {
        return ssdService.teste()
    }

}