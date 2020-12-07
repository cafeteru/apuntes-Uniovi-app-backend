package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.models.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

/**
 * Manage the users table
 */
interface UserRepository : JpaRepository<User, Long> {
    /**
     * Find user by username
     */
    fun findByUsername(userName: String): Optional<User>

    /**
     * Find user by numberIdentification
     */
    fun findByNumberIdentification(numberIdentification: String): Optional<User>

}