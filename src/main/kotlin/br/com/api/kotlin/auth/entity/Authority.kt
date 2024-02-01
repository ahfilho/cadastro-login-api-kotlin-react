package br.com.api.kotlin.auth.entity

import org.springframework.security.core.GrantedAuthority
import javax.persistence.*

@Table(name = "AUTH_AUTHORITY")
@Entity
class Authority : GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(name = "ROLE_CODE")
    var roleCode: String? = null

    @Column(name = "ROLE_DESCRIPTION")
    var roleDescription: String? = null

    override fun getAuthority(): String {
        return roleCode!!
    }
}