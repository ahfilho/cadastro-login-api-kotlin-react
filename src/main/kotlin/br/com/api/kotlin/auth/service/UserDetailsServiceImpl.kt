package br.com.api.kotlin.auth.service

import br.com.api.kotlin.auth.repository.UserDetailsRepository
import br.com.api.kotlin.auth.repository.UserRepository
import br.com.api.kotlin.entity.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl : UserDetailsService {
    @Autowired
    private val userDetailsRepository: UserDetailsRepository? = null

    @Autowired
    private val userRepository: UserRepository? = null

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user: User = userRepository!!.findByUserName(username) as User?
            ?: throw UsernameNotFoundException("Nome de usuário não encontrado:$username")

        return user
    }
}
