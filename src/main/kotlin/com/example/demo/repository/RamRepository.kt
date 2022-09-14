package com.example.demo.repository

import com.example.demo.entity.Ram
import org.springframework.data.jpa.repository.JpaRepository

interface RamRepository: JpaRepository<Ram, Long> {
}