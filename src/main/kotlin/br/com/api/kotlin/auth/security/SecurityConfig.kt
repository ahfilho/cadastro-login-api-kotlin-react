package br.com.api.kotlin.auth.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig {

    // Modo simplificado, ja que o WEBCONFIGURERADAPTER está depreciado.
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf().disable()
            .cors(Customizer.withDefaults())
            .authorizeHttpRequests()
            .antMatchers("/new/user/**").permitAll()
            .antMatchers("/h2-console/**", "/auth/login/login/").permitAll()
            .anyRequest()
            .authenticated()
            .and().sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().formLogin().disable().httpBasic()

        return http.csrf().disable().build()
    }

    @Throws(Exception::class)
    fun configure(web: WebSecurity) {
        // Ignorar o caminho do console do H2 para que ele seja acessível sem autenticação
        web.ignoring().antMatchers(
            "/h2-console/**", "/swagger-ui/**, /v3/api-docs/**", "/**.html",
            "/v2/api-docs",
            "/webjars/**",
            "/configuration/**",
            "/swagger-resources/**", "/v3/api-docs/**",
            "/swagger-ui/**", "/swagger-ui/index.html/**"
        )
    }

    @Bean
    fun authenticationManager(
        passEncoder: PasswordEncoder,
    ): AuthenticationManager {
        val authenticationProvider = DaoAuthenticationProvider()
        authenticationProvider.setPasswordEncoder(passEncoder)
        return ProviderManager(authenticationProvider)
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
        configuration.allowedOrigins = listOf("https://localhost:8080")
        configuration.allowedMethods = listOf("GET", "POST", "DELETE", "PUT")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}