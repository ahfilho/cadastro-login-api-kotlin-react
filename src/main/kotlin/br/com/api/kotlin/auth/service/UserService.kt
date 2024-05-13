package br.com.api.kotlin.auth.service

import br.com.api.kotlin.auth.entity.Authority
import br.com.api.kotlin.auth.repository.UserRepository
import br.com.api.kotlin.entity.User
import br.com.api.kotlin.enumer.Role
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional


@Service
class UserService(val passwordEncoder: PasswordEncoder) {

    @Autowired
    lateinit var userRepository: UserRepository

    @Transactional
    fun save(user: User): User {
        val authorityList: MutableList<Authority> = ArrayList()

        val lowerCaseProfile = user.profile?.lowercase(Locale.getDefault())

        if (lowerCaseProfile == "admin") {
            user.profile = Role.ADMIN.role.lowercase(Locale.getDefault())
            authorityList.add(createAuthority("ADMIN", "Admin role"))
        } else {
            if (lowerCaseProfile == "usuario") {
                user.profile = Role.USER.role.lowercase(Locale.getDefault())
                authorityList.add(createAuthority("USER", "User role"))
            } else {
                throw IllegalArgumentException("Perfil inválido." + user.profile)
            }
            user.enabled = true
            user.authorities = authorityList

        }
        userRepository.save(user)

        return user


    }

    private fun createAuthority(roleCode: String, roleDescription: String): Authority {
        val authority = Authority()
        authority.roleCode = roleCode
        authority.roleDescription = roleDescription
        return authority

    }

    fun listAll(authenticatedUser: User?): List<User> {
        return if (authenticatedUser == null || isAdmin(authenticatedUser)) {
            userRepository.findAll()
        } else {
            listOf(authenticatedUser)
        }
    }

    fun isAdmin(user: User): Boolean {
        return "admin".equals(user.profile, ignoreCase = true)
    }

    fun deleteUserById(id: Long) {
        val optionalUser: Optional<User> = userRepository.findById(id)
        if (optionalUser.isPresent) {
            val user: User = optionalUser.get()
            if ("admin".equals(user.profile, ignoreCase = true)) {
                userRepository.delete(user)
            } else {
                throw Exception("Usuário não autorizado para excluir.")
            }
        } else {
            throw Exception("Usuário não autorizado para excluir.");

        }
    }

}


