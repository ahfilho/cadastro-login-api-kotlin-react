package com.example.demo.repository

import com.example.demo.entity.Cpu
import org.springframework.data.jpa.repository.JpaRepository

interface CpuRepository: JpaRepository<Cpu,Long>{

}