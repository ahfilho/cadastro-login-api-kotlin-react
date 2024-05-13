package br.com.api.kotlin.auth.controller

import br.com.api.kotlin.auth.entity.UserInfo
import br.com.api.kotlin.auth.request.AuthenticationRequest
import br.com.api.kotlin.auth.response.LoginResponse
import br.com.api.kotlin.auth.service.UserDetailsServiceImpl
import br.com.api.kotlin.auth.token.JwtToken
import br.com.api.kotlin.entity.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.security.Principal
import java.security.spec.InvalidKeySpecException
import javax.servlet.http.HttpServletResponse


@RestController
@RequestMapping("/auth")
@CrossOrigin
class AuthenticationController() {

//    @Autowired
//    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var jwtToken: JwtToken

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    private lateinit var userDetailsServiceImpl: UserDetailsServiceImpl

    @Autowired
    private val authenticationManager: AuthenticationManager? = null

    @Autowired
    private val userDetailsService: UserDetailsService? = null


    @PostMapping("/login")
    @Throws(InvalidKeyException::class, NoSuchElementException::class)
    fun login(
        @RequestBody authenticationRequest: AuthenticationRequest,
        response: HttpServletResponse,
    ): ResponseEntity<*> {

        val authentication = authenticationManager!!.authenticate(
            UsernamePasswordAuthenticationToken(
                authenticationRequest.userName, authenticationRequest.password
            )
        )
        println(authentication)
        SecurityContextHolder.getContext().authentication = authentication
        val user = authentication.principal as User
        val jwtToken: String = jwtToken.generateToken(user.username)
        val response = LoginResponse()
        response.token = jwtToken
        println(jwtToken)
        return ResponseEntity.ok<Any>(response)


    }

    @GetMapping("/userinfo")
    fun getUserInfo(user: Principal): ResponseEntity<*> {
        val userObj = userDetailsServiceImpl.loadUserByUsername(user.name) as User

        val userInfo = UserInfo()
        userInfo.firstName = userObj.firstName
        userInfo.lastName = userObj.lastName
        userInfo.profile = userObj.profile
        userInfo.roles = userObj.authorities!!.toTypedArray()
        println(user.name)
        return ResponseEntity.ok(userInfo)
    }

}