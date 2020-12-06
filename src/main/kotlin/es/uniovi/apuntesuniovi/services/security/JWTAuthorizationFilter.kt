package es.uniovi.apuntesuniovi.services.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.TokenExpiredException
import es.uniovi.apuntesuniovi.infrastructure.constants.SecurityConstants.AUTHORIZATION_HEADER
import es.uniovi.apuntesuniovi.infrastructure.constants.SecurityConstants.SECRET
import es.uniovi.apuntesuniovi.infrastructure.constants.SecurityConstants.TOKEN_BEARER_PREFIX
import es.uniovi.apuntesuniovi.infrastructure.messages.UserMessages
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.SignatureException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 *
Decrypt tokens to authenticate users
 */
class JWTAuthorizationFilter(authManager: AuthenticationManager) : BasicAuthenticationFilter(authManager) {
    private val logService = LogService(this.javaClass)

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        logService.info("request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain - start")
        val token = request.getHeader(AUTHORIZATION_HEADER)
        if (token == null || !token.startsWith(TOKEN_BEARER_PREFIX)) {
            chain.doFilter(request, response)
            return
        }
        SecurityContextHolder.getContext().authentication = getAuthentication(request)
        chain.doFilter(request, response)
        logService.info("request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain - end")
    }

    private fun getAuthentication(request: HttpServletRequest): UsernamePasswordAuthenticationToken? {
        logService.info("getAuthentication(request: HttpServletRequest) - start")
        try {
            val token = request.getHeader(AUTHORIZATION_HEADER).replace(TOKEN_BEARER_PREFIX, "")
            if (token != "") {
                val user = JWT.require(Algorithm.HMAC512(SECRET)).build().verify(token).subject
                logService.info("getAuthentication(request: HttpServletRequest) - end")
                return UsernamePasswordAuthenticationToken(user, null, ArrayList())
            }
        } catch (e: ExpiredJwtException) {
            logService.error(UserMessages.EXPIRED_TOKEN)
        } catch (e: SignatureException) {
            logService.error(UserMessages.INVALID_TOKEN)
        } catch (e: TokenExpiredException) {
            logService.error(UserMessages.EXPIRED_TOKEN)
        }
        logService.info("getAuthentication(request: HttpServletRequest) - end")
        return null
    }
}