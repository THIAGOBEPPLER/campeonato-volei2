package com.api.campeonatovolei2.dtos

class CriarJogoDto {
    var campeonatoId: Int? = null
    var timeId1: Int? = null
    var timeId2: Int? = null

    constructor() {}
    constructor(campeonatoId: Int?, timeId1: Int?, timeId2: Int?) {
        this.campeonatoId = campeonatoId
        this.timeId1 = timeId1
        this.timeId2 = timeId2
    }
}

