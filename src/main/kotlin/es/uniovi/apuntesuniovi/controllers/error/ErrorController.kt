package es.uniovi.apuntesuniovi.controllers.error

import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.util.*


/**
 * Handle exceptions thrown in the app and report them
 */
@ControllerAdvice
class ErrorController @Autowired constructor(
  private val userService: UserService
) {
  private val logService = LogService(this.javaClass)

  /**
   * Returns a Json with the error occurred
   */
  @ExceptionHandler(value = [IllegalArgumentException::class])
  fun responseException(e: IllegalArgumentException): ResponseEntity<Map<String, String?>> {
    logService.error(e.message)
    val authentication = SecurityContextHolder.getContext().authentication
    val username = authentication.name
    val user = userService.findByUsername(username)
    println(user.language)
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