package es.uniovi.apuntesuniovi.servicies.messages

/**
 * Interfaz para obtener los mensajes de los ficheros de internacionalización
 *
 * @author Iván González Mahagamage
 *
 */
interface LoadMessages {
    /**
     * Establece el idioma para saber que fichero leer.
     *
     * @param language Idioma indicado.
     */
    fun setLanguage(language: String)

    /**
     * Devuelve el texto del identificador indicado.
     *
     * @param code Identificador
     * @return Texto asignado en el fiochero
     */
    fun getString(code: String): String
}