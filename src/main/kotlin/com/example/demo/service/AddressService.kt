package com.example.demo.service

import com.example.demo.entity.Address
import com.example.demo.repository.AddressRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class AddressService (private val addressRepository: AddressRepository){


    fun saveAddres(address: Address): Address{
      return  addressRepository.save(address)
    }
    fun List(): MutableList<Address>{
       return addressRepository.findAll()
    }




}