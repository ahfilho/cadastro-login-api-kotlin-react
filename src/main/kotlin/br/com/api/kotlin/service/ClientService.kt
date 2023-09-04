package br.com.api.kotlin.service

import br.com.api.kotlin.entity.Client
import br.com.api.kotlin.repository.ClientRepository
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional
<<<<<<< HEAD

@Service
@Transactional
class ClientService(private val clientRepository: br.com.api.kotlin.repository.ClientRepository) {

    fun List(): MutableList<br.com.api.kotlin.entity.Client> {
        return clientRepository.findAll()
    }

    fun saveClient(client: br.com.api.kotlin.entity.Client) {
=======
import kotlin.Comparator

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
>>>>>>> master

        findByCpf(client.cpf)

        client.dateRegister = Date()
        clientRepository.save(client)
    }

<<<<<<< HEAD
    fun updateClient(id: Long, client: br.com.api.kotlin.entity.Client): br.com.api.kotlin.entity.Client {
        val searchForUpdate = clientRepository.findById(id)
        if (searchForUpdate.isPresent) {
            val objClient: br.com.api.kotlin.entity.Client = searchForUpdate.get()
=======
    fun updateClient(id: Long, client: Client): Client {
        val searchForUpdate = clientRepository.findById(id)
        if (searchForUpdate.isPresent) {
            val objClient: Client = searchForUpdate.get()
>>>>>>> master
            objClient.name = client.name
            objClient.cpf = client.cpf
            objClient.phone = client.phone
            objClient.dateRegister = Date();
            println(objClient.dateRegister)

        }
        return client
    }

    fun deleteClient(id: Long) {
<<<<<<< HEAD
        val del: Optional<br.com.api.kotlin.entity.Client> = clientRepository.findById(id)
=======
        val del: Optional<Client> = clientRepository.findById(id)
>>>>>>> master
        if (clientRepository.existsById(id))
            clientRepository.delete(del.get())

    }

<<<<<<< HEAD
    fun findByCpf(cpf: String?): Optional<br.com.api.kotlin.entity.Client> {
=======
    fun findByCpf(cpf: String?): Optional<Client> {
>>>>>>> master
        return clientRepository.findByCpf(cpf)

    }


}

<<<<<<< HEAD
=======


>>>>>>> master
