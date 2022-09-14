package com.example.demo.controller

import com.example.demo.entity.Ram
import com.example.demo.service.RamService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.persistence.Entity
import javax.persistence.Table

@RestController
@RequestMapping("/ram")
class RamController {

    @Autowired
    lateinit var ramService: RamService

    @PostMapping
    fun save(@RequestBody ram: Ram): Ram {
        return ramService.saveRam(ram)
    }
    @GetMapping
    fun list(): List<Ram> {
        return ramService.List()
    }


}