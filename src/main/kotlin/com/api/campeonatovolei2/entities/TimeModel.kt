package com.api.campeonatovolei2.entities

import javax.persistence.*
@Entity(name = "Time")
class TimeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private var id: Int? = null

    @Column(nullable = false)
    private var nome: String? = null

    @Column
    @ManyToMany(mappedBy = "times")
    private var campeonatos: List<CampeonatoModel>? = null

    fun TimeModel() {}

    fun TimeModel(id: Int?, nome: String?, campeonatos: List<CampeonatoModel>?) {
        this.id = id
        this.nome = nome
        this.campeonatos = campeonatos
    }

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getNome(): String? {
        return nome
    }

    fun setNome(nome: String?) {
        this.nome = nome
    }

    fun getCampeonatos(): List<CampeonatoModel>? {
        return campeonatos
    }

    fun setCampeonatos(campeonatos: List<CampeonatoModel>?) {
        this.campeonatos = campeonatos
    }
}