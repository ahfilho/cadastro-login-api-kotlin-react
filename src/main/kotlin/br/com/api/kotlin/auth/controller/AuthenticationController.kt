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
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import java.security.InvalidKeyException
import java.security.Principal
import javax.servlet.http.HttpServletResponse


@RestController
@RequestMapping("/auth")
@CrossOrigin
class AuthenticationController() {

//    @Autowired
//    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    lateinit var jwtToken: JwtToken

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    private val userDetailsServiceImpl: UserDetailsServiceImpl? = null

    @Autowired
    private val authenticationManager: AuthenticationManager? = null

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

        val response: LoginResponse = LoginResponse()
        response.token(jwtToken)


        return ResponseEntity.ok<Any>(response)


    }

    @GetMapping("/auth/userinfo")
    fun getUserInfo(user: Principal): ResponseEntity<*> {
        val userObj = userDetailsServiceImpl?.loadUserByUsername(user.name) as User
        if (user == null!!) {
            println("USER NULL")
        }
            val userInfo: UserInfo = UserInfo()
            userInfo.firstName = userObj.firstName
            userInfo.lastName = userObj.lastName
            userInfo.password = userObj.password
            userInfo.profile = userObj.profile
            userInfo.roles = userObj.authorities!!.toTypedArray()


        return ResponseEntity.ok<Any>(userInfo)
    }

}