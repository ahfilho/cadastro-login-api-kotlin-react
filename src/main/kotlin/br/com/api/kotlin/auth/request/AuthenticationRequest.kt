package br.com.api.kotlin.auth.request

import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import java.security.NoSuchAlgorithmException
import java.security.spec.InvalidKeySpecException
import javax.servlet.http.HttpServletResponse


class AuthenticationRequest {

    val userName: String? = null
    val password: String? = null

}
