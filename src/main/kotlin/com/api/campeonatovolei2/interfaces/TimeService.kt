package com.api.campeonatovolei2.interfaces

import com.api.campeonatovolei2.dtos.TimeDto
import com.api.campeonatovolei2.entities.TimeModel
import java.util.*

interface TimeService {
    fun adicionarTime(time: TimeDto?): TimeModel?
    fun listarTimes(): ArrayList<TimeModel?>?
}


