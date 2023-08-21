package br.com.api.kotlin.controller

import br.com.api.kotlin.code.GeneratorCode
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/sale")
@RestController
class SaleController(
    private val saleService: br.com.api.kotlin.service.SaleService,
    private val generatorCode: GeneratorCode,
) {

    @GetMapping
    fun list(): List<br.com.api.kotlin.entity.Sale> {
        return saleService.List()
    }

    @PostMapping
    fun save(@RequestBody sale: br.com.api.kotlin.entity.Sale): ResponseEntity<Any> {

        val codeSize = 10
        val codeFinal = generatorCode.generateCode(codeSize)
        println(codeFinal)
        val existingSaleCode = saleService.findByClientName(sale.saleCode)

        if (existingSaleCode == codeFinal) {
            ResponseEntity.status(HttpStatus.FOUND)
            println("TESTEEEM DEU ERRADO PQ JÁ EXISTE")

        }
        try {
            sale.saleCode = codeFinal
            println("======= Nota Fiscal =======")
            if (sale.clientName.equals("")) {
                println("Nome: Não informado na venda.")
//                sale.clientName = ("Não informado na venda.")
            }
            println("Nome: ${sale.clientName}")
            println("Tipo de Cartão: ${sale.typeCard} ")
            println("Peso: ${sale.weigth} kg")
            println("Data da Venda: ${sale.saleDate} ")
            println("Valor da Venda: R$${sale.saleValue}")
            println("Código da Venda:$codeFinal")
            println("==========================")
            saleService.save(sale)
            return ResponseEntity.status(HttpStatus.OK).body("Venda realizada com sucesso.")
        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Venda não realizada.")
        }
    }


}