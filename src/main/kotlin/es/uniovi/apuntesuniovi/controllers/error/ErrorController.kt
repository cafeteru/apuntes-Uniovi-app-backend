package es.uniovi.apuntesuniovi.controllers.error

import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.util.*

/**
 * Handle exceptions thrown in the app and report them
 */
@ControllerAdvice
class ErrorController {
    private val logService = LogService(this.javaClass)

    /**
     * Returns a Json with the error occurred
     */
    @ExceptionHandler(value = [IllegalArgumentException::class])
    fun responseException(e: IllegalArgumentException): ResponseEntity<Map<String, String?>>? {
        logService.error(e.message)
        return ResponseEntity(createJsonError(e.message), HttpStatus.BAD_REQUEST)
    }

    private fun createJsonError(message: String?): Map<String, String?> {
        logService.info("createJsonError(message: ${message}) - start")
        val map = HashMap<String, String?>()
        map["error"] = message
        logService.info("createJsonError(message: ${message}) - end")
        return map
    }
}