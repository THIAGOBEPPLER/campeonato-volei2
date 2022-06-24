package com.api.campeonatovolei2.services

import com.api.campeonatovolei2.dtos.TimeDto
import com.api.campeonatovolei2.entities.CampeonatoModel
import com.api.campeonatovolei2.entities.TimeModel
import com.api.campeonatovolei2.interfaces.CampeonatoService
import com.api.campeonatovolei2.interfaces.TimeService
import com.api.campeonatovolei2.repositories.TimeRepository
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class TimeControllerTest {

    @Autowired
    var timeService: TimeService? = null

    @Autowired
    var campeonatoService: CampeonatoService? = null

    @Mock
    var timeRepository: TimeRepository? = null

    @Test
    fun testeListarTimes(){

        val listaTimes = timeService!!.listarTimes()

        assert(listaTimes is MutableList<TimeModel>)

    }

    @Test
    fun testeAdicionarTime(){

        val time = TimeDto(
            "timeTeste"
        )

        val timeAdd = timeService!!.adicionarTime(time)

        assert(timeAdd is TimeModel)
        assert(timeAdd!!.getNome() == time.nome)
        assert(timeAdd.getCampeonatos() == null)

    }

}