package com.api.campeonatovolei2.dtos

class AtualizarPontuacaoDto {
    var jogoId: Int? = null
    var time: Int? = null

    constructor() {}
    constructor(jogoId: Int?, time: Int?) {
        this.jogoId = jogoId
        this.time = time
    }
}
