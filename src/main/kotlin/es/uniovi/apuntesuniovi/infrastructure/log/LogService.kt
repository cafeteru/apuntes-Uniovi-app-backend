package es.uniovi.apuntesuniovi.infrastructure.log

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Manage the application log
 */
class LogService(controller: Class<Any>) {
  private val log: Logger = LoggerFactory.getLogger(controller)

  /**
   * Add an information message to the log
   *
   * @param message Message to display
   */
  fun info(message: String?) {
    log.info(message)
  }

  /**
   * Add an error message to the log
   *
   * @param message Message to display
   */
  fun error(message: String?) {
    log.error(message)
  }
}