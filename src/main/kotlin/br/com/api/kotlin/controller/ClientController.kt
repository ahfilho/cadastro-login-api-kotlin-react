package br.com.api.kotlin.controller

import br.com.api.kotlin.dto.ClientDto
import br.com.api.kotlin.entity.Client
import br.com.api.kotlin.service.ClientService
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
class ClientController(private val clientService: br.com.api.kotlin.service.ClientService) {

    @GetMapping
    fun list(): List<br.com.api.kotlin.entity.Client> {
        return clientService.List()
    }

    @PostMapping
    fun save(@RequestBody client: br.com.api.kotlin.entity.Client): ResponseEntity<Any> {
        val existingClient = clientService.findByCpf(client.cpf)
        if (existingClient.isPresent) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Não foi possível salvar o cliente. O Cpf informado: " + client.cpf + " já existe.")

        }
        clientService.saveClient(client)
        return ResponseEntity.status(HttpStatus.OK).body(/* body = */ "Cliente " + client.name + " salvo com sucesso!")
    }

    @PutMapping("/{id}")
    fun updateClient(@PathVariable id: Long?, @RequestBody client: br.com.api.kotlin.entity.Client): ResponseEntity<br.com.api.kotlin.entity.Client?>? {
        client.id
        return ResponseEntity.ok().body(clientService.updateClient(id!!, client))
    }

    @DeleteMapping("/{id}")
    fun deleteClient(@PathVariable id: Long?): HttpStatus? {
        this.clientService.deleteClient(id!!)
        return HttpStatus.OK
    }


}