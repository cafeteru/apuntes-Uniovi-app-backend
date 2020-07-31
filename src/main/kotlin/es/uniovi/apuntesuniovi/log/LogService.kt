package es.uniovi.apuntesuniovi.log

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Clase para manejar el log de la aplicación
 */
class LogService(controller: Class<Any>) {
    private val log: Logger = LoggerFactory.getLogger(controller)

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
}