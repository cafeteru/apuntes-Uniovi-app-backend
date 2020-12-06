package es.uniovi.apuntesuniovi.infrastructure

import es.uniovi.apuntesuniovi.infrastructure.log.LogService

abstract class AbstractCommand<T> : Command<T> {
    protected val logService = LogService(this.javaClass)
}