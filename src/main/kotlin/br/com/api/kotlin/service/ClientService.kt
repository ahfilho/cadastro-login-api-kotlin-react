package br.com.api.kotlin.service

import br.com.api.kotlin.entity.Client
import br.com.api.kotlin.repository.ClientRepository
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
@Transactional
class ClientService(private val clientRepository: br.com.api.kotlin.repository.ClientRepository) {

    fun List(): MutableList<br.com.api.kotlin.entity.Client> {
        return clientRepository.findAll()
    }

    fun saveClient(client: br.com.api.kotlin.entity.Client) {

        findByCpf(client.cpf)

        client.dateRegister = Date()
        clientRepository.save(client)
    }

    fun updateClient(id: Long, client: br.com.api.kotlin.entity.Client): br.com.api.kotlin.entity.Client {
        val searchForUpdate = clientRepository.findById(id)
        if (searchForUpdate.isPresent) {
            val objClient: br.com.api.kotlin.entity.Client = searchForUpdate.get()
            objClient.name = client.name
            objClient.cpf = client.cpf
            objClient.phone = client.phone
            objClient.dateRegister = Date();
            println(objClient.dateRegister)

        }
        return client
    }

    fun deleteClient(id: Long) {
        val del: Optional<br.com.api.kotlin.entity.Client> = clientRepository.findById(id)
        if (clientRepository.existsById(id))
            clientRepository.delete(del.get())

    }

    fun findByCpf(cpf: String?): Optional<br.com.api.kotlin.entity.Client> {
        return clientRepository.findByCpf(cpf)

    }


}

