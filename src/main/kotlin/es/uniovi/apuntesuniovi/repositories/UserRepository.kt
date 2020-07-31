package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(userName: String): Optional<User>
}