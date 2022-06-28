package com.api.campeonatovolei2.services

import com.api.campeonatovolei2.dtos.AtualizarPontuacaoDto
import com.api.campeonatovolei2.dtos.CriarJogoDto
import com.api.campeonatovolei2.entities.JogoModel
import com.api.campeonatovolei2.interfaces.JogoService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*


@SpringBootTest
class JogoControllerTest {

    @Autowired
    var jogoService: JogoService? = null


    @Test
    fun testeListarJogos(){

        val jogos = jogoService!!.listarJogos()

        assert(jogos is MutableList<JogoModel>?)

    }

    @Test
    fun testeListarJogosPorCampeonato(){

        val jogos = jogoService!!.listarJogosPorCampeonato(10000)

        assert(jogos is List<JogoModel?>?)
    }

    @Test
    fun testeBuscarJogo(){

        val jogos = jogoService!!.buscarJogo(10000)

        assert(jogos is Optional<JogoModel>)
    }

    @Test
    fun testeAtualizarPontuacao_JogoNaoEncontrado(){

      val atualizarPontuacaoDto = AtualizarPontuacaoDto(
            10000,
            1
        )

        val erro = Assertions.assertThrows(
            java.lang.IllegalArgumentException::class.java
        ) { jogoService?.atualizarPontuacao(atualizarPontuacaoDto) }

        Assertions.assertEquals("Jogo não encontrado!", erro.message)
    }

    @Test
    fun testeAtualizarPontuacao_JogoJaFinalizado(){ //TODO:Ainda deve ser testado

        val atualizarPontuacaoDto = AtualizarPontuacaoDto(
            10000,
            1
        )

        val erro = Assertions.assertThrows(
            java.lang.IllegalArgumentException::class.java
        ) { jogoService?.atualizarPontuacao(atualizarPontuacaoDto) }

        Assertions.assertEquals("Jogo já finalizado!", erro.message)
    }
//    todo
//    CriarJogo_TimeContraEleMesmo
//    CriarJogo_CampeonatoNaoCadastrado
//    CriarJogo_CampeonatoJaFinalizado
//    CriarJogo_TimeJaEstaJogando

    @Test
    fun testeCriarJogo_TimeContraEleMesmo(){

        val criarJogoDto = CriarJogoDto(
            1,
            1,
            1
        )

        val erro = Assertions.assertThrows(
            java.lang.IllegalArgumentException::class.java
        ) { jogoService?.criarJogo(criarJogoDto) }

        Assertions.assertEquals("Um time não pode jogar contra ele mesmo!", erro.message)
    }

    @Test
    fun testeCriarJogo_CampeonatoNaoCadastrado(){

        val criarJogoDto = CriarJogoDto(
            100000,
            1,
            2
        )

        val erro = Assertions.assertThrows(
            java.lang.IllegalArgumentException::class.java
        ) { jogoService?.criarJogo(criarJogoDto) }

        Assertions.assertEquals("Campeonato não cadastrado!", erro.message)
    }

    @Test
    fun testeCriarJogo_CampeonatoJaFinalizado(){ //TODO:Ainda deve ser testado

        val criarJogoDto = CriarJogoDto(
            13,
            1,
            2
        )

        val erro = Assertions.assertThrows(
            java.lang.IllegalArgumentException::class.java
        ) { jogoService?.criarJogo(criarJogoDto) }

        Assertions.assertEquals("Campeonato já foi finalizado!", erro.message)
    }

    @Test
    fun testeCriarJogo_TimeJaEstaJogando(){ //TODO:Ainda deve ser testado

        val criarJogoDto = CriarJogoDto(
            22,
            1,
            2
        )

        val erro = Assertions.assertThrows(
            java.lang.IllegalArgumentException::class.java
        ) { jogoService?.criarJogo(criarJogoDto) }

        Assertions.assertEquals("Time1 já está jogando!", erro.message)
    }

    @Test
    fun testeCriarJogo_TimeNaoEstaNoCampeonato(){ //TODO:Ainda deve ser testado

        val criarJogoDto = CriarJogoDto(
            22,
            3,
            2
        )

        val erro = Assertions.assertThrows(
            java.lang.IllegalArgumentException::class.java
        ) { jogoService?.criarJogo(criarJogoDto) }

        Assertions.assertEquals("Time1 não está nesse campeonato!", erro.message)
    }

}
