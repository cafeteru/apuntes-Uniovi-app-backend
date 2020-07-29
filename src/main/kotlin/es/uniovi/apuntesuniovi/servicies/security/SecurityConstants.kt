package es.uniovi.apuntesuniovi.servicies.security

object SecurityConstants {
    // Spring Security
    const val LOGIN_URL = "/login"
    const val HEADER_STRING = "Authorization"
    const val TOKEN_BEARER_PREFIX = "Bearer "

    // JWT
    const val SECRET = "SecretKeyToGenJWTs";
    const val EXPIRATION_TIME = 1000 * 60 * 60 * 6;

}