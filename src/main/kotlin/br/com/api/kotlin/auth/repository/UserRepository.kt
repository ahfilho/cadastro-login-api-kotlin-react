package br.com.api.kotlin.auth.repository

import br.com.api.kotlin.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByUserName(userName: String?): User?

}