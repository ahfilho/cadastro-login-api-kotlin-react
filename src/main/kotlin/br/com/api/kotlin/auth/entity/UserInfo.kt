package br.com.api.kotlin.auth.entity


data class UserInfo(

     var firstName: String? = null,
     var lastName: String? = null,
     var userName: String? = null,
     var profile: String? = null,
     var roles: Any? = null,

     )