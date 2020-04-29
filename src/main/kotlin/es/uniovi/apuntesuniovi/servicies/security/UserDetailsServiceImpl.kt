package es.uniovi.apuntesuniovi.servicies.security

import es.uniovi.apuntesuniovi.entities.Person
import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.repositories.RepositoryFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserDetailsServiceImpl @Autowired constructor(
        private val repositoryFactory: RepositoryFactory
) : UserDetailsService {
    private val logService: LogService = LogService(this)
    override fun loadUserByUsername(username: String?): UserDetails {
        if (username == null) {
            throw UsernameNotFoundException(username)
        }
        val employees: Optional<Person> = repositoryFactory.getPersons()
                .findByUsername(username)
        return if (employees.isPresent) {
            val employee = employees.get()
            val user = User(employee.username,
                    employee.password, emptyList())
            logService.info("Usuario '${user.username} (${employee.name} ${employee.surname}" +
                    ") inicia sesión en la aplicación")
            user
        } else {
            throw UsernameNotFoundException(username)
        }
    }
}