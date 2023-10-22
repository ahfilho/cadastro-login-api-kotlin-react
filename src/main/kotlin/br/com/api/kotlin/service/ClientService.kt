package br.com.api.kotlin.service

import br.com.api.kotlin.entity.Client
import br.com.api.kotlin.repository.ClientRepository
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
@Transactional
class ClientService(private val clientRepository: ClientRepository) {

    fun list(): MutableList<Client> {
        var listOfClients = clientRepository.findAll()
        listOfClients.sortBy { it.name }
        for (client in listOfClients) {
            client.address?.client
        }
        if (listOfClients.isEmpty()) {
            println("Não existe cliente cadastrado.")
        }
        return listOfClients

    }

    fun saveClient(client: Client) {
        findByCpf(client.cpf)

        client.dateRegister = Date()
        clientRepository.save(client)
    }

    fun updateClient(id: Long, client: Client): Client {
        val searchForUpdate = clientRepository.findById(id)
        if (searchForUpdate.isPresent) {
            val objClient: Client = searchForUpdate.get()
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

    fun findByCpf(cpf: String?): Optional<Client> {
            return clientRepository.findByCpf(cpf)

    }
}

