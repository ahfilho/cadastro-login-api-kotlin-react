package br.com.api.kotlin.service

import br.com.api.kotlin.entity.Address
import br.com.api.kotlin.repository.AddressRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class AddressService (private val addressRepository: br.com.api.kotlin.repository.AddressRepository){


    fun saveAddres(address: br.com.api.kotlin.entity.Address): br.com.api.kotlin.entity.Address {
      return  addressRepository.save(address)
    }
    fun List(): MutableList<br.com.api.kotlin.entity.Address>{
       return addressRepository.findAll()
    }




}