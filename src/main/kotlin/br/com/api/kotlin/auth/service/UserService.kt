package br.com.api.kotlin.auth.service

import br.com.api.kotlin.auth.entity.Authority
import br.com.api.kotlin.auth.repository.UserRepository
<<<<<<< HEAD
import br.com.api.kotlin.entity.User
import br.com.api.kotlin.enumer.Role
import io.jsonwebtoken.Claims
=======
import br.com.api.kotlin.enumer.Role
import br.com.api.kotlin.entity.User
>>>>>>> 5ae51cb (H2 Liberado, authorities para o tipo de usuario. Config do banco h2, config de segurança.)
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional
<<<<<<< HEAD
=======
import kotlin.collections.ArrayList
>>>>>>> 5ae51cb (H2 Liberado, authorities para o tipo de usuario. Config do banco h2, config de segurança.)


@Service
class UserService(private val userRepository: UserRepository, val passwordEncoder: PasswordEncoder) {
    @Transactional
    fun save(user: User): User {
        val authorityList: List<Authority> = ArrayList()

        val authorityListMutable = authorityList.toMutableList()

        val lowerCaseProfile = user.profile.lowercase(Locale.getDefault())
        if ("admin".equals(lowerCaseProfile)) {
            user.profile = Role.ADMIN.role.lowercase(Locale.getDefault())
            authorityListMutable.add(createAuthority("ADMIN", "Admin role"))
        } else {
            if ("usuario".equals(lowerCaseProfile)) {
                user.profile = Role.USER.role.lowercase(Locale.getDefault())
                authorityListMutable.add(createAuthority("USER", "User role"))
            } else {
                throw IllegalArgumentException("Perfil inválido." + user.profile)
            }
            user.authorities(authorityListMutable)

        }
        var encryptedPassword = passwordEncoder.encode(user.userPassword)
        user.userPassword = encryptedPassword
        userRepository.save(user)

        return user


    }

    private fun createAuthority(roleCode: String, roleDescription: String): Authority {
        val authority = Authority()
        authority.roleCode = roleCode
        authority.roleDescription = roleDescription
        return authority

    }
<<<<<<< HEAD

=======
>>>>>>> 5ae51cb (H2 Liberado, authorities para o tipo de usuario. Config do banco h2, config de segurança.)
}


