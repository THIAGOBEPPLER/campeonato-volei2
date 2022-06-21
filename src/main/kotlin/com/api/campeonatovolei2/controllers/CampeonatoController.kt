package com.api.campeonatovolei2.controllers

import com.api.campeonatovolei2.dtos.CriarCampeonatoDto
import com.api.campeonatovolei2.dtos.FinalizarCampeonatoDto
import com.api.campeonatovolei2.interfaces.CampeonatoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/campeonato")
class CampeonatoController {
    @Autowired
    var campeonatoService: CampeonatoService? = null
    @PostMapping("/novo")
    fun adicionarTime(@RequestBody model: CriarCampeonatoDto?): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.CREATED).body(campeonatoService!!.criarCampeonato(model!!))
    }

    @GetMapping("/lista")
    fun listarCampeonatos(): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.FOUND).body(campeonatoService!!.listarCampeonatos())
    }

    @PostMapping("/finalizar")
    fun finalizarCampeonato(@RequestBody model: FinalizarCampeonatoDto?): ResponseEntity<Any> {
        return try {
            ResponseEntity.status(HttpStatus.OK).body(campeonatoService!!.finalizarCampeonato(model!!))
        } catch (e: IllegalArgumentException) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }
    }

    @GetMapping("/tabela/{id}")
    fun listarCampeonatos(@PathVariable(value = "id") id: Int?): ResponseEntity<Any> {
        return try {
            ResponseEntity.status(HttpStatus.FOUND).body(campeonatoService!!.tabela(id!!))
        } catch (e: IllegalArgumentException) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }
    }
}
