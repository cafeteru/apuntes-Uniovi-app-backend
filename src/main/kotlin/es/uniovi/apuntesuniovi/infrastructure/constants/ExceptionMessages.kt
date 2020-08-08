package es.uniovi.apuntesuniovi.infrastructure.constants

/**
 * Defines the messages that the exceptions return
 */
object ExceptionMessages {
    const val ALREADY_REGISTERED_USERNAME = "already registered username"
    const val ALREADY_REGISTERED_SUBJECT = "already registered subject"
    const val EXPIRED_TOKEN = "expired token"
    const val NOT_FOUND_USERNAME = "There is no registered user with"
    const val NULL_DATE = "the date can´t be null"
    const val NULL_EMPTY_DATE = "the date can´t be null or empty"
    const val NULL_EMPTY_HOUR = "the hour can´t be null or empty"
    const val NULL_IDENTIFICATION_TYPE = "null identification type"
    const val ILLEGAL_FORMAT_DATE = "the date format is not valid"
    const val ILLEGAL_FORMAT_HOUR = "the time format is not valid"
    const val INVALID_TOKEN = "invalid token"
    const val INVALID_IDENTIFICATION_TYPE = "invalid identification type"
}