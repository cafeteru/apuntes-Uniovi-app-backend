package es.uniovi.apuntesuniovi.servicies.security

class SecurityConstants {
    companion object {
        const val SECRET = "SecretKeyToGenJWTs"
        const val EXPIRATION_TIME = 1000 * 60 * 60 * 6 // 6 h
                .toLong()
        const val HEADER_STRING = "Authorization"
    }
}