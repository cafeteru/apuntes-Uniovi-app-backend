package es.uniovi.apuntesuniovi.servicies.security

import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.repositories.RepositoryFactory
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import javax.inject.Inject

/**
 * Carga los datos del usuario cuando inicia sesión en la aplicación
 */
@Service
class UserDetailsServiceImpl @Inject constructor(
        private val repositoryFactory: RepositoryFactory
) : UserDetailsService {
    private val logService = LogService(this.javaClass)

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        logService.info("loadUserByUsername(username: $username) - start")
        val optional = repositoryFactory.getUsers().findByUsername(username)
        if (optional.isPresent) {
            val employee = optional.get()
            logService.info("loadUserByUsername(username: $username) - end")
            return User(employee.username, employee.password, emptyList())
        } else {
            logService.error("loadUserByUsername(username: $username) - error")
            throw UsernameNotFoundException(username)
        }
    }
}