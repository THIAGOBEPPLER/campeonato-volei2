package com.api.campeonatovolei2.models

class TabelaModel {
    var timeId: Int? = null
    var saldo: Int? = null
    var vitorias: Int? = null
    var jogos: Int? = null
    var pontos: Int? = null

    constructor() {}
    constructor(timeId: Int?, saldo: Int?, vitorias: Int?, jogos: Int?, pontos: Int?) {
        this.timeId = timeId
        this.saldo = saldo
        this.vitorias = vitorias
        this.jogos = jogos
        this.pontos = pontos
    }
}

