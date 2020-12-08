package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.models.User
import java.util.*

/**
 * Manage the users table
 */
interface UserRepository : PageableRepository<User> {
    /**
     * Find user by username
     */
    fun findByUsername(userName: String): Optional<User>

    /**
     * Find user by numberIdentification
     */
    fun findByNumberIdentification(numberIdentification: String): Optional<User>

}