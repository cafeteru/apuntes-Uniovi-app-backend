package es.uniovi.apuntesuniovi.servicies.security

import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.servicies.security.SecurityConstants.HEADER_STRING
import es.uniovi.apuntesuniovi.servicies.security.SecurityConstants.SECRET
import es.uniovi.apuntesuniovi.servicies.security.SecurityConstants.TOKEN_BEARER_PREFIX
import io.jsonwebtoken.Jwts
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
        logService.info("getAuthentication(request: HttpServletRequest) - start")
        val token = request.getHeader(HEADER_STRING)
        if (token != null) {
            // Se procesa el token y se recupera el usuario.
            val user: String = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_BEARER_PREFIX, "")).body.subject
            logService.info("getAuthentication(request: HttpServletRequest) - end")
            return UsernamePasswordAuthenticationToken(user, null, ArrayList())
        }
        logService.info("getAuthentication(request: HttpServletRequest) - end")
        return null
    }
}