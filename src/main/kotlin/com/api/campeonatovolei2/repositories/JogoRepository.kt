package com.api.campeonatovolei2.repositories

import com.api.campeonatovolei2.entities.JogoModel
import org.springframework.data.jpa.repository.JpaRepository

interface JogoRepository : JpaRepository<JogoModel, Int> {

    fun findByCampeonatoId(campeonatoId: Int): List<JogoModel?>?

    fun findByCampeonatoIdAndFinalizado(campeonatoId: Int, finalizado: Boolean): List<JogoModel?>?
}