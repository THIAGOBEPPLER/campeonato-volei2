package com.api.campeonatovolei2.services

import com.api.campeonatovolei2.dtos.AtualizarPontuacaoDto
import com.api.campeonatovolei2.dtos.CriarJogoDto
import com.api.campeonatovolei2.entities.CampeonatoModel
import com.api.campeonatovolei2.entities.JogoModel
import com.api.campeonatovolei2.entities.TimeModel
import com.api.campeonatovolei2.interfaces.JogoService
import com.api.campeonatovolei2.repositories.CampeonatoRepository
import com.api.campeonatovolei2.repositories.JogoRepository
import com.api.campeonatovolei2.repositories.TimeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Suppress("UNCHECKED_CAST", "IMPLICIT_BOXING_IN_IDENTITY_EQUALS")
@Service
class JogoServiceImpl : JogoService {
    @Autowired
    var jogoRepository: JogoRepository? = null

    @Autowired
    var campeonatoRepository: CampeonatoRepository? = null

    @Autowired
    var timeRepository: TimeRepository? = null

    override fun criarJogo(jogoDto: CriarJogoDto): Any {

        require(jogoDto.timeId1 != jogoDto.timeId2) { "Um time não pode jogar contra ele mesmo!" }

        val campeonato: CampeonatoModel = campeonatoRepository!!.findById(jogoDto.campeonatoId!!).orElse(null)
            ?: throw IllegalArgumentException("Campeonato não cadastrado!")

        require(!campeonato.finalizado!!) { "Campeonato já foi finalizado!" }

        val times = campeonato.times
        val timesIds = times!!.stream().map(TimeModel::getId).collect(Collectors.toList())

        if(timesIds.contains(jogoDto.timeId1)) {
            throw IllegalArgumentException("Time1 não está nesse campeonato!")
        }

        if(timesIds.contains(jogoDto.timeId2)) {
            throw IllegalArgumentException("Time2 não está nesse campeonato!")
        }

        val jogos: List<JogoModel> = jogoRepository!!.findByCampeonatoIdAndFinalizado(jogoDto.campeonatoId!!, false) as List<JogoModel>

        val times1Ids = jogos.stream().map(JogoModel::time1).collect(Collectors.toList())
        val times2Ids = jogos.stream().map(JogoModel::time2).collect(Collectors.toList())

        require(!(times1Ids.contains(jogoDto.timeId1) || times2Ids.contains(jogoDto.timeId1))) { "Time1 já está jogando!" }
        require(!(times1Ids.contains(jogoDto.timeId2) || times2Ids.contains(jogoDto.timeId2))) { "Time2 já está jogando!" }

        val jogo = JogoModel()
        jogo.finalizado = false
        jogo.pontuacaoTime1 = 0
        jogo.pontuacaoTime2 = 0
        jogo.time1 = jogoDto.timeId1
        jogo.time2 = jogoDto.timeId2
        jogo.campeonatoId = jogoDto.campeonatoId
        jogoRepository!!.save(jogo)

        return jogo
    }

    override fun listarJogos(): MutableList<JogoModel> {
        return jogoRepository!!.findAll()
    }

    override fun listarJogosPorCampeonato(campeonatoId: Int): List<JogoModel?>? {
        return jogoRepository!!.findByCampeonatoId(campeonatoId)
    }

    override fun buscarJogo(jogoId: Int): Optional<JogoModel> {
        return jogoRepository!!.findById(jogoId)
    }

    override fun atualizarPontuacao(atualizarPontuacaoDto: AtualizarPontuacaoDto): Any {
        require(!(atualizarPontuacaoDto.time !== 1 && atualizarPontuacaoDto.time !== 2)) { "Campo time deve ser 1 ou 2!" }

        val jogo: JogoModel = jogoRepository!!.findById(atualizarPontuacaoDto.jogoId!!).orElse(null)
            ?: throw IllegalArgumentException("Jogo não encontrado!")

        require(!jogo.finalizado!!) { "Jogo já finalizado!" }

        if (atualizarPontuacaoDto.time === 1) {
            jogo.pontuacaoTime1 = jogo.pontuacaoTime1!! + 1
        } else {
            jogo.pontuacaoTime2 = jogo.pontuacaoTime2!! + 1
        }
        if ((jogo.pontuacaoTime1!! >= 10 || jogo.pontuacaoTime2!! >= 10) && Math.abs(jogo.pontuacaoTime1!! - jogo.pontuacaoTime2!!) > 1) {
            jogo.finalizado = true
            if (jogo.pontuacaoTime1!! > jogo.pontuacaoTime2!!) {
                jogo.vencedor = jogo.time1
            } else {
                jogo.vencedor = jogo.time2
            }
        }
        jogoRepository!!.save(jogo)

        return jogo
   }
}
