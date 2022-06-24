package com.api.campeonatovolei2.services

import com.api.campeonatovolei2.dtos.CriarCampeonatoDto
import com.api.campeonatovolei2.dtos.FinalizarCampeonatoDto
import com.api.campeonatovolei2.entities.CampeonatoModel
import com.api.campeonatovolei2.entities.JogoModel
import com.api.campeonatovolei2.entities.TimeModel
import com.api.campeonatovolei2.interfaces.CampeonatoService
import com.api.campeonatovolei2.models.TabelaModel
import com.api.campeonatovolei2.repositories.CampeonatoRepository
import com.api.campeonatovolei2.repositories.JogoRepository
import com.api.campeonatovolei2.repositories.TimeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class CampeonatoServiceImpl : CampeonatoService {
    @Autowired
    var campeonatoRepository: CampeonatoRepository? = null

    @Autowired
    var timeRepository: TimeRepository? = null

    @Autowired
    var jogoRepository: JogoRepository? = null

    override fun criarCampeonato(campeonato: CriarCampeonatoDto): CampeonatoModel {
        val campeonatoModel = CampeonatoModel()

        val listaTimes: ArrayList<TimeModel> = ArrayList<TimeModel>()

        for (time in campeonato.times!!) {
            val timeModel = timeRepository!!.findById(time).orElse(null)
                ?: throw IllegalArgumentException("Lista de times invalida.")

            listaTimes.add(timeModel)
        }

        if(listaTimes.count() < 2){
            throw IllegalArgumentException("Campeonato não pode ter menos de 2 times")
        }

        campeonatoModel.nome = campeonato.nome
        campeonatoModel.times = listaTimes
        campeonatoModel.finalizado = false

        return campeonatoRepository!!.save(campeonatoModel)
    }

    override fun listarCampeonatos(): MutableList<CampeonatoModel> {
        return campeonatoRepository!!.findAll()
    }

    override fun finalizarCampeonato(finalizarCampeonato: FinalizarCampeonatoDto): MutableList<TabelaModel> {

        val id = finalizarCampeonato.id!!

        val campeonato = campeonatoRepository!!.findById(id).orElse(null)
            ?: throw IllegalArgumentException("Campeonato não cadastrado")

        val jogos = jogoRepository!!.findByCampeonatoIdAndFinalizado(finalizarCampeonato.id!!, false)

        require(jogos!!.isNotEmpty()) { "Esse campeonato possui jogos em andamento" }

        require(!campeonato.finalizado!!) { "Campeonato já finalizado" }

        campeonato.finalizado = true
        campeonatoRepository!!.save(campeonato)

        return tabela(campeonato.id!!)
    }

    override fun tabela(campeonatoId: Int): ArrayList<TabelaModel> {

        val campeonato: CampeonatoModel = campeonatoRepository!!.findById(campeonatoId).orElse(null)
            ?: throw IllegalArgumentException("Campeonato não cadastrado")

        val times = campeonato.times!!
        val jogos: List<JogoModel> = jogoRepository!!.findByCampeonatoIdAndFinalizado(campeonatoId, true) as List<JogoModel>
        val times1Ids = jogos.stream().map(JogoModel::time1).collect(Collectors.toList())
        val times2Ids = jogos.stream().map(JogoModel::time2).collect(Collectors.toList())
        val vencedores = jogos.stream().map(JogoModel::vencedor).collect(Collectors.toList())
        val tabela: ArrayList<TabelaModel> = ArrayList<TabelaModel>()

        for (time : TimeModel in times) {

            val timeJogos1 = times1Ids.stream()
                .filter { x -> x!!.equals(time.getId()) }
                .count()

            val timeJogos2 = times2Ids.stream()
                .filter { x -> x!!.equals(time.getId())}
                .count()

            val vitorias = vencedores.stream()
                .filter { x -> x!!.equals(time.getId()) }
                .count()

            val numeroJogos: Long = timeJogos1 + timeJogos2
            var saldo = 0

            for (jogo in jogos) {

                if (jogo.time1 === time.getId()){
                    saldo += (jogo.pontuacaoTime1!! - jogo.pontuacaoTime2!!)
                }

                else if (jogo.time2 === time.getId()){
                    saldo += (jogo.pontuacaoTime2!! - jogo.pontuacaoTime1!!)
                }
            }

            val tabelaTime = TabelaModel()
            tabelaTime.jogos = numeroJogos as Int
            tabelaTime.vitorias  = vitorias as Int
            tabelaTime.timeId =  time.getId()
            tabelaTime.pontos = (vitorias as Int) * 3
            tabelaTime.saldo =  saldo
            tabela.add(tabelaTime)
        }

        tabela.sortBy(TabelaModel::pontos)

        return tabela
    }
}
