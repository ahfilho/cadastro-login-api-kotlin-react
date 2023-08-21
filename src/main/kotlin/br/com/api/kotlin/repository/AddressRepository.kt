package br.com.api.kotlin.repository;

import br.com.api.kotlin.entity.Address
import org.springframework.data.jpa.repository.JpaRepository

interface AddressRepository : JpaRepository<br.com.api.kotlin.entity.Address, Long> {
}