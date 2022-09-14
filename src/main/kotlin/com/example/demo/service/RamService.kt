package com.example.demo.service

import com.example.demo.entity.Ram
import com.example.demo.repository.RamRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional


@Service
@Transactional
class RamService {

    @Autowired
    lateinit var ramRepository: RamRepository

    fun saveRam(ram: Ram): Ram {
        return ramRepository.save(ram)
    }
    fun List(): MutableList<Ram>{
        return ramRepository.findAll()
    }
}