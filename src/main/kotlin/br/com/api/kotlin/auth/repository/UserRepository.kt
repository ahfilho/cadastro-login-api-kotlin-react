package br.com.api.kotlin.auth.repository

import br.com.api.kotlin.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
<<<<<<< HEAD

    fun findByUserName(userName: String?): User?

=======
>>>>>>> 5ae51cb (H2 Liberado, authorities para o tipo de usuario. Config do banco h2, config de segurança.)
}