package br.com.api.kotlin.service

import br.com.api.kotlin.entity.Address
import br.com.api.kotlin.repository.AddressRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class AddressService (private val addressRepository: AddressRepository){


    fun saveAddres(address: Address): Address {
      return  addressRepository.save(address)
    }
    fun List(): MutableList<Address>{
       return addressRepository.findAll()
    }




}