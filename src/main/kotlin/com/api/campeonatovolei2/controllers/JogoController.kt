package com.api.campeonatovolei2.controllers

import com.api.campeonatovolei2.dtos.AtualizarPontuacaoDto
import com.api.campeonatovolei2.dtos.CriarJogoDto
import com.api.campeonatovolei2.interfaces.JogoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/jogo")
class JogoController {
    @Autowired
    var jogoService: JogoService? = null
    @PostMapping("/criar")
    fun criarJogo(@RequestBody body: CriarJogoDto?): ResponseEntity<Any> {
        return try {
            ResponseEntity.status(HttpStatus.CREATED).body(jogoService!!.criarJogo(body!!))
        } catch (e: IllegalArgumentException) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }
    }

    @GetMapping("/{id}")
    fun buscarJogo(@PathVariable(value = "id") id: Int?): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.FOUND).body(jogoService!!.buscarJogo(id!!))
    }

    @GetMapping("")
    fun listarJogos(): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.FOUND).body(jogoService!!.listarJogos())
    }

    @GetMapping("/campeonato/{id}")
    fun listarJogosPorCampeonato(@PathVariable(value = "id") id: Int?): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.FOUND).body(jogoService!!.listarJogosPorCampeonato(id!!))
    }

    @PostMapping("/atualizarPontuacao")
    fun atualizarPontuacao(@RequestBody body: AtualizarPontuacaoDto?): ResponseEntity<Any> {
        return try {
            ResponseEntity.status(HttpStatus.CREATED).body(jogoService!!.atualizarPontuacao(body!!))
        } catch (e: IllegalArgumentException) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }
    }
}