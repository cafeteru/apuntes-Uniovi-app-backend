package es.uniovi.apuntesuniovi.infrastructure.messages

/**
 * Defines the messages that the users exceptions return
 */
object UserMessages {
    const val ALREADY_REGISTERED_USERNAME = "error.already.registered.username"
    const val ALREADY_REGISTERED_NUMBER_IDENTIFICATION = "error.already.registered.number.identification"
    const val EXPIRED_TOKEN = "error.expired.token"
    const val LOGIN_SYSTEM = "error.login.system"
    const val INVALID_DATA_USER = "error.invalid.data.user"
    const val INVALID_EMAIL = "error.invalid.email"
    const val INVALID_ID = "error.invalid.id"
    const val INVALID_IDENTIFICATION_NUMBER = "error.invalid.identification.number"
    const val INVALID_IDENTIFICATION_TYPE = "error.invalid.identification.type"
    const val INVALID_PHONE = "error.invalid.phone"
    const val INVALID_ROLE_TYPE = "error.invalid.role.type"
    const val INVALID_TOKEN = "error.invalid.token"
    const val INVALID_USERNAME = "error.invalid.username"
    const val LIMIT_BIRTH_DATE = "error.limit.user.birth.date"
    const val LIMIT_IMG = "error.limit.user.img"
    const val LIMIT_NAME = "error.limit.user.name"
    const val LIMIT_PASSWORD = "error.limit.user.password"
    const val LIMIT_SURNAME = "error.limit.user.surname"
    const val LIMIT_USERNAME = "error.limit.user.username"
    const val NOT_EXISTS = "error.user.not.exists"
    const val NOT_FOUND_USERNAME = "error.not.found.username"
    const val NULL_USER = "error.null.user"
}