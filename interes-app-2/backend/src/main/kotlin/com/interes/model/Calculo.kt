package com.interes.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Calculo(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val fecha: LocalDateTime = LocalDateTime.now(),
    val montoInicial: Double,
    val tasaInteres: Double,
    val anios: Int
)