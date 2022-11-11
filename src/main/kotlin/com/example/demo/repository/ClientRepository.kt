package com.example.demo.repository

import com.example.demo.entity.Client
import org.springframework.data.jpa.repository.JpaRepository

interface ClientRepository: JpaRepository<Client,Long>{
}