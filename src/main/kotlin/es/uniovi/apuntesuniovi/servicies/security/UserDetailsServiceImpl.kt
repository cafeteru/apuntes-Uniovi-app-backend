package es.uniovi.apuntesuniovi.servicies.security

import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.repositories.RepositoryFactory
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import javax.inject.Inject

/**
 * Loads user data when they log into the app
 */
@Service
class UserDetailsServiceImpl @Inject constructor(
        private val repositoryFactory: RepositoryFactory
) : UserDetailsService {
    private val logService = LogService(this.javaClass)

    override fun loadUserByUsername(username: String): UserDetails {
        logService.info("loadUserByUsername(username: $username) - start")
        val optional = repositoryFactory.getUsers().findByUsername(username)
        if (optional.isPresent) {
            val employee = optional.get()
            logService.info("loadUserByUsername(username: $username) - end")
            return User(employee.username, employee.password, emptyList())
        } else {
            logService.error("loadUserByUsername(username: $username) - error")
            throw IllegalArgumentException(username)
        }
    }
}