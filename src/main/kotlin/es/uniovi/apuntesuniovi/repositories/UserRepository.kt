package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

/**
 * Manage the users table
 */
interface UserRepository : JpaRepository<User, Long> {
    /**
     * Find subject by username
     */
    fun findByUsername(userName: String): Optional<User>

}