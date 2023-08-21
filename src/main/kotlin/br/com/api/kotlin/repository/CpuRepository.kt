package br.com.api.kotlin.repository

import br.com.api.kotlin.entity.Cpu
import org.springframework.data.jpa.repository.JpaRepository

interface CpuRepository: JpaRepository<br.com.api.kotlin.entity.Cpu,Long>{

}