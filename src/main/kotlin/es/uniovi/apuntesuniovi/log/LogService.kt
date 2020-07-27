package es.uniovi.apuntesuniovi.log

import es.uniovi.apuntesuniovi.infrastructure.constants.UrlUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File

/**
 * Clase para manejar el log de la aplicación
 */
class LogService(controller: Class<Any>) {
    private val log: Logger = LoggerFactory.getLogger(controller)

    init {
        createFolder()
    }

    /**
     * Añade un mensaje de información al log
     *
     * @param message Mensaje a mostrar
     */
    fun info(message: String?) {
        log.info(message)
    }

    /**
     * Añade un mensage de error al log
     *
     * @param message Mensaje a mostrar
     */
    fun error(message: String?) {
        log.error(message)
    }

    /**
     * Formatea un json para mostrarlo en el log
     *
     * @param json Json a modificar
     */
    fun formatJson(json: String?): String {
        if (json.isNullOrEmpty()) {
            return ""
        }
        return json.replace("\n", "").replace("\r", "")
    }

    /**
     * Función que crea la carpeta contenedora del log si no existe previamente
     */
    private fun createFolder() {
        val file = File(UrlUtils.urlLog)
        if (file.exists()) {
            return
        }
        if (file.mkdir()) {
            log.info("Creada carpeta log correctamente")
        }
    }
}