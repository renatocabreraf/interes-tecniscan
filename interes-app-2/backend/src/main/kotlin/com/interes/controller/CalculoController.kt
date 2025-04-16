package com.interes.controller

import com.interes.model.InteresRequest
import com.interes.service.CalculoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class CalculoController(val service: CalculoService) {

    @PostMapping("/calcular-interes")
    fun calcular(@RequestBody request: InteresRequest): ResponseEntity<Any> =
        try {
            ResponseEntity.ok(service.calcularInteres(request))
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().body(mapOf("error" to e.message))
        }

    @GetMapping("/historial-calculos")
    fun historial() = service.obtenerHistorial()
}