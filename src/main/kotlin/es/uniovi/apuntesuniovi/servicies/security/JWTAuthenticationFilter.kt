package es.uniovi.apuntesuniovi.servicies.security

import com.fasterxml.jackson.databind.ObjectMapper
import es.uniovi.apuntesuniovi.entities.User
import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.servicies.security.SecurityConstants.EXPIRATION_TIME
import es.uniovi.apuntesuniovi.servicies.security.SecurityConstants.HEADER_STRING
import es.uniovi.apuntesuniovi.servicies.security.SecurityConstants.SECRET
import es.uniovi.apuntesuniovi.servicies.security.SecurityConstants.TOKEN_BEARER_PREFIX
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthenticationFilter(
        authenticationManager: AuthenticationManager
) : UsernamePasswordAuthenticationFilter() {
    private val logService = LogService(this.javaClass)

    init {
        this.authenticationManager = authenticationManager
    }

    @Throws(AuthenticationException::class)
    override fun attemptAuthentication(
            request: HttpServletRequest, response: HttpServletResponse
    ): Authentication {
        logService.info("attemptAuthentication(request: HttpServletRequest, " +
                "response: HttpServletResponse) - start")
        try {
            val credentials: User = ObjectMapper().readValue(request.inputStream, User::class.java)
            logService.info("attemptAuthentication(request: $request, response: $response) - end")
            return authenticationManager.authenticate(UsernamePasswordAuthenticationToken(
                    credentials.username, credentials.password, ArrayList()))
        } catch (e: IOException) {
            logService.error("attemptAuthentication(request: HttpServletRequest, " +
                    "response: HttpServletResponse) - error")
            throw RuntimeException(e)
        }
    }

    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication(
            request: HttpServletRequest, response: HttpServletResponse,
            chain: FilterChain, auth: Authentication
    ) {
        logService.info("successfulAuthentication(request: HttpServletRequest, " +
                "response: HttpServletResponse, chain: FilterChain, auth: Authentication) - start")
        val token: String = Jwts.builder().setIssuedAt(Date())
                .setSubject((auth.principal as org.springframework.security.core.userdetails.User).username)
                .setExpiration(Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact()
        response.addHeader(HEADER_STRING, "$TOKEN_BEARER_PREFIX $token")
        logService.info("successfulAuthentication(request: HttpServletRequest, " +
                "response: HttpServletResponse, chain: FilterChain, auth: Authentication) - end")
    }

}