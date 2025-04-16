package com.interes.model

import java.time.LocalDateTime

data class MontoAnual(
    val anio: Int,
    val monto: Double
)

data class InteresResponse(
    val id: Long,
    val fecha: LocalDateTime,
    val interes_compuesto: List<MontoAnual>,
    val interes_simple: List<MontoAnual>
)