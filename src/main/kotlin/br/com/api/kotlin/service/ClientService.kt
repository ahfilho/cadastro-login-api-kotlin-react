package br.com.api.kotlin.service

import br.com.api.kotlin.entity.Address
import br.com.api.kotlin.entity.Client
import br.com.api.kotlin.repository.AddressRepository
import br.com.api.kotlin.repository.ClientRepository
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
@Transactional
class ClientService(private val clientRepository: ClientRepository, private val addressRepository: AddressRepository) {

    fun list(): MutableList<Client> {
        val listOfClients = clientRepository.findAll()
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
        clientRepository.findByCpf(client.cpf)

        client.dateRegister = Date()
        clientRepository.save(client)
    }

    fun updateClient(id: Long, client: Client, address: Address): Client {
        val searchForUpdate = clientRepository.findById(id)
        val addresUpdate = addressRepository.findById(id);
        if (searchForUpdate.isPresent) {
            val objClient: Client = searchForUpdate.get()
            objClient.name = client.name
            objClient.cpf = client.cpf
            objClient.phone = client.phone
            objClient.dateRegister = Date();
            println(objClient.dateRegister)

            clientRepository.save(objClient)

        }

        if (addresUpdate.isPresent) {
            val addressEdit: Address = addresUpdate.get()
            addressEdit.city = address.city
            addressEdit.district = address.district
            addressEdit.number = address.number
            addressEdit.street = address.street

            addressRepository.save(addressEdit)

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



