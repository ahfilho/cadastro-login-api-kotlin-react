package com.example.demo.controller

import com.example.demo.entity.Client
import com.example.demo.service.ClientService
import org.apache.coyote.Response
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
@RequestMapping("/client")
class ClientController(private val clientService: ClientService) {

//
//    @Autowired
//    lateinit var clientService: ClientService


    @GetMapping
    fun list(): List<Client> {
        return clientService.List()
    }

    @PostMapping
    fun save(@RequestBody client: Client): Client {
        return clientService.saveClient(client)
    }

    @PutMapping("/{id}")
    fun updateClient(@PathVariable id: Long?, @RequestBody client: Client): ResponseEntity<Client?>? {
        client.id
        return ResponseEntity.ok().body(clientService.updateClient(id!!, client))
    }

    @DeleteMapping("/{id}")
    fun deleteClient(@PathVariable id: Long?): HttpStatus? {
        this.clientService.deleteClient(id!!)
        return HttpStatus.OK
    }


}