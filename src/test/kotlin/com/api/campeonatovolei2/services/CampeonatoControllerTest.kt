package com.api.campeonatovolei2.services

import com.api.campeonatovolei2.dtos.CriarCampeonatoDto
import com.api.campeonatovolei2.dtos.FinalizarCampeonatoDto
import com.api.campeonatovolei2.entities.CampeonatoModel
import com.api.campeonatovolei2.interfaces.CampeonatoService
import com.api.campeonatovolei2.repositories.CampeonatoRepository
import jdk.jfr.Name
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.annotation.DirtiesContext.*


@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
class CampeonatoControllerTest {

    @Autowired
    var campeonatoService: CampeonatoService? = null

    @Autowired
    var campeonatoRepository: CampeonatoRepository? = null


    @Test
    fun testeListarCampeonatos(){

        val listaCampeonatos = campeonatoService?.listarCampeonatos()

        assert(listaCampeonatos is MutableList<CampeonatoModel>?)

    }

    @Test
    fun testeTabela_CampeonatoNaoCadastrado(){

        val erro = Assertions.assertThrows(
            java.lang.IllegalArgumentException::class.java
        ) { campeonatoService?.tabela(100000) }

        Assertions.assertEquals("Campeonato não cadastrado", erro.message)
    }

    @Test
    fun testeCriarCampeonato_ListaTimesInvalida(){

        val times = listOf(1, 2121)

        val campeonatoDto = CriarCampeonatoDto(
            "Campeonato teste",
            times
        )

        val erro = Assertions.assertThrows(
            java.lang.IllegalArgumentException::class.java
        ) { campeonatoService?.criarCampeonato(campeonatoDto)}

        Assertions.assertEquals("Lista de times invalida.", erro.message)
    }

    @Test
    fun testeCriarCampeonato_ListaTimesMenorQue2(){

        val times = listOf(1)

        val campeonatoDto = CriarCampeonatoDto(
            "Campeonato teste",
            times
        )

        val erro = Assertions.assertThrows(
            java.lang.IllegalArgumentException::class.java
        ) { campeonatoService?.criarCampeonato(campeonatoDto)}

        Assertions.assertEquals("Campeonato não pode ter menos de 2 times", erro.message)
    }

    @Name("teste finalizarCampeonato(); Should Throw IllegalArgumentsException; When Campeonato Inexistente")
    @Test
    fun testeFinalizarCampeonato_Inexistente(){

        val finalizarCampeonatoDto = FinalizarCampeonatoDto(
            10000
        )

        val erro = Assertions.assertThrows(
            java.lang.IllegalArgumentException::class.java
        ) { campeonatoService?.finalizarCampeonato(finalizarCampeonatoDto) }

        Assertions.assertEquals("Campeonato não cadastrado", erro.message)
    }

    @Test
    fun testeFinalizarCampeonato_JogosEmAndamento(){

        val finalizarCampeonatoDto = FinalizarCampeonatoDto(
            22
        )

        val erro = Assertions.assertThrows(
            java.lang.IllegalArgumentException::class.java
        ) { campeonatoService?.finalizarCampeonato(finalizarCampeonatoDto) }

        Assertions.assertEquals("Esse campeonato possui jogos em andamento", erro.message)

    }

    @Test
    fun testeFinalizarCampeonato_CampeonatoJaFinalizado(){

        val finalizarCampeonatoDto = FinalizarCampeonatoDto(
            22
        )

        val erro = Assertions.assertThrows(
            java.lang.IllegalArgumentException::class.java
        ) { campeonatoService?.finalizarCampeonato(finalizarCampeonatoDto) }

        Assertions.assertEquals("Campeonato já finalizado", erro.message)

    }

    @Test
    fun testeFinalizarCampeonato_Funcionando(){

        val times = listOf(1, 2)

        val campeonatoDto = CriarCampeonatoDto(
            "Campeonato teste",
            times
        )

        val criarCampeonato = campeonatoService?.criarCampeonato(campeonatoDto)

        val id = criarCampeonato?.id

        val finalizarCampeonatoDto = FinalizarCampeonatoDto(
            id
        )

        campeonatoService?.finalizarCampeonato(finalizarCampeonatoDto)

        if (id != null){
            val campeonatoFinalizado = campeonatoRepository?.findById(id)?.orElse(null)

            Assertions.assertTrue(campeonatoFinalizado?.finalizado === true)
        }

    }

}