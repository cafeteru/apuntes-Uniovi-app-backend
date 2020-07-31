package es.uniovi.apuntesuniovi.infrastructure

/**
 * Interface to apply the Command pattern
 */
interface Command<T> {
    /**
     * Run the command and return a value
     */
    fun execute(): T
}