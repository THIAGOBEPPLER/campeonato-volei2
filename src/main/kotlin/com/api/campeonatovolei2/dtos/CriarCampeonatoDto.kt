package com.api.campeonatovolei2.dtos

class CriarCampeonatoDto {
    var nome: String? = null
    var times: List<Int>? = null

    constructor() {}
    constructor(nome: String?, times: List<Int>?) {
        this.nome = nome
        this.times = times
    }
}

