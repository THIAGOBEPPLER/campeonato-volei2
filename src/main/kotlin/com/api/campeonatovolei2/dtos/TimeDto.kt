package com.api.campeonatovolei2.dtos

class TimeDto {
    var nome: String? = null

    constructor() {}
    constructor(id: Int?, nome: String?) {
        this.nome = nome
    }
}

