package com.api.campeonatovolei2.services

import com.api.campeonatovolei2.dtos.TimeDto
import com.api.campeonatovolei2.entities.TimeModel
import com.api.campeonatovolei2.interfaces.TimeService
import com.api.campeonatovolei2.repositories.TimeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TimeServiceImpl : TimeService {
    @Autowired
    var timeRepository: TimeRepository? = null

     override fun adicionarTime(time: TimeDto): TimeModel {
        val timeModel = TimeModel()
        timeModel.setNome(time.nome)
        return timeRepository!!.save(timeModel)
    }

    override fun listarTimes(): ArrayList<TimeModel> {
        return timeRepository!!.findAll() as ArrayList<TimeModel>
    }


}
