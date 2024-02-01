package br.com.api.kotlin.auth.controller

import br.com.api.kotlin.auth.dto.UserDto
import br.com.api.kotlin.auth.service.UserService
import br.com.api.kotlin.entity.User
import org.modelmapper.ModelMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/new/user")
class UserController(private val userService: UserService) {

    @PostMapping
    fun saveNewUser(@RequestBody userDto: UserDto): ResponseEntity<HttpStatus> {

        val modelMapper = ModelMapper()
        val user: User? = modelMapper.map(userDto, User::class.java)

        if (user != null) {
            userService.save(user)
        };
        return ResponseEntity.ok().body(HttpStatus.CREATED)
    }

}