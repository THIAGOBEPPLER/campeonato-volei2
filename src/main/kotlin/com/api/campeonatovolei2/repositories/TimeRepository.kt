package com.api.campeonatovolei2.repositories

import com.api.campeonatovolei2.entities.CampeonatoModel
import com.api.campeonatovolei2.entities.JogoModel
import org.hibernate.type.IntegerType
import org.springframework.data.jpa.repository.JpaRepository

interface TimeRepository : JpaRepository<TimeRepository, IntegerType> {
}