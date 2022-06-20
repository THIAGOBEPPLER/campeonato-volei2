package com.api.campeonatovolei2.entities

import javax.persistence.*

@Entity(name = "Jogo")
class JogoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Int? = null

    @Column(nullable = false)
    var time1: Int? = null

    @Column(nullable = false)
    var time2: Int? = null

    @Column(nullable = false)
    var pontuacaoTime1: Int? = null

    @Column(nullable = false)
    var pontuacaoTime2: Int? = null

    @Column(nullable = false)
    var finalizado: Boolean? = null

    @Column
    var vencedor: Int? = null

    @Column
    var campeonatoId: Int? = null

    constructor() {}
    constructor(
        id: Int?,
        time1: Int?,
        time2: Int?,
        pontuacaoTime1: Int?,
        pontuacaoTime2: Int?,
        finalizado: Boolean?,
        vencedor: Int?,
        campeonatoId: Int?
    ) {
        this.id = id
        this.time1 = time1
        this.time2 = time2
        this.pontuacaoTime1 = pontuacaoTime1
        this.pontuacaoTime2 = pontuacaoTime2
        this.finalizado = finalizado
        this.vencedor = vencedor
        this.campeonatoId = campeonatoId
    }
}
