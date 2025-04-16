package com.interes.service

import com.interes.model.*
import com.interes.repository.CalculoRepository
import org.springframework.stereotype.Service

@Service
class CalculoService(val repo: CalculoRepository) {
    fun calcularInteres(req: InteresRequest): InteresResponse {
        require(req.montoInicial > 0) { "Monto inicial debe ser mayor a 0" }
        require(req.tasaInteres > 0) { "Tasa de interés debe ser mayor a 0" }
        require(req.anios in 1..50) { "Años debe estar entre 1 y 50" }

        val simple = (1..req.anios).map { anio ->
            val monto = req.montoInicial * (1 + (req.tasaInteres / 100) * anio)
            MontoAnual(anio, "%.2f".format(monto).toDouble())
        }

        val compuesto = (1..req.anios).map { anio ->
            val monto = req.montoInicial * Math.pow(1 + (req.tasaInteres / 100), anio.toDouble())
            MontoAnual(anio, "%.2f".format(monto).toDouble())
        }

        val entity = repo.save(Calculo(
            montoInicial = req.montoInicial,
            tasaInteres = req.tasaInteres,
            anios = req.anios
        ))

        return InteresResponse(entity.id, entity.fecha, compuesto, simple)
    }

    fun obtenerHistorial(): List<Calculo> = repo.findAll()
}