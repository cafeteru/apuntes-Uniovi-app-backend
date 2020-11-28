package es.uniovi.apuntesuniovi.infrastructure.exceptions

class ExceptionWithOutStackTrace(message: String) : Exception(message) {
    @Synchronized
    fun fillInStackTrace(): Throwable {
        return this
    }
}