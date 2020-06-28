package es.uniovi.apuntesuniovi.servicies.security

import com.google.gson.Gson
import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.servicies.ServiceFactory
import es.uniovi.apuntesuniovi.servicies.dtos.entities.PersonDto
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

data class Login(val username: String, val password: String)

class JWTAuthenticationFilter(
        authenticationManager: AuthenticationManager,
        private val serviceFactory: ServiceFactory
) : UsernamePasswordAuthenticationFilter() {
    private var logService: LogService = LogService(this)

    @Throws(AuthenticationException::class)
    override fun attemptAuthentication(req: HttpServletRequest, res: HttpServletResponse): Authentication {
        return try {
            val personDto: Login = Gson().fromJson(req.inputStream.toString(), Login::class.java)
            val list: PersonDto = serviceFactory.getPersons().findByUsername(personDto.username)
            if (list.active) {
                this.authenticationManager
                        .authenticate(UsernamePasswordAuthenticationToken(
                                personDto.username, personDto.password,
                                ArrayList()))
            } else {
                throw IllegalArgumentException("Usuario no permitido")
            }
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }
}