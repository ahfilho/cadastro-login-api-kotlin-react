package com.example.demo.service

import com.example.demo.entity.Client
import com.example.demo.repository.ClientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional
import javax.transaction.Transactional

@Service
@Transactional
class ClientService {

    @Autowired
    lateinit var clientRepository: ClientRepository

    fun List(): MutableList<Client> {
        return clientRepository.findAll()
    }

    fun saveClient(client: Client): Client {
        return clientRepository.save(client)
    }

    fun updateClient(id: Long, client: Client): Client {
        val busca = clientRepository.findById(id)
        if (busca.isPresent) {
            val objClient: Client = busca.get()
            objClient.name = client.name
            objClient.cpf = client.cpf
            objClient.telephone = client.telephone
            objClient.contactNumber = client.contactNumber
        }
        return client
    }

    fun deleteClient(id: Long) {
        val del: Optional<Client> = clientRepository.findById(id)
        if (clientRepository.existsById(id))
            clientRepository.delete(del.get())

    }


}

