package es.uniovi.apuntesuniovi.log

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File

/**
 * Clase para manejar el log de la aplicación
 */
class LogService(controller: Any) {
    private val log: Logger

    init {
        createFolder()
        log = LoggerFactory.getLogger(controller.javaClass)
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
        val file = File("../log")
        if (file.exists()) {
            return
        }
        if (file.mkdir()) {
            println("Creada carpeta log correctamente")
        }
    }
}