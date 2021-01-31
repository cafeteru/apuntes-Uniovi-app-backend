package es.uniovi.apuntesuniovi.services.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm.HMAC512
import com.fasterxml.jackson.databind.ObjectMapper
import es.uniovi.apuntesuniovi.infrastructure.constants.SecurityConstants.AUTHORIZATION_HEADER
import es.uniovi.apuntesuniovi.infrastructure.constants.SecurityConstants.EXPIRATION_TIME
import es.uniovi.apuntesuniovi.infrastructure.constants.SecurityConstants.SECRET
import es.uniovi.apuntesuniovi.infrastructure.constants.SecurityConstants.TOKEN_BEARER_PREFIX
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.infrastructure.messages.UserMessages
import es.uniovi.apuntesuniovi.services.UserService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.InternalAuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Service to authenticate users and create their token
 */
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

  override fun attemptAuthentication(req: HttpServletRequest, res: HttpServletResponse): Authentication? {
    logService.info("attemptAuthentication(req: HttpServletRequest, res: HttpServletResponse) - start")
    try {
      val user = ObjectMapper().readValue(req.inputStream, es.uniovi.apuntesuniovi.models.User::class.java)
      val authentication = UsernamePasswordAuthenticationToken(user.username, user.password, getAuthorities(user))
      val result = authenticationManager.authenticate(authentication)
      logService.info("attemptAuthentication(req: $req, response: $res) - end")
      return result
    } catch (e: IOException) {
      logService.error(UserMessages.LOGIN_SYSTEM)
      logService.error(
        "attemptAuthentication(req: HttpServletRequest, res: HttpServletResponse) - " +
            UserMessages.LOGIN_SYSTEM
      )
    } catch (e: InternalAuthenticationServiceException) {
      logService.error(
        "attemptAuthentication(req: HttpServletRequest, res: HttpServletResponse) - " +
            UserMessages.NOT_EXISTS
      )
    }
    return null
  }

  override fun successfulAuthentication(
    request: HttpServletRequest, response: HttpServletResponse,
    chain: FilterChain, auth: Authentication
  ) {
    logService.info(
      "successfulAuthentication(request: HttpServletRequest, " +
          "response: HttpServletResponse, chain: FilterChain, auth: Authentication) - start"
    )
    val token = createToken(auth)
    response.contentType = "application/json;charset=UTF-8"
    response.writer.print("{ \"$AUTHORIZATION_HEADER\" : \"$TOKEN_BEARER_PREFIX$token\" }")
    logService.info(
      "successfulAuthentication(request: HttpServletRequest, " +
          "response: HttpServletResponse, chain: FilterChain, auth: Authentication) - end"
    )
  }

  private fun createToken(auth: Authentication): String {
    logService.info("createToken(auth: Authentication) - start")
    val username = (auth.principal as User).username
    val user = userService.findByUsername(username)
    val token = JWT.create()
      .withSubject(user.username)
      .withClaim("role", "ROLE_" + user.role)
      .withClaim("id", user.id)
      .withExpiresAt(Date(System.currentTimeMillis() + EXPIRATION_TIME))
      .sign(HMAC512(SECRET))
    logService.info("createToken(auth: Authentication) - end")
    return token
  }

  private fun getAuthorities(user: es.uniovi.apuntesuniovi.models.User): List<GrantedAuthority> {
    return listOf(SimpleGrantedAuthority("ROLE_$user.role"))
  }
}