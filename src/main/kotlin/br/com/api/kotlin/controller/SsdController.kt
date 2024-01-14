package br.com.api.kotlin.controller

import br.com.api.kotlin.dto.SsdDto
import br.com.api.kotlin.entity.Ssd
import br.com.api.kotlin.service.SsdService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/ssd")

class SsdController(private val ssdService: SsdService) {

    //    Constructor injection: permite testar se a instância que estamos tentando injetar está nula,
    //    famoso NullPointerException, enquanto @Autowired não.
    //    Construction injection força o Spring a fornecer dependências obrigatórias. Garantindo que
    //    os objetos criados sejam válidos após a construção.
    //    Por último aqui, mas não a última vantagem, é que as dependências passadas como parâmetros
    //    para o construtor são obrigatórias. As dependências fornecidas pelo método de injeção
    //    Property ou setter são opcionais.

    //    @Autowired, segundo a documentação:
    //    "A injeção de setter deve ser usada principalmente apenas para dependências opcionais que
    //    podem receber valores padrão razoáveis dentro da classe. Caso contrário, as verificações
    //    não nulas devem ser executadas em todos os lugares em que o código usa a dependência.
    //    Um benefício da injeção de setter é que os métodos de setter tornam os objetos dessa classe
    //    passíveis de reconfiguração ou reinjeção posteriormente. O gerenciamento por meio de JMX MBeans é,
    //    portanto, um caso de uso atraente para injeção de setter."

    //    @Autowired
    //    lateinit var ssdService: HdSsdService
    fun convertDtoToEntity(ssdDto: SsdDto): Ssd {
        val ssd = Ssd()
        ssd.brand = ssdDto.brand
        ssd.serialNumber = ssdDto.serialNumber
        ssd.size = ssdDto.size
        ssd.purchasePrice = ssdDto.purchasePrice
        ssd.purchaseDate = ssdDto.purchaseDate
        ssd.saleValue = ssdDto.saleValue
        ssd.arrivalDate = ssdDto.arrivalDate
        ssd.currentDate = ssdDto.currentDate
        ssd.saleDate = ssdDto.saleDate
        ssd.condition = ssdDto.condition
        return ssd
    }


    @GetMapping
    fun list(): List<Ssd> {
        return ssdService.list()
    }

    @PostMapping
    fun save(@RequestBody ssdDto: SsdDto): ResponseEntity<HttpStatus> {
        val entityConverted = convertDtoToEntity(ssdDto)
        ssdService.save(entityConverted)
        return ResponseEntity.ok().body(HttpStatus.CREATED)
    }

    @DeleteMapping("/{id}")
    fun deleteSsd(@PathVariable("id") id: Long): HttpStatus? {
        ssdService.delete(id!!)
        return HttpStatus.OK
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long?, @RequestBody ssd: Ssd): ResponseEntity<Ssd?>? {
        ssd.id
        return ResponseEntity.ok().body(ssdService.updateSsd(id!!, ssd))
    }

    @GetMapping("/teste")
    fun teste(): List<Ssd> {
        return ssdService.teste()
    }

}