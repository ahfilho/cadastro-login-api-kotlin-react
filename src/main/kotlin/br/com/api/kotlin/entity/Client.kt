package br.com.api.kotlin.entity


import org.jetbrains.annotations.NotNull
import java.util.*
import javax.persistence.*


@Entity
@Table(name = "CLIENT")
class Client {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    var id: Long? = null

    @Column(name = "NAME")
    @NotNull("nome não pode ser vazio")
    var name: String? = null

    @NotNull("phone não pode ser vazio")
    @Column(name = "PHONE")
    var phone: Int? = null

    @NotNull("cpf não pode ser vazio")
    @Column(name = "CPF")
    var cpf: String? = null

    @NotNull("Data de registro nome não pode ser vazio")
    @Column(name = "DATE_REGISTER")
    var dateRegister: Date? = null

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "CLIENT_ID")
    val address: br.com.api.kotlin.entity.Address? = null


    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "SALE_ID")
    val sale: br.com.api.kotlin.entity.Sale? = null
}