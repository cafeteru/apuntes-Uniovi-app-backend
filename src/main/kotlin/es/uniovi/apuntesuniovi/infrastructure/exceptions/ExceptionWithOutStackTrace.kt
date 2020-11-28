package es.uniovi.apuntesuniovi.infrastructure.exceptions

/**
 * Custom exception without StackError
 */
class ExceptionWithOutStackTrace(message: String) : Exception(message) {

    /**
     * Configure how to show StackError
     */
    @Synchronized
    fun fillInStackTrace(): Throwable {
        return this
    }
}