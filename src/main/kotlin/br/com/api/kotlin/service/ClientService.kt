package br.com.api.kotlin.service

import br.com.api.kotlin.dto.AddressDto
import br.com.api.kotlin.dto.ClientDto
import br.com.api.kotlin.entity.Address
import br.com.api.kotlin.entity.Client
import br.com.api.kotlin.repository.AddressRepository
import br.com.api.kotlin.repository.ClientRepository
import org.springframework.stereotype.Service
import java.lang.RuntimeException
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

    fun convertEntityClient(clientDto: ClientDto): Client {
        val client = Client()

        client.name = clientDto.name
        client.dateRegister = clientDto.dateRegister
        client.cpf = clientDto.cpf
        client.phone = clientDto.phone

        val addressDto = clientDto.address

        if (addressDto != null) {
            val address = Address()

            address.city = addressDto.city
            address.district = addressDto.district
            address.number = addressDto.number
            address.street = addressDto.street

            client.address = address
        }

        return client
    }

    fun saveClient(clientDto: ClientDto) {

        val searchByCpf = clientRepository.findByCpf(clientDto.cpf)
        if (searchByCpf != null) {
            throw RuntimeException("Já existe um cliente com o CPF fornecido.")
        } else {
            val convertEntityForDto = convertEntityClient(clientDto)
            convertEntityForDto.dateRegister = Date()
            clientRepository.save(convertEntityForDto)
        }
    }

    fun updateClient(id: Long, client: Client, address: Address): Client {
        val searchForUpdate = clientRepository.findById(id)
        val addressUpdate = addressRepository.findById(id);

        if (searchForUpdate.isPresent) {
            val objClient: Client = searchForUpdate.get()
            objClient.name = client.name
            objClient.cpf = client.cpf
            objClient.phone = client.phone
            objClient.dateRegister = Date();
            println(objClient.dateRegister)
            clientRepository.save(objClient)

        }

        if (addressUpdate.isPresent) {
            val addressEdit: Address = addressUpdate.get()
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



