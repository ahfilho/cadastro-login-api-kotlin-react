package br.com.api.kotlin.entity

import br.com.api.kotlin.auth.entity.Authority
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

@Entity
@Table(name = "AUTH_USER_DETAILS")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @NotBlank(message = "O nome não pode estar em branco")
    @Size(min = 2, max = 30, message = "O nome deve ter entre 2 e 50 caracteres")
    @Column(name = "USER_NAME", unique = true)
    var userName: String? = null,

    @NotBlank(message = "O e-mail não pode estar em branco")
    @Email(message = "O e-mail deve ser válido")
    var email: String? = null,

    @NotEmpty(message = "A senha não pode estar em branco")
    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
    @Column(name = "USER_KEY")
    var userPassword: String? = null,

    @Column(name = "CREATED_ON")
    var createdAt: Date? = null,

    @Column(name = "UPDATED_ON")
    var updatedAt: Date? = null,

    @Column(name = "FIRST_NAME")
    var firstName: String? = null,

    @Column(name = "LAST_NAME")
    var lastName: String? = null,

    @NotBlank(message = "O CPF não pode estar em branco")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "O CPF deve ter o formato 999.999.999-99")
    @Size(min = 14, max = 14, message = "O CPF deve conter 11 dígitos")
    var cpf: String,

    @NotBlank(message = "O perfil não pode estar em branco")
    @Pattern(regexp = "usuario|admin", message = "O perfil deve ser usuario ou admin")
    @Column(name = "USER_TYPE")
    var profile: String,

    @Column(name = "ENABLED")
    var enabled: Boolean = true,

    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinTable(
        name = "AUTH_USER_AUTHORITY",
        joinColumns = [JoinColumn(referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(referencedColumnName = "id")]
    )
    val authorities: List<Authority>,
) : UserDetails {

    fun authorities(authorityListMutable: MutableList<Authority>): List<Authority> {
        return authorities
    }

    override fun getUsername(): String? {
        return userName
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        TODO("Not yet implemented")
    }

    override fun getPassword(): String? {
        return userPassword
    }

    override fun isAccountNonExpired(): Boolean {
        return enabled
    }

    override fun isAccountNonLocked(): Boolean {
        return enabled
    }

    override fun isCredentialsNonExpired(): Boolean {
        return enabled
    }

    override fun isEnabled(): Boolean {
        return enabled
    }


}
