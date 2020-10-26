package es.uniovi.apuntesuniovi.servicies.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm.HMAC512
import com.fasterxml.jackson.databind.ObjectMapper
import es.uniovi.apuntesuniovi.infrastructure.constants.SecurityConstants.EXPIRATION_TIME
import es.uniovi.apuntesuniovi.infrastructure.constants.SecurityConstants.AUTHORIZATION_HEADER
import es.uniovi.apuntesuniovi.infrastructure.constants.SecurityConstants.SECRET
import es.uniovi.apuntesuniovi.infrastructure.constants.SecurityConstants.TOKEN_BEARER_PREFIX
import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.servicies.UserService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.InternalAuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import java.io.PrintWriter
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JWTAuthenticationFilter(
        authenticationManager: AuthenticationManager,
        userService: UserService
) : UsernamePasswordAuthenticationFilter() {
    private val logService = LogService(this.javaClass)
    private var userService: UserService

    init {
        this.authenticationManager = authenticationManager
        this.userService = userService
    }

    override fun attemptAuthentication(req: HttpServletRequest, res: HttpServletResponse): Authentication {
        logService.info("attemptAuthentication(req: HttpServletRequest, res: HttpServletResponse) - start")
        try {
            val user = ObjectMapper().readValue(req.inputStream, es.uniovi.apuntesuniovi.entities.User::class.java)
            val result = authenticationManager.authenticate(UsernamePasswordAuthenticationToken(
                    user.username, user.password, ArrayList()))
            logService.info("attemptAuthentication(req: $req, response: $res) - end")
            return result
        } catch (e: IOException) {
            logService.error("attemptAuthentication(req: HttpServletRequest, res: HttpServletResponse) - error")
            throw RuntimeException(e)
        } catch (e: InternalAuthenticationServiceException) {
            logService.error("\"attemptAuthentication(req: HttpServletRequest, res: HttpServletResponse) - error")
            throw IllegalArgumentException("User doesn't exist")
        }
    }

    override fun successfulAuthentication(
            request: HttpServletRequest, response: HttpServletResponse,
            chain: FilterChain, auth: Authentication
    ) {
        logService.info("successfulAuthentication(request: HttpServletRequest, " +
                "response: HttpServletResponse, chain: FilterChain, auth: Authentication) - start")
        val token = JWT.create()
                .withSubject((auth.principal as User).username)
                .withExpiresAt(
                        Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET))
        response.addHeader(AUTHORIZATION_HEADER, token)
        response.contentType = "application/json;charset=UTF-8"
        createResponseBody(response, token, auth)
        logService.info("successfulAuthentication(request: HttpServletRequest, " +
                "response: HttpServletResponse, chain: FilterChain, auth: Authentication) - end")
    }

    private fun createResponseBody(response: HttpServletResponse, token: String, auth: Authentication) {
        logService.info("createResponseBody(response: HttpServletResponse, token: " +
                "String, auth: Authentication) - start")
        val out = response.writer
        val username = (auth.principal as User).username
        val user = userService.findByUsername(username)
        out.print("{ \n\t")
        addParam(out, AUTHORIZATION_HEADER, "$TOKEN_BEARER_PREFIX$token")
        addParam(out, "username", user.username)
        addParam(out, "role", user.role)
        out.print("}")
        out.flush()
        logService.info("createResponseBody(response: HttpServletResponse, token: " +
                "String, auth: Authentication) - end")
    }

    private fun addParam(out: PrintWriter, key: String, value: String) {
        logService.info("addParam(out: PrintWriter, key: String, value: String) - start")
        out.print("\"$key\" : \"$value\",")
        logService.info("addParam(out: PrintWriter, key: String, value: String) - end")
    }

}