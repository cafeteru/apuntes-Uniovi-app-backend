package es.uniovi.apuntesuniovi.controllers.error

import es.uniovi.apuntesuniovi.log.LogService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.util.*

/**
 * Controlador para manejar las excepciones producidas en la aplicación e informar de ellas
 */
@ControllerAdvice
class ErrorController {
    private val logService = LogService(this.javaClass)

    /**
     * Controla la excepciones que ocurren en el sistema
     * y devuelve un Json con el error ocurrido
     */
    @ExceptionHandler(value = [IllegalArgumentException::class])
    fun responseException(e: IllegalArgumentException): ResponseEntity<Map<String, String?>>? {
        logService.error(e.message)
        return ResponseEntity(setMapErrors(e.message), HttpStatus.BAD_REQUEST)
    }

    /**
     * Crea json con los errores detectados
     */
    private fun setMapErrors(message: String?): Map<String, String?> {
        logService.info("setMapErrors(message: ${message}) - start")
        val map: MutableMap<String, String?> = HashMap()
        map["error"] = message
        logService.info("setMapErrors(message: ${message}) - end")
        return map
    }
}