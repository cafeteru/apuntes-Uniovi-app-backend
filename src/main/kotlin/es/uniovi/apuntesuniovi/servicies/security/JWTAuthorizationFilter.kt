package es.uniovi.apuntesuniovi.servicies.security

import es.uniovi.apuntesuniovi.infrastructure.constants.ExceptionMessages
import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.infrastructure.constants.SecurityConstants.HEADER_STRING
import es.uniovi.apuntesuniovi.infrastructure.constants.SecurityConstants.SECRET
import es.uniovi.apuntesuniovi.infrastructure.constants.SecurityConstants.TOKEN_BEARER_PREFIX
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import java.io.IOException
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthorizationFilter(authManager: AuthenticationManager) : BasicAuthenticationFilter(authManager) {
    private val logService = LogService(this.javaClass)

    @Throws(IOException::class, ServletException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        logService.info("request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain - start")
        val header = request.getHeader(HEADER_STRING)
        if (header == null || !header.startsWith(TOKEN_BEARER_PREFIX)) {
            chain.doFilter(request, response)
            return
        }
        SecurityContextHolder.getContext().authentication = getAuthentication(request)
        chain.doFilter(request, response)
        logService.info("request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain - end")
    }

    private fun getAuthentication(request: HttpServletRequest): UsernamePasswordAuthenticationToken? {
        try {
            logService.info("getAuthentication(request: HttpServletRequest) - start")
            val token = request.getHeader(HEADER_STRING)
            if (token != null) {
                val user: String = Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token.replace(TOKEN_BEARER_PREFIX, "")).body.subject
                logService.info("getAuthentication(request: HttpServletRequest) - end")
                return UsernamePasswordAuthenticationToken(user, null, ArrayList())
            }
        } catch (e: ExpiredJwtException) {
            logService.error(ExceptionMessages.EXPIRED_TOKEN)
        } catch (e: SignatureException) {
            logService.error(ExceptionMessages.INVALID_TOKEN)
        }
        logService.info("getAuthentication(request: HttpServletRequest) - end")
        return null
    }
}