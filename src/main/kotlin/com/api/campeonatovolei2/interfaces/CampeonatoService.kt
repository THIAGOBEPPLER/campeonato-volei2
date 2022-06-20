package com.api.campeonatovolei2.interfaces

import com.api.campeonatovolei2.dtos.CriarCampeonatoDto
import com.api.campeonatovolei2.dtos.FinalizarCampeonatoDto
import com.api.campeonatovolei2.entities.CampeonatoModel
import com.api.campeonatovolei2.models.TabelaModel

interface CampeonatoService {
    fun criarCampeonato(campeonato: CriarCampeonatoDto?): CampeonatoModel?
    fun listarCampeonatos(): ArrayList<CampeonatoModel?>?
    fun finalizarCampeonato(finalizarCampeonato: FinalizarCampeonatoDto?): ArrayList<TabelaModel?>?
    fun tabela(campeonatoId: Int?): ArrayList<TabelaModel?>?
}
