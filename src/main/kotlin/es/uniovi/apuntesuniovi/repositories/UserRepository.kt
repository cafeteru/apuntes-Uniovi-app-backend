package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.models.types.RoleType
import java.util.*

/**
 * Manage the users table
 */
interface UserRepository : PagingQueryDslRepository<User> {
    /**
     * Find user by username
     */
    fun findByUsername(userName: String): Optional<User>

    /**
     * Find user by numberIdentification
     */
    fun findByNumberIdentification(numberIdentification: String): Optional<User>

    /**
     * Count users by active
     */
    fun countByActive(active: Boolean): Int


    /**
     * Count users by RoleType
     */
    fun countByRole(role: RoleType): Int

}