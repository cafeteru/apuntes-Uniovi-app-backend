package es.uniovi.apuntesuniovi.servicies.security

object SecurityConstants {
    // Spring Security
    const val LOGIN_URL = "/login"
    const val HEADER_STRING = "Authorization"
    const val TOKEN_BEARER_PREFIX = "Bearer "

    // JWT
    const val SECRET = "SecretKeyToGenJWTs";
    private const val HOURS = 6
    const val EXPIRATION_TIME = 1000 * 60 * 60 * HOURS;


}