package com.api.campeonatovolei2.services

import com.api.campeonatovolei2.dtos.CriarCampeonatoDto
import com.api.campeonatovolei2.dtos.FinalizarCampeonatoDto
import com.api.campeonatovolei2.entities.CampeonatoModel
import com.api.campeonatovolei2.interfaces.CampeonatoService
import com.api.campeonatovolei2.repositories.TimeRepository
import jdk.jfr.Name
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class CampeonatoControllerTest {

    @Autowired
    var campeonatoService: CampeonatoService? = null

//    @Autowired
//    lateinit var mockMvc: MockMvc

    @Mock
    var timeRepository: TimeRepository? = null

    @Test
    fun testeListarCampeonatos(){

        val listaCampeonatos = campeonatoService?.listarCampeonatos()

        assert(listaCampeonatos is MutableList<CampeonatoModel>?)

    }

    @Test
    fun testeTabelaNula(){

        val tabelaNula = campeonatoService?.tabela(100000)

        Assertions.assertNull(tabelaNula)
    }

    @Test
    fun testeCriarCampeonatoInexistente(){

        val times = listOf(10000, 100000)

        val campeonatoDto = CriarCampeonatoDto(
            "Campeonato teste",
            times

        )

        val campeonatoNulo = campeonatoService?.criarCampeonato(campeonatoDto)

        Assertions.assertNull(campeonatoNulo)
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
    fun testeFinalizarCampeonatoInexistente(){

        val finalizarCampeonatoDto = FinalizarCampeonatoDto(
            10000
        )

        val erro = Assertions.assertThrows(
            java.lang.IllegalArgumentException::class.java
        ) { campeonatoService?.finalizarCampeonato(finalizarCampeonatoDto) }

        Assertions.assertEquals("Campeonato não cadastrado", erro.message)
    }

}