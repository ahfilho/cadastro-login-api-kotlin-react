package br.com.api.kotlin.auth.dto

data class UserDto(
    var userName: String? = null,
    var userPassword: String? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var email: String? = null,
    var cpf: String? = null,
    var profile: String? = null,
)