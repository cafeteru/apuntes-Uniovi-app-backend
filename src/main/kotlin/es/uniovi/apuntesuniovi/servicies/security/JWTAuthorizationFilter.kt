package es.uniovi.apuntesuniovi.servicies.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.TokenExpiredException
import es.uniovi.apuntesuniovi.servicies.security.SecurityConstants.Companion.HEADER_STRING
import es.uniovi.apuntesuniovi.servicies.security.SecurityConstants.Companion.SECRET
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import java.io.IOException
import java.lang.IllegalArgumentException
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 * Comprueba el token para la autentificación del usuario
 */
class JWTAuthorizationFilter internal constructor(authManager: AuthenticationManager?) : BasicAuthenticationFilter(authManager) {
    @Throws(IOException::class, ServletException::class)
    override fun doFilterInternal(
            req: HttpServletRequest, res: HttpServletResponse, chain: FilterChain) {
        val header = req.getHeader(HEADER_STRING)
        if (header == null) {
            chain.doFilter(req, res)
            return
        }
        val authentication = getAuthentication(
                req)
        SecurityContextHolder.getContext().authentication = authentication
        chain.doFilter(req, res)
    }

    private fun getAuthentication(
            request: HttpServletRequest): UsernamePasswordAuthenticationToken? {
        try {
            val token = request.getHeader(HEADER_STRING)
            if (token != null) {
                // parse the token.
                val user: String = JWT.require(Algorithm.HMAC512(SECRET))
                        .build().verify(token).subject
                return UsernamePasswordAuthenticationToken(user, null,
                        ArrayList())
            }
        } catch (e: TokenExpiredException) {
            throw IllegalArgumentException("Token Expirado")
        }
        return null
    }
}
