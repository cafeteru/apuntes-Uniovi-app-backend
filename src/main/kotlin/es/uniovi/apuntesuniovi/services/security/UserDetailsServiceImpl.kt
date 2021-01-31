package es.uniovi.apuntesuniovi.services.security

import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.repositories.UserRepository
import org.springframework.context.annotation.Role
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import javax.inject.Inject
import org.springframework.security.core.authority.SimpleGrantedAuthority

import java.util.HashSet
import java.util.function.Consumer


/**
 * Loads user data when they log into the app
 */
@Service
class UserDetailsServiceImpl @Inject constructor(
  private val userRepository: UserRepository
) : UserDetailsService {
  private val logService = LogService(this.javaClass)

  override fun loadUserByUsername(username: String): UserDetails {
    logService.info("loadUserByUsername(username: $username) - start")
    val optional = userRepository.findByUsername(username)
    if (optional.isPresent) {
      val user = optional.get()
      logService.info("loadUserByUsername(username: $username) - end")
      val role = SimpleGrantedAuthority("ROLE_" + user.role)
      return User(user.username, user.password, listOf(role))
    } else {
      logService.error("loadUserByUsername(username: $username) - error")
      throw IllegalArgumentException(username)
    }
  }
}