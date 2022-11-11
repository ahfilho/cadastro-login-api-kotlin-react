package com.example.demo.service

import com.example.demo.entity.Client
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional
import javax.transaction.Transactional

@Service
@Transactional
class ClientService {

    @Autowired
    lateinit var clientRepository: ClientRepository

    fun List(): MutableList<Client>{
        return clientRepository.findAll
    }
    fun saveClient(client: Client) : Client{
        return clientRepository.save(client)
    }
    fun updateClient(id: Long, client: Client): Client {
        val search = clientRepository.findById(id).map{
            it.name
            it.email
            it.cpf
            it.contactNumber
        }
        clientRepository.save(client)
        return client
    }
    fun deleteClient(id:Long){
        val del: Optional<Client> = clientRepository.findById(id)
        if(del!= null){
            clientRepository.delete(del.get)
        }
    }


}

