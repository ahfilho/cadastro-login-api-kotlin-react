package br.com.api.kotlin.repository

import br.com.api.kotlin.entity.Ram
import org.springframework.data.jpa.repository.JpaRepository

interface RamRepository: JpaRepository<Ram, Long> {
}