package es.uniovi.apuntesuniovi.infrastructure.messages

/**
 * Defines the messages that the users exceptions return
 */
object UserMessages {
  const val ALREADY_REGISTERED_USERNAME = "error.user.already.registered.username"
  const val ALREADY_REGISTERED_NUMBER_IDENTIFICATION = "error.user.already.registered.number.identification"
  const val EXPIRED_TOKEN = "error.user.expired.token"
  const val LOGIN_SYSTEM = "error.user.login.system"
  const val INVALID_DATA_USER = "error.user.invalid.data.user"
  const val INVALID_EMAIL = "error.user.invalid.email"
  const val INVALID_JSON = "error.user.invalid.json"
  const val INVALID_ID = "error.user.invalid.id"
  const val INVALID_IDENTIFICATION_NUMBER = "error.user.invalid.identification.number"
  const val INVALID_IDENTIFICATION_TYPE = "error.user.invalid.identification.type"
  const val INVALID_LANGUAGE = "error.user.invalid.language"
  const val INVALID_PHONE = "error.user.invalid.phone"
  const val INVALID_ROLE_TYPE = "error.user.invalid.role.type"
  const val INVALID_TOKEN = "error.user.invalid.token"
  const val INVALID_USERNAME = "error.user.invalid.username"
  const val LIMIT_BIRTH_DATE = "error.user.limit.birth.date"
  const val LIMIT_IMG = "error.user.limit.img"
  const val LIMIT_NAME = "error.user.limit.name"
  const val LIMIT_PASSWORD = "error.user.limit.password"
  const val LIMIT_SURNAME = "error.user.limit.surname"
  const val LIMIT_USERNAME = "error.user.limit.username"
  const val NOT_EXISTS = "error.user.not.exists"
  const val NOT_FOUND = "error.user.not.found"
  const val NOT_FOUND_USERNAME = "error.user.not.found.username"
  const val NULL = "error.user.null"
}