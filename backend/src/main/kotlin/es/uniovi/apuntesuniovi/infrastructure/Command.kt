package es.uniovi.apuntesuniovi.infrastructure

interface Command<T> {
    fun execute(): T
}