package br.com.api.kotlin.auth.token

import br.com.api.kotlin.auth.service.UserDetailsServiceImpl
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetails
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter(
    private val userDetailsServiceImpl: UserDetailsServiceImpl,
    private val jwtToken: JwtToken,
) : OncePerRequestFilter() {

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val authToken: String? = jwtToken.getToken(request)
        if (authToken != null) {
            val userName: String? = jwtToken.getUsernameFromToken(authToken)

            if (userName != null) {
                val userDetails: UserDetails = userDetailsServiceImpl.loadUserByUsername(userName)

                if (jwtToken.validateToken(authToken, userDetails)) {
                    val authentication = UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.authorities
                    )
                    authentication.details = WebAuthenticationDetails(request)
                    SecurityContextHolder.getContext().authentication = authentication
                } else {
                    logger.error("Token inválido para o usuário: $userName")
                }
            }
        }

        filterChain.doFilter(request, response)
    }
}
