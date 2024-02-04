package br.com.api.kotlin.auth.controller

import br.com.api.kotlin.auth.request.AuthenticationRequest
import br.com.api.kotlin.auth.response.LoginResponse
import br.com.api.kotlin.auth.token.JwtToken
import br.com.api.kotlin.entity.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.InvalidKeyException
import java.util.NoSuchElementException
import javax.servlet.http.HttpServletResponse


@RestController
@RequestMapping("/auth/login")
@CrossOrigin
class AuthenticationController {

    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    lateinit var jwtToken: JwtToken

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder
    @PostMapping("/login")
    @Throws(InvalidKeyException::class, NoSuchElementException::class)
    fun login(@RequestBody authenticationRequest: AuthenticationRequest, response: HttpServletResponse): ResponseEntity<Any> {
        val authentication: Authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authenticationRequest.userName, authenticationRequest.password
            )
        )
        SecurityContextHolder.getContext().authentication = authentication

        val user  = authentication.principal as User
        val jwtToken: String = jwtToken.generateToken(user.userName)

        val response: LoginResponse = LoginResponse()
        response.token(jwtToken)


        return ResponseEntity.ok<Any>(response)

    }


}