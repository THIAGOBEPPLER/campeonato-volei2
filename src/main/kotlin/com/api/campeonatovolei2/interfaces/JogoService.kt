package com.api.campeonatovolei2.interfaces

import com.api.campeonatovolei2.dtos.AtualizarPontuacaoDto
import com.api.campeonatovolei2.dtos.CriarJogoDto
import com.api.campeonatovolei2.entities.JogoModel
import java.util.*

interface JogoService {
    fun criarJogo(jogoDto: CriarJogoDto?): Any?
    fun listarJogos(): ArrayList<JogoModel?>?
    fun listarJogosPorCampeonato(campeonatoId: Int?): ArrayList<JogoModel?>?
    fun buscarJogo(jogoId: Int?): Optional<JogoModel?>?
    fun atualizarPontuacao(atualizarPontuacaoDto: AtualizarPontuacaoDto?): Any?
}

