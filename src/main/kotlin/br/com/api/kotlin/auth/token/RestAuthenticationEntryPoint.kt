package br.com.api.kotlin.auth.token

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class RestAuthenticationEntryPoint : AuthenticationEntryPoint {
    @Throws(IOException::class, ServletException::class)
    override fun commence(
        request: HttpServletRequest?, response: HttpServletResponse,
        authException: AuthenticationException,
    ) {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.message)
    }

}