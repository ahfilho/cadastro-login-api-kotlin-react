package br.com.api.kotlin.controller

import br.com.api.kotlin.entity.Cpu
import br.com.api.kotlin.service.CpuService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/cpu")
class CpuController(private val cpuService: CpuService) {

//    @Autowired
//    lateinit var cpuService: CpuService

    @PostMapping
    fun save(@RequestBody cpu: Cpu): ResponseEntity<Any> {
        try {
            cpuService.saveCpu(cpu)
            return ResponseEntity.status(HttpStatus.OK).body("SALVO COM SUCESSO!")
        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERRO AO SALVAR")
        }
    }

    @GetMapping
    fun list(): List<Cpu> {
        return cpuService.List()
    }

    //TODO
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long?, @RequestBody cpu: Cpu): ResponseEntity<Cpu?>? {
        return ResponseEntity.ok().body(cpuService.updateCpu(id!!, cpu))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long?): HttpStatus? {
        cpuService.deleteCpu(id!!)
        return HttpStatus.OK
    }

}



