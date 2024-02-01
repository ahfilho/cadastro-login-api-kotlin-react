package br.com.api.kotlin.auth.controller

<<<<<<< HEAD
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
import org.springframework.web.bind.annotation.*
import java.security.NoSuchAlgorithmException
import java.security.spec.InvalidKeySpecException
import javax.servlet.http.HttpServletResponse
=======
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.persistence.GeneratedValue

>>>>>>> 5ae51cb (H2 Liberado, authorities para o tipo de usuario. Config do banco h2, config de segurança.)

@RestController
@RequestMapping("/login")
@CrossOrigin
<<<<<<< HEAD
class LoginController {

    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    lateinit var jwtToken: JwtToken

    @Throws(InvalidKeySpecException::class, NoSuchAlgorithmException::class)
    @PostMapping
    fun login(
        @RequestBody authenticationRequest: AuthenticationRequest,
        httpServletResponse: HttpServletResponse?,
    ): ResponseEntity<*> {

        val authentication: Authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authenticationRequest.userName, authenticationRequest.password

            )
        )

        SecurityContextHolder.getContext().authentication = authentication

        val user = authentication.principal as User
        val token: String = jwtToken.generateToken(user.userName)
        val response: LoginResponse = LoginResponse(token)
        return ResponseEntity.ok<Any>(response)


    }
=======
class LoginController  {


>>>>>>> 5ae51cb (H2 Liberado, authorities para o tipo de usuario. Config do banco h2, config de segurança.)
}