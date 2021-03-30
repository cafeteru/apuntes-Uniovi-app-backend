package es.uniovi.apuntesuniovi.infrastructure.constants

/**
 * Defines the constants of security services
 */
object SecurityConstants {
    const val LOGIN_URL = "/login"
    const val AUTHORIZATION_HEADER = "authorization"
    const val TOKEN_BEARER_PREFIX = "Bearer "

    const val SECRET = "SecretKeyToGenJWTs"
    private const val HOURS = 6
    const val EXPIRATION_TIME = 1000 * 60 * 60 * HOURS
}