package es.uniovi.apuntesuniovi.log

import es.uniovi.apuntesuniovi.controllers.Controller
import es.uniovi.apuntesuniovi.infrastructure.GlobalConstants
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File

/**
 * Clase para manejar el log de la aplicación
 */
class LogService(controller: Class<Controller>) {
    private val log: Logger = LoggerFactory.getLogger(controller)

    init {
        createFolder()
    }

    /**
     * Añade un mensaje de información al log
     */
    fun info(message: String?) {
        log.info(message)
    }

    /**
     * Añade un mensage de error al log
     */
    fun error(message: String?) {
        log.error(message)
    }

    /**
     * Función que crea la carpeta contenedora del log si no existe previamente
     */
    private fun createFolder() {
        val file = File(GlobalConstants.urlLog)
        if (file.exists()) {
            return
        }
        if (file.mkdir()) {
            log.info("Creada carpeta log correctamente")
        }
    }
}