package br.com.api.kotlin.auth.controller

import br.com.api.kotlin.auth.dto.UserDto
import br.com.api.kotlin.auth.service.UserDetailsServiceImpl
import br.com.api.kotlin.auth.service.UserService
import br.com.api.kotlin.entity.User
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/new/user")
@CrossOrigin
class UserController(private val userService: UserService) {


    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    @PostMapping
    fun saveNewUser(@RequestBody userDto: UserDto): ResponseEntity<HttpStatus> {

        val modelMapper = ModelMapper()
        val user: User? = modelMapper.map(userDto, User::class.java)

        if (user != null) {
            var encryptedPassword = passwordEncoder.encode(user.userPassword)
            user.userPassword = encryptedPassword
            userService.save(user)

        };
        return ResponseEntity.ok().body(HttpStatus.CREATED)
    }

    @GetMapping("/all")
    fun listAllUsers(principal: Principal?): ResponseEntity<List<User>> {
        if (principal == null) {
            val allUsers = userService.listAll(null)
            return ResponseEntity.ok(allUsers)
        }
        val userDetailsService: UserDetailsServiceImpl? = null
        val authenticatedUser = userDetailsService!!.loadUserByUsername(principal.name) as User

        if (userService.isAdmin(authenticatedUser)) {
            val allUsers = userService.listAll(authenticatedUser)
            return ResponseEntity.ok(allUsers)
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build()
        }
    }


}
