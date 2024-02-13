package br.com.api.kotlin.entity

import br.com.api.kotlin.auth.entity.Authority
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

@Entity
@Table(name = "AUTH_USER_DETAILS")
class User : UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     val id: Long? = null

    @Column(name = "USER_NAME", unique = true)
     var userName: @NotBlank(message = "O nome não pode estar em branco") @Size(
        min = 2,
        max = 30,
        message = "O nome deve ter entre 2 e 50 caracteres"
    ) String? = null

     val email: @NotBlank(message = "O e-mail não pode estar em branco") @Email(message = "O e-mail deve ser válido") String? =
        null

    @Column(name = "USER_KEY")
     var userPassword: @NotBlank(message = "A senha não pode estar em branco") @Size(
        min = 6,
        message = "A senha deve ter pelo menos 6 caracteres"
    ) String? = null

    @Column(name = "CREATED_ON")
     var createdAt: Date? = null

    @Column(name = "UPDATED_ON")
     var updatedAt: Date? = null

    @Column(name = "FIRST_NAME")
     var firstName: String? = null

    @Column(name = "LAST_NAME")
     var lastName: String? = null

     val cpf: @NotBlank(message = "O CPF não pode estar em branco") @Pattern(
        regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}",
        message = "O CPF deve ter o formato 999.999.999-99"
    ) @Size(min = 14, max = 14, message = "O CPF deve conter 11 dígitos") String? = null

    @Column(name = "USER_TYPE")
     var profile: @NotBlank(message = "O perfil não pode estar em branco") @Pattern(
        regexp = "usuario|admin",
        message = "O perfil deve ser usuario ou admin"
    ) String? = null

    @Column(name = "ENABLED")
     var enabled = true


    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinTable(
        name = "AUTH_USER_AUTHORITY",
        joinColumns = [JoinColumn(referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(referencedColumnName = "id")]
    )
    var authorities: List<Authority?>? = null

    override fun getAuthorities(): Collection<GrantedAuthority?>? {
        return authorities
    }

    override fun getUsername(): String? {
        return this.userName
    }

    override fun getPassword(): String? {
        return this.userPassword
    }

    override fun isAccountNonExpired(): Boolean {
        return this.enabled
    }

    override fun isAccountNonLocked(): Boolean {
        return this.enabled
    }

    override fun isCredentialsNonExpired(): Boolean {
        return this.enabled
    }

    override fun isEnabled(): Boolean {
        return this.enabled
    }
}
