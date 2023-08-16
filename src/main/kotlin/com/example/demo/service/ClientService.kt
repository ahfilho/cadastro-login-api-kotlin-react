package com.example.demo.service

import com.example.demo.dto.ClientDto
import com.example.demo.entity.Client
import com.example.demo.repository.ClientRepository
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
@Transactional
class ClientService(private val clientRepository: ClientRepository ) {

    fun List(): MutableList<Client> {
        return clientRepository.findAll()
    }
    fun saveClient(client: Client) {

        client.dateRegister= Date();
             clientRepository.save(client)
    }

    fun updateClient(id: Long, client: Client): Client {
        val busca = clientRepository.findById(id)
        if (busca.isPresent) {
            val objClient: Client = busca.get()
            objClient.name = client.name
            objClient.cpf = client.cpf
            objClient.phone = client.phone
            objClient.dateRegister = Date();
            println(objClient.dateRegister)

        }
        return client
    }

    fun deleteClient(id: Long) {
        val del: Optional<Client> = clientRepository.findById(id)
        if (clientRepository.existsById(id))
            clientRepository.delete(del.get())

    }


}

