package es.uniovi.apuntesuniovi.infrastructure

import es.uniovi.apuntesuniovi.infrastructure.log.LogService

/**
 * Abstract class to apply the Command pattern
 */
abstract class AbstractCommand<T> {
    protected val logService = LogService(this.javaClass)

    /**
     * Run the command and return a value
     */
    abstract fun execute(): T
}