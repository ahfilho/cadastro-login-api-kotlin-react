package br.com.api.kotlin.auth.service

import br.com.api.kotlin.auth.repository.UserDetailsRepository
import br.com.api.kotlin.auth.repository.UserRepository
import br.com.api.kotlin.entity.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

class UserDetailsServiceImpl : UserDetailsService {

    @Autowired
    lateinit var useDetailsRepository: UserDetailsRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository!!.findByUserName(username)
            ?: throw UsernameNotFoundException("Nome de usuário não encontrado:$username")

        return user
    }
}
