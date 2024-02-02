package br.com.api.kotlin.auth.token

import br.com.api.kotlin.auth.repository.UserRepository
import br.com.api.kotlin.auth.service.UserDetailsServiceImpl
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetails
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter : OncePerRequestFilter() {

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var jwtToken: JwtToken

    @Autowired
    lateinit var userDetailsServiceImpl: UserDetailsServiceImpl

    //    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val authToken: String = jwtToken.getToken(request).toString()
        if (null != authToken) {
            val userName: String = jwtToken.getUsernameFromToken(authToken).toString()

            if (null != userName) {
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