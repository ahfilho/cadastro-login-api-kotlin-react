package br.com.api.kotlin.auth.security

import br.com.api.kotlin.auth.service.UserDetailsServiceImpl
import br.com.api.kotlin.auth.token.JwtAuthenticationFilter
import br.com.api.kotlin.auth.token.JwtToken
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig<UserDetailsServiceImpml> {

    @Autowired
    private val userDetailsServiceImpl: UserDetailsServiceImpl? = null

    @Autowired
    private val jwtToken: JwtToken? = null

    @Autowired
    private val authenticationEntryPoint: AuthenticationEntryPoint? = null

    @Bean
    fun authenticationManager(
        passEncoder: PasswordEncoder,
    ): AuthenticationManager {
        val authenticationProvider = DaoAuthenticationProvider()
        authenticationProvider.setPasswordEncoder(passEncoder)
        return ProviderManager(authenticationProvider)
    }

    @Bean
    fun authenticationManager(
        userDetailsService: UserDetailsService,
        passwordEncoder: PasswordEncoder,
    ): AuthenticationManager {
        val authenticationProvider = DaoAuthenticationProvider()
        authenticationProvider.setUserDetailsService(userDetailsService)
        authenticationProvider.setPasswordEncoder(passwordEncoder)

        return ProviderManager(authenticationProvider)
    }


    @Bean
    fun authenticationProvider(): DaoAuthenticationProvider {
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(userDetailsServiceImpl)
        authProvider.setPasswordEncoder(passwordEncoder())
        return authProvider
    }

    // Modo simplificado, ja que o WEBCONFIGURERADAPTER está depreciado.
    @Bean
    fun securityFilterChain(
        http: HttpSecurity,
        jwtToken: JwtToken,
        userDetailsServiceImpl: UserDetailsServiceImpl,
    ): SecurityFilterChain {
        http
            .csrf().disable()
            .cors().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(authenticationEntryPoint)
            .and()
            .authorizeHttpRequests()
            .antMatchers(

                "/localhost:3000/**",
                "/localhost:8080/**",
                "/h2-console/**",
            ).permitAll()
            .antMatchers(
                "/new/user",
                "/auth/login",
                "/auth/userinfo",
                "/localhost:3000/**",
                "/localhost:8080/**",
                "/auth/auth/userinfo",
                "/h2-console/**",
            ).permitAll()
            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .anyRequest().authenticated()
            .and()
//            .addFilterBefore(
//                JwtAuthenticationFilter(userDetailsServiceImpl, jwtToken),
//                UsernamePasswordAuthenticationFilter::class.java
//            )
            .formLogin().disable().httpBasic()

        return http.build()
    }


    @Throws(Exception::class)
    fun configure(web: WebSecurity) {
        // Ignora o caminho do console do H2 para que ele seja acessível sem autenticação
        web.ignoring().antMatchers(
            "/h2-console/**", "/swagger-ui/**, /v3/api-docs/**", "/**.html",
            "/v2/api-docs",
            "/webjars/**",
            "/configuration/**",
            "/swagger-resources/**", "/v3/api-docs/**",
            "/swagger-ui/**", "/swagger-ui/index.html/**"
        )
    }


    @Throws(Exception::class)
    fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService<UserDetailsService>(userDetailsServiceImpl).passwordEncoder(passwordEncoder())
    }

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer { web: WebSecurity ->
            web.ignoring().requestMatchers(AntPathRequestMatcher("/h2-console/**"))
        }
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("https://localhost:8080", "https://localhost:3000")
        configuration.allowedMethods = listOf("GET", "POST", "DELETE", "PUT")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}