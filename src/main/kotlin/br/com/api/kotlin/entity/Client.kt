package br.com.api.kotlin.entity


import java.time.LocalDate
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "CLIENT")
class Client {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CLIENT_ID")
    var id: Long? = null

    @NotNull
    @Column(name = "NAME")
    var name: String? = null

    @NotNull
    @Size(min = 10, max = 11)
    @Column(name = "PHONE")
    var phone: String? = null

    @NotNull
    @Column(name = "CPF")
    var cpf: String? = null

    @NotNull
    @Column(name = "DATE_REGISTER")
    var dateRegister: LocalDate? = null

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "ADDRESS_ID")
    var address: Address? = null
}
