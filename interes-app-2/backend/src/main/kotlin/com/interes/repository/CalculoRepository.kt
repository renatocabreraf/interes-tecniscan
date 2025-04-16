package com.interes.repository

import com.interes.model.Calculo
import org.springframework.data.jpa.repository.JpaRepository

interface CalculoRepository : JpaRepository<Calculo, Long>