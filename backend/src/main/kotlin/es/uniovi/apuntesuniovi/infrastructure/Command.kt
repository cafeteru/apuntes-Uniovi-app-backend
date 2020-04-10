package es.uniovi.apuntesuniovi.infrastructure

/**
 * Interfaz para aplicar el patrón Command
 */
interface Command<T> {
    /**
     * Ejecuta el comando y devuelve un valor
     */
    fun execute(): T
}