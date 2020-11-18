package es.uniovi.apuntesuniovi.servicies

import es.uniovi.apuntesuniovi.servicies.dtos.entities.UserDto

/**
 * Service to manage users
 */
interface UserService {
    /**
     * Returns all users
     */
    fun findAll(): List<UserDto>

    /**
     * Returns the user whose username matches
     *
     * @param username User identifier
     */
    fun findByUsername(username: String): UserDto

    /**
     * Saves the user
     *
     * @param userDto User to save
     */
    fun create(userDto: UserDto): List<UserDto>
}