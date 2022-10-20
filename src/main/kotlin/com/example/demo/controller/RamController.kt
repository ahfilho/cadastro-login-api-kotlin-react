package com.example.demo.controller

import com.example.demo.entity.Ram
import com.example.demo.service.RamService
import org.springframework.beans.factory.annotation.Autowired

import org.springframework.http.HttpStatus
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
@RequestMapping("/ram")
class RamController {

@Autowired
lateinit var ramService: RamService;

    @PostMapping
    fun save(@RequestBody ram: Ram): Ram? {
        return ramService.saveRam(ram);
    }
    @GetMapping
    fun list(): List<Ram>{
        return ramService.listALl();
    }
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long?,@RequestBody ram: Ram): ResponseEntity<Ram?>?{
        ram.id
        return ResponseEntity.ok().body(ramService.update(id!!,ram))
    }
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long?): HttpStatus?{
        ramService.delete(id!!)
        return HttpStatus.OK
    }
}