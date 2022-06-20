package com.api.campeonatovolei2.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity(name = "Campeonato")
class CampeonatoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Int? = null

    @Column(nullable = false)
    var nome: String? = null

    @Column(nullable = false)
    var finalizado: Boolean? = null

    @Column
    @ManyToMany
    @JoinTable(
        name = "Campeonato_Time",
        joinColumns = [JoinColumn(name = "campeonato_id")],
        inverseJoinColumns = [JoinColumn(name = "time_id")]
    )
    @JsonIgnore
    var times: List<TimeModel>? = null

    constructor() {}
    constructor(id: Int?, nome: String?, finalizado: Boolean?, times: List<TimeModel>?) {
        this.id = id
        this.nome = nome
        this.finalizado = finalizado
        this.times = times
    }
}
